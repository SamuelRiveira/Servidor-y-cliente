package clientetipo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;



public class ClienteTipo {

    
    public static void main(String[] args) {
        
        Random random = new Random();
        int LimiteInferior = 0;
        int LimiteSuperior = 100;
        
        // IP y puerto del servidor
        String host = "localhost";
        int port = 1234;
        
        try {
            //Conectarse al servidor
            Socket socket = new Socket(host, port);
            System.out.println("Conectado al servidor " + host + " en el puerto " + port + ".");
            
            // Enviarle un mensaje
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            boolean acierto = false;
            
            while (acierto == false) {
                
                int numero = random.nextInt(LimiteInferior, LimiteSuperior);
                
                System.out.println("Tratando de avininar el número: " + numero);
                output.println(numero);

                String answer = input.readLine();
                System.out.println("La respuesta del servidor es: " + answer);
                
                if (answer.contains("Acertaste")) {
                    acierto = true;
                    System.out.println("Ganaste");
                    break;
                } else if (answer.contains("mayor")) {
                    LimiteInferior = numero;
                } else if (answer.contains("menor")) {
                    LimiteSuperior = numero;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
