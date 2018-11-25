package com.business.ventas.viewAdapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.business.ventas.R;
import com.business.ventas.beans.Producto;

import java.util.List;

public class ItemPedidosBaseAdapter extends ArrayAdapter<Producto> {

    private List<Producto> productos;
    private Activity context;
    private int resource;

    public ItemPedidosBaseAdapter(@NonNull Activity context, int resource, List<Producto> productos) {
        super(context, resource);
        this.productos = productos;
        this.context = context;
        this.resource = resource;
    }

    @Override
    public int getCount() {
        return productos.size();
    }

    @Override
    public Producto getItem(int i) {
        return productos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = new ViewHolder();
        Producto p = productos.get(i);
        if (view == null) {
            if (context != null) {
                view = context.getLayoutInflater().inflate(resource, null);
                viewHolder.txtCantidad = view.findViewById(R.id.txtCantidad);
                viewHolder.txtDescripcion = view.findViewById(R.id.txtDescripcion);
                viewHolder.txtPrecio = view.findViewById(R.id.txtPrecio);
                viewHolder.txtImporte = view.findViewById(R.id.txtImporte);
                view.setTag(viewHolder);
            }
        }else {
            viewHolder = (ViewHolder)view.getTag();
        }

        viewHolder.txtCantidad.setText(p.getCantidad() + "");
        viewHolder.txtDescripcion.setText(p.getNombre() + "");
        viewHolder.txtPrecio.setText(p.getPrecioUnitario() + "");
        viewHolder.txtImporte.setText(p.getPrecioCantidad() + "");

        return view;
    }

    static class ViewHolder {
        TextView txtCantidad;
        TextView txtDescripcion;
        TextView txtPrecio;
        TextView txtImporte;
    }
}
