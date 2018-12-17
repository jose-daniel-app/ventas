package com.business.ventas.beans;

import java.util.Date;

public abstract class Comprobante {
    protected String nombre;
    protected int foto;
    protected String ruc;
    protected String fecha;
    protected String codigo;
    protected Date fechaPublicacion;
    protected double pagoTotal;

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

    public abstract int tipoDecomprobante();

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

    public double getPagoTotal() {
        return pagoTotal;
    }

    public void setPagoTotal(double pagoTotal) {
        this.pagoTotal = pagoTotal;
    }

    @Override
    public String toString() {
        return "Comprobante{" +
                "nombre='" + nombre + '\'' +
                ", foto=" + foto +
                ", ruc='" + ruc + '\'' +
                ", fecha='" + fecha + '\'' +
                ", codigo='" + codigo + '\'' +
                ", fechaPublicacion=" + fechaPublicacion +
                ", pagoTotal=" + pagoTotal +
                '}';
    }
}