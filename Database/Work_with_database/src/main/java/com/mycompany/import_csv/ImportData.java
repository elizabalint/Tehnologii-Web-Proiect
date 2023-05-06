/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.import_csv;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ignat
 */
public class ImportData {
    
    //create and return country of this metadata
    private static Country createCountry(String[] metadata) {
        String short_name = metadata[0];
        String name = metadata[1];
        String continent= metadata[5];
        String region = metadata[6];

        return new Country(short_name,name,continent,region);
    }
    
    //import the data from the CSV
    public static List<Country> readCountriesFromCSV() {
        List<Country> countries = new ArrayList<>();
        Path pathToFile = Paths.get("../countryContinent.csv");

        // create an instance of BufferedReader
        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.ISO_8859_1)) 
        {

            String line = br.readLine();
            while (line != null) {

                String[] attributes = line.split(",");
                Country country = createCountry(attributes);
                countries.add(country);

                line = br.readLine();
            }

        } catch (IOException e) {
             System.out.println(e);
        }

        return countries;
    }
    
}
