/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mycompany.database.*;
import com.mycompany.objects.*;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ignat
 */
public class MapColorsHandler implements HttpHandler {

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

            String request = requestBody.toString();

            // Retrieve the values of "country" and "session" from the JSON
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonrequest = objectMapper.readTree(request);

            String sessionx = jsonrequest.get("session").asText();
            int equalsIndex = sessionx.indexOf("=");
            String session = sessionx.substring(equalsIndex + 1);

            try {
                //get session user
                SessionsDAO s = new SessionsDAO();
                Session sesiune = s.findBySession(session);

                //get list of visited countries ids
                VisitedCountriesDAO v = new VisitedCountriesDAO();
                List<Integer> IDsCountries = v.AllVisitedCountries(sesiune.getId_user());

                //create the name and short_name lists of the countries
                CountriesDAO c = new CountriesDAO();
                List<String> NameCountries = new ArrayList<>();
                List<String> Short_NameCountries = new ArrayList<>();

                for (Integer i : IDsCountries) {
                    Country tara = c.findById(i);
                    NameCountries.add(tara.getName());
                    Short_NameCountries.add(tara.getShort_name());
                }

                //Send data to the js
                // Create a JSON object to hold the variable assignments
                ObjectNode rootNode = objectMapper.createObjectNode();
                rootNode.put("classNamesToChange", objectMapper.writeValueAsString(NameCountries));
                rootNode.put("idsToChange", objectMapper.writeValueAsString(Short_NameCountries));

                // Convert the JSON object to a string
                String jsonData = objectMapper.writeValueAsString(rootNode);

                // Set the response headers
                exchange.getResponseHeaders().set("Content-Type", "application/javascript");
                exchange.sendResponseHeaders(200, jsonData.getBytes().length);

                // Write the JSON data to the response
                OutputStream outputStream = exchange.getResponseBody();
                outputStream.write(jsonData.getBytes());
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
