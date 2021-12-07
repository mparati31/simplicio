package utils;

public class MathUtils {
    public static final double PRECISION = 1e-5;

    /**
     * Calcola il massimo comune divisione tra {@code n1} e {@code n2}.
     *
     * @param n1 primo operando
     * @param n2 secondo operando
     * @return il massimo comune divisione tra {@code n1} e {@code n2}
     * @throws IllegalArgumentException se almeno uno tra {@code n1} e {@code n2} non è positivo
     */
    public static int gcd(int n1, int n2) {
        if (n1 < 0 || n2 < 0) throw new IllegalArgumentException();
        int gcd = 1;
        for (int i = 1; i <= n1 && i <= n2; ++i) if (n1 % i == 0 && n2 % i == 0) gcd = i;
        return gcd;
    }

    /**
     * Calcola il minimo comune multiplo tra {@code n1} e {@code n2}.
     *
     * @param n1 primo operando
     * @param n2 secondo operando
     * @return il minimo comune multiplo tra {@code n1} e {@code n2}
     * @throws IllegalArgumentException se almeno uno tra {@code n1} e {@code n2} non è positivo
     */
    public static int lcm(int n1, int n2) {
        if (n1 < 0 || n2 < 0) throw new IllegalArgumentException();
        return (n1 * n2) / gcd(n1, n2);
    }

    /**
     * Calcola la radice che ha {@code argument} come argomento e {@code index} come indice.
     *
     * @param argument l'argomento della radice
     * @param index    l'indice della radice
     * @return il risultato della radice che ha {@code argument} come argomento e {@code index}
     *         come indice
     * @throws IllegalArgumentException se {@code index} non è positivo oppure se è pari e
     *                                  {@code argument} è negativo
     * @throws ArithmeticException      se il risultato della radice non è un numero naturale
     */
    public static int root(int argument, int index) {
        if (index <= 0) throw new IllegalArgumentException();
        if (index == 1) return argument;
        if (argument == 0) return 0;
        if (argument == 1) return 1;
        if (index % 2 == 0 && argument < 0) throw new IllegalArgumentException();

        int sign = argument < 0 ? -1 : 1;
        argument = Math.abs(argument);
        double powValue = Math.pow(argument, 1 / (double) index);
        double roundedValue = Math.round(powValue);

        if (Math.abs(roundedValue - powValue) < PRECISION) return (int) (sign * roundedValue);
        throw new ArithmeticException();
    }
}
