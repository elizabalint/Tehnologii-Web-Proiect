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
    
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        HandlerCommander hc= new HandlerCommander();
        // Set CORS headers
        hc.setCORS(exchange);
        System.out.println("aici");
        
        if ("GET".equals(exchange.getRequestMethod())) {
             InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "UTF-8");
             BufferedReader br = new BufferedReader(isr);
             StringBuilder content = new StringBuilder();
             String line;
             while ((line = br.readLine()) != null) {
                  content.append(line);
                }
             System.out.println(content);
             br.close();
            String requestBody = content.toString();  
            System.out.println(requestBody + "gf");
            sendResponse(exchange, "true", "succes" ,200);



            // Obțineți datele din baza de date sub forma de obiecte
        SouvenirDAO souvenirDAO = new SouvenirDAO();
        try{
        List<Souvenir> souvenirs = souvenirDAO.getAllSouvenirs();

        // Convertiți obiectele într-un șir de caractere JSON
        String response = ("nbgfdfcs");

        // Setați răspunsul HTTP cu codul 200 și conținutul JSON
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, response.length());

        // Trimiteți datele JSON către client
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(response.getBytes());
        outputStream.close();
            if (requestBody != null ) {
                List<Souvenir> searchResults = performSearch(requestBody); 
                if (!searchResults.isEmpty()) {
                    sendResponse(exchange, "true", "succes" ,200);
                } else {
                    // No search result found
                    sendResponse(exchange, "false", "No result found", 404);                    
                }
                
            } else {
                // Query parameters are missing
                sendResponse(exchange, "false", "Missing query ", 400); 
            
        }} catch (SQLException ex) {
                System.out.println(ex);
            }
            
        }
        else  {
                    System.out.println("Eroare la cererea HTTP: " );
            sendResponse(exchange, "false", "Invalid request method", 405);
        }
    }
    private void sendResponse(HttpExchange exchange, String token, String message, int code) throws IOException {
        String response = "{ \"success\": " + token + ", \"message\": \"" + message + "\" }";
        exchange.sendResponseHeaders(code, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
    
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
