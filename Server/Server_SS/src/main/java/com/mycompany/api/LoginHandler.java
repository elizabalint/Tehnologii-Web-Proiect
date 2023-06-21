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

        // Set CORS headers
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        exchange.getResponseHeaders().set("Access-Control-Allow-Headers", "Content-Type");

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

            //System.out.println(username + " " + password + " " + remembered);

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
                    
                    sendResponse(exchange, "true",sesiune , 200);

                    }
                    //incorrect password
                    else sendResponse(exchange, "false", "Wrong password", 403);
                    
                } else { //user does not exist
                    sendResponse(exchange, "false", "User does not exist", 404);
                }
            } catch (SQLException ex) {
               System.out.println(ex);
            }

        } else {
            sendResponse(exchange, "false", "Invalid request method", 405);
        }
        
        CloseConnection cc= new CloseConnection();
        cc.close();
        
    }

    private void sendResponse(HttpExchange exchange, String token, String message, int code) throws IOException {

        String response = "{ \"success\": " + token + ", \"message\": \"" + message + "\" }";
        exchange.sendResponseHeaders(code, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
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
