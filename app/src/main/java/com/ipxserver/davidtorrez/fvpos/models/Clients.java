package com.ipxserver.davidtorrez.fvpos.models;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Vector;

/**
 *
 * @author David Torrez
 */
public class Clients implements Serializable
{
    private String id;
    private String name;
    private String nit;
    private String public_id;

    //nueva version generica cloud
    private String vat_number;
    private String matricula;

    public static Clients fromJson(String jsonText)
    {
        Clients client = new Clients();
        try {
            JSONObject json =new  JSONObject(jsonText);
            if(json.has("id"))
            {
                client.setId(json.getString("id"));
            }
            if(json.has("name"))
            {
                client.setName(json.getString("name"));
            }
            if(json.has("nit"))
            {
                client.setNit(json.getString("nit"));
            }
            if(json.has("vat_number"))
            {
                client.setVat_number(json.getString("vat_number"));
            }
            if(json.has("public_id"))
            {
                client.setPublic_id(json.getString("public_id"));
            }
            if(json.has("custom_value4"))
            {
                client.setMatricula(json.getString("custom_value4"));
            }
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return client;
    }
    public static Vector fromJsonArray(String jsonArray)
    {
        Vector vectorClients = new Vector();
        try{
            JSONArray array = new JSONArray(jsonArray);
            for(int i=0;i<array.length();i++)
            {
                JSONObject json = array.getJSONObject(i);
                Clients client = Clients.fromJson(json.toString());
                vectorClients.addElement(client);
            }
        }catch(JSONException e)
        {
            e.printStackTrace();
        }
        return vectorClients;
    }
    public void setId(String id)
    {
        this.id = id;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public void setNit(String nit)
    {
        this.nit = nit;
    }
    public String getId()
    {
        return this.id;
    }
    public String getName()
    {
        return this.name;
    }
    public String getNit()
    {
        return this.nit;
    }

    public String getPublic_id() {
        return this.public_id;
    }

    public void setPublic_id(String public_id) {
        this.public_id = public_id;
    }

    public String getVat_number() {
        return vat_number;
    }

    public void setVat_number(String vat_number) {
        this.vat_number = vat_number;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

}