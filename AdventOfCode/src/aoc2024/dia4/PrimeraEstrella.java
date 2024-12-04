package aoc2024.dia4;

import input.AdventOfCodeInputFetcher;

import java.util.List;

public class PrimeraEstrella {

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
        int[][] direcciones = {
                {0, 1},  // Derecha
                {0, -1}, // Izquierda
                {1, 0},  // Abajo
                {-1, 0}, // Arriba
                {1, 1},  // Diagonal abajo-derecha
                {-1, -1},// Diagonal arriba-izquierda
                {1, -1}, // Diagonal abajo-izquierda
                {-1, 1}  // Diagonal arriba-derecha
        };

        for(int fila = 0; fila<filas; fila++){
            for(int columna = 0; columna<columnas;columna++){
                //Aquí ya intento encontrar la palabra en todas las direcciones
                for(int[] direccion: direcciones){
                    if(existePalabra(mapa, fila,columna, direccion,palabra)){
                        contador++;
                    }
                }
            }
        }
        return contador;
    }
    //Mejor si me lo saco a un metodo
    private static boolean existePalabra(char[][] mapa, int fila, int columna, int[] direccion, String palabra) {
        int filas = mapa.length;
        int columnas = mapa[0].length;
        int palabraLength = palabra.length();

        boolean existe = true;

        for(int i=0;i<palabraLength;i++){
            char letra = palabra.charAt(i);

            int nuevaFila = fila + i * direccion[0];
            int nuevaColumna = columna + i * direccion[1];

            try{
                char letraMapa = mapa[nuevaFila][nuevaColumna];
                System.out.println("Letra: " + letra + " LetraMapa: " + letraMapa);


            if(nuevaFila<0 || nuevaFila>=filas || nuevaColumna<0 || nuevaColumna>=columnas){
                existe =  false;
            }

            if(letraMapa != letra){
                existe =  false;
            }

            }catch (ArrayIndexOutOfBoundsException e){
                existe = false;
                break;
            }
        }
        return existe;
    }
}
