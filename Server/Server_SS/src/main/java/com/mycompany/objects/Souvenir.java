/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.objects;

/**
 *
 * @author eliza
 */
public class Souvenir {
    private int id, id_country, age, popularity;
    private String name, country, period, gender,buy;

    public Souvenir() {
    }
    
    public Souvenir(int id,  String name, int id_country, String country, String period, String gender, int age, int popularity, String buy) {
        this.id = id;
        this.id_country = id_country;
        this.country=country;
        this.age = age;
        this.popularity = popularity;
        this.name = name;
        this.period = period;
        this.gender = gender;
        this.buy = buy;
    }

    public String getCountry() {
        return country;
    }

    
    public int getId() {
        return id;
    }

    public int getId_country() {
        return id_country;
    }

    public int getAge() {
        return age;
    }

    public int getPopularity() {
        return popularity;
    }

    public String getName() {
        return name;
    }

    public String getPeriod() {
        return period;
    }

    public String getGender() {
        return gender;
    }

    public String getBuy() {
        return buy;
    }

    @Override
    public String toString() {
        return "Souvenir{" + "id=" + id + ", id_country=" + id_country + ", country=" + country + ", age=" + age + ", popularity=" + popularity + ", name=" + name + ", period=" + period + ", gender=" + gender + ", buy=" + buy + '}';
    }
    
    
}
