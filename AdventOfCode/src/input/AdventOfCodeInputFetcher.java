package input;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class AdventOfCodeInputFetcher {
    private static final String SESSION = "53616c7465645f5f607e74b23bfb9ef148b17d04b722a2c31c75e23c8464dc12ee966d499571931fae486e4ec2155a8dd2a9aa28592c7ec9ff80f488d9329844"; // Esta es la cookie de la sesión. Reemplazar por la cookie de su sesión
    private static final String BASE_URL = "https://adventofcode.com/";
    private static final String COOKIE = "session="+SESSION;
    private static final String INPUT_DIR = "src/input/";

    public static String fetchInput(int dia , int anyo) {
        String urlString = BASE_URL + anyo +"/day/"+ dia + "/input";
        StringBuilder contenido = new StringBuilder();
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

            // Construir el contenido
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String linea;

            while ((linea = reader.readLine()) != null) {
                contenido.append(linea).append("\n");
            }

        }catch (Exception e) {
            e.printStackTrace();
        }

        return contenido.toString();

    }

}
