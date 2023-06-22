/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api;

import com.mycompany.database.SessionsDAO;
import com.mycompany.database.UsersDAO;
import com.mycompany.objects.User;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ignat
 */
public class LoginHandler implements HttpHandler {

    public LoginHandler() {
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

            StringBuilder usernameBody = new StringBuilder();
            StringBuilder passwordBody = new StringBuilder();
            StringBuilder rememberedBody = new StringBuilder();
            int line_number = 0;

            //Extract the data
            while ((line = br.readLine()) != null) {
                line_number++;
                //requestBody.append(line);
                if (line_number == 4) {
                    usernameBody.append(line);
                }
                if (line_number == 8) {
                    passwordBody.append(line);
                }
                if (line_number == 12) {
                    rememberedBody.append(line);
                }

            }
            br.close();

            String username = usernameBody.toString();
            String password = passwordBody.toString();
            if (rememberedBody.isEmpty()) {
                rememberedBody.append("off");
            }
            String remembered = rememberedBody.toString();
         

            //Verify the login
            var u = new UsersDAO();

            try {
                if (u.UsernameExists(username) == true) {
                    User user=u.findByUsername(username);
                    
                    //correct password
                    if(password.equals(user.getPassword())) {
                        
                    //create session
                    deleteSession(user.getId());
                    String sesiune=createSession(user.getId());
                    
                    if("true".equals(user.getAdmin())) hc.sendAdminResponse(exchange, "true",sesiune,"true" , 200);
                    else hc.sendResponse(exchange, "true",sesiune , 200);

                    }
                    //incorrect password
                    else hc.sendResponse(exchange, "false", "Wrong password", 403);
                    
                } else { //user does not exist
                    hc.sendResponse(exchange, "false", "User does not exist", 404);
                }
            } catch (SQLException ex) {
               System.out.println(ex);
            }

        } else {
            hc.sendResponse(exchange, "false", "Invalid request method", 405);
        }
        
        hc.closeconnection();
    }

  
    
    private String createSession(int id_user) {
        
       String id_session= UUID.randomUUID().toString();
       SessionsDAO s = new SessionsDAO();
        try {
            s.create(id_session, id_user);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    return id_session;
    }
    
    private void deleteSession(int id_user){
     SessionsDAO s = new SessionsDAO();
        try {
            s.deleteByUser(id_user);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

}
