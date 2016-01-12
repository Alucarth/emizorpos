package com.ipxserver.davidtorrez.fvpos;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ipxserver.davidtorrez.fvpos.models.User;
import com.ipxserver.davidtorrez.fvpos.rest.Conexion;


public class LoginActivity extends FragmentActivity {

    private String TAG = "DavidLog";
    ProgressDialog pDialog;
    EditText ednit,eduser,edpass;
    Button btsesion;
    User usuario;
    Conexion conexion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        inicializarComponentes();
//        Double num= 12.29*2.3;
//        Double num=Double.parseDouble("10.00");
//
//        Log.i("Login conversor","imprimiendo double:"+num);

    }

    private void inicializarComponentes() {
        ednit = (EditText) findViewById(R.id.etStore);
        eduser = (EditText)findViewById(R.id.etUserName);
        edpass = (EditText)findViewById(R.id.etPass);
        btsesion =(Button) findViewById(R.id.btnSingIn);

        btsesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Printer printer = Printer.getInstance();
//                printer.printText("ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789",2);
//                printer.printText("ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789",3);
//                printer.printEndLine();

                usuario = new User(eduser.getText().toString(), ednit.getText().toString(), edpass.getText().toString());
                conexion = new Conexion(usuario.getUser(), usuario.getPassword(),usuario.getNitempresa());
                pDialog = new ProgressDialog(LoginActivity.this);
                pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                pDialog.setTitle("Autentificando");
                pDialog.setMessage("Por favor espere...");
                pDialog.setCancelable(true);


                AsyncCallWS task = new AsyncCallWS();
                //Call execute
                task.execute();


            }
        });
        Log.w(TAG,"direccion app:"+ getApplicationContext().getFilesDir().getAbsolutePath());
    }
    public void limpar()
    {
        usuario =null;

        eduser.setText("");
        edpass.setText("");
        ednit.setText("");

    }
    public void Cambiar()
    {
        Intent intent = new Intent(this, PrincipalActivity.class);
        intent.putExtra("cuenta", conexion.getRespuesta());
        intent.putExtra("usuario",usuario);
//		 intent.putExtra("", value)
        startActivity(intent);
    }
    public void alerta(String titulo,String mensaje) {
        //se prepara la alerta creando nueva instancia
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private class AsyncCallWS extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            Log.i(TAG, "doInBackground");
//	            getFahrenheit(celcius);
            //getCobro();
//	            calcularEdad();
            conexion.enviarGet(Conexion.LOGIN);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Log.i(TAG, "onPostExecute");
            pDialog.dismiss();
            if(conexion.getCodigo()==200)
            {
                Cambiar();

            }
            else
            {
                alerta("Autentificacion","Por favor verifique que el usuario y password sean correctos");
            }
            Log.i("David", "codifgo" + conexion.getCodigo());
            Log.i("David","conexion"+conexion.getRespuesta());
            limpar();
//              Toast.makeText(MulticobroPrincipal.this, "Tarea finalizada!",
//              Toast.LENGTH_SHORT).show();
//	            mostrar.setText(david);

            //alerta("MultiCobro",cobro.getMenssage());


        }

        @Override
        protected void onPreExecute() {
            Log.i(TAG, "onPreExecute");
//	            mostrar.setText("Calculating...");

            pDialog.setProgress(0);
            pDialog.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            Log.i(TAG, "onProgressUpdate");
        }

    }
}
