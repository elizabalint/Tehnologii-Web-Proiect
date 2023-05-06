/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.import_csv;

/**
 *
 * @author ignat
 */
public class Country {
    private String short_name,name,continent,region;

    public Country(String short_name, String name, String continent, String region) {
        this.short_name = short_name;
        this.name = name;
        this.continent = continent;
        this.region = region;
    }
  
    //getters
    public String getShort_name() {
        return short_name;
    }

    public String getName() {
        return name;
    }

    public String getContinent() {
        return continent;
    }

    public String getRegion() {
        return region;
    }
    
    //setters

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public void setRegion(String region) {
        this.region = region;
    }
    
    //toString

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Country{");
        sb.append("short_name=").append(short_name);
        sb.append(", name=").append(name);
        sb.append(", continent=").append(continent);
        sb.append(", region=").append(region);
        sb.append('}');
        return sb.toString();
    }
    
    
    
}
