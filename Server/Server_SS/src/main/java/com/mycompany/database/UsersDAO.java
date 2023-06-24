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
import com.mycompany.objects.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO {

    public void create(String username, String password) throws SQLException {

        try (Connection con = Connection_Database.getConnection()) {
            try (PreparedStatement pstmt = con.prepareStatement(
                    "insert into users (username,password) values (?,?)")) {
                pstmt.setString(1, username);
                pstmt.setString(2, password);
                pstmt.executeUpdate();
            }
            con.commit();
            con.close();
        }

    }

    public void updaterequest(String username, String password) throws SQLException {

        try (Connection con = Connection_Database.getConnection()) {

            try (PreparedStatement pstmt = con.prepareStatement(
                    "UPDATE users SET new_password = ?, reset_password = 'true' WHERE username = ?")) {
                pstmt.setString(1, password);
                pstmt.setString(2, username);
                pstmt.executeUpdate();
            }
            con.commit();
            con.close();

        }

    }
    
        public void updatepassword(int id,String password) throws SQLException {

        try (Connection con = Connection_Database.getConnection()) {

            try (PreparedStatement pstmt = con.prepareStatement(
                    "UPDATE users SET password = ?, reset_password = 'false' WHERE id = ?")) {
                pstmt.setString(1, password);
                pstmt.setInt(2, id);
                pstmt.executeUpdate();
            }
            con.commit();
            con.close();

        }

    }
    
    

    public User findByUsername(String username) throws SQLException {
        try (Connection con = Connection_Database.getConnection()) {
            String query = "SELECT * FROM users WHERE username = ?";
            try (PreparedStatement pstmt = con.prepareStatement(query)) {
                pstmt.setString(1, username);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
                        return user;
                    }
                }
            }
        }
        return null;
    }

    public User findByID(int id) throws SQLException {
        try (Connection con = Connection_Database.getConnection()) {
            String query = "SELECT * FROM users WHERE id = ?";
            try (PreparedStatement pstmt = con.prepareStatement(query)) {
                pstmt.setInt(1, id);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
                        return user;
                    }
                }
            }
        }
        return null;
    }

    public List<User> findAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();

        try (Connection con = Connection_Database.getConnection()) {
            try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM users")) {

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("username");
                    String password = rs.getString("password");
                    String admin = rs.getString("admin");
                    String reset_password = rs.getString("reset_password");
                    String new_password = rs.getString("new_password");

                    User user = new User(id, name, password, admin, reset_password, new_password);
                    userList.add(user);
                }
            }
        }

        return userList;

    }

    public boolean UsernameExists(String username) throws SQLException {
        try (Connection con = Connection_Database.getConnection()) {
            String query = "SELECT COUNT(*) FROM users WHERE username = ?";
            try (PreparedStatement pstmt = con.prepareStatement(query)) {
                pstmt.setString(1, username);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        int count = rs.getInt(1);
                        return count != 0;
                    }
                }
            }
        }
        return false;
    }

    public void delete(String username) throws SQLException {

        try (Connection con = Connection_Database.getConnection()) {
            try (PreparedStatement pstmt = con.prepareStatement(
                    "delete from users where username='" + username + "'")) {
                pstmt.executeUpdate();
            }
            con.commit();
            con.close();
        }
    }

    public void delete_by_ID(int id) throws SQLException {

        try (Connection con = Connection_Database.getConnection()) {
            try (PreparedStatement pstmt = con.prepareStatement(
                    "delete from users where id='" + id + "'")) {
                pstmt.executeUpdate();
            }
            con.commit();
            //con.close();
        }
    }

}
