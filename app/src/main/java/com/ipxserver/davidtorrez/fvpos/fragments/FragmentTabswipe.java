package com.ipxserver.davidtorrez.fvpos.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.astuetz.PagerSlidingTabStrip;
import com.ipxserver.davidtorrez.fvpos.Listeners.FragmentReceiver;
import com.ipxserver.davidtorrez.fvpos.Listeners.ProductReceiver;
import com.ipxserver.davidtorrez.fvpos.R;
import com.ipxserver.davidtorrez.fvpos.adapter.GridbarAdapter;
import com.ipxserver.davidtorrez.fvpos.adapter.PagerAdapter;
import com.ipxserver.davidtorrez.fvpos.models.Categoria;

import java.util.ArrayList;

/**
 * Created by David-Pc on 25/05/2015.
 */
public class FragmentTabswipe extends Fragment //implements ActionBar.TabListener
{
    private PagerAdapter pagerAdapter;
    private GridbarAdapter gridbarAdapter;
    private GridView gridbar;
    public ProductReceiver reciver;
    //ActionBar actionBar;
    ViewPager viewPager;
    private ArrayList<Categoria> categorias;
    private boolean activarMenu=false;

//    private User usuario;


    public static FragmentTabswipe newInstance(ArrayList<Categoria> categorias)
    {
        FragmentTabswipe  fragmentTabswipe = new FragmentTabswipe();
        Bundle arg = new Bundle();
        //Todo: Adicionar un parametro de monto total para tenerlo todo en el fragmento

        arg.putSerializable("categorias",categorias);
        fragmentTabswipe.setArguments(arg);
        return fragmentTabswipe;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categorias = (ArrayList<Categoria>) getArguments().getSerializable("categorias");
        Log.i("David", "categorias size:" + categorias.size());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        pagerAdapter = new PagerAdapter(this.getChildFragmentManager(),categorias);
//
        View rootView = inflater.inflate(R.layout.frament_tabswipe, container, false);
        viewPager =(ViewPager) rootView.findViewById(R.id.pager);
        viewPager.setOffscreenPageLimit(categorias.size());
        viewPager.setAdapter(pagerAdapter);

        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) rootView.findViewById(R.id.tabs);

        tabs.setViewPager(viewPager);
//        actionBar = getActivity().getActionBar();
//
//        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
//        actionBar.setHomeButtonEnabled(false);
////        inicializarTabs();
//        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
//
//            @Override
//            public void onPageSelected(int position) {
//                actionBar.setSelectedNavigationItem(position);
//            }
//        });
//
//        for (int i = 0; i < pagerAdapter.getCount(); i++) {
//            actionBar.addTab(
//                    actionBar.newTab()
//                            .setText(pagerAdapter.getPageTitle(i))
//                            .setTabListener(this));
//        }
//
//
        gridbarAdapter = new GridbarAdapter(this,rootView.getContext());
        gridbar= (GridView) rootView.findViewById(R.id.barraSaldo);
        gridbar.setAdapter(gridbarAdapter);

//        gridbar.setVisibility(View.INVISIBLE);


        setHasOptionsMenu(true);
        return rootView;
    }

//    private void inicializarTabs() {
//        final ActionBar actionBar = getActivity().getActionBar();
//        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
//        actionBar.setHomeButtonEnabled(false);
//
//    }
    public void activarVenta(boolean activar)
    {
        if(activar!=activarMenu) {
            this.activarMenu = activar;
            getActivity().invalidateOptionsMenu();
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        reciver = new ProductReceiver(gridbarAdapter,this);
        getActivity().registerReceiver(reciver, new IntentFilter("cast_product"));

    }
    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(reciver);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if(activarMenu)
        {
            inflater.inflate(R.menu.menu_tabswipe, menu);
        }
        else {

            inflater.inflate(R.menu.menu_toast, menu);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i("David", "entro al item selected");
        switch (item.getItemId())
        {
            case R.id.action_cancelar:

                cancelarFactura(item);
                return true;
            case R.id.action_facturar: llamarFacturar(item); return true;
            case R.id.action_ayuda: llamarAyudia(item);return true;
            default:  return super.onOptionsItemSelected(item);
        }

    }

    private void llamarAyudia(MenuItem item) {
        alerta("Emizor","Por favor seleccione un producto a facturar");
    }

    private void llamarFacturar(MenuItem item) {
        Intent intent = new Intent("cambiar_fragmento");

        intent.putExtra("operacion", FragmentReceiver.FRAGMENT_FACTURA);
        intent.putExtra("lista_seleccionados", reciver.getListaProductos());
        intent.putExtra("monto",reciver.getMonto());

        getActivity().sendBroadcast(intent);
        Log.i("David", "enviando  cambiar fragmento");


    }

    private void cancelarFactura(MenuItem item) {

        //Todo: long press usarlo a futuro para compartir informacion lo malo es que ocupa un espacion adicioanl arriba de action bar

//        ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
//            @Override
//            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
//
//                MenuInflater inflater = actionMode.getMenuInflater();
//                inflater.inflate(R.menu.menu_toast,menu);
//                return true;
//            }
//
//            @Override
//            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
//                return false;
//            }
//
//            @Override
//            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
//
////                        switch (menuItem.getItemId())
////                        {
////                            case R.id.action_cancelar: cancelarFactura(menuItem); return true;
////                            case R.id.action_facturar: llamarFacturar(menuItem); return true;
////                            default:  return false;
////                        }
//                return false;
//
//            }
//
//            @Override
//            public void onDestroyActionMode(ActionMode actionMode) {
//
//            }
//        };
//
//        mActionMode = getActivity().startActionMode(mActionModeCallback);
//        activarMenu = true;
//        getActivity().invalidateOptionsMenu();
        Intent intent = new Intent("cambiar_fragmento");

        intent.putExtra("operacion", FragmentReceiver.FRAGMENT_LISTA);
        getActivity().sendBroadcast(intent);
        Log.i("David","entro a la funcion de cancelar factura");
    }
    public void alerta(String titulo,String mensaje) {
        //se prepara la alerta creando nueva instancia
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        //seleccionamos la cadena a mostrar
        dialogBuilder.setMessage(mensaje);
        //elegimo un titulo y configuramos para que se pueda quitar
        dialogBuilder.setCancelable(true).setTitle(titulo);
        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        //mostramos el dialogBuilder
        dialogBuilder.create().show();

//		  ProgressDialog.show( MulticobroPrincipal.this,titulo,mensaje,true,true);

    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.v("Brian","entra al onclick bitch");
        Log.v("David","jajaja no va entrar ");
        //Product pro = (Product) gridAdapter.getItem(position);

        //int cant = Integer.parseInt(pro.getQty().toString())+1;
        //pro.setQty(""+cant);
//        pro.setCost(""+pro.getCost()+"x "+pro.getQty());
        //gridAdapter.notifyDataSetChanged();

        //enviando al broadcast
        //Intent intent = new Intent("cast_product");
        //intent.putExtra("operacion", ProductReceiver.PRODUCTO_AGREGADO);
        //Log.i("David","parsenado double:"+pro.getCost());
        //intent.putExtra("monto", Double.parseDouble(pro.getCost()));
        //intent.putExtra("producto",pro);
        //getActivity().sendBroadcast(intent);

//        Toast.makeText(view.getContext(),""+pro.getQty(), Toast.LENGTH_SHORT).show();
    }
}
