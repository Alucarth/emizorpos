package com.ipxserver.davidtorrez.fvpos.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by David-Pc on 07/06/2015.
 */
public class Account
{
    private String TAG="David";
    private String first_name,last_name;
    //TODO: tratar productos XD ver logica de envio
    private String productos;
    private ArrayList<Categoria> categorias;
   //para json response
   private String name;
    private String nit;
    private String address1;
    private String address2;
    private String num_auto;
    private String fecha_limite;
    private String subdominio;

    private String AccountJsonText;

    private ArrayList<Branches> branches;


    public Account(String jsonText)
    {

        this.AccountJsonText = jsonText;
        try {
            JSONObject json = new JSONObject(jsonText);
            if(json.has("branch"))
            {
                setBranches(Branches.fromArrayJson(json.getString("branch")));
            }
            if(json.has("first_name"))
            {
                setFirst_name(json.getString("first_name"));
            }
            if(json.has("last_name"))
            {
                setLast_name(json.getString("last_name"));
            }
            if(json.has("productos"))
            {
//                JSONArray jsonArrayProductos = new JSONArray(json.getString("productos"));
                JSONObject jsonProducto = new JSONObject(json.getString("productos"));
                JSONArray jsonArrayCategorias = new JSONArray(json.getString("categorias"));
                categorias = new ArrayList<Categoria>();
                for(int i=0;i<jsonArrayCategorias.length();i++)
                {
                     JSONObject jsonObject = jsonArrayCategorias.getJSONObject(i);
                     String nombreCategoria = jsonObject.getString("name");
                     String arrayProduto=null;
                     if(jsonProducto.has(nombreCategoria))
                     {
                         arrayProduto = jsonProducto.getString(nombreCategoria);
                     }
//                     JSONObject jsonArray = jsonArrayProductos.getJSONObject(i);
//                     String jsonproductos = jsonArray.getString(nombreCategoria);
                    Log.i(TAG, "categoria: "+nombreCategoria);
                    Log.i(TAG, "productos: "+arrayProduto);
                    Categoria categoria = new Categoria(nombreCategoria,arrayProduto);
                    categorias.add(categoria);
                }

            }
            if(json.has("name"))
            {
                name = json.getString("name");
            }
            if(json.has("nit"))
            {
                nit = json.getString("nit");
            }
            if(json.has("address1"))
            {
                address1=json.getString("address1");
            }
            if(json.has("address2"))
            {
                address2=json.getString("address2");
            }
            if(json.has("num_auto"))
            {
                num_auto=json.getString("num_auto");
            }
            if(json.has("fecha_limite"))
            {
                fecha_limite=json.getString("fecha_limite");
            }
            if(json.has("subdominio"))
            {
                setSubdominio(json.getString("subdominio"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String getSubdominio() {
        return subdominio;
    }

    public void setSubdominio(String subdominio) {
        this.subdominio = subdominio;
    }

    public ArrayList<Branches> getBranches() {
        return branches;
    }

    public void setBranches(ArrayList<Branches> branches) {
        this.branches = branches;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getProductos() {
        return productos;
    }

    public void setProductos(String productos) {
        this.productos = productos;
    }

    public ArrayList<Categoria> getCategorias() {
        return categorias;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getNum_auto() {
        return num_auto;
    }

    public void setNum_auto(String num_auto) {
        this.num_auto = num_auto;
    }

    public String getFecha_limite() {
        return fecha_limite;
    }

    public void setFecha_limite(String fecha_limite) {
        this.fecha_limite = fecha_limite;
    }

    public String getAccountJsonText() {
        return AccountJsonText;
    }
}
