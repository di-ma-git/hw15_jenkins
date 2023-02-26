package org.example.tcphttp;


import com.google.gson.Gson;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;

import static org.example.tcphttp.DataUtil.getFileAsByteArray;

public class SimpleHttpServer {
    private static UserApi userApi = UserApi.getInstance();

    public static Map<Integer,Integer> findOnlyOddelements(){
        return Collections.emptyMap();
    }

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8500), 0);
        setContext("/users", server, SimpleHttpServer::handleDataRequest);
        setContext("/static", server, SimpleHttpServer::handleStaticRequest);
        setContext("/website", server, SimpleHttpServer::handleStaticRequest);
        server.start();
    }

    private static void setContext(String url, HttpServer server, HttpHandler httpHandler){
        HttpContext dataContext = server.createContext(url);
        dataContext.setHandler(httpHandler);
    }

    private static void handleStaticRequest(HttpExchange exchange) throws IOException {
        sendResponse(exchange, getStaticData(exchange));
    }

    private static void handleDataRequest(HttpExchange exchange) throws IOException {
        sendResponse(exchange, getApiData());
    }

    private static byte[] getApiData() {
        return (new Gson().toJson(userApi.getAllUsers())).getBytes(StandardCharsets.UTF_8);
    }

    private static byte[] getStaticData(HttpExchange exchange) {
        String filePath = exchange.getRequestURI().getPath().replaceFirst("/", "");
        byte[] content = getFileAsByteArray(filePath);
        return content == null ? new byte[]{} : content;
    }

    private static void sendResponse(HttpExchange exchange, byte[] content) throws IOException {
        exchange.sendResponseHeaders(200, content.length);
        OutputStream os = exchange.getResponseBody();
        os.write(content);
        os.close();
    }

    public static void mainold(String[] args) throws IOException {
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
