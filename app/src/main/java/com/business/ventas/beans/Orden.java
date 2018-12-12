package com.business.ventas.beans;

public class Orden {

    private String codigo;
    private String nombreCliente;
    private String direcionCliente;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getDirecionCliente() {
        return direcionCliente;
    }

    public void setDirecionCliente(String direcionCliente) {
        this.direcionCliente = direcionCliente;
    }
}
