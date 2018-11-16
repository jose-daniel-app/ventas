package com.business.ventas.beans;

public class Comprobante {
    private String nombre;
    private int foto;
    private String ruc;
    private String fecha;
    private String codigo;


    public Comprobante() {

    }

    public Comprobante(String nombre, int foto, String ruc, String fecha, String codigo) {
        this.nombre = nombre;
        this.foto = foto;
        this.ruc = ruc;
        this.fecha = fecha;
        this.codigo = codigo;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}