package com.business.ventas.utils;

public abstract class LogFactory {

    private static VentasLog log;
    private LogFactory(){}
    public static VentasLog createInstance(){
        if(log == null)
            log = new VentasLogImpl();
        return log;
    }
}
