package com.ipxserver.davidtorrez.fvpos.fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ipxserver.davidtorrez.fvpos.R;

/**
 * Created by David-Pc on 30/05/2015.
 */
public class FragmentUserDialog extends DialogFragment implements TextView.OnEditorActionListener
{

    public FragmentUserDialog()
    {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_user_dialog,container);
       getDialog().setTitle("Datos del Cliente");

        final TextView txtNombre =(TextView) rootView.findViewById(R.id.txt_factura_nombre);


        final TextView txtNit = (EditText) rootView.findViewById(R.id.txtNitUser);
        txtNit.requestFocus();

        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        txtNit.setOnEditorActionListener(this);
        Button button = (Button) rootView.findViewById(R.id.buttonAceptar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtNombre.setText(txtNit.getText().toString() + " its work");
            }
        });

        return  rootView;
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

        if(EditorInfo.IME_ACTION_NEXT ==actionId)
        {
            Toast.makeText(getActivity().getApplicationContext(), "Enviando info al server", Toast.LENGTH_SHORT);
            Log.i("David","Se envio el toast");
        }
        return false;
    }
}
