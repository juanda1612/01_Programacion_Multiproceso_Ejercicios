package javajava;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class App {
   public static void main(String[] args) {
      try {
         // TODO CONSEGUIR QUE SE EJECUTE Prueba.java
         // Lanzamos el proceso
         String ruta = "C:\\Users\\anaij\\IdeaProjects\\01 Programación multiproceso\\2. Procesos. Conceptos teóricos\\Actividad resuelta 1.1\\src\\javajava\\Prueba.java";
         System.out.println("Creamos el proceso desde Java ");
         ProcessBuilder processBuilder = new ProcessBuilder("java", ruta);
         Process proceso = processBuilder.start();


         // Código para capturar el error en caso de producirse
         InputStream error = proceso.getErrorStream();
         InputStreamReader lector = new InputStreamReader(error);
         BufferedReader bufferedReader = new BufferedReader(lector);
         String line;
         while ((line = bufferedReader.readLine()) != null) {
            System.out.println("Error: " + line);
         }

         // Wait for the process to complete and get the exit status
         proceso.waitFor();
         int exitStatus = proceso.exitValue();
         System.out.println("Exit status: " + exitStatus);
      } catch (Exception e) {
         System.out.println(e.getMessage());
      }
   }
}