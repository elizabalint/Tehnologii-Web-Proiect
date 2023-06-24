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
import java.util.ArrayList;
import java.util.List;

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
        String query = "SELECT COUNT(*) FROM visited_user_countries WHERE id_user = ? AND id_country = ?";
        try (Connection con = Connection_Database.getConnection(); PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, id_user);
            pstmt.setInt(2, id_country);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) != 0;
                }
            }
        }
        return false;
    }

    public Integer NumberOfAssociations(int id_user) throws SQLException {
        String query = "SELECT COUNT(*) FROM visited_user_countries WHERE id_user = ?";
        try (Connection con = Connection_Database.getConnection(); PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, id_user);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return 0;
    }

    public List<Integer> AllVisitedCountries(int id_user) throws SQLException {
        List<Integer> idCountries = new ArrayList<>();
        String query = "SELECT id_country FROM visited_user_countries WHERE id_user = ?";
        try (Connection con = Connection_Database.getConnection(); PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, id_user);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int idCountry = rs.getInt("id_country");
                    idCountries.add(idCountry);
                }
            }
        }
        return idCountries;
    }

    public void deleteAssociation(int id_user, int id_country) throws SQLException {

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
