import exception.*;
import simplicio.ExpressionStepIterator;
import simplicio.Simplicio;

public class Main {
    private static final String FILE_START =
            "\\documentclass{article}\n" +
                    "\\usepackage{xcolor}\n" +
                    "\\usepackage{amsmath}\n\n" +
                    "\\begin{document}\n";

    private static final String FILE_END = "\n\\end{document}\n";

    /**
     * Metodo con il quale si avvia il progetto.
     *
     * @param args l'espressione da valutare
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Atteso un parametro");
            System.exit(0);
        }

        // Rimuove problema degli spazi
        StringBuilder input = new StringBuilder();
        for (String s : args) input.append(s).append(" ");

        input = new StringBuilder(input.toString().replace("*", "×"));

        StringBuilder outputFile = new StringBuilder();
        outputFile.append(FILE_START);

        try {
            ExpressionStepIterator iterator = Simplicio.solve(input.toString());

            while (iterator.hasNext()) {

                try {
                    String res = iterator.next();
                    outputFile
                            .append("$$")
                            .append(res)
                            .append("$$")
                            .append("\n");

                // Eccezioni su errori che capitano in fase di semplificazione dell'espressione.
                } catch (SimplicioArithmeticException e) {
                    outputFile
                            .append("$$")
                            .append(iterator.getCurrentLatex())
                            .append("$$")
                            .append("\n\\newline")
                            .append("\\begin{center}\n\\textbf{Errore}\n\\end{center}")
                            .append(e.getMessage());
                    break;
                } catch (Exception e) {
                    throw new IllegalStateException();
                }
            }

        // Eccezioni su errori presenti nella stringa in input.
        } catch (SimplicioException e) {
            outputFile
                    .append("\\begin{center}\n\\textbf{L'espressione in ingresso non è corretta}\n\\end{center}")
                    .append(e.getMessage());

            try {
                throw e;
            } catch (SimplicioArithmeticException ignored) {
            } catch (UnknownTokenException e1) {
                outputFile
                        .append(": \\verb|")
                        .append(input.toString().charAt(e1.getPosition()))
                        .append("|");
            } catch (SyntaxErrorException e1) {
                if (e1.getPosition() == input.length())
                    outputFile.append("\n\\newline\n\\newline \nSono attesi altri caratteri.");
                else {
                    outputFile
                            .append("\n\\newline\n\\newline \nEspressione: ")
                            .append(input.substring(0, e1.getPosition()))
                            .append("\\color{red}{")
                            .append(input.substring(e1.getPosition()));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException();
        } finally {
            outputFile.append(FILE_END);
            System.out.println(outputFile);
        }
    }
}
