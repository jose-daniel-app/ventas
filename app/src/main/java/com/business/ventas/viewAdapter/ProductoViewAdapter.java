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
import com.business.ventas.utils.Lista;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.Numeros;
import com.business.ventas.utils.SharedPreferenceProductos;
import com.business.ventas.utils.VentasLog;

import java.util.ArrayList;
import java.util.List;

public class ProductoViewAdapter extends RecyclerView.Adapter<ProductoViewAdapter.Holderview> {

    private VentasLog log = LogFactory.createInstance().setTag(ProductoViewAdapter.class.getSimpleName());
    private Lista<Producto> productlistAdap;
    private Activity activity;

    public static ProductoViewAdapter newInstance() {
        return new ProductoViewAdapter();
    }

    public BuildAdapter config() {
        return new BuildAdapter(this);
    }

    public List<Producto> getProductlistAdap() {
        return productlistAdap;
    }

    @NonNull
    @Override
    public ProductoViewAdapter.Holderview onCreateViewHolder(ViewGroup parent, int vewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_producto, parent, false);
        return new ProductoViewAdapter.Holderview(layout);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ProductoViewAdapter.Holderview holderview, final int position) {

        holderview.txtNombre.setText(productlistAdap.get(position).getNombre());
        holderview.txtStock.setText(productlistAdap.get(position).getStock() + "");
        holderview.txtCodigo.setText(productlistAdap.get(position).getItemCode());

        if(productlistAdap.get(position).getPathImg() != null){
            Glide.with(activity).load(productlistAdap.get(position).getPathImg()).into(holderview.img);
        }else{
            holderview.img.setImageResource(R.drawable.ic_photo_black_24dp);
        }

        holderview.txtCantidad.setText(productlistAdap.get(position).getCantidad() + "");

        /*holderview.txtCantidad.setOnFocusChangeListener((view, isFocus) -> {
            if (isFocus)
                holderview.txtCantidad.setText("");
        });*/

        holderview.txtCantidad.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                int cantidad = Numeros.getCantidad(editable.toString());
                productlistAdap.get(position).setCantidad(cantidad);
                productlistAdap.get(position).actualizarPrecioCantidad();
                log.info("cantidad %d del codigo: %s", productlistAdap.get(position).getCantidad(), productlistAdap.get(position).getItemCode());
                //holderview.txtCantidad.setText();
            }
        });

        holderview.cardviewMas.setOnClickListener(view -> {
            int cantidad = Numeros.getCantidad(holderview.txtCantidad.getText().toString());
            productlistAdap.get(position).setCantidad(cantidad + 1);
            productlistAdap.get(position).actualizarPrecioCantidad();
            holderview.txtCantidad.setText((cantidad + 1) + "");
        });

        holderview.cardviewMenos.setOnClickListener(view -> {
            int cantidad = Numeros.getCantidad(holderview.txtCantidad.getText().toString());
            if (cantidad > 0) {
                productlistAdap.get(position).setCantidad(cantidad - 1);
                productlistAdap.get(position).actualizarPrecioCantidad();
                holderview.txtCantidad.setText((cantidad - 1) + "");
            }
        });
    }

    public Lista<Producto> obtenerProductosElegidos() {
        return productlistAdap.filtrar(producto -> producto.getCantidad() > 0);
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
            log.info("codigo: %s", producto.getItemCode());
            int cantidad = Numeros.getCantidad(s.toString());
            producto.setCantidad(cantidad);
            producto.actualizarPrecioCantidad();
            log.info("precio es: " + producto.getPrecioCantidad());
        }

        @Override
        public void afterTextChanged(Editable editable) {
            log.info("afterTextChanged : "+editable.toString());
        }
    }

    @Override
    public int getItemCount() {
        return productlistAdap.size();
    }

    public void setfilter(Lista<Producto> productos) {
        productlistAdap = new Lista<>();
        productlistAdap.addAll(productos);
        notifyDataSetChanged();
    }

    class Holderview extends RecyclerView.ViewHolder {

        TextView txtNombre;
        TextView txtCodigo;
        TextView txtStock;
        ImageView img;
        CardView cardviewMas;
        CardView cardviewMenos;
        EditText txtCantidad;

        public Holderview(View itemview) {
            super(itemview);
            txtNombre = itemView.findViewById(R.id.txtNombre);
            txtCodigo = itemview.findViewById(R.id.txtCodigo);
            txtStock = itemview.findViewById(R.id.txtStock);
            img = itemview.findViewById(R.id.img);
            cardviewMas = itemView.findViewById(R.id.cardviewMas);
            cardviewMenos = itemView.findViewById(R.id.cardviewMenos);
            txtCantidad = itemview.findViewById(R.id.txtCantidad);
        }
    }

    public class BuildAdapter {
        ProductoViewAdapter productoViewAdapter;

        public BuildAdapter(ProductoViewAdapter productoViewAdapter) {
            this.productoViewAdapter = productoViewAdapter;
        }

        public BuildAdapter setProductlistAdap(Lista<Producto> productlistAdap) {
            this.productoViewAdapter.productlistAdap = productlistAdap;
            return this;
        }

        public BuildAdapter setActivity(Activity activity) {
            this.productoViewAdapter.activity = activity;
            return this;
        }

        public ProductoViewAdapter build() {
            return this.productoViewAdapter;
        }
    }
}
