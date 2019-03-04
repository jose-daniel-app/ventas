package com.business.ventas.apiRest;

public class Constants {
    public static final String URL_ROOT = "https://torres.rockoo.pw";
    public static final String URL_LOGIN = "/api/method/login";
    public static final String URL_LOGIN_ESTADO = "/api/method/frappe.auth.get_logged_user";
    public static final String URL_LISTA_PRODUCTO = "/api/resource/Item";
    public static final String URL_LISTA_CLIENTES = "api/resource/Customer";
    public static final String URL_LISTA_ORDENES = "api/resource/Sales Order";
    public static final String URL_LISTA_RUTAS = "/api/resource/Warehouse";
    public static final String URL_DETALLE_ORDEN = "/api/resource/Sales Order/{codigo}";
    public static final String URL_LISTA_FACTURAS = "/api/resource/Sales Invoice";
    public static final String URL_LISTA_GUIAS = "/api/resource/Delivery Note";
    public static final String URL_DETALLE_FACTURA = "/api/resource/Sales Invoice/{codigo}";
    public static final String URL_DETALLE_GUI = "/api/resource/Delivery Note/{codigo}";
    public static final String URL_CREAR_ORDEN = "/api/resource/Sales Order";
    public static final String URL_ELIMINAR_ORDEN = "/api/resource/Sales Order/{codigo}";
    public static final String URL_UPDATE_ORDEN = "/api/resource/Sales Order/{codigo}";
    public static final String URL_OBTENER_ALMACEN = "/api/resource/Warehouse";
    public static final String URL_CERRAR_SESSION = "/api/method/logout";
    public static final String URL_LISTA_REQUERIMIENTOS = "/api/resource/Material Request";
}
