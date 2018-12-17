package com.business.ventas.beans;

public class Factura extends Comprobante {

    @Override
    int tipoDecomprobante() {
        return Comprobante.FACTURA;
    }
}
