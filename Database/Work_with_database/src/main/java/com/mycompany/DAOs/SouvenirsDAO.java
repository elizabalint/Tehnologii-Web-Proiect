/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author eliza
 */
public class SouvenirsDAO {
    public void create(String name, int id_souvenir, int id_country, String data_popularity, Connection con) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into countries (short_name,name,continent,region) values (?,?,?,?)")) {
            pstmt.setString(1, name);
            pstmt.setInt(2, id_souvenir);
            pstmt.setInt(3, id_country);
            pstmt.setString(4, data_popularity);
            pstmt.executeUpdate();
        }

    }
    
    public void delete(String name, Connection con) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(
                "delete from souvenirs where name='" + name + "'")) {
            pstmt.executeUpdate();
        }
    }
}
