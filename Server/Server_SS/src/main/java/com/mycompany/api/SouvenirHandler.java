/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api;

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

/**
 *
 * @author eliza
 */
public class SouvenirHandler implements HttpHandler {

    public SouvenirHandler() {
    }
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        // Set CORS headers
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        exchange.getResponseHeaders().set("Access-Control-Allow-Headers", "Content-Type");

        if ("GET".equals(exchange.getRequestMethod())) {
            String query = exchange.getRequestURI().getQuery();

            // Verificăm dacă parametrii de interogare sunt prezenți
            if (query != null && !query.isEmpty()) {
                // Extragem parametrii de interogare și căutăm suvenirurile în funcție de aceștia
                List<Souvenir> searchResults = performSearch(query); 
                if (!searchResults.isEmpty()) {
                    //Setarea răspunsului HTTP și trimiterea acestuia
                    sendResponse(exchange, "true", "succes" ,200);
                } else {
                    // Nu s-au găsit rezultate pentru căutare, trimiteți un răspuns de eroare
                    sendResponse(exchange, "false", "No result found", 404);                    
                }
            } else {
                // Parametrii de interogare lipsesc, trimiteți un răspuns de eroare
                sendResponse(exchange, "false", "Missing query parameters", 400);

            }
        } else {
            // Metoda HTTP nu este GET, trimiteți un răspuns de eroare
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
    
    
    private List<Souvenir> performSearch(String query) {
        SouvenirDAO s = new SouvenirDAO();
        List<Souvenir> searchResults = new ArrayList<>();
        try {
        // Apelați metoda findByName() din SouvenirDAO pentru a obține suvenirurile în funcție de nume
        searchResults = (List<Souvenir>) s.findByName(query);
        } catch (SQLException ex) {
           System.out.println(ex);   
        }
        
    return searchResults;
    }

}
