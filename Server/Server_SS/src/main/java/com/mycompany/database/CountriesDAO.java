/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.database;

import com.mycompany.objects.Country;
import com.mycompany.objects.Session;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ignat
 */
public class CountriesDAO {
   public Country findById(Integer id) throws SQLException {
    String query = "SELECT * FROM countries WHERE id = ?";
    try (Connection con = Connection_Database.getConnection(); PreparedStatement pstmt = con.prepareStatement(query)) {
        pstmt.setInt(1, id);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                Country country = new Country(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                return country;
            }
        }
    }
    return null;
}

public Country findByName(String name) throws SQLException {
    String query = "SELECT * FROM countries WHERE name = ?";
    try (Connection con = Connection_Database.getConnection(); PreparedStatement pstmt = con.prepareStatement(query)) {
        pstmt.setString(1, name);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                Country country = new Country(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                return country;
            }
        }
    }
    return null;
}

public Boolean CountryExists(String name) throws SQLException {
    String query = "SELECT COUNT(*) FROM countries WHERE name = ?";
    try (Connection con = Connection_Database.getConnection(); PreparedStatement pstmt = con.prepareStatement(query)) {
        pstmt.setString(1, name);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1) != 0;
            }
        }
    }
    return false;
}

    
  
}
