/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.database;

import com.mycompany.objects.Session;
import com.mycompany.objects.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ignat
 */
public class SessionsDAO {

    public void create(String id_session, int id_user) throws SQLException {

        try (Connection con = Connection_Database.getConnection()) {
            try (PreparedStatement pstmt = con.prepareStatement(
                    "insert into sessions (id_session,id_user) values (?,?)")) {
                pstmt.setString(1, id_session);
                pstmt.setInt(2, id_user);
                pstmt.executeUpdate();
            }
            con.commit();
            con.close();
        }

    }

    public Session findByUser(int id_user) throws SQLException {
        String query = "SELECT * FROM sessions WHERE id_user = ?";
        try (Connection con = Connection_Database.getConnection(); PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, id_user);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Session session = new Session(rs.getString(1), rs.getInt(2));
                    return session;
                }
            }
        }
        return null;
    }

    public Session findBySession(String id_session) throws SQLException {
        String query = "SELECT * FROM sessions WHERE id_session = ?";
        try (Connection con = Connection_Database.getConnection(); PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, id_session);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Session session = new Session(rs.getString(1), rs.getInt(2));
                    return session;
                }
            }
        }
        return null;
    }

    public Boolean SessionExists(String id_session) throws SQLException {
        String query = "SELECT COUNT(*) FROM sessions WHERE id_session = ?";
        try (Connection con = Connection_Database.getConnection(); PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, id_session);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) != 0;
                }
            }
        }
        return false;
    }

    public void delete(String id_session) throws SQLException {

        try (Connection con = Connection_Database.getConnection()) {
            try (PreparedStatement pstmt = con.prepareStatement(
                    "delete from sessions where id_session='" + id_session + "'")) {
                pstmt.executeUpdate();
            }
            con.commit();
            con.close();
        }
    }

    public void deleteByUser(int id_user) throws SQLException {

        try (Connection con = Connection_Database.getConnection()) {
            try (PreparedStatement pstmt = con.prepareStatement(
                    "delete from sessions where id_user='" + id_user + "'")) {
                pstmt.executeUpdate();
            }
            con.commit();
            con.close();
        }
    }

}
