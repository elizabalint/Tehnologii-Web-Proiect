/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.objects;

/**
 *
 * @author ignat
 */
public class Session {
    String id_session;
    int id_user;

    public Session(String id_session, int id_user) {
        this.id_session = id_session;
        this.id_user = id_user;
    }

    public Session() {
    }

    public String getId_session() {
        return id_session;
    }

    public int getId_user() {
        return id_user;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Session{");
        sb.append("id_session=").append(id_session);
        sb.append(", id_user=").append(id_user);
        sb.append('}');
        return sb.toString();
    }
    
    
    
    
}
