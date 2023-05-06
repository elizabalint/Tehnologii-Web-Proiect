/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author ignat
 */
public class CountryDAO {
    
    public void create(String short_name, String name, String continent, String region, Connection con) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into countries (short_name,name,continent,region) values (?,?,?,?)")) {
            pstmt.setString(1, short_name);
            pstmt.setString(2, name);
            pstmt.setString(3, continent);
            pstmt.setString(4, region);
            pstmt.executeUpdate();
        }

    }
    
    public void delete(String name, Connection con) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(
                "delete from countries where name='" + name + "'")) {
            pstmt.executeUpdate();
        }
    }
}
