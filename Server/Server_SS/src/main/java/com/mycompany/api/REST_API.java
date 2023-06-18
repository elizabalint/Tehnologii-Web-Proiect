/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;


/**
 *
 * @author ignat
 */
public class REST_API {

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8081), 0);

        server.createContext("/api/login", (HttpHandler) new LoginHandler());
        

        server.start();
        System.out.println("Server started on port 8081");
    }

    static class LoginHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {

            // Set CORS headers
            exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            exchange.getResponseHeaders().set("Access-Control-Allow-Headers", "Content-Type");

            if ("POST".equals(exchange.getRequestMethod())) {

                InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "UTF-8");
                BufferedReader br = new BufferedReader(isr);
                String line;

                StringBuilder usernameBody = new StringBuilder();
                StringBuilder passwordBody = new StringBuilder();
                StringBuilder rememberedBody = new StringBuilder();
                
                // StringBuilder requestBody = new StringBuilder();
                int line_number = 0;
              
                //Extract the data
                while ((line = br.readLine()) != null) {
                    line_number++;
                    //requestBody.append(line);
                    if (line_number == 4) {
                        usernameBody.append(line);
                    }
                    if (line_number == 8) {
                        passwordBody.append(line);
                    }
                    if (line_number == 12) {
                        rememberedBody.append(line);
                    }

                }
                br.close();

                // String request = requestBody.toString();
                 
                String username = usernameBody.toString();
                String password = passwordBody.toString();
                if (rememberedBody.isEmpty()) {
                    rememberedBody.append("off");
                }
                String remembered = rememberedBody.toString();

                System.out.println(username + " " + password + " " + remembered);
               
                
                // Verify login credentials
                

                // Return a response
                String response = "{ \"success\": true, \"message\": \"Login successful\" }";
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
                
            } else {
                String response = "{ \"success\": false, \"message\": \"Invalid request method\" }";
                exchange.sendResponseHeaders(405, response.getBytes().length); // Method Not Allowed
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }
    }

}
