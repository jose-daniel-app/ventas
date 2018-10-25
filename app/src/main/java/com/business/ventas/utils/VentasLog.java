package com.business.ventas.utils;

public interface VentasLog {
    VentasLog setTag(String nameClas);
    void verbose(String message);
    void debug(String message);
    void info(String message);
    void warn(String message);
    void error(String message);

    void verbose(String message,Throwable t);
    void debug(String message,Throwable t);
    void info(String message,Throwable t);
    void warn(String message,Throwable t);
    void error(String message,Throwable t);

}

