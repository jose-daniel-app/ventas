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
import com.business.ventas.beans.Cliente;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.VentasLog;
import com.business.ventas.ordenes.views.ClienteFragment;
import com.business.ventas.ordenes.views.ProductosFragment;

import java.util.ArrayList;
import java.util.List;

public class ClienteViewAdapter extends RecyclerView.Adapter<ClienteViewAdapter.Holderview> {

    private VentasLog log = LogFactory.createInstance().setTag(ClienteViewAdapter.class.getSimpleName());
    private List<Cliente> productlistAdap;
    //private ClienteFragment fragment;
    private SeleccionCliente seleccionCliente;

    public ClienteViewAdapter(List<Cliente> productlist, SeleccionCliente seleccionCliente) {
        this.seleccionCliente = seleccionCliente;
        this.productlistAdap = productlist;
    }

    @Override
    public Holderview onCreateViewHolder(ViewGroup parent, int vewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_cliente, parent, false);
        return new Holderview(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull Holderview holderview, final int position) {
        holderview.v_nombre.setText(productlistAdap.get(position).getNombre());
        holderview.v_ruc.setText(productlistAdap.get(position).getRuc());
        holderview.v_direccion.setText(productlistAdap.get(position).getDireccion());
        holderview.v_foto.setImageResource(productlistAdap.get(position).getFoto());
        holderview.itemView.setOnClickListener(view -> {
            seleccionCliente.escucharClienteSeleccionado(productlistAdap.get(position));
        });
    }

    /*private void onclick(View view) {
        fragment.getMainActivity().newFragmentHandler().changeFragment(ProductosFragment.newInstance());
    }*/

    @Override
    public int getItemCount() {
        return productlistAdap.size();
    }

    public void setfilter(List<Cliente> listcliente) {

        productlistAdap = new ArrayList<>();
        productlistAdap.addAll(listcliente);
        notifyDataSetChanged();

    }

    class Holderview extends RecyclerView.ViewHolder {
        ImageView v_foto;
        TextView v_nombre;
        TextView v_ruc;
        TextView v_direccion;
        CardView itemCarview;


        Holderview(View itemview) {

            super(itemview);
            itemCarview = itemview.findViewById(R.id.cardViewIdcliente);
            v_foto = (ImageView) itemview.findViewById(R.id.product_image);
            v_nombre = (TextView) itemview.findViewById(R.id.textView2);
            v_ruc = itemview.findViewById(R.id.textView3);
            v_direccion = itemview.findViewById(R.id.textView4);
        }

    }

    @FunctionalInterface
    public interface SeleccionCliente {
        void escucharClienteSeleccionado(Cliente cliente);
    }

}
