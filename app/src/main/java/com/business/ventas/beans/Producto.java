package com.business.ventas.beans;

public class Producto {

    private int img;
    private String nombre;
    private String Descripcion;

    public Producto(String nombre, String descripcion,int img) {
        this.img = img;
        this.nombre = nombre;
        Descripcion = descripcion;
    }

    public Producto() {
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}