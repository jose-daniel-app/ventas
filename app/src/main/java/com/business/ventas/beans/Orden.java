package com.business.ventas.beans;

import com.business.ventas.utils.Lista;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.VentasLog;

public class Orden {

    private String codigo;
    private String nombreCliente;
    private String direcionCliente;
    private Lista<Producto> productos;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getDirecionCliente() {
        return direcionCliente;
    }

    public void setDirecionCliente(String direcionCliente) {
        this.direcionCliente = direcionCliente;
    }

    public Lista<Producto> getProductos() {
        return productos;
    }

    public void setProductos(Lista<Producto> productos) {
        this.productos = productos;
    }

    public static class handerProductos {

        VentasLog log = LogFactory.createInstance().setTag(handerProductos.class.getSimpleName());

        private String detalleHtml;
        private Lista<Producto> productos;

        public handerProductos(String detalleHtml) {
            this.detalleHtml = detalleHtml;
        }

        public handerProductos parserList() {

            if (detalleHtml == null)
                return this;

            String[] lineas = detalleHtml.trim().split("\n");

            for (String linea : lineas) {
                if(linea.trim().length()> 0)
                log.info("fila contenido de: %s", linea);
            }

            return this;
        }

        public Lista<Producto> getProductos() {
            if (this.productos == null)
                this.productos = new Lista<Producto>();
            return this.productos;
        }
    }
}
