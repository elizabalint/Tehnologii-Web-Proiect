/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.objects;

/**
 *
 * @author ignat
 */
public class User {

    private int id;
    private String username, password,admin,reset_password,new_password;

    public User() {
    }

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;       
    }

    public User(int id, String username, String password, String admin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.admin = admin;
    }

    public User(int id, String username, String password, String admin, String reset_password, String new_password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.admin = admin;
        this.reset_password = reset_password;
        this.new_password = new_password;
    }

    public String getNew_password() {
        return new_password;
    }
    
    public String getReset_password() {
        return reset_password;
    }
    

    public String getAdmin() {
        return admin;
    } 
    
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", password=" + password + ", admin=" + admin + '}';
    }

    

}
