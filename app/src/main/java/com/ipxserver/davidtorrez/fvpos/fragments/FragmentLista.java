package com.ipxserver.davidtorrez.fvpos.fragments;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ipxserver.davidtorrez.fvpos.Listeners.FacturaReceiver;
import com.ipxserver.davidtorrez.fvpos.Listeners.FragmentReceiver;
import com.ipxserver.davidtorrez.fvpos.R;
import com.ipxserver.davidtorrez.fvpos.adapter.FacturaCardAdapter;
import com.ipxserver.davidtorrez.fvpos.models.FacturaCardItem;
import com.software.shell.fab.ActionButton;

import java.util.ArrayList;

/**
 * Created by David Torrez on 13/05/2015.
 */
public class FragmentLista extends Fragment
{
    View rootView;

    ActionButton actionButton;
    RecyclerView recyclerView;
    FacturaReceiver receiver;
    FacturaCardAdapter facturaCardAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_lista,container,false);
        recyclerView =(RecyclerView)rootView.findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(rootView.getContext());
        recyclerView.setLayoutManager(llm);
        ArrayList<FacturaCardItem> lista = new ArrayList<FacturaCardItem>();
        for (int i=0;i<10;i++)
        {
            FacturaCardItem item = new FacturaCardItem();
            int num=100+i;
            item.setNumero(""+num);
            item.setFecha("20/5/2015");
            int monto= 100*i;
            item.setMonto(""+monto);
            lista.add(item);
        }

        facturaCardAdapter= new FacturaCardAdapter(lista);
        recyclerView.setAdapter(facturaCardAdapter);
        actionButton = (ActionButton) rootView.findViewById(R.id.action_button);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent("cambiar_fragmento");

                intent.putExtra("operacion", FragmentReceiver.FRAGMENT_TABSWIPE);
                getActivity().sendBroadcast(intent);
            }
        });
        setHasOptionsMenu(true);

        return rootView;

    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(receiver);
    }

    @Override
    public void onResume() {
        super.onResume();
        receiver = new FacturaReceiver(facturaCardAdapter);
        getActivity().registerReceiver(receiver, new IntentFilter("factura"));
    }
}
