package com.ipxserver.davidtorrez.fvpos.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ipxserver.davidtorrez.fvpos.R;
import com.ipxserver.davidtorrez.fvpos.models.FacturaCardItem;

import java.util.ArrayList;

/**
 * Created by David-Pc on 24/06/2015.
 */
public class FacturaCardAdapter extends RecyclerView.Adapter<FacturaCardAdapter.FacturaViewHolder>
{
    ArrayList<FacturaCardItem> listaFacturados;
    public FacturaCardAdapter(ArrayList<FacturaCardItem> listaFacturados)
    {
           this.listaFacturados = listaFacturados;
    }
    @Override
    public FacturaViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_cardview_item,viewGroup,false);
        FacturaViewHolder facturaViewHolder = new FacturaViewHolder(view);
        return facturaViewHolder;
    }

    @Override
    public void onBindViewHolder(FacturaViewHolder facturaViewHolder, int i) {
        FacturaCardItem facturaCardItem = (FacturaCardItem)listaFacturados.get(i);

        facturaViewHolder.numfact.setText("Factura No."+facturaCardItem.getNumero());
        facturaViewHolder.monto.setText(facturaCardItem.getMonto()+" Bs");
        facturaViewHolder.fecha.setText(facturaCardItem.getFecha());
        facturaViewHolder.icono.setImageResource(R.drawable.ic_action_print_black);
    }

    @Override
    public int getItemCount() {
        return listaFacturados.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    public void addFactura(FacturaCardItem facturaCardItem)
    {
        this.listaFacturados.add(facturaCardItem);
    }

    public static class FacturaViewHolder extends RecyclerView.ViewHolder
    {
        CardView cardView;
        TextView numfact;
        TextView monto;
        TextView fecha;
        ImageView icono;

        public FacturaViewHolder(View itemView)
        {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cv);
            numfact = (TextView) itemView.findViewById(R.id.factura);
            monto = (TextView) itemView.findViewById(R.id.monto);
            fecha = (TextView) itemView.findViewById(R.id.fecha);
            icono = (ImageView) itemView.findViewById(R.id.ic_print);
        }
    }
}
