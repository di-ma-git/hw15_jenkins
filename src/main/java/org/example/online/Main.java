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

public class Main {
    private static UserApi userApi = UserApi.getInstance();

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8500), 0);
        HttpContext dataContext = server.createContext("/users");

        dataContext.setHandler(new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                byte[] users = getApiData();
                exchange.sendResponseHeaders(200, users.length);

                OutputStream os = exchange.getResponseBody();
                os.write(users);
                os.close();
            }
        });

        server.start();
    }

    private static byte[] getApiData() {
        return (new Gson().toJson(userApi.getAllUsers())).getBytes(StandardCharsets.UTF_8);
    }

}
