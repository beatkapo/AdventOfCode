package aoc2024.dia1;

import input.AdventOfCodeInputFetcher;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PrimeraEstrella {

    private static final int DIA = 1; // Cambiar por el día del mes correspondiente
    private static final int ANYO = 2024; // Cambiar por el año correspondiente

    public static void main(String[] args) {
        // Recuperar el input
        String input = AdventOfCodeInputFetcher.fetchInput(DIA, ANYO);
        System.out.println("*** Input del día "+ DIA +" ***\n" + input);
        // Resolver el problema
        int resultado = resolverProblema(input);
        System.out.println("Resultado: " + resultado);

    }

    private static int resolverProblema(String input) {
        String[] lineas = input.split("\n");

        List<Integer> izq = new ArrayList<>();
        List<Integer> der = new ArrayList<>();

        for (String linea : lineas) {

            String[] tokens = linea.split("   ");

            int a = Integer.parseInt(tokens[0]);
            int b = Integer.parseInt(tokens[1]);

            izq.add(a);
            der.add(b);
        }

        izq.sort(Comparator.naturalOrder());
        der.sort(Comparator.naturalOrder());

        List<Integer> distancias = new ArrayList<>();

        for (int i = 0; i < izq.size(); i++) {
            int max = Math.max(izq.get(i), der.get(i));
            int min = Math.min(izq.get(i), der.get(i));
            distancias.add(max - min);
        }

        int suma = distancias.stream().mapToInt(Integer::intValue).sum();

        return suma;
    }
}
