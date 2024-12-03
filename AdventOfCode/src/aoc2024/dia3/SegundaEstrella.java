package aoc2024.dia3;

import input.AdventOfCodeInputFetcher;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SegundaEstrella {

    private static final int DIA = 3;
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

        String expresion = "mul\\(\\d{1,3},\\d{1,3}\\)";

        //El ejercicio dos de hoy se resuelve añadiendo esta linea.
        input = input.replaceAll("(?s)don't\\(\\).*?do\\(\\)", "");

        System.out.println("Resultado tras el replaceAll");
        System.out.println(input);

        Pattern pattern = Pattern.compile(expresion);
        Matcher matcher = pattern.matcher(input);

        List<String> apariciones = new ArrayList<>();

        while (matcher.find()){

            apariciones.add(matcher.group());

        }

        int resultado = 0;

        for (String aparicion : apariciones) {
            String[] numeros = aparicion.substring(4, aparicion.length() - 1).split(",");
            resultado += Integer.parseInt(numeros[0]) * Integer.parseInt(numeros[1]);
        }

        return resultado;
    }
}
