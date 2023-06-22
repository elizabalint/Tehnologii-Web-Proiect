/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mycompany.database.SessionsDAO;
import com.mycompany.database.SouvenirDAO;
import com.mycompany.objects.Session;
import com.mycompany.objects.Souvenir;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.http.HttpRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eliza
 */
public class SouvenirHandlerGeneral implements HttpHandler{

    public SouvenirHandlerGeneral() {
    }
   
    
     @Override
    public void handle(HttpExchange exchange) throws IOException{

        HandlerCommander hc= new HandlerCommander();
        // Set CORS headers
        hc.setCORS(exchange);
        System.out.println("aici");

        if ("GET".equals(exchange.getRequestMethod())) {

            InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String line;

            StringBuilder requestBody = new StringBuilder();

            //Extract the data
            while ((line = br.readLine()) != null) {
                requestBody.append(line);
            }

            String request = requestBody.toString();

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonrequest = objectMapper.readTree(request);

            String sessionx = jsonrequest.get("session").asText();
            int equalsIndex = sessionx.indexOf("=");
            String session = sessionx.substring(equalsIndex + 1);

            try {
                //get session user
                SessionsDAO s = new SessionsDAO();
                Session sesiune = s.findBySession(session);
                //get all souvenirs

                List<Souvenir> obj = SouvenirDAO.getAllSouvenirs();

                //create the name country, name souvenir and period
                SouvenirDAO c = new SouvenirDAO();
                List<String> NameSouvenirs = new ArrayList<>();
                List<String> NameCountries = new ArrayList<>();
                List<String> Period = new ArrayList<>();
                for (Souvenir i : obj) {
                    Souvenir ss = c.findByName(i.getName());
                    NameSouvenirs.add(ss.getName());
                    NameCountries.add(ss.getCountry());
                    Period.add(ss.getPeriod());
                }
                System.out.println("szdxfg");
                System.out.println(NameSouvenirs);
                System.out.println(NameCountries);
                //Send data to the js
                // Create a JSON object to hold the variable assignments
                ObjectNode rootNode = objectMapper.createObjectNode();
                rootNode.put("nameS", objectMapper.writeValueAsString(NameSouvenirs));
                rootNode.put("nameC", objectMapper.writeValueAsString(NameCountries));
                rootNode.put("period", objectMapper.writeValueAsString(Period));
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
/*
    private List<Souvenir> performSearch(String query) throws IOException {
        List<Souvenir> searchResults = new ArrayList<>();
     try {
        // Search after name
         /*   Souvenir nameResults = SouvenirDAO.findByName(query);
        searchResults.addAll(nameResults);
        
        // Search after period
        List<Souvenir> periodResults = SouvenirDAO.findByPeriod(query);
        searchResults.addAll(periodResults);
        
        // Search after gender
        List<Souvenir> genderResults = SouvenirDAO.findByGender(query);
        searchResults.addAll(genderResults);
        
        try {
            // Search after age (converting the query to int)
            int age = Integer.parseInt(query);
            List<Souvenir> ageResults = SouvenirDAO.findByAge(age);
            searchResults.addAll(ageResults);
        } catch (NumberFormatException e) {
            // Ignore the exception if the query cannot be parsed as an integer
        }
        
        if (searchResults.isEmpty()) {
            // No search results found
            throw new SQLException("No result found");
        }
    } catch (SQLException e) {
        // Error occurred during the search
        e.printStackTrace();
        throw new IOException("Error occurred: " + e.getMessage());
    }
    
    return searchResults;
    }*/


