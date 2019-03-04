package com.business.ventas.repository;

import android.content.Context;

import com.business.ventas.beans.Requerimiento;
import com.business.ventas.utils.Fechas;
import com.business.ventas.utils.Lista;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.VentasLog;

import java.util.List;

public class RequerimientosRepositoryImpl extends PadreRepository implements RequerimientosRepository {

    VentasLog log = LogFactory.createInstance().setTag(RequerimientosRepositoryImpl.class.getSimpleName());

    @Override
    public void listarRequerimientos(Context context, RespuestaSucces<List<Requerimiento>> succes, RespuestaError error) {
        log.info("solicitando la listarRequerimientos al api rest");
        String columnas = "\"status,name,transaction_date,title,schedule_date\"";
        getService(context).listarFacturas(columnas).enqueue(new PadreRepository.CallRespuesta().listenRespuesta(resp -> {

            Lista<Requerimiento> lista = new Lista<>();
            recorrerLista(resp.body().get("data").getAsJsonArray().iterator(), (item) -> {
                Requerimiento rq = new Requerimiento();
                rq.setName(getString(item.get("name")));
                rq.setStatus(getString(item.get("status")));
                rq.setTitle(getString(item.get("title")));
                rq.setTransactionDate(Fechas.asDate(getString(item.get("transaction_date"))));
                rq.setScheduleDate(Fechas.asDate(getString(item.get("schedule_date"))));
                lista.agregar(rq);
            });

            succes.onRespuestaSucces(lista);

        }).listenError(error::onRespuestaError));
    }
}
