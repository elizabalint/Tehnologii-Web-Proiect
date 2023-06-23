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

    public User findByUsername(String username) throws SQLException {

        try (Connection con = Connection_Database.getConnection()) {
            try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(
                    "select * from users where username='" + username + "'")) {

                User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                //con.close();
                return user;

            }
        }

    }

    public User findByID(int id) throws SQLException {

        try (Connection con = Connection_Database.getConnection()) {
            try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(
                    "select * from users where id='" + id + "'")) {

                User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                //con.close();
                return user;

            }
        }

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

                    User user = new User(id, name, password, admin);
                    userList.add(user);
                }
            }
        }

        return userList;

    }

    public Boolean UsernameExists(String username) throws SQLException {

        try (Connection con = Connection_Database.getConnection()) {

            try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(
                    "select COUNT(*) from users where username='" + username + "'")) {

                //con.close();
                return rs.getInt(1) != 0;

            }
        }
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
