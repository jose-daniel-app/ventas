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

    public RutaViewAdapter(List<Ruta> productlist, RutaFragment fragment) {
        this.productlistAdap = productlist;
        this.fragment = fragment;
    }

    @Override
    public Holderview onCreateViewHolder(ViewGroup parent, int vewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_ruta, parent, false);
        return new Holderview(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull Holderview holderview, final int position) {

        holderview.v_codRuta.setText(productlistAdap.get(position).getCodRuta());
        holderview.v_dirRuta.setText(productlistAdap.get(position).getDirRuta());
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

    public void setfilter(List<Ruta> listruta) {

        productlistAdap = new ArrayList<>();
        productlistAdap.addAll(listruta);
        notifyDataSetChanged();

    }

    class Holderview extends RecyclerView.ViewHolder {

        TextView v_codRuta;
        TextView v_dirRuta;
        CardView itemCarview;


        Holderview(View itemview) {

            super(itemview);
            itemCarview = itemview.findViewById(R.id.cardViewIdruta);

            v_codRuta = itemview.findViewById(R.id.textView2);
            v_dirRuta = itemview.findViewById(R.id.textView3);

        }

    }

}
