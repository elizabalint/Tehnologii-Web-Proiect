/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.database.*;
import com.mycompany.objects.*;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ignat
 */
public class Table_UsersHandler implements HttpHandler {

    public Table_UsersHandler() {
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        HandlerCommander hc = new HandlerCommander();
        // Set CORS headers
        hc.setCORS(exchange);

        if ("GET".equals(exchange.getRequestMethod())) {

            UsersDAO u = new UsersDAO();
            try {
                List<User> user_list = u.findAllUsers();

                ObjectMapper objectMapper = new ObjectMapper();
                String jsonUsers = objectMapper.writeValueAsString(user_list);

                exchange.getResponseHeaders().set("Content-Type", "application/json");
                exchange.sendResponseHeaders(200, jsonUsers.getBytes().length);

                // Write the JSON response
                OutputStream outputStream = exchange.getResponseBody();
                outputStream.write(jsonUsers.getBytes());
                outputStream.close();

            } catch (SQLException ex) {
                System.out.println(ex);
            }

        } else {
            hc.sendResponse(exchange, "false", "Invalid request method", 405);
        }

        hc.closeconnection();
    }

}
