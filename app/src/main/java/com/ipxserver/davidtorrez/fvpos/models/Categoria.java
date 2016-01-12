package com.ipxserver.davidtorrez.fvpos.models;

import java.util.ArrayList;

/**
 * Created by David-Pc on 12/06/2015.
 */
public class Categoria
{
    private String nombre;
    private String jsonArray;

    public Categoria(String nombre, String jsonArray)
    {
        this.nombre = nombre;
        this.jsonArray = jsonArray;


    }

    public String getNombre() {
        return nombre;
    }

    public void setProdcutos(ArrayList<Product> productosAModificar)
    {
        ArrayList<Product> lista= this.getProductos();
        for(int i =0;i<lista.size();i++)
        {
            Product producto = (Product) lista.get(i);
            for (int j=0;j<productosAModificar.size();j++)
            {
                Product seleccionado = (Product) productosAModificar.get(j);
                if(producto.getId().equals(seleccionado.getId()))
                {
                    lista.set(i,seleccionado);
                }
            }

        }

        this.jsonArray = Product.updateJSONArray(lista).toString();
    }

    public ArrayList<Product> getProductos() {
        ArrayList<Product> productos = Product.fromJsonArray(jsonArray);
        return productos;
    }
}
