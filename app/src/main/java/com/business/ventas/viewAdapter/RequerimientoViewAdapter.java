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
import com.business.ventas.beans.Requerimiento;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.VentasLog;
import com.business.ventas.requerimiento.views.RequerimientoFragment;

import java.util.ArrayList;
import java.util.List;

public class RequerimientoViewAdapter extends RecyclerView.Adapter<RequerimientoViewAdapter.Holderview> {
    private VentasLog log = LogFactory.createInstance().setTag(RequerimientoViewAdapter.class.getSimpleName());
    private List<Requerimiento> productlistAdap;
    private RequerimientoFragment fragment;
    private OnSelectCardListener listener;

    public static RequerimientoViewAdapter newInstance() {
        return new RequerimientoViewAdapter();
    }

    public Builder config() {
        return new Builder(this);
    }

    @Override
    public Holderview onCreateViewHolder(ViewGroup parent, int vewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_requerimiento, parent, false);
        return new Holderview(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull Holderview holderview, final int position) {

        holderview.v_codigo.setText(productlistAdap.get(position).getCodigo());
        holderview.v_fecha_pedido.setText(productlistAdap.get(position).getFecha_pedido());
        holderview.v_fecha_entrega.setText(productlistAdap.get(position).getFecha_entrega());
        holderview.v_ruta.setText(productlistAdap.get(position).getRuta());
        holderview.itemView.setOnClickListener(view -> {
            listener.onClickCard(productlistAdap.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return productlistAdap.size();
    }

    public void setfilter(List<Requerimiento> listrequerimiento) {

        productlistAdap = new ArrayList<>();
        productlistAdap.addAll(listrequerimiento);
        notifyDataSetChanged();

    }

    class Holderview extends RecyclerView.ViewHolder {

        TextView v_ruta;
        TextView v_fecha_pedido;
        TextView v_fecha_entrega;
        TextView v_codigo;
        CardView itemCarview;

        Holderview(View itemview) {

            super(itemview);
            itemCarview = itemview.findViewById(R.id.cardViewIdrequerimiento);

            v_ruta = itemview.findViewById(R.id.textView2);
            v_fecha_pedido = itemview.findViewById(R.id.textView3);
            v_fecha_entrega = itemview.findViewById(R.id.textView4);
            v_codigo = itemview.findViewById(R.id.textView5);
        }

    }

    public interface OnSelectCardListener {
        void onClickCard(Requerimiento requerimiento);
    }

    public class Builder {

        RequerimientoViewAdapter requerimientoViewAdapter;

        public Builder(RequerimientoViewAdapter requerimientoViewAdapter) {
            this.requerimientoViewAdapter = requerimientoViewAdapter;
        }

        public Builder setProductlistAdap(List<Requerimiento> productlistAdap) {
            this.requerimientoViewAdapter.productlistAdap = productlistAdap;
            return this;
        }

        public Builder setFragment(RequerimientoFragment fragment) {
            this.requerimientoViewAdapter.fragment = fragment;
            return this;
        }

        public Builder setListener(OnSelectCardListener listener) {
            this.requerimientoViewAdapter.listener = listener;
            return this;
        }

        public RequerimientoViewAdapter build() {
            return requerimientoViewAdapter;
        }

    }

}
