package ejemploPing;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class EjemploPing {
    public static void main(String[] args) {
        // vamos a ejecutar un comando u otro en función de que estemos en Windows o en Linux
        ProcessBuilder pb = null;
        if (System.getProperty("os.name").contains("Windows")) {
            pb = new ProcessBuilder("ping", "google.com");
        } else {
            pb = new ProcessBuilder("ping", "-c 4", "google.com");
        }
        
        // creamos el proceso
        try {
            Process p = pb.start();
            
            // ahora guardamos la salida del proceso en un InputStream
            InputStream inputStream = p.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            
            // y también guardamos la salida de los errores por si hubiera
            InputStream error = p.getErrorStream();
            InputStreamReader errorReader = new InputStreamReader(error);
            BufferedReader bufferedError = new BufferedReader(errorReader);
            
            // si hay un error, que me lo diga, lo leemos línea a línea
            String linea = bufferedError.readLine();
            while (linea != null) {
                System.out.println(linea);
                linea = bufferedError.readLine();
            }
            
            // si no hay error, llegará aquí, y entonces ya quiero que me muestre la salida del comando
            linea = bufferedReader.readLine();
            while (linea != null) {
                System.out.println(linea);
                linea = bufferedReader.readLine();
            }
            
        } catch (Exception e) {
            System.out.println("Problemita");
        }
        
    }
}
