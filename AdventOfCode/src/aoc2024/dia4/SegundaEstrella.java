package aoc2024.dia4;

import input.AdventOfCodeInputFetcher;

public class SegundaEstrella {

    private static final int DIA = 4; // Cambiar por el día del mes correspondiente
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
        // Convierto el input en una matriz de char.
        char[][] mapa = input.lines().map(String::toCharArray).toArray(char[][]::new);

        String palabra = "XMAS";

        int contador = contarApariciones(mapa, palabra);

        return contador;
    }

    private static int contarApariciones(char[][] mapa, String palabra) {
        int filas = mapa.length;
        int columnas = mapa[0].length;
        int contador = 0;

        //Direcciones: {movimientoFila, movimientoColumna}


        for(int fila = 0; fila<filas; fila++){
            for(int columna = 0; columna<columnas;columna++){
                //Aquí se intenta encontrar la X MAS
                if(existeX(mapa, fila,columna)){
                    contador++;
                }

            }
        }
        return contador;
    }

    private static boolean existeX(char[][] mapa, int fila, int columna) {
        int filas = mapa.length;
        int columnas = mapa[0].length;

        boolean existe = false;

        try{
            char letra = mapa[fila][columna];
            char arribaDer = mapa[fila-1][columna+1];
            char arribaIzq = mapa[fila-1][columna-1];
            char abajoDer = mapa[fila+1][columna+1];
            char abajoIzq = mapa[fila+1][columna-1];

            if(letra == 'A'){
                existe =
                        (arribaIzq == 'M'&& abajoDer == 'S') && (abajoIzq == 'M' && arribaDer == 'S') ||
                                (arribaIzq == 'M'&& abajoDer == 'S') && (abajoIzq == 'S' && arribaDer == 'M') ||
                                (arribaIzq == 'S'&& abajoDer == 'M') && (abajoIzq == 'M' && arribaDer == 'S') ||
                                (arribaIzq == 'S'&& abajoDer == 'M') && (abajoIzq == 'S' && arribaDer == 'M');

            }
        } catch (Exception e) {
            return false;
        }

        return existe;
    }
}
