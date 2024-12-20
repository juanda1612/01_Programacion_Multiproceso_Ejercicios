package VariosComandos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author Juan Daniel
 */

public class CurrentDate {
    public static void main(String[] args) {
        // ejecutamos un comando u otro según el sistema operativo
        String currentDate = "";
        if (System.getProperty("os.name").contains("Windows")) {
            currentDate = "cmd.exe /c  date /T";
        } else {
            currentDate = "date";
        }

        Process process = null;
        try {
            // CREACIÓN Y LANZAMIENTO DEL PROCESO
            // creamos el proceso
            ProcessBuilder processBuilder = new ProcessBuilder(currentDate.split(" "));
            // lo lanzamos
            process = processBuilder.start();

            // guardamos su salida en un stream
            InputStream inputStream = process.getInputStream();

            // GESTIÓN DE LOS ERRORES
            // y por si acaso también el error de salida
            InputStream inputError = process.getErrorStream();
            // leemos el error, inputStream -> reader -> buffered reader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputError));
            String line = bufferedReader.readLine();
            while (line != null) {
                System.out.println(line);
                line = bufferedReader.readLine();
            }

            // SI NO HA HABIDO ERROR, LLEGAMOS AQUÍ Y LEEMOS LA SALIDA DEL PROCESO
            // reutilizamos la variable bufferedReader y line
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            // leemos la primera línea
            line = bufferedReader.readLine();
            while (line != null) {
                System.out.println(line);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (process != null)
                System.out.println("Proceso finalizado con valor de salida " + process.exitValue());
            else
                System.out.printf("No se puede obtener el valor de salida porque el proceso es nulo.");
        }
    }
}
