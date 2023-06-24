/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mycompany.database.SouvenirDAO;
import com.mycompany.objects.Souvenir;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 *
 * @author eliza
 */

public class SouvenirHandler implements HttpHandler {

    public SouvenirHandler() {
    }
    
    @Override
    public void handle(HttpExchange exchange) throws IOException {

         HandlerCommander hc= new HandlerCommander();
        // Set CORS headers
        hc.setCORS(exchange);

        if ("GET".equals(exchange.getRequestMethod())) {

            SouvenirDAO s = new SouvenirDAO();
            try {
                List<Souvenir> allSouvenirs = s.getAllSouvenirs();

                ObjectMapper objectMapper = new ObjectMapper();
                String jsonSouvenir = objectMapper.writeValueAsString(allSouvenirs);

                exchange.getResponseHeaders().set("Content-Type", "application/json");
                exchange.sendResponseHeaders(200, jsonSouvenir.getBytes().length);

                // Write the JSON response
                OutputStream outputStream = exchange.getResponseBody();
                outputStream.write(jsonSouvenir.getBytes());
                outputStream.close();

            } catch (SQLException ex) {
                System.out.println(ex);
            }

        } else {
            hc.sendResponse(exchange, "false", "Invalid request method", 405);
        }

        hc.closeconnection();
    
    }}
    /*
    private List<Souvenir> performSearch(String query) throws IOException {
        List<Souvenir> searchResults = new ArrayList<>();
     try {
        // Search after name
       
        
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
    }

}
*/