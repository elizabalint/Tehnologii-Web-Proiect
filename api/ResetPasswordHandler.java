/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api;

import com.mycompany.database.UsersDAO;
import com.mycompany.objects.User;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

/**
 *
 * @author ignat
 */
public class ResetPasswordHandler implements HttpHandler {

    public ResetPasswordHandler() {

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

            StringBuilder usernameBody = new StringBuilder();
            StringBuilder passwordBody = new StringBuilder();
            StringBuilder password_confirmBody = new StringBuilder();
            int line_number = 0;

            //Extract the data
            while ((line = br.readLine()) != null) {
                line_number++;

                if (line_number == 4) {
                    usernameBody.append(line);
                }
                if (line_number == 8) {
                    passwordBody.append(line);
                }
                if (line_number == 12) {
                    password_confirmBody.append(line);
                }

            }
            br.close();

            String username = usernameBody.toString();
            String password = passwordBody.toString();
            String password_confirm = password_confirmBody.toString();

            try {
                UsersDAO u = new UsersDAO();
                
                if (username.length() < 6 || username.length() > 12 || password.length() < 6 || password.length() > 12) {

                hc.sendResponse(exchange, "false", "Invalid credentials", 401);}
              
                else{
                //Check if username exists
                if (u.UsernameExists(username) == true) {

                    if (!password.equals(password_confirm)) {
                        hc.sendResponse(exchange, "false", "Passwords do not match", 401);
                    }
                    
                    else {
                    //create request to reset password
                    u.updaterequest(username, password);
                                      
                    hc.sendResponse(exchange, "true", "Request has been sent to the admins", 200);
                    }
                

                } else {
                    hc.sendResponse(exchange, "false", "Invalid username", 401);
                }

            }
            }catch (SQLException ex) {
                System.out.println(ex);
            }

        } else {
            hc.sendResponse(exchange, "false", "Invalid request method", 405);
        }

        hc.closeconnection();

    }
}
