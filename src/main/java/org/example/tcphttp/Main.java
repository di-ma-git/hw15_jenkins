package org.example.tcphttp;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);

        while (true) {
            Socket clientSocket = serverSocket.accept();

            InputStream input = clientSocket.getInputStream();
            OutputStream output = clientSocket.getOutputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            PrintWriter writer = new PrintWriter(output, true);

            String line;
            StringBuilder requestBuilder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                requestBuilder.append(line).append("\r\n");

                if (line.isEmpty()) {
                    break;
                }
            }

            System.out.println("what came to localhost:");
            System.out.println(requestBuilder);
            String requestData = requestBuilder.toString();

            String response = "HTTP/1.1 200 OK\r\n" +
                    "Content-Type: text/plain\r\n" +
                    "Content-Length: " + requestData.length() + "\r\n" +
                    "\r\n" +
                    "\r\n" +
                    requestData;

            writer.print(response);
            writer.close();
            clientSocket.close();
        }



    }

}
