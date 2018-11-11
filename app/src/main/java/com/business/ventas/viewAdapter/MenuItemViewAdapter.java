package com.business.ventas.viewAdapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.business.ventas.R;
import com.business.ventas.utils.LogFactory;
import com.business.ventas.utils.VentasLog;

import java.util.List;

public class MenuItemViewAdapter extends RecyclerView.Adapter<MenuItemViewAdapter.Holderview> {

    private VentasLog log = LogFactory.createInstance().setTag(MenuItemViewAdapter.class.getSimpleName());

    private List<Elemento> lista;
    private OnClickListener listener;

    public MenuItemViewAdapter(List<Elemento> lista, OnClickListener listener) {
        this.lista = lista;
        this.listener = listener;
    }

    @NonNull
    @Override
    public Holderview onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_menu_item, viewGroup, false);
        return new MenuItemViewAdapter.Holderview(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull Holderview holderview, int i) {
        holderview.imgItemMenu.setImageResource(lista.get(i).imagen);
        holderview.txtDecripcion.setText(lista.get(i).descripcion);
        holderview.itemView.setOnClickListener(view -> {
            listener.onclick(lista.get(i));
        });
    }

    @Override
    public int getItemCount() {
        int cantidad = 0;
        if (lista != null)
            cantidad = lista.size();
        return cantidad;
    }

    class Holderview extends RecyclerView.ViewHolder {

        public ImageView imgItemMenu;
        public TextView txtDecripcion;

        public Holderview(@NonNull View view) {
            super(view);
            imgItemMenu = view.findViewById(R.id.imgItemMenu);
            txtDecripcion = view.findViewById(R.id.txtDecripcion);
        }
    }

    public static class Elemento {
        private int id;
        private int imagen;
        private String descripcion;

        public Elemento setId(int id) {
            this.id = id;
            return this;
        }

        public Elemento setImagen(int imagen) {
            this.imagen = imagen;
            return this;
        }

        public Elemento setDescripcion(String descripcion) {
            this.descripcion = descripcion;
            return this;
        }

        public int getId() {
            return id;
        }

        public int getImagen() {
            return imagen;
        }

        public String getDescripcion() {
            return descripcion;
        }
    }

    public interface OnClickListener {
        void onclick(Elemento elemento);
    }
}
