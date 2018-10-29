package com.business.ventas.login.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.business.ventas.R;
import com.business.ventas.beans.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteActivityAdapter extends RecyclerView.Adapter<ClienteActivityAdapter.Holderview> {

private List<Cliente> productlistAdap;
private Context context;

public ClienteActivityAdapter(List<Cliente> productlist,Context context){
    this.productlistAdap = productlist;
    this.context = context;

}

@Override
    public Holderview onCreateViewHolder (ViewGroup parent, int vewType){
    View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.customcliente,parent,false);

    return new Holderview(layout);

}

    @Override
    public void onBindViewHolder(@NonNull Holderview holderview, final int position) {

        holderview.v_nombre.setText(productlistAdap.get(position).getNombre());
        holderview.v_foto.setImageResource(productlistAdap.get(position).getFoto());

        holderview.itemView.setOnClickListener(new View.OnClickListener()
                                               {
                                             @Override
                                             public void onClick(View view){
                                                 Toast.makeText(context, "no hay datos de " + productlistAdap.get(position).getNombre(), Toast.LENGTH_LONG).show();

                                                 }
                                               });
}

    @Override
    public int getItemCount() {
        return productlistAdap.size();
    }

    public void setfilter(List<Cliente> listcliente){

        productlistAdap= new ArrayList<>();
        productlistAdap.addAll(listcliente);
    notifyDataSetChanged();

    }

    class Holderview extends RecyclerView.ViewHolder{
    ImageView v_foto;
    TextView v_nombre;

    Holderview(View itemview){

        super(itemview);
        v_foto=(ImageView) itemview.findViewById(R.id.product_image);
        v_nombre=(TextView) itemview.findViewById(R.id.product_title);
    }


}

}
