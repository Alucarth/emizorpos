package com.ipxserver.davidtorrez.fvpos.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ipxserver.davidtorrez.fvpos.R;

/**
 * Created by David-Pc on 02/06/2015.
 */
public class DialogUser extends DialogFragment
{
//    UserDialgoListener facturaListener;
//    public interface UserDialgoListener{
//        public void onDialogPositiveClick(DialogUser dialogUser);
//        public void onDialgoNegativeClick(DialogUser dialogUser);
//    }
    TextView txtNombre,txtNit;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View rootView = inflater.inflate(R.layout.fragment_user_dialog,null);

        builder.setView(rootView)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //evento para el BUtton Positivio
//                        facturaListener.onDialgoNegativeClick(DialogUser.this);
                        txtNombre=(TextView)rootView.findViewById(R.id.txt_factura_nombre);
                        txtNit=(TextView) rootView.findViewById(R.id.txt_factura_nit);
//                        String cadena = txtNit.getText().toString()+" its work XD";
                        txtNombre.setText(txtNit.getText().toString()+" its work XD");
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        facturaListener.onDialgoNegativeClick(DialogUser.this);
                        getDialog().cancel();
                    }
                });
        return builder.create();
    }

//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        try{
//        facturaListener = (UserDialgoListener) activity;
//        } catch (ClassCastException e) {
//            // The activity doesn't implement the interface, throw exception
//            throw new ClassCastException(activity.toString()
//                    + " must implement UserDialogListener XD");
//        }
//    }
}
