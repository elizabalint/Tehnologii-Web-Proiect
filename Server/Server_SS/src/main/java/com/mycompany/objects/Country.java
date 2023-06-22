/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.objects;

/**
 *
 * @author ignat
 */
public class Country {
    int id;
    String name,short_name,continent,region;

    public Country() {
    }

    public Country(int id, String name, String short_name, String continent, String region) {
        this.id = id;
        this.name = name;
        this.short_name = short_name;
        this.continent = continent;
        this.region = region;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getShort_name() {
        return short_name;
    }

    public String getContinent() {
        return continent;
    }

    public String getRegion() {
        return region;
    }

    @Override
    public String toString() {
        return "Country{" + "id=" + id + ", name=" + name + ", short_name=" + short_name + ", continent=" + continent + ", region=" + region + '}';
    }
    
    
    
}
