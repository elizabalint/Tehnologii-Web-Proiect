/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mycompany.database.CountriesDAO;
import com.mycompany.database.SessionsDAO;
import com.mycompany.database.SouvenirDAO;
import com.mycompany.database.VisitedCountriesDAO;
import com.mycompany.objects.Country;
import com.mycompany.objects.Session;
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
public class SouvenirHandlerPersonalised implements HttpHandler{

    public SouvenirHandlerPersonalised() {
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
            
            String request = requestBody.toString();
            System.out.println(request);
            //take teh curent request
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonrequest = objectMapper.readTree(request);
            
            JsonNode searches = objectMapper.readTree(request);
            //verify if there is a search request. if exists, then extract the search
            Boolean bool = searches.has("search");
            if(bool){
            String search =searches.get("search").asText();
            int equalsIndexs = search.indexOf(":");
            String se = search.substring(equalsIndexs + 1);
            System.out.println(search);
            }
            //take the curent session
            String sessionx = jsonrequest.get("session").asText();
            int equalsIndex = sessionx.indexOf("=");
            String session = sessionx.substring(equalsIndex + 1);
           System.out.println(session);
            //if no search, get all souvenirs from all of the countries the curent user visited
            if(!bool)
            {
            try{
                //get session user
                SessionsDAO s = new SessionsDAO();
                Session sesiune = s.findBySession(session);
                
                List<Souvenir> souvenirs = new ArrayList<>();
                VisitedCountriesDAO visited = new VisitedCountriesDAO();
                List<Integer> vis = new ArrayList<>();
                //countries user visited
                vis = visited.AllVisitedCountries(sesiune.getId_user());
                SouvenirDAO so = new SouvenirDAO();
                List<Souvenir> allSouvenirs = new ArrayList<>();
                for(Integer i : vis)
                {
                    List<Souvenir> countrySouvenir = new ArrayList<>();
                    countrySouvenir = so.findByIdCountry(i);
                    allSouvenirs.addAll(countrySouvenir);
                }
                
                //Send data to the js
                // Create a JSON object to hold the variable assignments
                ObjectNode rootNode = objectMapper.createObjectNode();
                rootNode.put("allSouvenirsUser", objectMapper.writeValueAsString(allSouvenirs));

                // Convert the JSON object to a string
                String jsonData = objectMapper.writeValueAsString(allSouvenirs);

                // Set the response headers
                exchange.getResponseHeaders().set("Content-Type", "application/javascript");
                exchange.sendResponseHeaders(200, jsonData.getBytes().length);

                // Write the JSON data to the response
                OutputStream outputStream = exchange.getResponseBody();
                outputStream.write(jsonData.getBytes());
                outputStream.close();
        
            }catch (SQLException ex) {
                System.out.println(ex);
            }
     }
            else{//get the couvenirs searched
                try{
            //get session user
                List<Souvenir> all= new ArrayList<>();
                SouvenirDAO souve = new SouvenirDAO();
                SessionsDAO s = new SessionsDAO();
                Session sesiune = s.findBySession(session);
                
                JsonNode a = objectMapper.readTree(request);
                System.out.println(request);
                String b = a.get("search").asText();
                int c = b.indexOf(":");
                String d = b.substring(c + 1);
                //countries user visited
                List<Souvenir> souvenirs = new ArrayList<>();
                VisitedCountriesDAO visited = new VisitedCountriesDAO();
                List<Integer> vis = new ArrayList<>();
                
                vis = visited.AllVisitedCountries(sesiune.getId_user());
                SouvenirDAO so = new SouvenirDAO();
                List<Souvenir> allSouvenirs = new ArrayList<>();
                
                
                all = souve.performSearch(d);
                System.out.println(d);
                
                //all souvenirs from the countries you have been
                
                    List<Souvenir> countrySouvenir = new ArrayList<>();
                    //all the souvenirs found afer search
                    countrySouvenir = so.performSearch(d);
                    
                    for(Integer i :vis)
                        for( Souvenir j : countrySouvenir)
                              if( i == j.getId_country())
                                    allSouvenirs.add(j);
                
                System.out.println(vis);
                //Send data to the js
                // Create a JSON object to hold the variable assignments
                ObjectNode rootNode = objectMapper.createObjectNode();
                rootNode.put("allSouvenirsUser", objectMapper.writeValueAsString(allSouvenirs));
               
                // Convert the JSON object to a string
                String jsonData = objectMapper.writeValueAsString(allSouvenirs);

                // Set the response headers
                exchange.getResponseHeaders().set("Content-Type", "application/javascript");
                exchange.sendResponseHeaders(200, jsonData.getBytes().length);

                // Write the JSON data to the response
                OutputStream outputStream = exchange.getResponseBody();
                outputStream.write(jsonData.getBytes());
                outputStream.close();
                }
                catch (SQLException ex) {
                System.out.println(ex);
            }
            }
        } else {
            hc.sendResponse(exchange, "false", "Invalid request method", 405);
        }
        System.out.println("erca");
        hc.closeconnection();

    }

    }
    

