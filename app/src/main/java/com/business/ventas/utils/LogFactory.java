package com.business.ventas.utils;

public abstract class LogFactory {

    private LogFactory(){}
    public static VentasLog createInstance(){
        return new VentasLogImpl();
    }
}
