import java.io.*;
import java.net.*;

public class servidorJava {
    public static void main(String[] args) {
	if (args.length != 1) {
            System.out.println("Uso: java servidor parte 1 <puerto>");
            return;
        }

        int puerto = Integer.parseInt(args[0]);

        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("Servidor esperando conexiones en el puerto " + puerto + "...");
            
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado desde " + clientSocket.getInetAddress());

                // Configurar streams de entrada y salida
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                // Leer mensaje del cliente
                String mensajeCliente = in.readLine();
                System.out.println("Cliente dice: " + mensajeCliente);

                // Responder al cliente
                String respuesta = "Hola que tal";
                out.println(respuesta);
                out.flush(); // Asegurarse de que los datos se envíen inmediatamente

                // Cerrar la conexión con el cliente
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
