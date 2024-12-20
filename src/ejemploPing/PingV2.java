package ejemploPing;

import java.io.*;
import java.util.Scanner;
/**
 * @author Juan Daniel
 */
public class PingV2 {
    static Scanner teclado = new Scanner(System.in);
    static String ping;

    public static void main(String[] args) {

        do {
            menu();
            if (!ping.equals("0")) {
                ProcessBuilder processBuilder = new ProcessBuilder(ping.split(" "));
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

                    // PROCESS OUTPUT
                    bufferedReader = new BufferedReader(new InputStreamReader(processInputStream));
                    line = bufferedReader.readLine();
                    while (line != null) {
                        System.out.println(line);
                        line = bufferedReader.readLine();
                    }

                } catch (IOException e) {
                    System.out.println("El proceso no puede ser lanzado.");
                    e.printStackTrace();
                }
            }else {
                System.out.println("¡Adios!");
            }
        }while (!ping.equals("0"));
    }

    public static String menu(){
        System.out.println("Introduce el comando que quieras usar:\n1. ping\n2. ipconfig\n3. getmac\n4. Salir");
        int numero = teclado.nextInt(); teclado.nextLine();
        return switch (numero) {
            case 1 -> ping = ping();
            case 2 -> ping = ipconfig();
            case 3 -> ping = getmac();
            case 4 -> ping = "0";
            default -> "";
        };
    }

    public static String ping(){
        System.out.println("Introduce la URl");
        if (System.getProperty("os.name").contains("Windows")) {
            return ping = "cmd.exe /c ping " + teclado.nextLine();
        } else {
            return "ping -c 4 " + teclado.nextLine();
        }
    }

    public static String ipconfig(){
        if (System.getProperty("os.name").contains("Windows")) {
            return ping = "cmd.exe /c ipconfig ";
        } else {
            return "ifconfig";
        }
    }

    public static String getmac(){
        if (System.getProperty("os.name").contains("Windows")) {
            return ping = "cmd.exe /c getmac ";
        } else {
            return "ip";
        }
    }
}