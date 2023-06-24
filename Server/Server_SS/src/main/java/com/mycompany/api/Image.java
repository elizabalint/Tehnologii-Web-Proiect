/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api;

/**
 *
 * @author eliza
 */
public class Image {
     private String path;
    private String s;
    
    public Image(String path, String s) {
        this.path = path;
        this.s = s;
    }
    
    public String getPath() {
        return path;
    }
    
    public String getS() {
        return s;
    }
    
}
