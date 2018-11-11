package com.business.ventas.utils;

public class Numeros {

    public static int getCantidad(String cantidad) {
        if (cantidad == null)
            return 0;
        if (cantidad.trim().length() == 0)
            return 0;
        return Integer.parseInt(cantidad);
    }
}
