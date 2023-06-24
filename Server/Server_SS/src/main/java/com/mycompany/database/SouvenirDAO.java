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
                    int age = rs.getInt("age");
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
                int age = rs.getInt("age");
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
                int age = rs.getInt("age");
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
                    int age = rs.getInt("age");
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
                    int age = rs.getInt("age");
                    int popularity = rs.getInt("popularity");
                    String buy = rs.getString("buy");

                  Souvenir souvenir = new Souvenir(id, name, idCountry, country, period, gender, age, popularity, buy);
                  results.add(souvenir);
                }
    }}
        return results;
    }
    public static List<Souvenir> findByAge(int age) throws SQLException {
        List<Souvenir> results = new ArrayList<>();
        try (Connection con = Connection_Database.getConnection();
         PreparedStatement stmt = con.prepareStatement("SELECT souvenirs.id, souvenirs.name, souvenirs.id_country, countries.name AS country, souvenirs.period, souvenirs.gender, souvenirs.age, souvenirs.popularity, souvenirs.buy FROM souvenirs JOIN countries ON countries.id = souvenirs.id_country WHERE souvenirs.age = ?")) {
        
        stmt.setInt(1, age);
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
                int age = rs.getInt("age");
                int popularity = rs.getInt("popularity");
                String buy = rs.getString("buy");

                Souvenir souvenir = new Souvenir(id, name, idCountry, country ,period, gender, age, popularity, buy);
                souvenirs.add(souvenir);
            }
        }
    } System.out.println(souvenirs + "gkfjdhgfs");
    return souvenirs;
   
}
     public Boolean SouvenirExists(String name) throws SQLException {

        try (Connection con = Connection_Database.getConnection()) {

            try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(
                    "select COUNT(*) from souvenirs where name='" + name + "'")) {

                
                return rs.getInt(1) != 0;

            }
        }
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
        
        try {
            // Search after age (converting the query to int)
            int age = Integer.parseInt(query);
            List<Souvenir> ageResults = SouvenirDAO.findByAge(age);
            searchResults.addAll(ageResults);
        } catch (NumberFormatException e) {
            // Ignore the exception if the query cannot be parsed as an integer
        }
        
        if (searchResults.isEmpty()) {
            // No search results found
            throw new SQLException("No result found");
        }
    } catch (SQLException e) {
        // Error occurred during the search
        e.printStackTrace();
        throw new IOException("Error occurred: " + e.getMessage());
    }
    
    return searchResults;
    }
}
