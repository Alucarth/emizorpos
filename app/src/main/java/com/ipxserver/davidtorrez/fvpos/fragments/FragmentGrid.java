package com.ipxserver.davidtorrez.fvpos.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.ipxserver.davidtorrez.fvpos.Listeners.ProductReceiver;
import com.ipxserver.davidtorrez.fvpos.R;
import com.ipxserver.davidtorrez.fvpos.adapter.GridAdapter;
import com.ipxserver.davidtorrez.fvpos.models.Product;

import java.util.ArrayList;


/**
 * Created by David Torrez on 13/05/2015.
 */
public class FragmentGrid extends Fragment implements AdapterView.OnItemClickListener
{
    View rootView;
    GridView gv;
    GridAdapter gridAdapter;
    public String tituloCategoria=null;

    private ArrayList<Product> productos;
    public static FragmentGrid newInstance(ArrayList<Product> productos)
    {
        FragmentGrid fragmentGrid = new FragmentGrid();
        Bundle arg = new Bundle();
        arg.putSerializable("productos",productos);
        fragmentGrid.setArguments(arg);
        return fragmentGrid;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        productos = (ArrayList<Product>)getArguments().getSerializable("productos");
        Log.i("Fragment grid", "productos size:" + productos.size());

        //enviar productos
        enviarProductos(productos);
    }

    private void enviarProductos(ArrayList<Product> productos) {
        for(int i =0;i<productos.size();i++)
        {
            Product pro = (Product) productos.get(i);
            if(!pro.getQty().equals("0"))
            {
                Intent intent = new Intent("cast_product");
                intent.putExtra("operacion", ProductReceiver.PRODUCTO_AGREGADO);
                Log.i("David", "parsenado double:" + pro.getCost());
                Double monto = Double.parseDouble(pro.getCost())*Double.parseDouble(pro.getQty());
                intent.putExtra("monto", monto);
                intent.putExtra("producto",pro);
                getActivity().sendBroadcast(intent);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_grid,container,false);
        gv = (GridView) rootView.findViewById(R.id.gridview);


        gridAdapter = new GridAdapter(rootView.getContext(),productos);
        gv.setAdapter(gridAdapter);
        gv.setOnItemClickListener(this);

//        gv.setOnClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
////                Toast.makeText(gv, "" + position,Toast.LENGTH_SHORT).show();
//                Product prod = parent.getSelectedItem();
//                Log.d("david",""+position);
////     Toast.makeText(getApplicationContext(),gv.get(position).getTitle(), Toast.LENGTH_SHORT).show();
//            }
//        });
        setHasOptionsMenu(true);
        return rootView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Product pro = (Product) gridAdapter.getItem(position);

        int cant = Integer.parseInt(pro.getQty().toString())+1;
        pro.setQty(""+cant);
//        pro.setCost(""+pro.getCost()+"x "+pro.getQty());
        gridAdapter.notifyDataSetChanged();

        //enviando al broadcast
        Intent intent = new Intent("cast_product");
        intent.putExtra("operacion", ProductReceiver.PRODUCTO_AGREGADO);
        Log.i("David","parsenado double:"+pro.getCost());
        intent.putExtra("monto", Double.parseDouble(pro.getCost()));
        intent.putExtra("producto",pro);
        getActivity().sendBroadcast(intent);

//        Toast.makeText(view.getContext(),""+pro.getQty(), Toast.LENGTH_SHORT).show();
    }



}
