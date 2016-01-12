package com.ipxserver.davidtorrez.fvpos.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ipxserver.davidtorrez.fvpos.Listeners.ProductReceiver;
import com.ipxserver.davidtorrez.fvpos.R;
import com.ipxserver.davidtorrez.fvpos.Util.Font;
import com.ipxserver.davidtorrez.fvpos.models.Product;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by keyrus on 18-05-15.
 */
public class GridAdapter extends BaseAdapter
{
    ArrayList<Product> lista;

    Context context;


    public GridAdapter(Context context,ArrayList<Product> lista){
        this.context = context;
        this.lista = lista;

//        lista = new ArrayList<Product>();
//        for(int i=0;i<20;i++)
//        {
//            Product producto = new Product();
//            producto.setId("a"+i);
//            producto.setNotes(" Producto n " + i);
//            producto.setCost((i + 2) + "");
//            lista.add(producto);
//
//        }
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int i) {
        return lista.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView = view;
        TextView txtNotes;
        TextView txtCost;
        TextView txtQty;
        TextView txtSubtotal;
        ImageView img;
        CardView cardView;

        if (rootView ==null)
        {
            rootView = inflater.inflate(R.layout.gridview_item,viewGroup,false);
            txtNotes= (TextView) rootView.findViewById(R.id.txtNotes);
            txtCost = (TextView) rootView.findViewById(R.id.txtCost);
            txtQty = (TextView) rootView.findViewById(R.id.txtQty);
            //txtSubtotal = (TextView) rootView.findViewById(R.id.txtSubtotal);
            txtSubtotal = (TextView) rootView.findViewById(R.id.txtCost);
            img = (ImageView) rootView.findViewById(R.id.img_ic_remove);
            cardView =(CardView) rootView.findViewById(R.id.cvgrid);
            rootView.setTag(new ViewHolder(cardView,img,txtNotes,txtCost,txtQty,txtSubtotal));
        }
        else
        {
            ViewHolder viewHolder =(ViewHolder) rootView.getTag();
            txtNotes =viewHolder.textNotes;
            txtCost =viewHolder.textCost;
            txtQty = viewHolder.textQty;
            txtSubtotal = viewHolder.textSubtotal;
            img = viewHolder.imageButton;
            cardView =viewHolder.cardView;
        }
        final Product producto = lista.get(i);

        final int contador =i;


        txtNotes.setText(producto.getNotes());




        if(Integer.parseInt(producto.getQty())>0)
        {

            img.setVisibility(View.VISIBLE);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("David", "posision " + contador);
                    Product prod = (Product) lista.get(contador);
                    int cantidad = Integer.parseInt(prod.getQty());
                    cantidad--;
                    prod.setQty("" + cantidad);
                    //enviando al broadcast

                    Intent intent = new Intent("cast_product");
                    intent.putExtra("operacion", ProductReceiver.PRODUCTO_ELIMINADO);
                    intent.putExtra("monto",Double.parseDouble(prod.getCost()));
                    intent.putExtra("producto",prod);
                    context.getApplicationContext().sendBroadcast(intent);
                    Log.i("David","Broad Cast enviado");
                    notifyDataSetChanged();

                }
            });
            txtQty.setText("x"+producto.getQty());
            int cantidad = Integer.parseInt(producto.getQty());
            Double costo= Double.parseDouble(producto.getCost());

            Double subtotal = (Double)(cantidad*costo);
            //Double subtotal = (double)(Double.parseDouble(producto.getCost())*Integer.parseInt(producto.getQty()));
            txtSubtotal.setText(subtotal.toString() + "Bs");
            itemSeleccionado(rootView);

            txtNotes.setTextColor(Color.WHITE);
            txtQty.setTextColor(Color.WHITE);
            txtCost.setTextColor(Color.WHITE);
            txtSubtotal.setTextColor(Color.WHITE);
            cardView.setCardBackgroundColor(Color.parseColor("#3F51B5"));
            txtQty.setVisibility(View.VISIBLE);
            txtSubtotal.setVisibility(View.VISIBLE);
            txtCost.setText(producto.getCost() + " Bs");
            Font.NINBUS.apply(context,txtCost);

        }else{
//            itemNormal(rootView);
            cardView.setCardBackgroundColor(Color.parseColor("#ffffff"));
            txtCost.setText(producto.getCost() + " Bs");
            Font.NINBUS.apply(context, txtCost);



            txtNotes.setTextColor(Color.BLACK);
            txtQty.setTextColor(Color.BLACK);
            txtCost.setTextColor(Color.parseColor("#3F51B5"));
            txtSubtotal.setTextColor(Color.BLACK);

            txtQty.setVisibility(View.INVISIBLE);
            txtSubtotal.setVisibility(View.INVISIBLE);

//            float elevation = 2;
//            rootView.setElevation(elevation);
            img.setVisibility(View.INVISIBLE);
        }







        return rootView;
    }

    private void itemNormal(View rootView) {

        rootView.setBackgroundResource(R.drawable.layer_card_background);

    }

    private void itemSeleccionado(View rootView) {

        rootView.setBackgroundResource(R.drawable.layer_card_selected);
    }

    public ArrayList<Product> getLista()
    {
        ArrayList<Product> listProduct=null;
        for(int i=0;i<lista.size();i++)
        {
            Product pro = (Product) lista.get(i);
            if(Integer.parseInt(pro.getQty())>0)
            {
                listProduct.add(pro);
            }
        }

        return listProduct;
    }
    private static class ViewHolder
    {
        public final ImageView imageButton;
        public final TextView textNotes,textCost,textSubtotal,textQty;
        public final CardView cardView;

        private ViewHolder(CardView cardView,ImageView imageButton, TextView textNotes, TextView textCost,TextView textQty,TextView textSubtotal) {
            this.cardView = cardView;
            this.imageButton = imageButton;
            this.textNotes = textNotes;
            this.textCost = textCost;
            this.textQty = textQty;
            this.textSubtotal = textSubtotal;
        }


    }
    double redondear(double d)
    {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(d));
    }

}
