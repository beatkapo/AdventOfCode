package aoc2024.dia1;

import input.AdventOfCodeInputFetcher;

public class PrimeraEstrella {

    private static final int DIA = 1; // Cambiar por el día del mes correspondiente
    private static final int ANYO = 2024; // Cambiar por el año correspondiente

    public static void main(String[] args) {
        // Recuperar el input
        String input = AdventOfCodeInputFetcher.leerInput(DIA, ANYO);
        System.out.println("*** Input del día "+ DIA +" ***\n" + input);
        // Resolver el problema
        int resultado = resolverProblema(input);
        System.out.println("Resultado: " + resultado);

    }

    private static int resolverProblema(String input) {
        // TODO: Implementar la lógica necesaria para resolver el problema

        return 0;
    }
}
