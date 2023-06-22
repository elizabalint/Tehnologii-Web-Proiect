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
import java.util.ArrayList;
import java.util.List;
import java.sql.* ;


/**
 *
 * @author eliza
 */
public class SouvenirDAO {
    public void create (int id, String name, int id_country, String period, String gender, int age, int popularity, String buy) throws SQLException
    {
        try (Connection con = Connection_Database.getConnection()) {
            try (PreparedStatement pstmt = con.prepareStatement(
                 "insert into souvenirs (id, name, id_country, period, gender, age, popularity, buy) values (?,?,?,?,?,?,?,?,?)")) {
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setInt(3, id_country);
            pstmt.setString(4, period);
            pstmt.setString(5, gender);
            pstmt.setInt(6, age);
            pstmt.setInt(7, popularity);
            pstmt.setString(8, buy);
            pstmt.executeUpdate();
            }
            con.commit();
            con.close();
        }
      }
    
    public static List<Souvenir> findByName(String name) throws SQLException {
    List<Souvenir> results = new ArrayList<>();
    if (name != null) {
    try (Connection con = Connection_Database.getConnection();
         Statement stmt = con.createStatement();
            
         ResultSet rs = stmt.executeQuery("SELECT souvenirs.id, souvenirs.name, souvenirs.id_country, countries.name AS country, souvenirs.period, souvenirs.gender, souvenirs.age, souvenirs.popularity, souvenirs.buy  FROM souvenirs JOIN countries ON countries.id = souvenirs.id_country  WHERE souvenirs.name = '" + name + "'")) {
                
          
        while (rs.next()) {
                int id = rs.getInt("id");
                int idCountry = rs.getInt("id_country");
                String country=rs.getString("country");
                String period = rs.getString("period");
                String gender = rs.getString("gender");
                int age = rs.getInt("age");
                int popularity = rs.getInt("popularity");
                String buy = rs.getString("buy");

                Souvenir souvenir = new Souvenir(id, name, idCountry, country,  period, gender, age, popularity, buy);
                results.add(souvenir);
            }
        con.close();
    }
    }
    return results;
}
     public static List<Souvenir> findByCountry(String country) throws SQLException {
        List<Souvenir> results = new ArrayList<>();
        try (Connection con = Connection_Database.getConnection();
             Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT souvenirs.id, souvenirs.name, souvenirs.id_country, countries.name AS country, souvenirs.period, souvenirs.gender, souvenirs.age, souvenirs.popularity, souvenirs.buy  FROM countries JOIN souvenirs ON  souvenirs.id_country =countries.id  WHERE country.name = '" + country + "'")) {
            {
            
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
         return results;
    }
     }
    public static List<Souvenir> findByPeriod(String period) throws SQLException {
        List<Souvenir> results = new ArrayList<>();
        try (Connection con = Connection_Database.getConnection();
             Statement stmt = con.createStatement();
         
         ResultSet rs = stmt.executeQuery("SELECT souvenirs.id, souvenirs.name, souvenirs.id_country, countries.name AS country, souvenirs.period, souvenirs.gender, souvenirs.age, souvenirs.popularity, souvenirs.buy  FROM souvenirs JOIN countries ON countries.id = souvenirs.id_country  WHERE souvenirs.period = '" + period + "'")) {
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
         return results;
    }
    public static List<Souvenir> findByGender(String gender) throws SQLException {
        List<Souvenir> results = new ArrayList<>();
        try (Connection con = Connection_Database.getConnection();
             Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT souvenirs.id, souvenirs.name, souvenirs.id_country, countries.name AS country, souvenirs.period, souvenirs.gender, souvenirs.age, souvenirs.popularity, souvenirs.buy  FROM souvenirs JOIN countries ON countries.id = souvenirs.id_country  WHERE souvenirs.gender = '" + gender + "'")) {
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
    }
        return results;
    }
    public static List<Souvenir> findByAge(int age) throws SQLException {
        List<Souvenir> results = new ArrayList<>();
        try (Connection con = Connection_Database.getConnection();
             Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT souvenirs.id, souvenirs.name, souvenirs.id_country, countries.name AS country, souvenirs.period, souvenirs.gender, souvenirs.age, souvenirs.popularity, souvenirs.buy  FROM souvenirs JOIN countries ON countries.id = souvenirs.id_country  WHERE souvenirs.age = '" + age + "'")) {
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
    }
        return results;
    }
    public static List<Souvenir> getAllSouvenirs() throws SQLException {
    List<Souvenir> souvenirs = new ArrayList<>();

    try (Connection con = Connection_Database.getConnection()) {
        try (Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT souvenirs.id, souvenirs.name, souvenirs.id_country, countries.name AS country, souvenirs.period, souvenirs.gender, souvenirs.age, souvenirs.popularity, souvenirs.buy  FROM souvenirs JOIN countries ON countries.id = souvenirs.id_country")) {

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
    }

    return souvenirs;
}
}
