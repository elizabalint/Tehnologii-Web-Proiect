/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.database;

import com.mycompany.objects.Souvenir;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mycompany.objects.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.sql.* ;


/**
 *
 * @author eliza
 */
public class SouvenirDAO {
    
   public static List<Souvenir> findByName(String name) throws SQLException {
        List<Souvenir> results = new ArrayList<>();
        try (Connection con = Connection_Database.getConnection();
         PreparedStatement stmt = con.prepareStatement("SELECT souvenirs.id, souvenirs.name, souvenirs.id_country, countries.name AS country, souvenirs.period, souvenirs.gender, souvenirs.age, souvenirs.popularity, souvenirs.buy FROM souvenirs JOIN countries ON countries.id = souvenirs.id_country WHERE souvenirs.name = ?")) {
        
        stmt.setString(1, name);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                    int id = rs.getInt("id");
                    int idCountry = rs.getInt("id_country");
                    String country=rs.getString("country");
                    String period = rs.getString("period");
                    String gender = rs.getString("gender");
                    String age = rs.getString("age");
                    int popularity = rs.getInt("popularity");
                    String buy = rs.getString("buy");

                    Souvenir souvenir = new Souvenir(id, name, idCountry, country, period, gender, age, popularity, buy);
                   results.add(souvenir);
                }
        }
    }
         return results;
    }
     
 public static List<Souvenir> findByIdCountry(Integer idCountry) throws SQLException {
    List<Souvenir> results = new ArrayList<>();
    try (Connection con = Connection_Database.getConnection();
         PreparedStatement stmt = con.prepareStatement("SELECT souvenirs.id, souvenirs.name, souvenirs.id_country, countries.name AS country, souvenirs.period, souvenirs.gender, souvenirs.age, souvenirs.popularity, souvenirs.buy FROM souvenirs JOIN countries ON countries.id = souvenirs.id_country WHERE souvenirs.id_country = ?")) {
        
        stmt.setInt(1, idCountry);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String country = rs.getString("country");
                String period = rs.getString("period");
                String gender = rs.getString("gender");
                String age = rs.getString("age");
                int popularity = rs.getInt("popularity");
                String buy = rs.getString("buy");

                Souvenir souvenir = new Souvenir(id, name, idCountry, country, period, gender, age, popularity, buy);
                results.add(souvenir);
            }
        }
    }
    return results;
}
     public static List<Souvenir> findByCountry(String country) throws SQLException {
    List<Souvenir> results = new ArrayList<>();
    try (Connection con = Connection_Database.getConnection();
         PreparedStatement stmt = con.prepareStatement("SELECT souvenirs.id, souvenirs.name, souvenirs.id_country, countries.name AS country, souvenirs.period, souvenirs.gender, souvenirs.age, souvenirs.popularity, souvenirs.buy FROM countries JOIN souvenirs ON countries.id = souvenirs.id_country WHERE countries.name = ?")) {
        
        stmt.setString(1, country);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int idCountry = rs.getInt("id_country");
                String period = rs.getString("period");
                String gender = rs.getString("gender");
                String age = rs.getString("age");
                int popularity = rs.getInt("popularity");
                String buy = rs.getString("buy");

                Souvenir souvenir = new Souvenir(id, name, idCountry, country, period, gender, age, popularity, buy);
                results.add(souvenir);
            }
        }
    }
    return results;
}
    public static List<Souvenir> findByPeriod(String period) throws SQLException {
        List<Souvenir> results = new ArrayList<>();
        try (Connection con = Connection_Database.getConnection();
         PreparedStatement stmt = con.prepareStatement("SELECT souvenirs.id, souvenirs.name, souvenirs.id_country, countries.name AS country, souvenirs.period, souvenirs.gender, souvenirs.age, souvenirs.popularity, souvenirs.buy FROM souvenirs JOIN countries ON countries.id = souvenirs.id_country WHERE souvenirs.period = ?")) {
        
        stmt.setString(1, period);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int idCountry = rs.getInt("id_country");
                    String country=rs.getString("country");
                    String gender = rs.getString("gender");
                    String age = rs.getString("age");
                    int popularity = rs.getInt("popularity");
                    String buy = rs.getString("buy");

                    Souvenir souvenir = new Souvenir(id, name, idCountry, country, period, gender, age, popularity, buy);
                   results.add(souvenir);
                }
        }
    }
         return results;
    }
    public static List<Souvenir> findByGender(String gender) throws SQLException {
        List<Souvenir> results = new ArrayList<>();
        try (Connection con = Connection_Database.getConnection();
         PreparedStatement stmt = con.prepareStatement("SELECT souvenirs.id, souvenirs.name, souvenirs.id_country, countries.name AS country, souvenirs.period, souvenirs.gender, souvenirs.age, souvenirs.popularity, souvenirs.buy FROM souvenirs JOIN countries ON countries.id = souvenirs.id_country WHERE souvenirs.gender = ?")) {
        
        stmt.setString(1, gender);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int idCountry = rs.getInt("id_country");
                    String country = rs.getString("country");
                    String period = rs.getString("period");
                    String age = rs.getString("age");
                    int popularity = rs.getInt("popularity");
                    String buy = rs.getString("buy");

                  Souvenir souvenir = new Souvenir(id, name, idCountry, country, period, gender, age, popularity, buy);
                  results.add(souvenir);
                }
    }}
        return results;
    }
    public static List<Souvenir> findByAge(String age) throws SQLException {
        List<Souvenir> results = new ArrayList<>();
        try (Connection con = Connection_Database.getConnection();
         PreparedStatement stmt = con.prepareStatement("SELECT souvenirs.id, souvenirs.name, souvenirs.id_country, countries.name AS country, souvenirs.period, souvenirs.gender, souvenirs.age, souvenirs.popularity, souvenirs.buy FROM souvenirs JOIN countries ON countries.id = souvenirs.id_country WHERE souvenirs.age = ?")) {
        
        stmt.setString(1, age);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int idCountry = rs.getInt("id_country");
                    String country = rs.getString("country");
                    String period = rs.getString("period");
                    String gender = rs.getString("gender");
                    int popularity = rs.getInt("popularity");
                    String buy = rs.getString("buy");

                Souvenir souvenir = new Souvenir(id, name, idCountry, country, period, gender, age, popularity, buy);
                results.add(souvenir);
                }
    }}
        return results;
    }
    public List<Souvenir> getAllSouvenirs() throws SQLException {
    List<Souvenir> souvenirs = new ArrayList<>();

    try (Connection con = Connection_Database.getConnection();
         PreparedStatement stmt = con.prepareStatement("SELECT souvenirs.id, souvenirs.name, souvenirs.id_country, countries.name AS country, souvenirs.period, souvenirs.gender, souvenirs.age, souvenirs.popularity, souvenirs.buy FROM souvenirs JOIN countries ON countries.id = souvenirs.id_country ")) {
     
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int idCountry = rs.getInt("id_country");
                String country = rs.getString("country");
                String period = rs.getString("period");
                String gender = rs.getString("gender");
                String age = rs.getString("age");
                int popularity = rs.getInt("popularity");
                String buy = rs.getString("buy");

                Souvenir souvenir = new Souvenir(id, name, idCountry, country ,period, gender, age, popularity, buy);
                souvenirs.add(souvenir);
            }
        }
    } System.out.println(souvenirs + "gkfjdhgfs");
    return souvenirs;
   
}
     public Souvenir SouvenirExists(String text) throws SQLException {
            String query = "SELECT souvenirs.id, souvenirs.name, souvenirs.id_country, countries.name AS country, souvenirs.period, souvenirs.gender, souvenirs.age, souvenirs.popularity, souvenirs.buy FROM souvenirs JOIN countries ON countries.id = souvenirs.id_country WHERE souvenirs.name ='" + text + "' or souvenirs.period='" + text + "' or souvenirs.gender='" + text + "' or souvenirs.age='" + text + "'" ;
                try (Connection con = Connection_Database.getConnection(); PreparedStatement pstmt = con.prepareStatement(query)) {
                 pstmt.setString(1, text);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                            Souvenir souvenir = new Souvenir(rs.getInt("id"), rs.getString("name"), rs.getInt("id_country"), rs.getString("country") ,rs.getString("period"), rs.getString("gender"), rs.getString("age"), rs.getInt("popularity"), rs.getString("buy"));
                       return souvenir;
            }
        }
    }
    return null;
}
     public List<Souvenir> performSearch(String query) throws IOException {
        List<Souvenir> searchResults = new ArrayList<>();
     try {
        // Search after name
            List<Souvenir> nameResults = SouvenirDAO.findByName(query);
        searchResults.addAll(nameResults);
        // Search after country
        List<Souvenir> countryResults = SouvenirDAO.findByCountry(query);
        searchResults.addAll(countryResults);
        // Search after period
        List<Souvenir> periodResults = SouvenirDAO.findByPeriod(query);
        searchResults.addAll(periodResults);
        
        // Search after gender
        List<Souvenir> genderResults = SouvenirDAO.findByGender(query);
        searchResults.addAll(genderResults);
        
         List<Souvenir> ageResults = SouvenirDAO.findByAge(query);
        searchResults.addAll(ageResults);
        
    } catch (SQLException e) {
        // Error occurred during the search
        e.printStackTrace();
        throw new IOException("Error occurred: " + e.getMessage());
    }
    
    return searchResults;
    }
}
