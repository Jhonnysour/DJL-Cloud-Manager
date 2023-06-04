/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djl.backend;

import java.io.Serializable;

/**
 *
 * @author Kaso5-PC
 */
public class Usuario implements Serializable {
    private String password;
    private String userName;
    private double tiempo;
    private long espacioUtilizado;
    
    public Usuario(String userName,String password){
        this.password=password;
        this.userName=userName;
        this.tiempo=0;
    }
    public Usuario(String userName,String password,double time,long espacio){
        this.password=password;
        this.userName=userName;
        this.tiempo=time;
        this.espacioUtilizado=espacio;
    }
    public Usuario() {
    }

    public long getEspacioUtilizado() {
        return espacioUtilizado;
    }

    public void setEspacioUtilizado(long espacioUtilizado) {
        this.espacioUtilizado = espacioUtilizado;
    }
    
    public double getTiempo() {
        return tiempo;
    }

    public void setTiempo(double tiempo) {
        this.tiempo = tiempo;
    }
    
    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    
}
