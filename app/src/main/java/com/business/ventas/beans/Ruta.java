package com.business.ventas.beans;

public class Ruta {

    private String codRuta;
    private String dirRuta;

    public Ruta(){}

    public Ruta(String codRuta, String dirRuta) {
        this.codRuta = codRuta;
        this.dirRuta = dirRuta;
    }

    public String getCodRuta() {
        return codRuta;
    }

    public void setCodRuta(String codRuta) {
        this.codRuta = codRuta;
    }

    public String getDirRuta() {
        return dirRuta;
    }

    public void setDirRuta(String dirRuta) {
        this.dirRuta = dirRuta;
    }
}
