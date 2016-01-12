package com.ipxserver.davidtorrez.fvpos;


import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.ipxserver.davidtorrez.fvpos.Listeners.FragmentReceiver;
import com.ipxserver.davidtorrez.fvpos.adapter.GridbarAdapter;
import com.ipxserver.davidtorrez.fvpos.adapter.NavAdapter;
import com.ipxserver.davidtorrez.fvpos.database.SqliteController;
import com.ipxserver.davidtorrez.fvpos.fragments.FragmentEmpresa;
import com.ipxserver.davidtorrez.fvpos.fragments.FragmentFactura;
import com.ipxserver.davidtorrez.fvpos.fragments.FragmentLista;
import com.ipxserver.davidtorrez.fvpos.fragments.FragmentTabswipe;
import com.ipxserver.davidtorrez.fvpos.models.Account;
import com.ipxserver.davidtorrez.fvpos.models.Branches;
import com.ipxserver.davidtorrez.fvpos.models.Categoria;
import com.ipxserver.davidtorrez.fvpos.models.NavItem;
import com.ipxserver.davidtorrez.fvpos.models.Product;
import com.ipxserver.davidtorrez.fvpos.models.User;

import java.util.ArrayList;


public class PrincipalActivity extends ActionBarActivity {


    GridbarAdapter gridbarAdapter;
    Spinner branchSelect;

   FragmentReceiver reciver;
    FragmentLista fragmentLista=null;
//    FragmentFactura fragmentFactura=null;
    FragmentTabswipe fragmentTabswipe=null;
    FragmentEmpresa fragmentEmpresa=null;
    FragmentTabswipe fragmentEditTabswipe=null;

    User usuario;




    //Variables para el navigation drawer
    private DrawerLayout drawerLayout;
    private ListView navList;
    private CharSequence mTitle;

    private ActionBarDrawerToggle drawerToggle;
    private LinearLayout drawer_child;

    private String respuesta;
    private Account cuenta;
    NavAdapter navAdapter;
    String sucursales[];
    String branch_id;
    private ArrayList<NavItem> navmenu;

    private SqliteController base;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        base = new SqliteController(this);
        Intent intent = getIntent();
        respuesta = intent.getStringExtra("cuenta");
        usuario = (User) intent.getSerializableExtra("usuario");
        cuenta = new Account(respuesta);

        sucursales = new String[cuenta.getBranches().size()];
        for (int i = 0; i < cuenta.getBranches().size(); i++)
        {
            sucursales[i]= ((Branches) cuenta.getBranches().get(i)).getName();
        }
        branch_id = getString(R.string.branch_id);
        branch_id = cuenta.getBranches().get(0).getId();
        base.insertCuenta(cuenta.getName(),cuenta.getBranches().get(0).getId(),cuenta.getBranches().get(0).getName(),cuenta.getSubdominio());

        branchSelect = (Spinner) findViewById(R.id.branch_select);

        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item,
                sucursales);
        branchSelect.setAdapter(spinnerArrayAdapter);
        branchSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                branch_id = cuenta.getBranches().get(i).getId();

                Log.i("Branch "," selecciono la sucursal "+branch_id);
                Log.i("Branch ","actual _>"+base.getSucursal());
                base.modificarSucursal(branch_id);
                Log.i("Branch ", "cambiando a _>" + base.getSucursal());
//                Log.i("Branch "," valor del recurso branch_id "+getString(R.string.branch_id));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
//        branchSelect.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
////                branch_id = cuenta.getBranches().get(i).getId();
//                Log.i("Branch "," selecciono la sucursal "+branch_id);
//            }
//        });


//        cuenta = new Account("{\"productos\":{\"categoria1\":[{\"id\":9,\"product_key\":\"AM11\",\"notes\":\" producto 1\",\"cost\":\"10.00\"},{\"id\":13,\"product_key\":\"AM15\",\"notes\":\" producto con descripsion\",\"cost\":\"99.00\"},{\"id\":11,\"product_key\":\"AM13\",\"notes\":\" producto 3\",\"cost\":\"10.00\"}],\"categoria2\":[{\"id\":12,\"product_key\":\"AM14\",\"notes\":\" producto 4\",\"cost\":\"10.00\"},{\"id\":10,\"product_key\":\"AM12\",\"notes\":\" producto 2\",\"cost\":\"10.00\"},{\"id\":14,\"product_key\":\"AM16\",\"notes\":\" producto 6\",\"cost\":\"10.00\"}],\"categoria3\":[{\"id\":16,\"product_key\":\"AM18\",\"notes\":\" producto 8\",\"cost\":\"10.00\"},{\"id\":17,\"product_key\":\"AM19\",\"notes\":\" producto 9\",\"cost\":\"10.00\"},{\"id\":18,\"product_key\":\"AM20\",\"notes\":\" producto 0\",\"cost\":\"10.00\"}]},\"categorias\":[{\"categoria\":\"categoria1\"},{\"categoria\":\"categoria2\"},{\"categoria\":\"categoria3\"}],\"first_name\":\"Aurora\",\"last_name\":\"Bustillo Bravo\",\"branch\":\"Casa Matriz\"}");
        Log.i("David","respuesta "+respuesta);
        Log.i("David", "usuario " + usuario.getUser());
        navigationInit();

       inicializarContenido();
//        cargarFragmento(getFragmentLista());
        cambiarFragmento(FragmentReceiver.FRAGMENT_LISTA);

    }

    private void navigationInit() {
        this.drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.navList = (ListView) findViewById(R.id.left_drawer);
        this.drawer_child = (LinearLayout) findViewById(R.id.drawer_child);
        // Load an array of options names
        navmenu = new ArrayList<NavItem>();

        NavItem navItem = new NavItem("Nueva Factura",R.drawable.ic_action_new_black);
        navmenu.add(navItem);

        navItem = new NavItem("Reporte de Ventas",R.drawable.ic_action_report);

        navmenu.add(navItem);

        navItem = new NavItem("Acerca de Emizor",R.drawable.ic_action_empresa_black);
        navmenu.add(navItem);

        navItem = new NavItem("Cerrar Sesion",R.drawable.ic_action_exit);
        navmenu.add(navItem);

//        names = new String[]{"Nueva Factura","Reporte del Dia","Acerca de Factura Virtual","Cerrar Sesion"};
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, names);
        navAdapter = new NavAdapter(navList.getContext(),navmenu);
        navList.setAdapter(navAdapter);
        //hasta aqui el esquelo funciona bien XD

        navList.setOnItemClickListener(new DrawerItemClickListener());

        //action bar toogle
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout
                , R.string.open_drawer,
                R.string.close_drawer) {

            /**
             * Called when a drawer has settled in a completely closed state.
             */
            public void onDrawerClosed(View view) {

                // creates call to onPrepareOptionsMenu()
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mTitle);
            }

            /**
             * Called when a drawer has settled in a completely open state.
             */
            public void onDrawerOpened(View drawerView) {

                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(cuenta.getName());
                // creates call to onPrepareOptionsMenu()

            }
        };

        drawerLayout.setDrawerListener(drawerToggle);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }

    public void cambiarFragmento(int fragment_id)
    {
        switch (fragment_id)
        {
            case FragmentReceiver.FRAGMENT_FACTURA: //cargarFragmento(getFragmentFactura());
                FragmentManager manager = getSupportFragmentManager();

                FragmentTransaction transaction = manager.beginTransaction();
//                ArrayList<Product> lista = reciver.getListaProductos();
                FragmentFactura fragmentFactura = FragmentFactura.newInstance(reciver.getListaProductos(),reciver.getMonto(),usuario);
                transaction.replace(R.id.contenedor_fragmnet, fragmentFactura);//reemplazar el id con el del contenedor
                transaction.commit();

//                getFragmentFactura().listAdapter.adcionarProducto(product);

//                for(int i=0;i<reciver.getListaProductos().size();i++)
//                {
//                    Product pro = (Product) reciver.getListaProductos().get(i);
//                    getFragmentFactura().listAdapter.adcionarProducto(pro);
//                }
                mTitle = "Factura";
                getSupportActionBar().setTitle(mTitle);
                navAdapter.seleccionar(0);
                break;
            case FragmentReceiver.FRAGMENT_TABSWIPE:
                navAdapter.seleccionar(0);
//                navmenu.get(0).setHabilitado(true);
//
//                uncheck(0);

                cargarFragmento(getFragmentTabswipe());
                mTitle = "Productos";
                getSupportActionBar().setTitle(mTitle);

                Log.e("Camviando a tabswipe","id:"+FragmentReceiver.FRAGMENT_TABSWIPE);
                break;
            case FragmentReceiver.FRAGMENT_LISTA:
                navAdapter.seleccionar(1);
//                navmenu.get(1).setHabilitado(true);
//
//                uncheck(1);
                cargarFragmento(getFragmentLista());

                mTitle = "Reporte de Ventas";
                getSupportActionBar().setTitle(mTitle);
                break;
            case FragmentReceiver.FRAGMENT_EMPRESA: cargarFragmento(getFragmentEmpresa());
                mTitle = "Emizor";
                navAdapter.seleccionar(2);
                getSupportActionBar().setTitle(mTitle);
                break;
            case FragmentReceiver.FRAGMENT_EDITTABSWIPE:
                ArrayList<Product> listaModificada= reciver.getListaProductos();
                cargarFragmento(getFragmentEditTabswipe());
                reciver.setListaProductos(listaModificada);

//                Intent intent = new Intent("cast_product");
//                intent.putExtra("operacion", ProductReceiver.ACTUALIZAR_LISTA);
//
//                intent.putExtra("lista_editada",listaModificada);
//                sendBroadcast(intent);
                mTitle = "Productos";
                navAdapter.seleccionar(0);
                getSupportActionBar().setTitle(mTitle);
                break;

        }

    }
    private void cargarFragmento(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.contenedor_fragmnet, fragment);//reemplazar el id con el del contenedor
        transaction.commit();

    }

    public void inicializarContenido()
    {
        TextView txtFirstName = (TextView)findViewById(R.id.txt_fist_name);
        TextView txtLastName = (TextView)findViewById(R.id.txt_last_name);
        txtFirstName.setText(cuenta.getFirst_name());
        txtLastName.setText(cuenta.getLast_name());
//        actionBar.show();
//        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
//
//        viewPager =(ViewPager) findViewById(R.id.pager);
//        viewPager.setAdapter(pagerAdapter);
//
//        //Todo: Revisar el tipo de contexto al cual pertenese el gridbarAdapter
//        gridbarAdapter = new GridbarAdapter(getApplicationContext());
//        gridbar= (GridView) findViewById(R.id.barraSaldo);
//        gridbar.setAdapter(gridbarAdapter);
//        gridbar.setVisibility(View.INVISIBLE);

    }
    public FragmentLista getFragmentLista() {
        if(fragmentLista==null)
        {
            fragmentLista = new FragmentLista();
        }

        return fragmentLista;
    }

//    public FragmentFactura getFragmentFactura() {
//        if(fragmentFactura==null)
//        {
//            fragmentFactura = new FragmentFactura();
//        }
//
//        return fragmentFactura;
//    }

    public FragmentTabswipe getFragmentTabswipe() {
        if(fragmentTabswipe==null)
        {
            fragmentTabswipe = FragmentTabswipe.newInstance(cuenta.getCategorias());
        }
        return fragmentTabswipe;
    }
    public FragmentTabswipe getFragmentEditTabswipe() {

            Account cuentaMod = new Account(cuenta.getAccountJsonText());

            for(int i=0;i<cuentaMod.getCategorias().size();i++)
            {
                Categoria categoria = (Categoria)cuentaMod.getCategorias().get(i);
                categoria.setProdcutos(reciver.getListaProductos());
                cuentaMod.getCategorias().set(i,categoria);
            }
            fragmentEditTabswipe = FragmentTabswipe.newInstance(cuentaMod.getCategorias());



        return fragmentEditTabswipe;
    }
    public  FragmentEmpresa getFragmentEmpresa()
    {
        if(fragmentEmpresa==null)
        {
            fragmentEmpresa = new FragmentEmpresa();
        }
        return fragmentEmpresa;
    }

    @Override
    protected void onResume() {
        super.onResume();
        reciver = new FragmentReceiver(gridbarAdapter,this);
        registerReceiver(reciver, new IntentFilter("cambiar_fragmento"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(reciver);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent("cambiar_fragmento");

            intent.putExtra("operacion", FragmentReceiver.FRAGMENT_LISTA);
            sendBroadcast(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen = drawerLayout.isDrawerOpen(drawer_child);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {

        //Todo Colocar evento para cada opsion de la lista
        switch (position)
        {
            case 0: cambiarFragmento(FragmentReceiver.FRAGMENT_TABSWIPE);
                break;
            case 1: cambiarFragmento(FragmentReceiver.FRAGMENT_LISTA);
                break;
            case 2: cambiarFragmento(FragmentReceiver.FRAGMENT_EMPRESA);
                break;
            case 3: this.finish();
                break;
        }
//        NavItem navItem = (NavItem) navmenu.get(position);
//        navItem.setHabilitado(true);
//
////        navmenu.set(position, navItem);
//        if(position==0)
//        {
//            mTitle = "Factura";
//        }
//        else{
//            mTitle = navItem.getTitulo();
//        }
//        navAdapter.seleccionar(position);
//        navList.setItemChecked(position, true);
//        getSupportActionBar().setTitle(mTitle);
        drawerLayout.closeDrawer(drawer_child);

    }

}
