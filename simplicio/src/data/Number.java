package data;
import exception.DivisionByZeroException;
import exception.IrrationalNumberException;
import exception.NonCalculableOperationException;
import exception.OverflowException;
import utils.MathUtils;

import java.util.Objects;

/** Questa classe rappresenta un numero razionale immutabile. */
public class Number implements AbstractSyntaxTreeNodeValue {
    // Il numero è rappresentato con numeratore e denominatore.
    // Il segno del numero è dato dal segno del numeratore (il denominatore è sempre positivo).
    // Il numero zero è rappresentato con il numeratore uguale a zero e il denominatore un qualsiasi numero
    // purché diverso da zero.

    private final int numerator;
    private final int denominator;

    /**
     * Istanzia il numero razionale che ha {@code numerator} come numeratore e {@code denominator} come
     * denominatore.
     *
     * @param numerator   il numeratore
     * @param denominator il denominatore
     * @throws DivisionByZeroException se {@code denominator} vale zero
     */
    public Number(int numerator, int denominator) {
        if (denominator == 0) throw new DivisionByZeroException();
        boolean sign = numerator > 0 && denominator > 0 || numerator < 0 && denominator < 0;
        this.numerator = (sign ? 1 : -1) * Math.abs(numerator);
        this.denominator = Math.abs(denominator);
    }

    /**
     * Istanzia il numero razionale che ha {@code numerator} come numeratore e {@code denominator} come
     * denominatore.
     *
     * @param numerator   il numeratore
     * @param denominator il denominatore
     * @throws DivisionByZeroException se {@code denominator} vale zero
     */
    public Number(Number numerator, Number denominator) {
        if (denominator.isZero()) throw new DivisionByZeroException();
        Number number = numerator.div(denominator);
        this.numerator = number.numerator;
        this.denominator = number.denominator;
    }

    /**
     * Istanzia il numero naturale con segno {@code number}.
     *
     * @param number il numero da istanziare
     */
    public Number(int number) {
        this(number, 1);
    }

    /**
     * Istanzia il numero razionale {@code number}.
     *
     * @param number il numero da istanziare
     * @throws OverflowException se il numero causa un overflow
     */
    public Number(double number) {
        if (number > Integer.MAX_VALUE) throw new OverflowException();

        int count = 0;
        while (Math.abs(((int) number) - number) > MathUtils.PRECISION) {
            number *= 10;
            count++;
        }

        Number n = new Number((int) number, (int) Math.pow(10, count)).reduce();

        this.numerator = n.numerator;
        this.denominator = n.denominator;
    }

    /**
     * Riduce questa frazione ai minimi termini e restituisce il risultato.
     *
     * @return la frazione ai minimi termini di questa
     */
    private Number reduce() {
        int sign = numerator > 0 ? 1 : -1;
        int gcd = MathUtils.gcd(Math.abs(numerator), denominator);
        return new Number(sign * (Math.abs(numerator) / gcd), denominator / gcd);
    }

    /**
     * Restituisce {@code true} se questo numero è positivo, altrimenti false.
     *
     * @return {@code true} se questo numero è positivo, altrimenti false
     */
    public boolean isPositive() {
        return numerator >= 0;
    }

    /**
     * Restituisce {@code true} se questo è un numero naturale con segno, altrimenti {@code false}.
     *
     * @return se questo è un numero naturale con segno, altrimenti {@code false}
     */
    public boolean isInZ() {
        return numerator == 0 || denominator == 1;
    }

    /**
     * Restituisce {@code true} se questo numero vale zero, altrimenti {@code false}.
     *
     * @return {@code true} se questo numero vale zero, altrimenti {@code false}
     */
    public boolean isZero() {
        return numerator == 0;
    }

    /**
     * Se questo è un numero intero con segno restituisce il suo valore, altrimenti viene lanciata una
     * {@link IllegalCallerException}.
     *
     * @return il valore di questo numero
     * @throws IllegalCallerException se questo non è un numero intero con segno
     */
    public int getZNumber() {
        if (this.isInZ()) return numerator;
        throw new IllegalCallerException();
    }

    /**
     * Restituisce l'opposto di questo numero.
     *
     * <p>Più formalmente, se {@code n} rappresenta questo numero, restituisce {@code -n}.
     *
     * @return l'opposto di questo numero
     */
    public Number opposite() {
        return new Number(-numerator, denominator);
    }

    /**
     * Restituisce l'inverso di questo numero.
     *
     * <p>Più formalmente, se {@code n} rappresenta questo numero, restituisce {@code 1/n}.
     *
     * @return l'inverso di questo numero
     * @throws DivisionByZeroException se questo numero vale zero.
     */
    public Number inverse() {
        if (numerator == 0) throw new DivisionByZeroException();
        return new Number(denominator, numerator);
    }

    /**
     * Restituisce la somma tra questo numero e {@code number}.
     *
     * @param number il numero da sommare a questo
     * @return la somma tra questo numero e {@code number}
     * @throws OverflowException se il risultato della somma causa un overflow
     */
    public Number add(Number number) {
        int den = MathUtils.lcm(this.denominator, number.denominator);

        try {
            int num = Math.addExact(
                    Math.multiplyExact((den / this.denominator), this.numerator),
                    Math.multiplyExact((den / number.denominator), number.numerator)
            );
            return new Number(num, den).reduce();
        } catch (ArithmeticException e) {
            throw new OverflowException();
        }
    }

    /**
     * Restituisce il risultato della sottrazione tra questo numero e {@code number}.
     *
     * @param number il numero da sottrarre a questo
     * @return il risultato della sottrazione tra questo numero e {@code number}
     * @throws OverflowException se il risultato della sottrazione causa un overflow
     */
    public Number sub(Number number) {
        return add(number.opposite());
    }

    /**
     * Restituisce il risultato della moltiplicazione tra questo numero e {@code number}.
     *
     * @param number il numero da moltiplicare a questo
     * @return il risultato della moltiplicazione tra questo numero e {@code number}
     * @throws OverflowException se il risultato della moltiplicazione causa un overflow
     */
    public Number mul(Number number) {
        try {
            int num = Math.multiplyExact(this.numerator, number.numerator);
            int den = Math.multiplyExact(this.denominator, number.denominator);
            return new Number(num, den).reduce();
        } catch (ArithmeticException e) {
            throw new OverflowException();
        }
    }

    /**
     * Restituisce il risultato della divisione tra questo numero e {@code number}.
     *
     * @param number il numero da dividere a questo
     * @return il risultato della divisione tra questo numero e {@code number}
     * @throws DivisionByZeroException se {@code number} vale zero
     */
    public Number div(Number number) {
        if (number.isZero()) throw new DivisionByZeroException();
        return mul(number.inverse());
    }

    /**
     * Restituisce il risultato della potenza che ha questo numero come base e {@code exponent} come esponente.
     *
     * @param exponent l'esponente della potenza
     * @return il risultato della potenza che ha questo numero come base e {@code exponent} come esponente
     * @throws OverflowException               se il risultato della potenza causa un overflow
     * @throws IrrationalNumberException       se il risultato finale oppure un risultato intermedio non è un
     *                                         numero razionale
     * @throws NonCalculableOperationException se la potenza non è calcolabile
     */
    public Number pow(Number exponent) {
        exponent = exponent.reduce();

        // Segno dell esponente
        int num = this.numerator;
        int den = this.denominator;
        if (!exponent.isPositive()) {
            num = this.denominator;
            den = this.numerator;
            exponent = exponent.opposite();
        }

        // calcola le potenze
        double _num = Math.pow(num, exponent.numerator);
        double _den = Math.pow(den, exponent.numerator);

        if (_num > Integer.MAX_VALUE || _den > Integer.MAX_VALUE) throw new OverflowException();

        num = (int) _num;
        den = (int) _den;

        // calcola le radici
        try {
            num = MathUtils.root(num, exponent.denominator);
            den = MathUtils.root(den, exponent.denominator);
        } catch (IllegalArgumentException e) {
            throw new NonCalculableOperationException();
        } catch (ArithmeticException e) {
            throw new IrrationalNumberException();
        }

        return new Number(num, den);
    }

    @Override
    public Number getValue(Number[] operands) {
        return this;
    }

    @Override
    public boolean isNumber() {
        return true;
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalCallerException sempre
     */
    @Override
    public boolean solveInorder() {
        throw new IllegalCallerException();
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public String getLatex(String[] s) {
        if (s.length != 0) throw new IllegalArgumentException();
        if (numerator == 0 || denominator == 1) return String.valueOf(numerator);
        return (numerator < 0 ? "-" : "") + "\\frac{" + Math.abs(numerator) + "}{" + Math.abs(denominator) + "}";
    }

    @Override
    public boolean equals(Object o) {
        // Due numeri sono uguali se:
        //  - entrambi i numeratori valgono zero
        //  - semplificati hanno lo stesso numeratore e denominatore

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Number number = ((Number) o).reduce();
        Number thisNumber = this.reduce();
        return number.numerator == 0 && thisNumber.numerator == 0
                || (number.numerator == thisNumber.numerator
                && number.denominator == thisNumber.denominator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }

    @Override
    public String toString() {
        if (numerator == 0 || denominator == 1) return String.valueOf(numerator);
        return (numerator < 0 ? "-" : "") + Math.abs(numerator) + "/" + Math.abs(denominator);
    }
}
