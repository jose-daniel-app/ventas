package com.business.ventas.viewAdapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.business.ventas.R;
import com.business.ventas.beans.Producto;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.VentasLog;

import java.util.List;

public class ItemBoletaViewAdapter extends RecyclerView.Adapter<ItemBoletaViewAdapter.Holderview> {

    private VentasLog log = LogFactory.createInstance().setTag(ItemBoletaViewAdapter.class.getSimpleName());
    List<Producto> productos;
    Activity activity;

    private ItemBoletaViewAdapter() {
    }

    public static ItemBoletaViewAdapter newInstance() {
        return new ItemBoletaViewAdapter();
    }

    public ItemBoletaViewAdapterBuild config() {
        return new ItemBoletaViewAdapterBuild(this);
    }

    @NonNull
    @Override
    public Holderview onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_boleta, parent, false);
        return new ItemBoletaViewAdapter.Holderview(layout);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull Holderview holderview, int i) {
        holderview.txtCantidad.setText(productos.get(i).getCantidad() + "");
        holderview.txtDescripcion.setText(productos.get(i).getDescripcion());
        holderview.txtPrecioUnitario.setText("s/ "+productos.get(i).getPrecioUnitario() + "");
        holderview.txtImporte.setText("s/ "+ productos.get(i).getPrecioCantidad());
    }

    @Override
    public int getItemCount() {
        if (productos != null)
            return productos.size();
        return 0;
    }


    public static class Holderview extends RecyclerView.ViewHolder {

        TextView txtCantidad;
        TextView txtDescripcion;
        TextView txtPrecioUnitario;
        TextView txtImporte;

        public Holderview(@NonNull View view) {
            super(view);

            txtCantidad = view.findViewById(R.id.txtCantidad);
            txtDescripcion = view.findViewById(R.id.txtDescripcion);
            txtPrecioUnitario = view.findViewById(R.id.txtPrecioUnitario);
            txtImporte = view.findViewById(R.id.txtImporte);

        }
    }


    public class ItemBoletaViewAdapterBuild {

        private ItemBoletaViewAdapter itemBoletaViewAdapter;

        public ItemBoletaViewAdapterBuild(ItemBoletaViewAdapter itemBoletaViewAdapter) {
            this.itemBoletaViewAdapter = itemBoletaViewAdapter;
        }

        public ItemBoletaViewAdapterBuild setActivity(Activity activity) {
            this.itemBoletaViewAdapter.activity = activity;
            return this;
        }

        public ItemBoletaViewAdapterBuild setListaProductos(List<Producto> productos) {
            this.itemBoletaViewAdapter.productos = productos;
            return this;
        }

        public ItemBoletaViewAdapter build() {
            return this.itemBoletaViewAdapter;
        }

    }

}
