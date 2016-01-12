package com.ipxserver.davidtorrez.fvpos.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Vector;


/**
 *
 * @author David Torrez
 */
public class Factura
{
    private String resultado;
    private String invoice_number;
    private String control_code;
    private String invoice_date;
    private String amount;
    private String subtotal;
    private String fiscal;
    private String ice;
    private String law;
    //falta cliente, cuenta y invoice items
    private Account account;
    private Vector items;
    //    private String invoiceitem;
    private Clients cliente;
    //

    private String address1;
    private String address2;
    private String num_auto;
    private String fecha_limite;
    private String actividad;
    public Factura(String jsonText)
    {
        try {
            JSONObject json = new JSONObject(jsonText);
            if(json.has("resultado"))
            {
                resultado = json.getString("resultado");
            }
            if(json.has("invoice_number"))
            {
                invoice_number=json.getString("invoice_number");
            }
            if(json.has("control_code"))
            {
                control_code = json.getString("control_code");
            }
            if(json.has("invoice_date"))
            {
                invoice_date = json.getString("invoice_date");
            }
            if(json.has("amount"))
            {
                amount = json.getString("amount");
            }
            if(json.has("subtotal"))
            {
                subtotal = json.getString("subtotal");
            }
            if(json.has("fiscal"))
            {
                fiscal = json.getString("fiscal");
            }
            if(json.has("client"))
            {
                cliente = Clients.fromJson(json.getString("client"));
            }
            if(json.has("account"))
            {
                account = new Account(json.getString("account"));

            }
            if(json.has("invoice_items"))
            {
                items = InvoiceItem.fromJsonArray(json.getString("invoice_items"));
//                items = InvoiceItems.fromJsonArray(json.getString("invoice_items"));
//                System.out.print("\ninvoice_items = "+json.getString("invoice_items"));
//                invoiceitem = json.getString("invoice_items");

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
            if(json.has("ice"))
            {
                ice =json.getString("ice");
            }
            if(json.has("law"))
            {
                law = json.getString("law");
            }
            if(json.has("activity_pri"))
            {
                actividad = json.getString("activity_pri");
            }
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

    }
    public String getResultado()
    {
        return this.resultado;
    }
    public String getInvoiceNumber()
    {
        return this.invoice_number;
    }
    public String getControlCode()
    {
        return this.control_code;
    }
    public String getInvoiceDate()
    {
        return this.invoice_date;
    }
    public String getAmount()
    {
        return this.amount;
    }
    public String getSubtotal()
    {
        return this.subtotal;
    }
    public String getFiscal()
    {
        return this.fiscal;
    }
    public Account getAccount()
    {
        return this.account;
    }
    public Vector getInvoiceItems()
    {
        return this.items;
    }
    public Clients getCliente()
    {
        return  this.cliente;
    }

    public String getAddress1()
    {
        return this.address1;
    }
    public String getAddress2()
    {
        return this.address2;
    }
    public String getNumAuto()
    {
        return this.num_auto;
    }
    public String getFechaLimite()
    {
        return this.fecha_limite;
    }
    public String getIce()
    {
        return this.ice;
    }
    public String getLaw()
    {
        return this.law;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

}