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
    server.createContext("/api/session", (HttpHandler) new SessionHandler());
    server.createContext("/api/deletesession", (HttpHandler) new DeleteSessionHandler());   
    server.createContext("/api/addcountry", (HttpHandler) new AddCountryHandler());
    server.createContext("/api/removecountry", (HttpHandler) new RemoveCountryHandler()); 
    server.createContext("/api/numberofcountries", (HttpHandler) new NumberOfCountriesHandler());   
    }

    public REST_API() {
    }
    
    

}
