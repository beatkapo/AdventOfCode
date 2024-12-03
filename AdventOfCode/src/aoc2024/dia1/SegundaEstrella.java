package aoc2024.dia1;

import input.AdventOfCodeInputFetcher;

import java.util.*;

public class SegundaEstrella {

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

        Map<Integer, Integer> aparicionesIZQ = new HashMap<>();

        for (int clave : izq) {
            if (der.contains(clave)) {
                int apariciones = Collections.frequency(der, clave);
                aparicionesIZQ.put(clave, apariciones);
            }
        }
        int suma = 0;
        for (Map.Entry<Integer, Integer> entry : aparicionesIZQ.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();
            int resultado = key * value;
            suma += resultado;
        }
        return suma;
    }
}
