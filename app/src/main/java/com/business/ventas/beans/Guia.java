package com.business.ventas.beans;

public class Guia extends Comprobante {

    @Override
    int tipoDecomprobante() {
        return Comprobante.GUIA;
    }
}
