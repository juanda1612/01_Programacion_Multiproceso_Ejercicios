package listarProgramas;

import java.io.*;

/**
 * @author Juan Daniel
 */

public class InstalledProgramsToFile {
    public static void main(String[] args) {
        // ejecutamos un comando u otro en función del sistema operativo
        String listPrograms;

        if (System.getProperty("os.name").contains("Windows")) {
            listPrograms = "cmd.exe /c dir \"C:\\Program files\"";
        } else {
            listPrograms = "ls -l /bin";
        }


        ProcessBuilder processBuilder = new ProcessBuilder(listPrograms.split(" "));
        try {
            Process process = processBuilder.start();

            InputStream processInputStream = process.getInputStream();

            // ERROR MANAGEMENT
            InputStream errorInputStream = process.getErrorStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(errorInputStream));
            String line = bufferedReader.readLine();
            while (line != null) {
                System.out.println(line);
                line = bufferedReader.readLine();
            }

            File fichero = new File("listado de programas.txt");
            FileWriter escritor = new FileWriter(fichero);
            // PROCESS OUTPUT
            bufferedReader = new BufferedReader(new InputStreamReader(processInputStream));
            line = bufferedReader.readLine();
            while (line != null) {
                escritor.write(line + "\n");
                line = bufferedReader.readLine();
            }

        } catch (IOException e) {
            System.out.println("El proceso no puede ser lanzado.");
            e.printStackTrace();
        }
    }
}
