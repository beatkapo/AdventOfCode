package aoc2024.dia5;

import input.AdventOfCodeInputFetcher;

import java.util.*;
import java.util.stream.Collectors;

public class SegundaEstrella {

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
            String[] R = regla.split("\\|");
            int from = Integer.parseInt(R[0]);
            int to = Integer.parseInt(R[1]);
            Set<Integer> set = mapa.getOrDefault(from, new LinkedHashSet<>());
            set.add(to);
            mapa.put(from, set);
        }

        int suma = 0;
        for (String pageString : paginas) {
            List<Integer> p = Arrays.stream(pageString.split(",")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
            suma += obtenerNumeroCentral(mapa, p);
        }

        return suma;
    }

    private static boolean esOrdenValido(List<Integer> lista, Map<Integer, List<Integer>> relaciones) {
        Map<Integer, Integer> indices = new HashMap<>();
        for (int i = 0; i < lista.size(); i++) {
            indices.put(lista.get(i), i);
        }

        for (Map.Entry<Integer, List<Integer>> entrada : relaciones.entrySet()) {
            int antes = entrada.getKey();
            for (int despues : entrada.getValue()) {
                if (indices.containsKey(antes) && indices.containsKey(despues)) {
                    if (indices.get(antes) >= indices.get(despues)) {
                        return false;
                    }
                }
            }
        }
        return true;
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
            return 0;
        } else {
            paginas.sort(new PageComparator(mapa));
            return paginas.get(paginas.size()/2);
        }
    }
    static class PageComparator implements Comparator<Integer> {
        private final Map<Integer, Set<Integer>> mapa;

        public PageComparator(Map<Integer, Set<Integer>> map) {
            this.mapa = map;
        }

        @Override
        public int compare(Integer i1, Integer i2) {
            if (mapa.get(i1).contains(i2)) {
                return 1;
            } else {
                return -1;
            }
        }
    }

}
