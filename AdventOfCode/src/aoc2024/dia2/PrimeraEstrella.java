package aoc2024.dia2;

import input.AdventOfCodeInputFetcher;

import java.io.IOException;

public class PrimeraEstrella {

    private static final int DIA = 2;
    private static final int ANYO = 2024;

    public static void main(String[] args) {
        // Recuperar el input
        String input = AdventOfCodeInputFetcher.leerInput(DIA, ANYO);
        System.out.println("*** Input del día "+ DIA +" ***\n" + input);
        // Resolver el problema
        int resultado = resolverProblema(input);
        System.out.println("Resultado: " + resultado);

    }

    private static int resolverProblema(String input) {
        // Contador de reportes válidos
        int validos = 0;

        // Separamos el input en líneas
        String[] lineas = input.split("\n");

        // Iteramos sobre cada línea
        for (String linea : lineas) {
            // Convertimos los números a enteros
            String[] partes = linea.split(" ");
            int[] niveles = new int[partes.length];
            for (int i = 0; i < partes.length; i++) {
                niveles[i] = Integer.parseInt(partes[i]);
            }

            // Verificamos las dos condiciones
            if (esSeguro(niveles)) {
                validos++;
            }
        }

        return validos;
    }

    // Función para verificar si un reporte es seguro
    private static boolean esSeguro(int[] niveles) {
        boolean creciente = true;
        boolean decreciente = true;

        for (int i = 1; i < niveles.length; i++) {
            int diferencia = niveles[i] - niveles[i - 1];

            // Si la diferencia no está entre 1 y 3, no es seguro
            if (Math.abs(diferencia) < 1 || Math.abs(diferencia) > 3) {
                return false;
            }

            // Verificamos si los niveles son crecientes o decrecientes
            if (diferencia > 0) {
                decreciente = false; // No puede ser decreciente
            } else if (diferencia < 0) {
                creciente = false; // No puede ser creciente
            }
        }

        // Es seguro si es totalmente creciente o totalmente decreciente
        return creciente || decreciente;
    }

}
