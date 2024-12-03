package servidortipo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;



public class ServidorTipo {
   
    public static void main(String[] args) {
        
        Random random = new Random();
        int numero = random.nextInt(101);
        System.out.println("Numero a adivinar: " + numero);
        
        // Puerto del servidor
        int port = 1234;
        
        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("Servidor iniciado en el puerto " + port + ".");
            
            while (true) {
                // Aceptar una conexion
                Socket client = server.accept();
                System.out.println("Cliente conectado: " + client.getInetAddress());
                
                // Leer y responder al cliente
                BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter output = new PrintWriter(client.getOutputStream(), true);
                
                int numeroSaludo = 0;
                boolean acierto = false;
                while (acierto == false) {
                    String message = input.readLine();
                    System.out.println("Se ha recibido este mensaje: " + message);
                    
                    if (Integer.parseInt(message) == numero){
                        System.out.println("El cliente ha acertado el número: " + numero);
                        String answer = "Acertaste, el número era el " + numero;
                        output.println(answer);
                        acierto = true;
                        break;
                    } else if (Integer.parseInt(message) < numero){
                        System.out.println("El cliente ha fallado el número, se le proporcionará una pista");
                        String answer = "Fallaste, el número es mayor";
                        output.println(answer);
                    } else if (Integer.parseInt(message) > numero){
                        System.out.println("El cliente ha fallado el número, se le proporcionará una pista");
                        String answer = "Fallaste, el número es menor";
                        output.println(answer);
                    }
                }
                
                
                
                
                client.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
