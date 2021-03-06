package com.business.ventas.viewAdapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.business.ventas.R;
import com.business.ventas.beans.Ruta;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.VentasLog;
import com.business.ventas.requerimiento.views.RutaFragment;

import java.util.ArrayList;
import java.util.List;


public class RutaViewAdapter extends RecyclerView.Adapter<RutaViewAdapter.Holderview> {
    private VentasLog log = LogFactory.createInstance().setTag(RutaViewAdapter.class.getSimpleName());
    private List<Ruta> productlistAdap;
    private RutaFragment fragment;
    private ListenerItem listener;

    /*public RutaViewAdapter(List<Ruta> productlist, RutaFragment fragment) {
        this.productlistAdap = productlist;
        this.fragment = fragment;
    }*/
    public static RutaViewAdapter newInstance() {
        return new RutaViewAdapter();
    }

    public RutaViewBuild config() {
        //TODO
        return new RutaViewBuild(this);
    }

    @Override
    public Holderview onCreateViewHolder(ViewGroup parent, int vewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_ruta, parent, false);
        return new Holderview(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull Holderview holderview, final int position) {
        Ruta ruta = productlistAdap.get(position);

        //holderview.v_codRuta.setText(ruta.getCodRuta());
        holderview.v_dirRuta.setText(ruta.getDirRuta());
        holderview.v_provincia.setText(ruta.getCompany());

        //holderview.v_distrito.setText(productlistAdap.get(position).getDistrito());
        holderview.itemView.setOnClickListener(view -> {
            listener.onSelectItem(ruta);
        });

    }

    @Override
    public int getItemCount() {
        return productlistAdap.size();
    }

    public void setfilter(List<Ruta> listruta) {

        productlistAdap = new ArrayList<>();
        productlistAdap.addAll(listruta);
        notifyDataSetChanged();

    }

    class Holderview extends RecyclerView.ViewHolder {

        TextView v_codRuta;
        TextView v_dirRuta;
        TextView v_provincia;
        //TextView v_distrito;
        CardView itemCarview;

        Holderview(View itemview) {

            super(itemview);
            itemCarview = itemview.findViewById(R.id.cardViewIdruta);

            v_codRuta = itemview.findViewById(R.id.textViewCodRuta);
            v_dirRuta = itemview.findViewById(R.id.textViewDirRuta);
            v_provincia = itemview.findViewById(R.id.textViewProvincia);
            //v_distrito = itemview.findViewById(R.id.textViewDistrito);


        }
    }

    public interface ListenerItem {
        void onSelectItem(Ruta ruta);
    }

    public class RutaViewBuild {
        //TODO
        private RutaViewAdapter rutaViewAdapter;

        public RutaViewBuild(RutaViewAdapter rutaViewAdapter) {
            this.rutaViewAdapter = rutaViewAdapter;
        }

        public RutaViewBuild setListenerItem(ListenerItem listener) {
            this.rutaViewAdapter.listener = listener;
            return this;
        }

        public RutaViewBuild setFragment(RutaFragment context) {
            this.rutaViewAdapter.fragment = context;
            return this;
        }

        public RutaViewBuild setListaRuta(List<Ruta> productlistAdap) {
            this.rutaViewAdapter.productlistAdap = productlistAdap;
            return this;
        }

        public RutaViewAdapter build() {
            return rutaViewAdapter;
        }
    }
}
