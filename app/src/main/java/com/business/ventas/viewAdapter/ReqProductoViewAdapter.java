package com.business.ventas.viewAdapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.business.ventas.R;
import com.business.ventas.beans.Producto;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.Numeros;
import com.business.ventas.utils.VentasLog;

import java.util.ArrayList;
import java.util.List;

public class ReqProductoViewAdapter extends RecyclerView.Adapter<ReqProductoViewAdapter.Holderview> {

    private VentasLog log = LogFactory.createInstance().setTag(ReqProductoViewAdapter.class.getSimpleName());
    private List<Producto> productlistAdap;
    private Activity activity;
    private int focusPosicion = 0;

    public static ReqProductoViewAdapter newInstance() {
        return new ReqProductoViewAdapter();
    }

    public BuildAdapter config() {
        return new BuildAdapter(this);
    }

    public List<Producto> getProductlistAdap() {
        return productlistAdap;
    }

    @NonNull
    @Override
    public ReqProductoViewAdapter.Holderview onCreateViewHolder(ViewGroup parent, int vewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_req_producto, parent, false);
        return new ReqProductoViewAdapter.Holderview(layout);
    }

    @SuppressLint("SetTextI18n")
    @Override
        public void onBindViewHolder(@NonNull ReqProductoViewAdapter.Holderview holderview, final int position) {

        Producto producto = productlistAdap.get(position);

        holderview.txtNombre.setText(producto.getNombre());
        holderview.txtCodigo.setText(producto.getItemCode());
        //Glide.with(activity).load(productlistAdap.get(position).getPathImg()).into(holderview.img);

        if(producto.getPathImg() != null){
            Glide.with(activity).load(producto.getPathImg()).into(holderview.img);
        }else{
            holderview.img.setImageResource(R.drawable.ic_photo_black_24dp);
        }

        holderview.txtCantidad.setText(producto.getCantidad() +"");

        holderview.txtCantidad.setOnFocusChangeListener((view, isFocus) -> {
            if (isFocus)
                holderview.txtCantidad.setText("");
        });

        holderview.txtCantidad.addTextChangedListener(new ViewTextHandler(producto));

        holderview.cardviewMas.setOnClickListener(view -> {
            int cantidad = Numeros.getCantidad(holderview.txtCantidad.getText().toString());
            holderview.txtCantidad.setText((cantidad + 1) + "");
        });

        holderview.cardviewMenos.setOnClickListener(view -> {
            int cantidad = Numeros.getCantidad(holderview.txtCantidad.getText().toString());
            if (cantidad > 0) {
                holderview.txtCantidad.setText((cantidad - 1) + "");
            }
        });

        if(this.focusPosicion == position) {
            holderview.txtCantidad.requestFocus();
        }

    }

    private class ViewTextHandler implements TextWatcher {
        Producto producto;

        public ViewTextHandler(Producto producto) {
            this.producto = producto;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence s, int i, int i1, int i2) {
            log.info("cantidad s: " + s.toString());
            int cantidad = Numeros.getCantidad(s.toString());
            producto.setCantidad(cantidad);
            producto.actualizarPrecioCantidad();
            log.info("precio es: " + producto.getPrecioCantidad());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
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
        TextView txtCodigo;
        //TextView txtStock;
        ImageView img;
        CardView cardviewMas;
        CardView cardviewMenos;
        EditText txtCantidad;

        public Holderview(View itemview) {
            super(itemview);
            txtNombre = itemView.findViewById(R.id.txtNombre);
            txtCodigo = itemview.findViewById(R.id.txtCodigo);
            //txtStock = itemview.findViewById(R.id.txtStock);
            img = itemview.findViewById(R.id.img);
            cardviewMas = itemView.findViewById(R.id.cardviewMas);
            cardviewMenos = itemView.findViewById(R.id.cardviewMenos);
            txtCantidad = itemview.findViewById(R.id.txtCantidad);

            txtCantidad.addTextChangedListener(new TextWatcher(){
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    int cantidad = Numeros.getCantidad(txtCantidad.getText().toString());
                    Producto p = productlistAdap.get(getAdapterPosition());
                    p.setCantidad(cantidad);
                    p.actualizarPrecioCantidad();
                    log.info("codigo %s, cantidad %d", p.getItemCode(), p.getCantidad());
                    //eventoProductoAgregado.onProductoAgregado(p);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }

    public class BuildAdapter {
        ReqProductoViewAdapter productoViewAdapter;
        public BuildAdapter(ReqProductoViewAdapter productoViewAdapter) {
            this.productoViewAdapter = productoViewAdapter;
        }

        public BuildAdapter setProductlistAdap(List<Producto> productlistAdap) {
            this.productoViewAdapter.productlistAdap = productlistAdap;
            return this;
        }

        public BuildAdapter setActivity(Activity activity) {
            this.productoViewAdapter.activity = activity;
            return this;
        }

        public ReqProductoViewAdapter build(){
            return this.productoViewAdapter;
        }
    }
}
