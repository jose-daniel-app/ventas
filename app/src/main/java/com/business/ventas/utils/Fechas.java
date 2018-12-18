package com.business.ventas.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Fechas {

    public static Date asDate(String fecha) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            return format.parse(fecha);
        } catch (Exception e) {
        }
        return null;
    }

}
