package com.djl.backend;
import java.io.Serializable;

public class Archivo implements Serializable {
    
    private int id;
    private String name;
    private float size;
    private String type;
    String fecha;
    private boolean publico;
    private String path;
    public Archivo(int id, String name,float size, String type,String fecha,String path,boolean publico){
        this.name = name;
        this.fecha=fecha;
        this.size = size;
        this.type = type;
        this.path=path;
        this.publico=true;
    }

    public Archivo() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean isPublico() {
        return publico;
    }

    public void setPublico(boolean publico) {
        this.publico = publico;
    }

    
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    
    // Getters
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public float getSize() {
        return size;
    }

    public String getType() {
        return type;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setType(String type) {
        this.type = type;
    }


}