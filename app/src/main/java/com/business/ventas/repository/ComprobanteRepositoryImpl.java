package com.business.ventas.repository;

import android.content.Context;

import com.business.ventas.beans.Comprobante;
import com.business.ventas.utils.Lista;
import com.google.gson.JsonObject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;


public class ComprobanteRepositoryImpl extends PadreRepository implements ComprobanteRepository {

    @Override
    public void listarComprovantes(Context context, RespuestaSucces<Lista<Comprobante>> succes, RespuestaError error) {
        String columnas = "\" name,customer_name,grand_total,status,posting_date,modified,posting_time,creation\"";

        /*Disposable subscribe = Observable.zip(
                getService(context).listarFacturas(columnas),
                getService(context).listarGuias(columnas),
                (json1, json2) -> {
                    json1.
                    return null;
                }
        ).subscribe();*/
    }
}
