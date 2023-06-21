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
public class VisitedCountriesDAO {

    public void create(int id_user, int id_country) throws SQLException {

        try (Connection con = Connection_Database.getConnection()) {
            try (PreparedStatement pstmt = con.prepareStatement(
                    "insert into visited_user_countries (id_user,id_country) values (?,?)")) {
                pstmt.setInt(1, id_user);
                pstmt.setInt(2, id_country);
                pstmt.executeUpdate();
            }
            con.commit();
            con.close();
        }

    }

    public boolean AssociationExists(int id_user, int id_country) throws SQLException {
        try (Connection con = Connection_Database.getConnection(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM visited_user_countries WHERE id_user='" + id_user + "' AND id_country='" + id_country + "'")) {

            if (rs.next()) {
                return rs.getInt(1) != 0;
            }
        }

        return false;
    }
    
     public Integer NumberOfAssociations(int id_user) throws SQLException {

        try (Connection con = Connection_Database.getConnection()) {

            try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(
                    "select COUNT(*) from visited_user_countries where id_user='" + id_user + "'")) {


                return rs.getInt(1) ;

            }
        }
    }
    
    public void deleteAssociation(int id_user, int id_country)  throws SQLException {

        try (Connection con = Connection_Database.getConnection()) {
            try (PreparedStatement pstmt = con.prepareStatement(
                    "delete from visited_user_countries where id_user='" + id_user + "' AND id_country='" + id_country + "'")) {
                pstmt.executeUpdate();
            }
            con.commit();
            con.close();
        }
    }

    public void deleteAll(int id_user) throws SQLException {

        try (Connection con = Connection_Database.getConnection()) {
            try (PreparedStatement pstmt = con.prepareStatement(
                    "delete from visited_user_countries where id_user='" + id_user + "'")) {
                pstmt.executeUpdate();
            }
            con.commit();
            con.close();
        }
    }

}
