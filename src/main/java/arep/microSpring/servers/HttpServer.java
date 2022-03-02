package arep.microSpring.servers;


import arep.microSpring.services.CurrentTimeMillisService;

import java.net.*;
import java.io.*;

public class HttpServer {


    public static OutputStream outputStream;

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = null;
        boolean running = true;
        try {
            serverSocket = new ServerSocket(getPort());
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        while (running) {
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }

            // Empezar a dividir
            outputStream = clientSocket.getOutputStream();
            PrintWriter out = new PrintWriter(outputStream, true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine, outputLine;
            boolean primeraLinea = true;
            String req = "";
            outputLine = "";
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received: " + inputLine);
                if (primeraLinea){
                    String[] peticion = inputLine.split(" ");
                    req = peticion[1];
                    System.out.println("REQ: " + req);
                    primeraLinea = false;
                }
                if (!in.ready()) {break; }
            }
            // Servicio pagina HTML de tiempo en milisegundos
            if(req.startsWith("/time")){
                outputLine = CurrentTimeMillisService.currentTimeMillis();
            }
            else {
                outputLine = "HTTP/1.1 200 OK\r\n"
                        + "Content-Type: text/html\r\n"
                        + "\r\n"
                        + "<!DOCTYPE html>"
                        + "<html>"
                        + "<head>"
                        + "<meta charset=\"UTF-8\">"
                        + "<title>Default</title>\n"
                        + "</head>"
                        + "<body>"
                        + "<h1>"
                        + "Default"
                        + "</h1>"
                        + "</body>"
                        + "</html>";
            }
            out.println(outputLine);
            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 35000;
        //returns default port if heroku-port isn't set (i.e. on localhost)
    }

}