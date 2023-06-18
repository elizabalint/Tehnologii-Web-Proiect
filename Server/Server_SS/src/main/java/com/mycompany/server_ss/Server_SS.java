/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.server_ss;

import java.net.*;
import java.io.*;
import com.mycompany.api.*;
import com.mycompany.database.*;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author ignat
 */
public class Server_SS {

    public static void main(String[] args) throws IOException, SQLException {
     
        Connection_Database.createConnection();
        Connection con=Connection_Database.getConnection();
        
       
        REST_API.main(null);
        
        var u = new UsersDAO();
        //u.create("Rovin", "parola_Rovin", con);
       //System.out.print( u.findPassword("Vlad", con));
       
        
        con.commit();
        con.close();
        
    }
}
