/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.database;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp.BasicDataSource;

/**
 *
 * @author ignat
 */
public class Connection_Database {

    private final static String DB_URL = "jdbc:sqlite:../../Database/Database.db";
    private final static String USER = "username";
    private final static String PASSWORD = "password";
    private static BasicDataSource basicDS;

    public static Connection getConnection() throws SQLException {
        if(basicDS == null){
            basicDS = new BasicDataSource();
            basicDS.setUrl(DB_URL);
            basicDS.setUsername(USER);
            basicDS.setPassword(PASSWORD);
            basicDS.setInitialSize(5);
            basicDS.setMaxActive(10);
            basicDS.setDefaultAutoCommit(false);
        }
        return basicDS.getConnection();
    }
    
    private Connection_Database(){}
  

}
