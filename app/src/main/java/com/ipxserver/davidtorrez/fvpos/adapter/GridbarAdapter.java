package com.ipxserver.davidtorrez.fvpos.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ipxserver.davidtorrez.fvpos.R;
import com.ipxserver.davidtorrez.fvpos.fragments.FragmentTabswipe;
import com.ipxserver.davidtorrez.fvpos.models.ItemBar;

import java.util.ArrayList;

/**
 * Created by David Torrez on 20/05/2015.
 */
public class GridbarAdapter extends BaseAdapter
{
    ArrayList <ItemBar> items;
    Context context;
    Double saldo;
    final FragmentTabswipe tabswipe;
    public GridbarAdapter(FragmentTabswipe tabswipe,Context context)
    {
        this.tabswipe = tabswipe;
        this.context = context;
        items = new ArrayList<ItemBar>();

        ItemBar ib = new ItemBar();
        ib.setNumero(0.0);
        Log.v("Brian","1ro");
        ib.setDescripcion("Subtotal");
        items.add(ib);

        ib = new ItemBar();
        ib.setNumero(0.0);
        Log.v("Brian","2do");
        ib.setDescripcion("Total");
        items.add(ib);

        saldo = 0.0;

    }
    public void incrementar(Double monto)
    {

        saldo = saldo + monto;
        verificarVenta();
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
    void verificarVenta()
    {
        if(saldo>0)
        {
            tabswipe.activarVenta(true);
        }
        else
        {
            tabswipe.activarVenta(false);
        }
    }
    public void disminuir(Double monto)
    {

        saldo = saldo -monto;
        verificarVenta();
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
//        if(i==0) {
        Log.i("david"," numero-> "+item.getNumero());
            txtNumero.setText("Bs " + String.format("%.2f", item.getNumero()));
//        }
//        else{
//            txtNumero.setText("PAGAR");
//        }

        Log.i("david"," descripsion-> "+item.getDescripcion());
//        txtDescripcion.setText(item.getDescripcion());

        txtNumero.setTextColor(Color.parseColor("#76FF03"));

//        txtDescripcion.setTextColor(Color.parseColor("#76FF03"));
        Log.v("david", "valor seteado");
        return rootView;
    }

    public Double getSaldo() {
        return saldo;
    }

    //Gridbar.setOnItemClickListener(new OnItemClickListener() {
    //public void onItemClick(AdapterView<?> parent, View v,
    //int position, long id) {
       // Intent intent = new Intent(this,
         //       MyNewActivity.class);
     //   startActivity(intent);
    //}
//});
    public void nextStep(){
        Log.v("Brian","this is us");
    }
}
