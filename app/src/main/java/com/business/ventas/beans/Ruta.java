package com.business.ventas.beans;

public class Ruta {

    private String codRuta;
    private String dirRuta;
    private String company;
    private String provincia;
    private String distrito;


    public Ruta(){}

    public Ruta(String codRuta, String dirRuta, String provincia, String distrito) {
        this.codRuta = codRuta;
        this.dirRuta = dirRuta;
        this.provincia = provincia;
        this.distrito = distrito;
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

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Ruta{" +
                "codRuta='" + codRuta + '\'' +
                ", dirRuta='" + dirRuta + '\'' +
                ", company='" + company + '\'' +
                ", provincia='" + provincia + '\'' +
                ", distrito='" + distrito + '\'' +
                '}';
    }
}
