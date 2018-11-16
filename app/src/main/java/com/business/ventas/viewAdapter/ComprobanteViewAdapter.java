package com.business.ventas.viewAdapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.business.ventas.R;
import com.business.ventas.beans.Comprobante;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.VentasLog;
import com.business.ventas.comprobante.views.ComprobanteFragment;

import java.util.ArrayList;
import java.util.List;

public class ComprobanteViewAdapter extends RecyclerView.Adapter<ComprobanteViewAdapter.Holderview> {
    private VentasLog log = LogFactory.createInstance().setTag(ComprobanteViewAdapter.class.getSimpleName());
    private List<Comprobante> productlistAdap;
    private ComprobanteFragment fragment;

    public ComprobanteViewAdapter(List<Comprobante> productlist, ComprobanteFragment fragment) {
        this.productlistAdap = productlist;
        this.fragment = fragment;
    }

    @Override
    public Holderview onCreateViewHolder(ViewGroup parent, int vewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_comprobante, parent, false);
        return new Holderview(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull Holderview holderview, final int position) {

        holderview.v_nombre.setText(productlistAdap.get(position).getNombre());
        holderview.v_ruc.setText(productlistAdap.get(position).getRuc());
        holderview.v_fecha.setText(productlistAdap.get(position).getFecha());
        holderview.v_codigo.setText(productlistAdap.get(position).getCodigo());
        holderview.v_foto.setImageResource(productlistAdap.get(position).getFoto());
        holderview.itemView.setOnClickListener(this::onclick);

    }

    private void onclick(View view) {
        log.info("el onclick de clase => " + this.fragment);
        fragment.onButtonPressed(this.fragment);
    }

    @Override
    public int getItemCount() {
        return productlistAdap.size();
    }

    public void setfilter(List<Comprobante> listcomprobante) {

        productlistAdap = new ArrayList<>();
        productlistAdap.addAll(listcomprobante);
        notifyDataSetChanged();

    }

    class Holderview extends RecyclerView.ViewHolder {
        ImageView v_foto;
        TextView v_nombre;
        TextView v_ruc;
        TextView v_fecha;
        TextView v_codigo;
        CardView itemCarview;


        Holderview(View itemview) {

            super(itemview);
            itemCarview = itemview.findViewById(R.id.cardViewIdcomprobante);
            v_foto = (ImageView) itemview.findViewById(R.id.product_image);
            v_nombre = (TextView) itemview.findViewById(R.id.textView2);
            v_ruc = itemview.findViewById(R.id.textView3);
            v_fecha = itemview.findViewById(R.id.textView4);
            v_codigo = itemview.findViewById(R.id.textView5);
        }

    }

}
