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
    server.createContext("/api/mapcolors", (HttpHandler) new MapColorsHandler());  
    server.createContext("/api/table_users", (HttpHandler) new Table_UsersHandler());
    server.createContext("/api/souvenirg", (HttpHandler) new SouvenirHandlerGeneral());
    server.createContext("/api/souvenir", (HttpHandler) new SouvenirHandler());
    server.createContext("/api/table_souvenirs", (HttpHandler) new Table_SouvenirsHandler());
    server.createContext("/api/souvenirp", (HttpHandler) new SouvenirHandlerPersonalised());
   

    }

    public REST_API() {
    }
    
    

}
