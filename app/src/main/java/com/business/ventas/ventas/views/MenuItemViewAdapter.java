package com.business.ventas.ventas.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.business.ventas.R;

public class MenuItemViewAdapter extends  RecyclerView.Adapter<MenuItemViewAdapter.Holderview> {




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

    class Holderview extends RecyclerView.ViewHolder {

        public ImageView imgItemMenu;
        public TextView txtDecripcion;

        public Holderview(@NonNull View view) {
            super(view);
            imgItemMenu = view.findViewById(R.id.imgItemMenu);
            txtDecripcion = view.findViewById(R.id.txtDecripcion);
        }
    }

}
