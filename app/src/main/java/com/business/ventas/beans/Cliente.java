package com.business.ventas.beans;

public class Cliente {
    private String nombre;
    private int foto;
    private String ruc;
    private String direccion;

    public Cliente() {

    }

    public Cliente(String nombre, int foto, String ruc, String direccion) {
        this.nombre = nombre;
        this.foto = foto;
        this.ruc = ruc;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}
