package com.business.ventas.beans;

public class Requerimiento {

    private String Codigo;
    private String fecha_pedido;
    private String fecha_entrega;
    private String ruta;

    public Requerimiento() {

    }

    public Requerimiento(String codigo, String fecha_pedido, String fecha_entrega, String ruta) {
        Codigo = codigo;
        this.fecha_pedido = fecha_pedido;
        this.fecha_entrega = fecha_entrega;
        this.ruta = ruta;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public String getFecha_pedido() {
        return fecha_pedido;
    }

    public void setFecha_pedido(String fecha_pedido) {
        this.fecha_pedido = fecha_pedido;
    }

    public String getFecha_entrega() {
        return fecha_entrega;
    }

    public void setFecha_entrega(String fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
}
