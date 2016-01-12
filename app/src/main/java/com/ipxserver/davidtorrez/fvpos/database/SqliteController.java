package com.ipxserver.davidtorrez.fvpos.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by David Torrez on 26/10/2015.
 */
public class SqliteController extends SQLiteOpenHelper {
    public static String TAG="DavidDB";
    public SqliteController(Context applicationcontext) {
        super(applicationcontext, "ipx.db", null, 1);
        Log.d(TAG, "Created");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query;

        Log.d(TAG, "tabla formulario Created");
        query = "CREATE TABLE cuentas (id INTEGER PRIMARY KEY, name TEXT, branch_id TEXT, branch_name TEXT, subdominio TEXT);";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query;
        query = "DROP TABLE IF EXISTS cuentas";
        sqLiteDatabase.execSQL(query);
    }
    public void insertCuenta(String name, String branch_id, String branc_name,String subdominio) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("branch_id", branch_id);
        values.put("branch_name", branc_name);
        values.put("subdominio",subdominio);
        database.insert("cuentas", null, values);
        database.close();
    }
    public String  getSucursal() {
        String selectQuery = "SELECT  * FROM cuentas where id=1";

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        String brach_id=null;
        cursor.moveToFirst();
        brach_id =  cursor.getString(2);
//        if (cursor.moveToFirst()) {
//            do {
//                Log.w(TAG, "id:" + cursor.getString(0));
//                Log.w(TAG, "nombre:" + cursor.getString(1));
//                Log.w(TAG, "estructura:" + cursor.getString(2));
//
//            } while (cursor.moveToNext());
//        }
        return brach_id;
    }
    public String  getSubdominio() {
        String selectQuery = "SELECT  * FROM cuentas where id=1";

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        String subdominio=null;
        cursor.moveToFirst();
        subdominio =  cursor.getString(4);
//        if (cursor.moveToFirst()) {
//            do {
//                Log.w(TAG, "id:" + cursor.getString(0));
//                Log.w(TAG, "nombre:" + cursor.getString(1));
//                Log.w(TAG, "estructura:" + cursor.getString(2));
//
//            } while (cursor.moveToNext());
//        }
        return subdominio;
    }
    public void modificarSucursal(String branch_id) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("branch_id", branch_id);
        database.update("cuentas", values, "id=1", null);
        database.close();
    }

}
