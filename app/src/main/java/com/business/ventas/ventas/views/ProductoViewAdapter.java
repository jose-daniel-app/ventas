package com.business.ventas.ventas.views;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.business.ventas.R;
import com.business.ventas.beans.Producto;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.VentasLog;

import java.util.ArrayList;
import java.util.List;

public class ProductoViewAdapter extends RecyclerView.Adapter<ProductoViewAdapter.Holderview> {


    private VentasLog log = LogFactory.createInstance().setTag(ProductoViewAdapter.class.getSimpleName());
    private List<Producto> productlistAdap;
    private Activity activity;

    public ProductoViewAdapter(List<Producto> productlist, Activity activity) {
        this.productlistAdap = productlist;
        this.activity = activity;
    }

    @Override
    public ProductoViewAdapter.Holderview onCreateViewHolder(ViewGroup parent, int vewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_producto, parent, false);
        return new ProductoViewAdapter.Holderview(layout);
    }


    @Override
    public void onBindViewHolder(@NonNull ProductoViewAdapter.Holderview holderview, final int position) {

        holderview.txtNombre.setText(productlistAdap.get(position).getNombre());
        holderview.txtDecripcion.setText(productlistAdap.get(position).getDescripcion());
        holderview.img.setImageResource(productlistAdap.get(position).getImg());

        holderview.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return productlistAdap.size();
    }

    public void setfilter(List<Producto> listProducto) {

        productlistAdap = new ArrayList<>();
        productlistAdap.addAll(listProducto);
        notifyDataSetChanged();

    }

    class Holderview extends RecyclerView.ViewHolder {

        TextView txtNombre;
        TextView txtDecripcion;
        ImageView img;

        public Holderview(View itemview) {
            super(itemview);
            txtNombre = itemView.findViewById(R.id.txtNombre);
            txtDecripcion = itemView.findViewById(R.id.txtDecripcion);
            img = itemview.findViewById(R.id.img);
        }


    }
}