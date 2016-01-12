package com.ipxserver.davidtorrez.fvpos.models;

import java.io.Serializable;

/**
 * Created by David-Pc on 24/06/2015.
 */
public class FacturaCardItem implements Serializable {
    private String numero;
    private String monto;
    private String fecha;
    private String id;
    private String idCliente;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }
}
