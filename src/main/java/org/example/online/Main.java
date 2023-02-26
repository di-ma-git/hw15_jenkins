package org.example.online;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.example.tcphttp.UserApi;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

import static org.example.tcphttp.DataUtil.getFileAsByteArray;

public class Main {
    private static UserApi userApi = UserApi.getInstance();

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8500), 0);
        setContext("/users", server, Main::handleDataRequest);
        setContext("/temp", server, Main::handleStaticRequest);
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

}
