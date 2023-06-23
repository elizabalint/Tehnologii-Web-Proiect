/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.database.CountriesDAO;
import com.mycompany.database.SessionsDAO;
import com.mycompany.database.SouvenirDAO;
import com.mycompany.objects.Country;
import com.mycompany.objects.Session;
import com.mycompany.objects.Souvenir;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

/**
 *
 * @author eliza
 */
public class SearchBar implements HttpHandler{
      @Override
    public void handle(HttpExchange exchange) throws IOException {
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

            String souvenir = jsonrequest.get("souvenir").asText();
            String sessionx = jsonrequest.get("session").asText();
            int equalsIndex = sessionx.indexOf("=");
            String session = sessionx.substring(equalsIndex + 1);

            //System.out.println(country+ " "+ session);
            //add to the database if the country exists
            try {
                SouvenirDAO s = new SouvenirDAO();
                if (s.SouvenirExists(souvenir) == true) {
                    SessionsDAO ses = new SessionsDAO();
                    Session sesiune = ses.findBySession(session);

                    Souvenir souv = s.findByName(souvenir);
                    //association already in database
                    if (s.SouvenirExists(souv.getName())) {
                        System.out.println(souv.getName());
   
                        hc.sendResponse(exchange, "false", "Exista", 200);

                    } 

                } else {
                    hc.sendResponse(exchange, "false", "Invalid country", 200);
                }

            } catch (SQLException ex) {
                System.out.println(ex);
            }

        }       
        else {
            hc.sendResponse(exchange, "false", "Invalid request method", 405);
        }
        
        
        hc.closeconnection();
        
        
}}
