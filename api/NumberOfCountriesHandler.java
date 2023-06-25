/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.database.*;
import com.mycompany.objects.*;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.SQLException;

/**
 *
 * @author ignat
 */
public class NumberOfCountriesHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // Set CORS headers
       HandlerCommander hc= new HandlerCommander();
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

            String request = requestBody.toString();

            // Retrieve the values of "country" and "session" from the JSON
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonrequest = objectMapper.readTree(request);

            String sessionx = jsonrequest.get("session").asText();
            int equalsIndex = sessionx.indexOf("=");
            String session = sessionx.substring(equalsIndex + 1);
            
          
            SessionsDAO s = new SessionsDAO();
            VisitedCountriesDAO v = new VisitedCountriesDAO();
            Session sesiune;
            try {
                sesiune = s.findBySession(session);
                Integer nr = v.NumberOfAssociations(sesiune.getId_user());
                String string_number=nr.toString();
                hc.sendResponse(exchange, "true", string_number, 200);
                
            } catch (SQLException ex) {
                System.out.println(ex);
            }

        } else {
            hc.sendResponse(exchange, "false", "Invalid request method", 405);
        }

        hc.closeconnection();

    }


}
