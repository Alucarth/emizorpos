package com.ipxserver.davidtorrez.fvpos.Listeners;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.ipxserver.davidtorrez.fvpos.PrincipalActivity;
import com.ipxserver.davidtorrez.fvpos.adapter.GridbarAdapter;
import com.ipxserver.davidtorrez.fvpos.models.Product;

import java.util.ArrayList;

/**
 * Created by David-Pc on 26/05/2015.
 */
public class FragmentReceiver extends BroadcastReceiver
{
    public static final int FRAGMENT_FACTURA=5;
    public static final int FRAGMENT_TABSWIPE=6;
    public static final int FRAGMENT_LISTA=7;
    public static final int FRAGMENT_EMPRESA=8;
    public static final int FRAGMENT_EDITTABSWIPE=10;

    private final GridbarAdapter gridAdapter;
    private final PrincipalActivity main;
    private ArrayList<Product> listaProductos=null;
    private Double monto;
    public FragmentReceiver( GridbarAdapter gridAdapter,PrincipalActivity main)
    {
        this.gridAdapter = gridAdapter;
        this.main = main;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
//        int operacion = intent.getIntExtra("operacion",-1);
        Log.v("Brian","get into onReceive from FragmentReceiver");
        cambiarFragmento(intent);
    }
    private void cambiarFragmento(Intent intent) {
        int fragment_id = intent.getIntExtra("operacion",-1);

        if(fragment_id==FRAGMENT_FACTURA)
        {
            listaProductos = (ArrayList<Product>)intent.getSerializableExtra("lista_seleccionados");
            monto = intent.getDoubleExtra("monto",-1);
            Log.i("David", "tamao del list product size " + listaProductos.size());
//            Product product = new Product();
//            product.setCost("23 ");
//            product.setQty("222");
//            product.setId("pa1");
//            product.setNotes("its work2");
//            main.getFragmentFactura().listAdapter.adcionarProducto(product);
//
        }
        main.cambiarFragmento(fragment_id);



    }

    public ArrayList<Product> getListaProductos() {
        Log.i("David", "retornando lista  size " + listaProductos.size());
        return listaProductos;
    }
    public void setListaProductos(ArrayList<Product> productos)
    {
        listaProductos = productos;
    }

    public Double getMonto() {
        return monto;
    }
}
