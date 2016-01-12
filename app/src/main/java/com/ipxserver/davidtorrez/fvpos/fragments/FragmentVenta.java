package com.ipxserver.davidtorrez.fvpos.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ipxserver.davidtorrez.fvpos.R;

/**
 * Created by david on 11-01-16.
 */
public class FragmentVenta extends Fragment
{


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_venta,container,false);

        return rootView;
    }
}
