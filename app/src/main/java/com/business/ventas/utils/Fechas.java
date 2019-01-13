package com.business.ventas.utils;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Fechas {

    public static Date asDate(String fecha) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            return format.parse(fecha);
        } catch (Exception e) {
        }
        return null;
    }

    public static String dateAsString(Date date) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            return format.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean esFechaDelDiaActual(Date fecha) {
        /*Log.i("ventas", String.format("fecha dia ini: %s > fecha(%s) < fecha dia fin: %s",
            darFormatoALaFecha("yyyy-MM-dd HH:mm:ss", obtenerFechaDelDiaInicio()),
            darFormatoALaFecha("yyyy-MM-dd HH:mm:ss", fecha),
            darFormatoALaFecha("yyyy-MM-dd HH:mm:ss", obtenerFechaDelDiaFin())
         ));*/
        return obtenerFechaDelDiaInicio().before(fecha) && obtenerFechaDelDiaFin().after(fecha) ||
                obtenerFechaDelDiaInicio().equals(fecha);
    }

    public static boolean esFechaDeLaSemana(Date fecha){
        return obtenerFechaSemanaIni().before(fecha) && obtenerFechaDelDiaFin().after(fecha) ||
                obtenerFechaSemanaIni().equals(fecha);
    }

    public static boolean esFechaDelMes(Date fecha){
        return obtenerFechaMesIni().before(fecha) && obtenerFechaDelDiaFin().after(fecha) ||
                obtenerFechaMesIni().equals(fecha);
    }

    private static Date obtenerFechaDelDiaInicio() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    private static Date obtenerFechaDelDiaFin() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    private static Date obtenerFechaSemanaIni(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR,-7);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    private static Date obtenerFechaMesIni(){
        Calendar cal = Calendar.getInstance();
        //cal.add(Calendar.DAY_OF_YEAR,-30);
        cal.add(Calendar.MONTH,-1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static String darFormatoALaFecha(String formato, Date fecha) {
        try {
            return new SimpleDateFormat(formato).format(fecha);
        } catch (Exception e) {
            return null;
        }
    }
}
