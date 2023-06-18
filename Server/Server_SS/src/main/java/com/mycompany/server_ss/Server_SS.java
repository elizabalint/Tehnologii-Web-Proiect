/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.server_ss;

import java.net.*;
import java.io.*;
import com.mycompany.api.*;
import com.mycompany.database.*;
import java.sql.SQLException;
import com.mycompany.objects.*;
import com.sun.net.httpserver.HttpServer;

/**
 *
 * @author ignat
 */
public class Server_SS {

    public static void main(String[] args) throws IOException, SQLException {
                    
        //connect with the templates using the API
        HttpServer server = HttpServer.create(new InetSocketAddress(8081), 0);      
        REST_API api=new REST_API();
        api.CreateContexts(server);
        
        //start the server
        server.start();
        System.out.println("Server started on port 8081");
      
    }
}
