package com.business.ventas.beans;

public class Producto {

    private int codigo;
    private int img;
    private String itemCode;
    private String pathImg;
    private String nombre;
    private String Descripcion;
    private int cantidad;
    private double precioUnitario;
    private double precioCantidad;

    public Producto(String nombre, String descripcion, int img) {
        this.img = img;
        this.nombre = nombre;
        Descripcion = descripcion;
    }

    public Producto() {
    }

    public Producto(String nombre, String descripcion, int cantidad, double precioUnitario, double precioCantidad) {
        this.nombre = nombre;
        Descripcion = descripcion;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.precioCantidad = precioCantidad;
    }

    public Producto(int codigo, int img, String nombre, String descripcion, double precioUnitario) {
        this.codigo = codigo;
        this.img = img;
        this.nombre = nombre;
        Descripcion = descripcion;
        this.precioUnitario = precioUnitario;
    }

    public Build config() {
        return new Build(this);
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getPrecioCantidad() {
        return precioCantidad;
    }

    public void setPrecioCantidad(double precioCantidad) {
        this.precioCantidad = precioCantidad;
    }

    public String getPathImg() {
        return pathImg;
    }

    public void setPathImg(String pathImg) {
        this.pathImg = pathImg;
    }

    public void actualizarPrecioCantidad() {
        this.setPrecioCantidad(this.getCantidad() * this.getPrecioUnitario());
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "codigo=" + codigo +
                ", img=" + img +
                ", itemCode='" + itemCode + '\'' +
                ", pathImg='" + pathImg + '\'' +
                ", nombre='" + nombre + '\'' +
                ", Descripcion='" + Descripcion + '\'' +
                ", cantidad=" + cantidad +
                ", precioUnitario=" + precioUnitario +
                ", precioCantidad=" + precioCantidad +
                '}';
    }

    public class Build {
        Producto producto;

        public Build(Producto producto) {
            this.producto = producto;
        }

        public Build setCodigo(int codigo) {
            producto.codigo = codigo;
            return this;
        }

        public Build setItemCode(String itemCode) {
            producto.itemCode = itemCode;
            return this;
        }

        public Build setPathImg(String pathImg) {
            producto.setPathImg(pathImg);
            return this;
        }

        public Build setNombre(String nombre) {
            producto.nombre = nombre;
            return this;
        }

        public Build setDescripcion(String descripcion) {
            producto.setDescripcion(descripcion);
            return this;
        }

        public Build setCantidad(int cantidad) {
            producto.cantidad = cantidad;
            return this;
        }

        public Build setPrecioUnitario(double precioUnitario) {
            producto.precioUnitario = precioUnitario;
            return this;
        }

        public Build actualizarPrecioCantidad() {
            producto.actualizarPrecioCantidad();
            return this;
        }

        public Producto build() {
            return producto;
        }
    }

}
