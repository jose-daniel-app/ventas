package com.business.ventas.viewAdapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.business.ventas.R;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.VentasLog;

public class ItemBoletaViewAdapter extends RecyclerView.Adapter<ItemBoletaViewAdapter.Holderview>{

    private VentasLog log = LogFactory.createInstance().setTag(ItemBoletaViewAdapter.class.getSimpleName());
    private Activity activity;

    public ItemBoletaViewAdapterBuild build(Activity activity){
        return new ItemBoletaViewAdapterBuild();
    }

    @NonNull
    @Override
    public Holderview onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull Holderview holderview, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public static class Holderview extends RecyclerView.ViewHolder{

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



    private class ItemBoletaViewAdapterBuild{



    }

}
