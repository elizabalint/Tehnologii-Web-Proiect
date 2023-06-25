/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api;

import com.mycompany.database.SessionsDAO;
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
public class DeleteSessionHandler implements HttpHandler {

    public DeleteSessionHandler() {
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

            //extract the session id
            String request = requestBody.toString();
            int equalsIndex = request.indexOf("=");
            String id_session = request.substring(equalsIndex + 1);
            // System.out.println(id_session);

            //Delete the sessions
            SessionsDAO s = new SessionsDAO();
            try {
                s.delete(id_session);
                hc.sendResponse(exchange, "true", "Deleted succesfully", 200);

            } catch (SQLException ex) {
                System.out.println(ex);
            }

        } else {
            hc.sendResponse(exchange, "false", "Invalid request method", 405);
        }

        hc.closeconnection();
    }

}
