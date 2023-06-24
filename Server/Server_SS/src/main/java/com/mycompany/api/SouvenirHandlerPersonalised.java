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

            //ia sesiunea userului conectat
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonrequest = objectMapper.readTree(request);
            System.out.println("sdfgh");
            

            String sessionx = jsonrequest.get("session").asText();
            int equalsIndex = sessionx.indexOf("=");
            String session = sessionx.substring(equalsIndex + 1);
            System.out.println(session);
            System.out.println("brvecd");
            try{
                //get session user
                SessionsDAO s = new SessionsDAO();
                Session sesiune = s.findBySession(session);
                System.out.println("bgvfdc");
                
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
            
        } else {
            hc.sendResponse(exchange, "false", "Invalid request method", 405);
        }
        System.out.println("erca");
        hc.closeconnection();

    }

    }
    

