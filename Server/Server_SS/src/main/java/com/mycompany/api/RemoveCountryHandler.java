/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.database.CountriesDAO;
import com.mycompany.database.SessionsDAO;
import com.mycompany.database.VisitedCountriesDAO;
import com.mycompany.objects.Country;
import com.mycompany.objects.Session;
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
public class RemoveCountryHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // Set CORS headers
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        exchange.getResponseHeaders().set("Access-Control-Allow-Headers", "Content-Type");

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

            String country = jsonrequest.get("country").asText();
            String sessionx = jsonrequest.get("session").asText();
            int equalsIndex = sessionx.indexOf("=");
            String session = sessionx.substring(equalsIndex + 1);

            //delete in the database if the country exists
            try {
                CountriesDAO c = new CountriesDAO();
                if (c.CountryExists(country) == true) {
                    SessionsDAO s = new SessionsDAO();
                    VisitedCountriesDAO v = new VisitedCountriesDAO();
                    Session sesiune = s.findBySession(session);
                    Country tara = c.findByName(country);

                    //association already in database
                    if (v.AssociationExists(sesiune.getId_user(), tara.getId()) == true) {
                        v.deleteAssociation(sesiune.getId_user(), tara.getId());
                        sendResponse(exchange, "true", "Association deleted", 200);

                    } //otherwise error
                    else {
                    sendResponse(exchange, "false", "Country is not marked as visited", 403);

                    }

                } else {
                    sendResponse(exchange, "false", "Invalid country", 404);
                }

            } catch (SQLException ex) {
                System.out.println(ex);
            }

        } else {
            sendResponse(exchange, "false", "Invalid request method", 405);
        }

        CloseConnection cc = new CloseConnection();
        cc.close();

    }

    private void sendResponse(HttpExchange exchange, String token, String message, int code) throws IOException {

        String response = "{ \"success\": " + token + ", \"message\": \"" + message + "\" }";
        exchange.sendResponseHeaders(code, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
