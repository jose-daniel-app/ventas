package com.business.ventas.viewAdapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.business.ventas.R;
import com.business.ventas.beans.Ruta;
import com.business.ventas.login.views.MainActivity;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.VentasLog;
import com.business.ventas.ventas.views.OrdenesFragment;
//import com.business.ventas.requerimiento.views.RutaFragment;

import java.util.ArrayList;
import java.util.List;

public class OrdenesViewAdapter extends RecyclerView.Adapter<OrdenesViewAdapter.Holderview> {
    private VentasLog log = LogFactory.createInstance().setTag(OrdenesViewAdapter.class.getSimpleName());
    private List<Ruta> productlistAdap;
    private OrdenesFragment fragment;
    private Activity activity;
    //  private ListenerItem listener;

    public OrdenesViewAdapter(List<Ruta> productlist, OrdenesFragment fragment, Activity activity) {
        this.productlistAdap = productlist;
        this.fragment = fragment;
        this.activity = activity;
    }
      /*  public static OrdenesViewAdapter newInstance(){
            return new OrdenesViewAdapter();
        }*/

       /* public OrdenesViewBuild config(){
            //TODO
            return new OrdenesViewBuild(this);
        }*/

    @Override
    public Holderview onCreateViewHolder(ViewGroup parent, int vewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_ruta, parent, false);
        return new Holderview(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull Holderview holderview, final int position) {

        holderview.v_codRuta.setText(productlistAdap.get(position).getCodRuta());
        holderview.v_dirRuta.setText(productlistAdap.get(position).getDirRuta());
        holderview.v_provincia.setText(productlistAdap.get(position).getProvincia());
        holderview.v_distrito.setText(productlistAdap.get(position).getDistrito());
        holderview.itemView.setOnClickListener(this::onclick);

                    /*view -> {
                listener.onSelectItem(productlistAdap.get(position).getCodRuta());
            });*/

    }

    private void onclick(View view) {
        log.info("el onclick de clase => " + this.fragment);
        MainActivity main = (MainActivity) activity;
        main.detalleGuiaFragment();
        //fragment.onButtonPressed(this.fragment);
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
        TextView v_distrito;
        CardView itemCarview;

        Holderview(View itemview) {

            super(itemview);
            itemCarview = itemview.findViewById(R.id.cardViewIdruta);

            v_codRuta = itemview.findViewById(R.id.textViewCodRuta);
            v_dirRuta = itemview.findViewById(R.id.textViewDirRuta);
            v_provincia = itemview.findViewById(R.id.textViewProvincia);
            v_distrito = itemview.findViewById(R.id.textViewDistrito);


        }
    }

}
