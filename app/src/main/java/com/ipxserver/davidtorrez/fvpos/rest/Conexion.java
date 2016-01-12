package com.ipxserver.davidtorrez.fvpos.rest;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

import com.ipxserver.davidtorrez.fvpos.database.SqliteController;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by David-Pc on 07/06/2015.
 */
public class Conexion {

    public final static int LOGIN=0;
    public final static int CLIENTE=1;
    public final static int GUARDARFACTURA=2;
    public final static int REGISTROCLIENTE=3;
//    public final static String SERVIDOR="192.168.2.194";
    public final static String SERVIDOR=".dev.emizor.com";
    public final static String PROTOCOLO="http://";
//    public final static  String LOGIN_URL="http://192.168.2.194/cloud/public/loginPOS";
//    public final static  String LOGIN_URL="/cloud2/public/loginPOS";
//    public final static String CLIENTE_URL="/clou d2/public/cliente/";
//    public final static String GUARDARFACTURA_URL="/cloud2/public/guardarFactura";
    public final static  String LOGIN_URL="/pos";
    public final static String CLIENTE_URL="/clientepos/";
    public final static String REGISTROCLIENTE_URL="/guardarCliente";
    public final static String GUARDARFACTURA_URL="/guardarFactura";
    private String respuesta;
    private int codigo;
    private String error;

    private SqliteController base;
    private String user;
    private String pass;
    private String domain;

    public Conexion(String user,String pass,Context context)
    {
        this.user = user;
        this.pass = pass;
        base = new SqliteController(context);
    }
    public Conexion(String user,String pass,String domain)
    {
        this.user = user;
        this.pass = pass;
        this.domain = domain;
    }
    public void enviarGet(int servicio)
    {
        String url = null;
        switch (servicio)
        {
            case LOGIN:
                url = LOGIN_URL;
                break;
            default: url="sin direccion";
        }
        sendGet(Conexion.PROTOCOLO+this.domain+Conexion.SERVIDOR+url);

    }
    public void enviarGet(int servicio,String parametros)
    {
        String url = null;
        switch (servicio)
        {
            case Conexion.CLIENTE:
                url = Conexion.CLIENTE_URL+parametros;
                break;
            default: url="sin direccion";
        }
        sendGet(Conexion.PROTOCOLO+base.getSubdominio()+Conexion.SERVIDOR+url);
    }
    public void enviarPost(int servicio,String parametros)
    {
        Log.e("David"," servicio:"+servicio);
        Log.e("David"," parametros:"+parametros);
        String url=null;
        switch (servicio)
        {
            case Conexion.GUARDARFACTURA:
                url = Conexion.GUARDARFACTURA_URL;
                break;
            case Conexion.REGISTROCLIENTE:
                url = Conexion.REGISTROCLIENTE_URL;
                break;
            default: url="sin direccion";
        }
        sendPost(Conexion.PROTOCOLO+base.getSubdominio()+Conexion.SERVIDOR+url,parametros);
    }
    private void sendGet(String direccion)
    {

        try {
            URL url = new URL(direccion);


            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            Log.i("Direcccion-----> ",direccion);

            Log.i("David","user:"+user);
            Log.i("David","pass:"+pass);
            String basicAuth = "Basic " + new String(Base64.encode((user + ":" + pass).getBytes(), Base64.NO_WRAP));
            con.setRequestProperty("Authorization", basicAuth);
            con.setConnectTimeout(30000);
            con.setReadTimeout(30000);
            con.setInstanceFollowRedirects(true);
            int codestatus =con.getResponseCode() ;
            setCodigo(codestatus);
            readStream(con.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
//        StringBuilder builder = new StringBuilder();
//        HttpClient client = new DefaultHttpClient();
//        Log.i("David",""+url);
//        HttpGet httpGet = new HttpGet(url);
//        Log.i("David","user"+user);
//        Log.i("David","pass"+pass);
//// Add authorization header
//
//        httpGet.addHeader(BasicScheme.authenticate(new UsernamePasswordCredentials(user, pass), "UTF-8", false));
//
//// Set up the header types needed to properly transfer JSON
//        httpGet.setHeader("Content-Type", "application/json");
//        try {
//            HttpResponse response = client.execute(httpGet);
//            StatusLine statusLine = response.getStatusLine();
//            int statusCode = statusLine.getStatusCode();
//            setCodigo(statusCode);
//            if (statusCode == 200) {
//                HttpEntity entity = response.getEntity();
//                InputStream content = entity.getContent();
//                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    builder.append(line);
//                }
//                setRespuesta(builder.toString());
//            } else {
//               // Log.e(ParseJSON.class.toString(), "Failed to download file");
//                 Log.e("Conexion","Error en la comunicacion");
//            }
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
    private void sendPost(String direccion,String urlParameters)
    {
        Log.i("Direcccion-----> ",direccion);
        try{
//        String urlParameters  = "param1=a&param2=b&param3=c";
            byte[] postData       = urlParameters.getBytes();
            int    postDataLength = postData.length;
    //        String request        = "http://example.com/index.php";
            URL    url            = new URL( direccion );
            HttpURLConnection con= (HttpURLConnection) url.openConnection();
            String basicAuth = "Basic " + new String(Base64.encode((user + ":" + pass).getBytes(), Base64.NO_WRAP));
            con.setRequestProperty("Authorization", basicAuth);
            con.setConnectTimeout(30000);
            con.setReadTimeout(30000);
            con.setInstanceFollowRedirects(true);
            con.setRequestMethod("POST");
            con.setRequestProperty( "Content-Type", "application/json");
//            conn.setRequestProperty( "charset", "utf-8");
            con.setRequestProperty("Content-Length", Integer.toString(postDataLength));
            con.setUseCaches(false);




            DataOutputStream wr = new DataOutputStream( con.getOutputStream());
            wr.write(postData);


            int codestatus =con.getResponseCode() ;
            Log.e("Conexion","status "+ codestatus);
            setCodigo(codestatus);

            readStream(con.getInputStream());

        }catch (Exception e){

            Log.e("David Error:","post error"+e.getStackTrace());
        }
    }
    private void readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                builder.append(line);
            }
            setRespuesta(builder.toString());
            Log.e("Conexion", "respuesta :" + builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public static int getLOGIN() {
        return LOGIN;
    }


    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
