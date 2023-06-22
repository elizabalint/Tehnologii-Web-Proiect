/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api;

import com.mycompany.database.Connection_Database;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author ignat
 */
public class HandlerCommander {

    public void closeconnection() {
        Connection connection;
        try {
            connection = Connection_Database.getConnection();
            if (connection != null) {
                connection.close();
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void sendResponse(HttpExchange exchange, String token, String message, int code) throws IOException {

        String response = "{ \"success\": " + token + ", \"message\": \"" + message + "\" }";
        exchange.sendResponseHeaders(code, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
    
     public void sendAdminResponse(HttpExchange exchange, String token, String message,String admin,int code) throws IOException {

        String response = "{ \"success\": " + token + ", \"message\": \"" + message + "\", \"admin\": " + admin + " }";
        exchange.sendResponseHeaders(code, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    public void setCORS(HttpExchange exchange) {
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        exchange.getResponseHeaders().set("Access-Control-Allow-Headers", "Content-Type");
    }

    public HandlerCommander() {
    }

}
