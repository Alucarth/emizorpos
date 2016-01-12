package com.ipxserver.davidtorrez.fvpos.Listeners;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.ipxserver.davidtorrez.fvpos.adapter.GridbarAdapter;
import com.ipxserver.davidtorrez.fvpos.fragments.FragmentTabswipe;
import com.ipxserver.davidtorrez.fvpos.models.Product;

import java.util.ArrayList;

/**
 * Created by David Torrez on 20/05/2015.
 */
public class ProductReceiver extends BroadcastReceiver
{
    //Todo: ver si esto ayudara en el envio del broadcast
    public static final int PRODUCTO_AGREGADO=0;

    public static final int PRODUCTO_ELIMINADO=1;
    public static final int PRODUCTO_ACTUALIZADO=2;
    public static final int FRAGMENT_FACTURA=5;
    public static final int FRAGMENT_TABSWIPE=6;
    public static final int FRAGMENT_LISTA=7;
    public static final int ACTUALIZAR_LISTA=11;
//   ArrayAdapter <Product> arrayAdapter;
    ArrayList<Product> listaProductos;
    private final GridbarAdapter gridAdapter;
    private final FragmentTabswipe fragmentTabswipe;


    public ProductReceiver(GridbarAdapter gridAdapter,FragmentTabswipe fragmentTabswipe)
    {
        this.gridAdapter = gridAdapter;
        this.fragmentTabswipe = fragmentTabswipe;

        listaProductos = new ArrayList<Product>();

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int operacion = intent.getIntExtra("operacion",-1);
        switch (operacion)
        {
            case PRODUCTO_AGREGADO: agregarProducto(intent);
                break;
            case PRODUCTO_ELIMINADO: eliminarProducto(intent);
                break;
            case ACTUALIZAR_LISTA: actualizarLista(intent);
                break;

        }

        Log.i("David","on recieve se activo XD metodo "+operacion);
    }

    private void actualizarLista(Intent intent) {
        listaProductos = (ArrayList<Product>) intent.getSerializableExtra("lista_editada");
    }
    public void updateList(ArrayList<Product> listaModificada)
    {
        listaProductos = listaModificada;
    }

    private void eliminarProducto(Intent intent) {
        Double monto = intent.getDoubleExtra("monto", -1);
        Product producto = (Product) intent.getSerializableExtra("producto");
        gridAdapter.disminuir(monto);
        //Todo: Revisar si los objetos estan correctos

        for(int i=0;i<listaProductos.size();i++)
        {
            Product p = (Product) listaProductos.get(i);
            if(p.getId().equals(producto.getId()))
            {
                if(Integer.parseInt(producto.getQty())>1)
                {
                    p.setQty(producto.getQty());
                    listaProductos.set(i, p);
                }
                else
                {
                    listaProductos.remove(i);
                }

            }

        }


    }


    private void agregarProducto(Intent intent)
    {
        Double monto= intent.getDoubleExtra("monto", -1);
        Product producto = (Product) intent.getSerializableExtra("producto");

        gridAdapter.incrementar(monto);
        boolean enlista=false;
        for(int i=0;i<listaProductos.size();i++)
        {
            Product p = (Product) listaProductos.get(i);
            if(p.getId().equals(producto.getId()))
            {
                enlista = true;
                p.setQty(producto.getQty());
                listaProductos.set(i,p);
            }

        }
        if(!enlista)
        {
            listaProductos.add(producto);
        }

    }

    public ArrayList<Product> getListaProductos() {
        return listaProductos;
    }
    public Double getMonto()
    {
        return gridAdapter.getSaldo();
    }
}
