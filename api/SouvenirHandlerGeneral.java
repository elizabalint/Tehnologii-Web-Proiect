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

            if(request.isEmpty())
            {
            try {
                //get session user
                
                List<Souvenir> all= new ArrayList<>();
                SouvenirDAO s = new SouvenirDAO();
                all=s.getAllSouvenirs();
                // Convert the JSON object to a string
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonData = objectMapper.writeValueAsString(all);

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
}       else{
            
               
                List<Souvenir> all= new ArrayList<>();
                SouvenirDAO s = new SouvenirDAO();
                
                 ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonrequest = objectMapper.readTree(request);
                String search = jsonrequest.get("search").asText();
                int equalsIndex = search.indexOf(":");
                String se = search.substring(equalsIndex + 1);
                
                all = s.performSearch(se);
                
                if(all.isEmpty())
                    hc.sendResponse(exchange, "false", "Doesn't exist", 200);
                else{
                // Convert the JSON object to a string
                ObjectMapper objectMappers = new ObjectMapper();
                String jsonData = objectMappers.writeValueAsString(all);

                // Set the response headers
                exchange.getResponseHeaders().set("Content-Type", "application/javascript");
                exchange.sendResponseHeaders(200, jsonData.getBytes().length);

                // Write the JSON data to the response
                OutputStream outputStream = exchange.getResponseBody();
                outputStream.write(jsonData.getBytes());
                outputStream.close();
        
            }
            }
        } else {
            hc.sendResponse(exchange, "false", "Invalid request method", 405);
        }
        
        hc.closeconnection();

    }

}

