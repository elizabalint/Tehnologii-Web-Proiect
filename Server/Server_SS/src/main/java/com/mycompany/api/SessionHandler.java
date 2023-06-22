/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api;

import com.mycompany.database.SessionsDAO;
import com.mycompany.objects.Session;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ignat
 */
public class SessionHandler implements HttpHandler {

    public SessionHandler() {
    }

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

            //extract the session id
            String request = requestBody.toString();
            int equalsIndex = request.indexOf("=");
            String id_session = request.substring(equalsIndex + 1);
            // System.out.println(id_session);
            
            
            //Verify and send response if there is or not a session with that name
            SessionsDAO s=new SessionsDAO();
            
            try {
                if(s.SessionExists(id_session)==true){
                //Session sesiune=s.findBySession(id_session);
                hc.sendResponse(exchange, "true","Session exists" , 200);
                }
                else{
                hc.sendResponse(exchange, "false", "Session does not exist", 404);
                }
                
                
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            
            
            
        }else {
            hc.sendResponse(exchange, "false", "Invalid request method", 405);
        }
     
        hc.closeconnection();
    }
    
  

}
