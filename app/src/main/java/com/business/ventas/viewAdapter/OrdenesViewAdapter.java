package com.business.ventas.viewAdapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.business.ventas.R;
import com.business.ventas.beans.Orden;
import com.business.ventas.beans.Ruta;
import com.business.ventas.ordenes.views.OrdenFragment;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.VentasLog;
import com.business.ventas.ordenes.views.OrdenesFragment;

import java.util.ArrayList;
import java.util.List;

public class OrdenesViewAdapter extends RecyclerView.Adapter<OrdenesViewAdapter.Holderview> {
    private VentasLog log = LogFactory.createInstance().setTag(OrdenesViewAdapter.class.getSimpleName());
    private List<Orden> ordenes;
    private OrdenesFragment fragment;

    public OrdenesViewAdapter(List<Orden> ordenes, OrdenesFragment fragment) {
        this.ordenes = ordenes;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public Holderview onCreateViewHolder(@NonNull ViewGroup parent, int vewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_orden, parent, false);
        return new Holderview(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull Holderview holderview, final int position) {
        holderview.v_codigo.setText(ordenes.get(position).getCodigo());
        holderview.v_direcion.setText(ordenes.get(position).getDirecionCliente());
        holderview.v_nombre.setText(ordenes.get(position).getNombreCliente());
        holderview.itemView.setOnClickListener(this::onclick);
    }

    private void onclick(View view) {
        log.info("el onclick de clase => " + this.fragment);
        fragment.guardarProductostemporales();
        fragment.getMainActivity().newFragmentHandler().changeFragment(OrdenFragment.newInstance());
    }

    @Override
    public int getItemCount() {
        return ordenes.size();
    }

    public void setfilter(List<Orden> ordenes) {
        this.ordenes = new ArrayList<>();
        this.ordenes.addAll(ordenes);
        notifyDataSetChanged();
    }

    class Holderview extends RecyclerView.ViewHolder {

        TextView v_codigo;
        TextView v_nombre;
        TextView v_direcion;
        CardView itemCarview;

        Holderview(View itemview) {

            super(itemview);
            itemCarview = itemview.findViewById(R.id.cardViewIdruta);
            v_codigo = itemview.findViewById(R.id.txtCodigoOrden);
            v_direcion = itemview.findViewById(R.id.txtDireccion);
            v_nombre = itemview.findViewById(R.id.txtNombreCliente);
        }
    }

}
