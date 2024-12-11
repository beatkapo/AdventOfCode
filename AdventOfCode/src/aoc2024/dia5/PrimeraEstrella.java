package aoc2024.dia5;

import input.AdventOfCodeInputFetcher;

import java.util.*;
import java.util.stream.Collectors;

public class PrimeraEstrella {

    private static final int DIA = 5; // Cambiar por el día del mes correspondiente
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

        String[] seccion = input.split("\n\n");
        String[] reglas = seccion[0].split("\n");
        String[] paginas = seccion[1].split("\n");

        Map<Integer, Set<Integer>> mapa = new HashMap<>();
        for (String regla : reglas) {
            String[] r = regla.split("\\|");
            int desde = Integer.parseInt(r[0]);
            int hasta = Integer.parseInt(r[1]);
            Set<Integer> set = mapa.getOrDefault(desde, new LinkedHashSet<>());
            set.add(hasta);
            mapa.put(desde, set);
        }

        int suma = 0;

        for (String pageString : paginas) {
            List<Integer> p = Arrays.stream(pageString.split(",")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
            suma += obtenerNumeroCentral(mapa, p);
        }

        return suma;
    }

    private static final Set<Integer> setVacio = new LinkedHashSet<>();
    private static boolean esValido(Map<Integer, Set<Integer>> mapa, List<Integer> paginas) {
        for (int i = 0; i < paginas.size()-1; i++) {
            if (!mapa.getOrDefault(paginas.get(i), setVacio).contains(paginas.get(i+1))) {
                return false;
            }
        }
        return true;
    }

    public static int obtenerNumeroCentral(Map<Integer, Set<Integer>> mapa, List<Integer> paginas) {
        if (esValido(mapa, paginas)) {
            return paginas.get(paginas.size()/2);
        } else {
            return 0;
        }
    }

}
