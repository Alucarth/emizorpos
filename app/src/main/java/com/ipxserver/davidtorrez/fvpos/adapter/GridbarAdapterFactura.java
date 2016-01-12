package com.ipxserver.davidtorrez.fvpos.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ipxserver.davidtorrez.fvpos.R;
import com.ipxserver.davidtorrez.fvpos.models.ItemBar;

import java.util.ArrayList;

/**
 * Created by David-Pc on 29/06/2015.
 */
public class GridbarAdapterFactura extends BaseAdapter
{
    ArrayList<ItemBar> items;
    Context context;
    Double saldo;
    public GridbarAdapterFactura(Context context)
    {
        this.context = context;
        this.context = context;
        items = new ArrayList<ItemBar>();

        ItemBar ib = new ItemBar();
        ib.setNumero(0.0);
        ib.setDescripcion("Subtotal");
        items.add(ib);

        ib = new ItemBar();
        ib.setNumero(0.0);
        ib.setDescripcion("Total");
        items.add(ib);
        saldo = 0.0;
    }
    public void incrementar(Double monto)
    {
        saldo = saldo + monto;
        if(items!=null)
        {
            for(int i=0;i<items.size();i++)
            {
                ItemBar it = (ItemBar) items.get(i);
                it.setNumero(saldo);
            }
            notifyDataSetChanged();
        }
    }

    public void disminuir(Double monto)
    {
        saldo = saldo -monto;
        if(items!=null)
        {
            for(int i=0;i<items.size();i++)
            {
                ItemBar it = (ItemBar) items.get(i);
                it.setNumero(saldo);
            }
            notifyDataSetChanged();
        }
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rootView = view;
        if(rootView==null)
        {
            rootView =  inflater.inflate(R.layout.gridbar_item,viewGroup,false);
        }
        TextView txtNumero =(TextView)rootView.findViewById(R.id.txtNumero);
//        TextView txtDescripcion= (TextView) rootView.findViewById(R.id.txtDescripcion);

        ItemBar item = (ItemBar) items.get(i);

        txtNumero.setText("Bs "+String.format("%.2f",item.getNumero()));
//        txtDescripcion.setText(item.getDescripcion());

        txtNumero.setTextColor(Color.parseColor("#76FF03"));
//        txtDescripcion.setTextColor(Color.parseColor("#76FF03"));

        return rootView;
    }

    public Double getSaldo() {
        return saldo;
    }
}
