package com.business.ventas.beans;

import java.util.Date;

public abstract class Comprobante {
    protected String nombre;
    protected int foto;
    protected String ruc;
    protected String fecha;
    protected String codigo;
    protected Date fechaPublicacion;

    public static final int FACTURA = 1;
    public static final int GUIA = 2;

    public Comprobante() {

    }

    public Comprobante(String nombre, int foto, String ruc, String fecha, String codigo) {
        this.nombre = nombre;
        this.foto = foto;
        this.ruc = ruc;
        this.fecha = fecha;
        this.codigo = codigo;
    }

    abstract int tipoDecomprobante();

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

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }
}