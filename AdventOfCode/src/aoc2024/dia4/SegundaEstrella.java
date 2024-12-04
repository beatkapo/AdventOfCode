package aoc2024.dia4;

import input.AdventOfCodeInputFetcher;

public class SegundaEstrella {

    private static final int DIA = 4;
    private static final int ANYO = 2024;

    public static void main(String[] args) {
        // Recuperar el input
        String input = AdventOfCodeInputFetcher.fetchInput(DIA, ANYO);
        System.out.println("*** Input del día "+ DIA +" ***\n" + input);
        // Resolver el problema
        int resultado = resolverProblema(input);
        System.out.println("Resultado: " + resultado);

    }

    private static int resolverProblema(String input) {
        // Convierto el input en una matriz de char.
        char[][] mapa = input.lines().map(String::toCharArray).toArray(char[][]::new);

        int contador = contarApariciones(mapa);

        return contador;
    }

    private static int contarApariciones(char[][] mapa) {
        int filas = mapa.length;
        int columnas = mapa[0].length;
        int contador = 0;

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
                existe = (arribaIzq == 'M'&& abajoDer == 'S') && (abajoIzq == 'M' && arribaDer == 'S') ||
                        (arribaIzq == 'M'&& abajoDer == 'S') && (abajoIzq == 'S' && arribaDer == 'M') ||
                        (arribaIzq == 'S'&& abajoDer == 'M') && (abajoIzq == 'M' && arribaDer == 'S') ||
                        (arribaIzq == 'S'&& abajoDer == 'M') && (abajoIzq == 'S' && arribaDer == 'M');
            }
        } catch (Exception e) { //No se si es la mejor forma, pero me vale
            return false;
        }

        return existe;
    }
}
