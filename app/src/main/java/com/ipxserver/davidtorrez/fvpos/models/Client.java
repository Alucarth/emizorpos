package com.ipxserver.davidtorrez.fvpos.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by David Torrez on 01/06/2015.
 */

public class Client
{
    public static final String ID="id";
    public static final String NOMBRE="name";
    public static final String NIT="nit";
    public static final String EMAIL="email";
    public static final String RESULTADO="resultado";
    public static final String MENSAJE="mensaje";
    public static final String PUBLIC_ID="public_id";

    String id;
    String nombre;
    String nit;
    String email;
    String resultado;
    String mensaje;
    String public_id;
    public Client()
    {

    }
    public static JSONObject toJson(Client client)
    {
        JSONObject json = new JSONObject();

        try {
            json.put("nit",client.getNit());
            json.put("name",client.getNombre());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
    public static Client fromJson(String jsonText)
    {
        Client cliente = new Client();
        try {
            JSONObject jsonres = new JSONObject(jsonText);
            if(jsonres.has("cliente"))
            {
                JSONObject json = new JSONObject(jsonres.getString("cliente"));

                if(json.has(Client.ID))
                {
                    cliente.setId(json.getString(Client.ID));
                }
                if(json.has(Client.PUBLIC_ID))
                {
                    cliente.setPublic_id(json.getString(Client.PUBLIC_ID));
                }
                if(json.has(Client.NOMBRE))
                {
                    cliente.setNombre(json.getString(Client.NOMBRE));
                }
                if(json.has(Client.NIT))
                {
                    cliente.setNit(json.getString(Client.NIT));
                }
                if(json.has(Client.EMAIL))
                {
                    cliente.setEmail(json.getString(Client.EMAIL));
                }

            }

            if(jsonres.has(Client.RESULTADO))
            {
                cliente.setResultado(jsonres.getString(Client.RESULTADO));
            }
            if(jsonres.has(Client.MENSAJE))
            {
                cliente.setMensaje(jsonres.getString(Client.MENSAJE));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cliente;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPublic_id() {
        return public_id;
    }

    public void setPublic_id(String public_id) {
        this.public_id = public_id;
    }
}
