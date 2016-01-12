package com.ipxserver.davidtorrez.fvpos.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by David-Pc on 20/06/2015.
 */
public class solicitudFactura {

    private String client_id;
    private String name;
    private String nit;
    private String branch_id;
    private ArrayList<Product> productos;

    /**
     * Allows to get the JSON String from the Client passed as parameter
     * @param solfac Client object to convert to JSON
     * @return JSON String of the Client passed as parameter
     */
    public static String toJSON(solicitudFactura solfac)
    {
        return toJSONObject(solfac).toString();
    }
    /**
     * This method should be used by this class only, that's why it is private.
     * Allows to get a JSONObject from the Client passed as parameter
     * @param solfac Client Object to convert to JSONObject
     * @return JSONObject representation of the Client passed as parameter
     */
    private static JSONObject toJSONObject(solicitudFactura solfac) {
        JSONObject json = new JSONObject();
        try {
//            json.put("invoice_items",solfac.getProductos() );
            json.put("client_id", solfac.client_id);
            json.put("name",solfac.name);
            json.put("nit",solfac.nit);
            json.put("branch_id",solfac.getBranch_id());
            json.put("invoice_items",solfac.getProductos() );
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return json;
    }
    public void setClient_id(String client_id)
    {
        this.client_id = client_id;
    }
    public void setProductos(ArrayList<Product> productos)
    {
        this.productos = productos;
    }
    public String getClient_id()
    {
        return this.client_id;
    }
    public JSONArray getProductos()
    {
        return Product.toJSONs(this.productos);
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

    public String getBranch_id() {
        return branch_id;
    }

    public void setBranch_id(String branch_id) {
        this.branch_id = branch_id;
    }
}