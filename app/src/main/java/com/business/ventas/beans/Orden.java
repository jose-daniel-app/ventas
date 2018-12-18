package com.business.ventas.beans;

import com.business.ventas.utils.Lista;

import java.util.Date;

public class Orden {

    private String codigo;
    private String nombreCliente;
    private String direcionCliente;
    private Date fechaEntrega;
    private double totalGeneral;
    private Lista<Producto> productos;

    public double getTotalGeneral() {
        return totalGeneral;
    }

    public void setTotalGeneral(double totalGeneral) {
        this.totalGeneral = totalGeneral;
    }

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

    public Lista<Producto> getProductos() {
        return productos;
    }

    public void setProductos(Lista<Producto> productos) {
        this.productos = productos;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    @Override
    public String toString() {
        return "Orden{" +
                "codigo='" + codigo + '\'' +
                ", nombreCliente='" + nombreCliente + '\'' +
                ", direcionCliente='" + direcionCliente + '\'' +
                ", fechaEntrega=" + fechaEntrega.toString() +
                ", totalGeneral=" + totalGeneral +
                '}';
    }
}
