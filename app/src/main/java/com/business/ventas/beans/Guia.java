package com.business.ventas.beans;

public class Guia extends Comprobante {

    @Override
    public int tipoDecomprobante() {
        return Comprobante.GUIA;
    }
}
