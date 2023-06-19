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

/**
 *
 * @author eliza
 */
public class SouvenirDAO {
    public void create (int id, String name, int id_country, String period, String gender, int age, int popularity, String buy) throws SQLException
    {
        try (Connection con = Connection_Database.getConnection()) {
            try (PreparedStatement pstmt = con.prepareStatement(
                 "insert into Souvenir (id, name, id_country, period, gender, age, popularity, buy) values (?,?,?,?,?,?,?,?)")) {
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
    
    public Souvenir findByName(String name) throws SQLException {
        try (Connection con = Connection_Database.getConnection()) {
            try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(
                    "select * from souvenir where name='" + name + "'")) {

                Souvenir souvenir = new Souvenir (rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getString(8));
                con.close();
                return souvenir;

            }
        }

    }
    public Souvenir findByPeriod(String period) throws SQLException {
        try (Connection con = Connection_Database.getConnection()) {
            try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(
                    "select * from souvenir where period='" + period + "'")) {
                Souvenir souvenir = new Souvenir (rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getString(8));
                con.close();
                return souvenir;

            }
        }

    }
    public Souvenir findByGender(String gender) throws SQLException {
        try (Connection con = Connection_Database.getConnection()) {
            try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(
                    "select * from souvenir where gender='" + gender + "'")) {
                Souvenir souvenir = new Souvenir (rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getString(8));
                con.close();
                return souvenir;

            }
        }
    }
    public Souvenir findByAge(String age) throws SQLException {
        try (Connection con = Connection_Database.getConnection()) {
            try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(
                    "select * from souvenir where age='" + age + "'")) {
                Souvenir souvenir = new Souvenir (rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getString(8));
                con.close();
                return souvenir;
            }
        }
    }
}
