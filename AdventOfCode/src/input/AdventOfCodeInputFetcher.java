package input;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class AdventOfCodeInputFetcher {
    private static final String SESSION = ""; // Esta es la cookie de la sesión. Reemplazar por la cookie de su sesión
    private static final String BASE_URL = "https://adventofcode.com/";
    private static final String COOKIE = "session="+SESSION;
    private static final String INPUT_DIR = "src/input/";

    private static void fetchInput(int dia , int anyo) {
        String urlString = BASE_URL + anyo +"/day/"+ dia + "/input";
        String archivoSalida = INPUT_DIR + anyo + "dia" + dia + ".txt";
        try {
            // Crear conexion
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // Configurar la peticion
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Cookie", COOKIE);

            // Verificar la respuesta
            int codigoRespuesta = conn.getResponseCode();
            if (codigoRespuesta != HttpURLConnection.HTTP_OK) {
                throw new IOException("Error al obtener el input: " + codigoRespuesta);
            }

            // Leer el input y guardarlo en un archivo
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                 FileWriter writer = new FileWriter(archivoSalida)) {

                String linea;
                while ((linea = reader.readLine()) != null) {
                    writer.write(linea);
                    writer.write(System.lineSeparator());
                }

                System.out.println("Input del día " + dia + " guardado en " + archivoSalida);
            }

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static String leerInput(int dia,int anyo){

        fetchInput(dia,anyo);// Descargar el input por si no existe

        String ruta = INPUT_DIR+anyo+"dia"+ dia + ".txt";
        File file = new File(ruta);

        if (!file.exists()) { //No deberia pasar nunca, pero pueden pasar un dia que no exista en la web

            //Esto no se si lo estoy haciendo de la mejor manera...
            try {
                throw new IOException("El archivo de entrada para el día " + dia + " del " + anyo + " no existe.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        // Construir el String del contenido del archivo
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String linea;

            while ((linea = reader.readLine()) != null) {
                content.append(linea).append("\n");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return content.toString();
    }
}
