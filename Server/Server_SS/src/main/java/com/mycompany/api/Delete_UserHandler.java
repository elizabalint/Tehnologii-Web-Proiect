/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.database.*;
import com.mycompany.objects.*;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

/**
 *
 * @author ignat
 */
public class Delete_UserHandler implements HttpHandler {

    public Delete_UserHandler() {
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {

      HandlerCommander hc = new HandlerCommander();
        // Set CORS headers
        hc.setCORS(exchange);
       
        if ("POST".equals(exchange.getRequestMethod())) {
            InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String line;

            StringBuilder requestBody = new StringBuilder();

            while ((line = br.readLine()) != null) {
                requestBody.append(line);
            }

            //extract the session id
            String request = requestBody.toString();

            // Retrieve the value the id_user from the JSON
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonrequest = objectMapper.readTree(request);
            String id_user = jsonrequest.get("id_user").asText();

            int user = Integer.parseInt(id_user);

            try {
                UsersDAO u = new UsersDAO();
                //delete user
                u.delete_by_ID(user);

                //delete data associated with the user
                VisitedCountriesDAO v = new VisitedCountriesDAO();
                v.deleteAll(user);
                 hc.sendResponse(exchange, "true", "User deleted", 200);

            } catch (SQLException ex) {
                System.out.println(ex);
            }

        } else {
            hc.sendResponse(exchange, "false", "Invalid request method", 405);
        }

        hc.closeconnection();
    }

}
