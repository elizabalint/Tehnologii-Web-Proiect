/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api;

import com.mycompany.database.Connection_Database;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author ignat
 */
public class CloseConnection {
    
    public void close(){
    Connection connection;
        try {
            connection = Connection_Database.getConnection();
            if (connection != null) connection.close();
       
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public CloseConnection() {
    }
    
    
}
