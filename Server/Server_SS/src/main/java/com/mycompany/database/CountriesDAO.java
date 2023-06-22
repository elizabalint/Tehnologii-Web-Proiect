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

        try (Connection con = Connection_Database.getConnection()) {
            try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(
                    "select * from countries where id='" + id + "'")) {

                Country country = new Country(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
               
                return country;

            }
        }
    }
    
  
    public Country findByName(String name) throws SQLException {

        try (Connection con = Connection_Database.getConnection()) {
            try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(
                    "select * from countries where name='" + name + "'")) {

                Country country = new Country(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
               
                return country;

            }
        }
    }
    
     public Boolean CountryExists(String name) throws SQLException {

        try (Connection con = Connection_Database.getConnection()) {

            try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(
                    "select COUNT(*) from countries where name='" + name + "'")) {

                
                return rs.getInt(1) != 0;

            }
        }
    }
    
  
}
