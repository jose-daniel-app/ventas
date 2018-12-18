package com.business.ventas.utils;

import android.util.Log;

public class VentasLogImpl implements VentasLog {

    private String tag;

    @Override
    public VentasLog setTag(String nameClas) {
        this.tag = "ventas-proyect-"+ nameClas;
        return this;
    }

    @Override
    public void verbose(String message) {
        Log.v(tag, message);
    }

    @Override
    public void debug(String message) {
        Log.d(tag, message);
    }

    @Override
    public void info(String message) {
        Log.i(tag, message);
    }

    @Override
    public void warn(String message) {
        Log.w(tag, message);
    }

    @Override
    public void error(String message) {
        Log.e(tag, message);
    }

    @Override
    public void verbose(String message, Throwable t) {
        Log.v(tag, message, t);
    }

    @Override
    public void debug(String message, Throwable t) {
        Log.d(tag, message, t);
    }

    @Override
    public void info(String message, Throwable t) {
        Log.i(tag, message, t);
    }

    @Override
    public void warn(String message, Throwable t) {
        Log.w(tag, message, t);
    }

    @Override
    public void error(String message, Throwable t) {
        Log.e(tag, message, t);
    }

    @Override
    public void info(String message, Object... args) {
        String smg = String.format(message, args);
        Log.i(tag, smg);
    }
}
