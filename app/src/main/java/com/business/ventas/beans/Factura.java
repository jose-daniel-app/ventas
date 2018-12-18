package com.business.ventas.beans;

public class Factura extends Comprobante {
    public Factura(String nombre, int foto, String ruc, String fecha, String codigo) {
        super(nombre, foto, ruc, fecha, codigo);
    }

    public Factura() {
    }

    @Override
    public int tipoDecomprobante() {
        return Comprobante.FACTURA;
    }
}
