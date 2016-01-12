package com.ipxserver.davidtorrez.fvpos.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by keyrus on 17-05-15.
 */
public class Product implements Serializable
{
    public final static String ID ="id";
    public final static String NOTES="notes";
    public final static String COST="cost";
    public final static String QTY="qyt";
    public final static String PRODUCT_KEY="product_key";
    private String product_key;
    private String notes;
    private String cost;
    private String id;
    private String qty="0";


    public static Product fromJson(String jsonText)
    {
        Product product = new Product();

        try {
            JSONObject json = new JSONObject(jsonText);

            if(json.has(Product.ID))
            {
                product.setId(json.getString(Product.ID));
            }
            if(json.has(Product.NOTES))
            {
                product.setNotes(json.getString(Product.NOTES));
            }
            if(json.has(Product.COST))
            {
                product.setCost(json.getString(Product.COST));
            }
            if(json.has(Product.PRODUCT_KEY))
            {
                product.setProduct_key(json.getString(Product.PRODUCT_KEY));
            }
            if(json.has(Product.QTY))
            {
                product.setQty(json.getString(Product.QTY));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return product;
    }

    public static ArrayList<Product> fromJsonArray(String jsonArray)
    {
        ArrayList<Product> productos= new ArrayList<Product>();
        Product producto;
        try {
            JSONArray array = new JSONArray(jsonArray);

            for(int i =0;i<array.length();i++)
            {
                JSONObject json = array.getJSONObject(i);
                producto = Product.fromJson(json.toString());
                productos.add(producto);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return productos;
    }

    private static JSONObject toJSONObject(Product producto) {
        JSONObject json = new JSONObject();
        try{

            json.put("id", producto.getId());
//            json.put("amount", producto.getCost());
            json.put("qty",producto.getQty());
//
//            json.put("boni", producto.getBoni());
//            json.put("desc", producto.getDesc());

        }catch (JSONException ex) {
            ex.printStackTrace();
        }
        return json;
    }
    private static JSONObject updateJSONObject(Product producto)
    {
        JSONObject json = new JSONObject();
        try {
            json.put(Product.ID,producto.getId());
            json.put(Product.NOTES,producto.getNotes());
            json.put(Product.COST,producto.getCost());
            json.put(Product.QTY,producto.getQty());
            json.put(Product.PRODUCT_KEY,producto.getProduct_key());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static JSONArray updateJSONArray(ArrayList<Product> products)
    {
        JSONArray actualizacion = new JSONArray();
        for(int i=0;i<products.size();i++)
        {
            Product product = (Product)products.get(i);
            JSONObject jsonObject = updateJSONObject(product);
            actualizacion.put(jsonObject);
        }
        return actualizacion;
    }

    public static JSONArray toJSONs(ArrayList<Product> productos) {
        JSONArray productsArray = new JSONArray();
        for (int i = 0; i < productos.size(); i++) {
            Product producto = (Product)productos.get(i);

            JSONObject jsonObject = toJSONObject(producto);
            productsArray.put(jsonObject);
        }
//        return productsArray.toString();
        return productsArray;
    }

    public String getProduct_key() {
        return product_key;
    }

    public void setProduct_key(String product_key) {
        this.product_key = product_key;
    }

    private boolean habilitado=false;

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }
}
