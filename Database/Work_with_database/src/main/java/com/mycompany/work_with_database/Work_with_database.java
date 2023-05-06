/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.work_with_database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import com.mycompany.import_csv.*;
import com.mycompany.DAOs.*;

/**
 *
 * @author ignat
 */
public class Work_with_database {

    public static void main(String[] args) throws SQLException {

        try {
            Connection con = Hikari_connection.getConnection();
            
            var work_countries=new CountryDAO();
            
//            List<Country> countries = ImportData.readCountriesFromCSV();
//
////           for (Country c : countries) {
////            //System.out.println(c);
////            //work_countries.create(c.getShort_name(),c.getName(), c.getContinent(), c.getRegion(), con);
////            }

          
          work_countries.delete("1", con);
                    
            con.commit();
            con.close();
                    
        } catch (SQLException e) {
            System.err.println(e);
            Hikari_connection.getConnection().rollback();
        }

    }

}
