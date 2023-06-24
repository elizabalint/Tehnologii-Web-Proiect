/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.database.SouvenirDAO;
import com.mycompany.objects.Souvenir;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author eliza
 */
public class Table_SouvenirsHandler implements HttpHandler {

    public Table_SouvenirsHandler() {
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        HandlerCommander hc = new HandlerCommander();
        // Set CORS headers
        hc.setCORS(exchange);

        if ("GET".equals(exchange.getRequestMethod())) {

            SouvenirDAO s = new SouvenirDAO();
            try {
                List<Souvenir> souvenir_list = s.getAllSouvenirs();

                ObjectMapper objectMapper = new ObjectMapper();
                String jsonSouvenirs = objectMapper.writeValueAsString(souvenir_list);

                exchange.getResponseHeaders().set("Content-Type", "application/json");
                exchange.sendResponseHeaders(200, jsonSouvenirs.getBytes().length);

                // Write the JSON response
                OutputStream outputStream = exchange.getResponseBody();
                outputStream.write(jsonSouvenirs.getBytes());
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

