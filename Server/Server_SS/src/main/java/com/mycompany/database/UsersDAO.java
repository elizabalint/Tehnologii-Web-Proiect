/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ignat
 */
public class UsersDAO {
    
     public void create(String username, String password, Connection con) throws SQLException {
      
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into users (username,password) values (?,?)")) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
        }

    }

    public Integer findByUsername(String username, Connection con) throws SQLException {
        
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(
                "select id from users where username='" + username+ "'")) {
            return rs.next() ? rs.getInt(1) : null;
        }
    }


    public String findPassword(String username, Connection con) throws SQLException {
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(
                "select password from users where username='" + username+ "'")) {

             return rs.next() ? rs.getString(1) : null;

        }
    }

    public void delete(String username, Connection con) throws SQLException {
        
        try (PreparedStatement pstmt = con.prepareStatement(
                "delete from users where username='" + username + "'")) {
            pstmt.executeUpdate();
        }
    }
}
