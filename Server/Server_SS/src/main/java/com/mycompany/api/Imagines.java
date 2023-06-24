/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api;

import com.mycompany.database.Connection_Database;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author eliza
 */
public class Imagines {
    
    public void image (File imagineFile) {
       
        String insertQuery = "UPDATE souvenirs SET name = ?, image = ?";

        try (Connection conn = Connection_Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {


            // Read image file into a byte array
            byte[] dateImagine = readImageFile(imagineFile);
            String name = imagineFile.getName();
            // Set value to the prepared statement
            pstmt.setString(1, name);
            pstmt.setBytes(2, dateImagine);

            // Execute the insert statement
            pstmt.executeUpdate();
            
            System.out.println(dateImagine);
            System.out.println("Imaginea a fost introdusa cu succes in baza de date.");

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private static byte[] readImageFile(File imagineFile) throws IOException {
        try (InputStream inputStream = new FileInputStream(imagineFile)) {
            byte[] buffer = new byte[(int) imagineFile.length()];
            inputStream.read(buffer);
            return buffer;
        }
    }
}
