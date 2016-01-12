package com.ipxserver.davidtorrez.fvpos.models;

/**
 * Created by David-Pc on 09/06/2015.
 */
public class NavItem
{
    public boolean habilitado;
    public String titulo;
    public int image;
    public NavItem(String titulo,int image)
    {
        this.titulo = titulo;
        this.image = image;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getImage() {
        return image;
    }
}
