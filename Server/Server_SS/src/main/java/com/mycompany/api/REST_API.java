/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
   

public class REST_API {

    
    public void CreateContexts(HttpServer server){
    server.createContext("/api/login", (HttpHandler) new LoginHandler());
    server.createContext("/api/signup", (HttpHandler) new SignupHandler());
    server.createContext("/api/souvenir", (HttpHandler) new SouvenirHandler());
    }

    public REST_API() {
    }
    
    

}
