/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.objects;

import java.util.List;

/**
 *
 * @author ignat
 */
public class CountriesNames {
    
    private List<String> nameCountries;
    private List<String> shortNameCountries;

    public CountriesNames(List<String> nameCountries, List<String> shortNameCountries) {
        this.nameCountries = nameCountries;
        this.shortNameCountries = shortNameCountries;
    }

    public List<String> getNameCountries() {
        return nameCountries;
    }

    public List<String> getShortNameCountries() {
        return shortNameCountries;
    }

    @Override
    public String toString() {
        return "CountriesNames{" + "nameCountries=" + nameCountries + ", shortNameCountries=" + shortNameCountries + '}';
    }
    
    
}
