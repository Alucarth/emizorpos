package com.ipxserver.davidtorrez.fvpos.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ipxserver.davidtorrez.fvpos.R;
import com.ipxserver.davidtorrez.fvpos.models.NavItem;

import java.util.ArrayList;

/**
 * Created by David-Pc on 09/06/2015.
 */
public class NavAdapter extends BaseAdapter {
    Context context;
    final ArrayList<NavItem> menu;

    public NavAdapter(Context context, final ArrayList<NavItem> menu) {
        this.context = context;
        this.menu = menu;
    }

    public void seleccionar(int position)
    {
        menu.get(position).setHabilitado(true);
        uncheck(position);
        notifyDataSetChanged();
    }
    private void uncheck(int position)
    {
        for(int i = 0;i<menu.size();i++)
        {

            if(i!=position)
            {
//                NavItem navItem = (NavItem)menu.get(i);
//                navItem.setHabilitado(false);
//                menu.set(i,navItem);
                menu.get(i).setHabilitado(false);
            }
        }
    }
    @Override
    public int getCount() {
        return menu.size();
    }

    @Override
    public Object getItem(int position) {
        return menu.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View item = inflater.inflate(R.layout.nav_item, null);

        TextView txtTitulo = (TextView)item.findViewById(R.id.txtTitulo);
        ImageView icono = (ImageView)item.findViewById(R.id.icono);
         NavItem navItem = (NavItem) menu.get(position);
        txtTitulo.setText(navItem.getTitulo());
        icono.setImageResource(navItem.getImage());
        Log.i("David Adapter:", navItem.getTitulo());
        if(navItem.habilitado)
        {
            txtTitulo.setTextColor(Color.parseColor("#ff25478e"));

            Drawable myIcon =context.getApplicationContext().getResources().getDrawable(navItem.getImage());
            myIcon.setColorFilter(Color.parseColor("#ff25478e"), PorterDuff.Mode.SRC_ATOP);
//            ((ImageView)findViewById(R.id.view_to_change)).setImageDrawable(myIcon);

            icono.setImageDrawable(myIcon);
        }
        else
        {
            txtTitulo.setTextColor(Color.parseColor("#cb000000"));

            Drawable myIcon =context.getApplicationContext().getResources().getDrawable(navItem.getImage());
            myIcon.setColorFilter(Color.parseColor("#FF414141"), PorterDuff.Mode.SRC_ATOP);
//            FF414141
        }

        return item;
    }
}
