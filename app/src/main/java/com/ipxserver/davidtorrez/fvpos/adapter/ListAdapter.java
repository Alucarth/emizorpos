package com.ipxserver.davidtorrez.fvpos.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ipxserver.davidtorrez.fvpos.R;
import com.ipxserver.davidtorrez.fvpos.models.Product;

import java.util.ArrayList;

/**
 * Created by David-Pc on 01/06/2015.
 */
public class ListAdapter extends BaseAdapter
{
    public ArrayList<Product> lista;
    ArrayList<Product> listaFactura;
    Context context;
    public ListAdapter (Context context,ArrayList<Product> lista)
    {
        this.context = context;
        this.lista = lista;
        listaFactura = new ArrayList<Product>();
        Product section = new Product();
        section.setQty("Cantidad");
        section.setNotes("Descripcion");
        section.setCost("Importe Bs");
        listaFactura.add(section);
        for(int i=0;i<lista.size();i++)
        {
            Product producto = (Product)lista.get(i);

            listaFactura.add(producto);

        }
//        Product product = new Product();
//        product.setCost("3 ");
//        product.setQty("2");
//        product.setId("p1");
//        product.setNotes("its work");
//        adcionarProducto(product);

    }
    @Override
    public int getCount() {
        return listaFactura.size();
    }

    @Override
    public Object getItem(int position) {
        return listaFactura.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View item = inflater.inflate(R.layout.list_producto_item, null);

        TextView txtQty = (TextView) item.findViewById(R.id.txtCantidad);
        TextView txtNotes = (TextView) item.findViewById(R.id.txtDescripcion);
        TextView txtCosto = (TextView) item.findViewById(R.id.txtCosto);
        Product producto = (Product) listaFactura.get(position);
        if(position==0)
        {
//            txtQty.setBackgroundColor(Color.parseColor("#9FA8DA"));
//            txtNotes.setBackgroundColor(Color.parseColor("#9FA8DA"));
//
//            txtCosto.setBackgroundColor(Color.parseColor("#9FA8DA"));

            item.setBackgroundColor(Color.parseColor("#C5CAE9"));
            txtCosto.setTextSize(16);
            txtNotes.setTextSize(16);
            txtQty.setTextSize(16);
            txtCosto.setTextColor(Color.BLACK);

            txtQty.setText(producto.getQty());
            txtNotes.setText(producto.getNotes());
            txtCosto.setText(producto.getCost());
        }
        else{
//            Product producto = (Product) listaFactura.get(position);
            txtQty.setText(producto.getQty());
            Double importe = Double.parseDouble(producto.getQty())*Double.parseDouble(producto.getCost());
            txtNotes.setText(producto.getNotes());
            txtCosto.setText(String.format("%.2f",importe));
        }
        return item;

    }
    public void adcionarProducto(Product product)
    {
        lista.add(product);

    }
}
