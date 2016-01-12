package com.ipxserver.davidtorrez.fvpos.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ipxserver.davidtorrez.fvpos.fragments.FragmentGrid;
import com.ipxserver.davidtorrez.fvpos.models.Categoria;

import java.util.ArrayList;

/**
 * Created by David Torrez on 13/05/2015.
 */
public class PagerAdapter extends FragmentPagerAdapter
{
    //Todo: antes de esto pasar el arrya list del objeto con toda la lista
    ArrayList<FragmentGrid> lista;

    public PagerAdapter(FragmentManager fm,ArrayList<Categoria> categorias) {
        super(fm);
        lista = new ArrayList<FragmentGrid>();

        for(int i=0;i<categorias.size();i++)
        {

            Categoria categoria = (Categoria)categorias.get(i);
            FragmentGrid fg = FragmentGrid.newInstance(categoria.getProductos());
            fg.tituloCategoria = categoria.getNombre();
            lista.add(fg);
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        //Todo: Colocar la lista de objetos utilizados dentro del array de objetos

        String titulo=((FragmentGrid)lista.get(position)).tituloCategoria;
//        switch(position)
//        {
//            case 0: titulo ="categoria 1";
//                break;
//            case 1: titulo ="categoria 2";
//                break;
//            case 2: titulo ="categoria 3";
//                break;
//
//            default: titulo="sin fragmento";
//        }
//        return super.getPageTitle(position);
        return titulo;
    }

    @Override
    public Fragment getItem(int i) {

//        switch (i)
//        {
//            case 0: return new FragmentGrid();
//
//            case 1: return new FragmentGrid();
//            case 2: return new FragmentGrid();
//            //case 3: return new FragmentFactura();
//            default: return null;
//        }
        return (FragmentGrid)lista.get(i);

    }

    @Override
    public int getCount() {
        return lista.size();
    }
}
