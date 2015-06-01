package com.systemboy.proyecto.proyecto;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class NavigationAdapter extends BaseAdapter {
    private Activity activity;
    ArrayList<ItemObject> arrayitems;

    public NavigationAdapter(Activity activity, ArrayList<ItemObject> listarray){
        super();
        this.activity = activity;
        this.arrayitems = listarray;
    }
    @Override
    public int getCount() {
        return arrayitems.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayitems.get(position);
    }

    public static class Fila{
        TextView titulo_item;
        ImageView icono;
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Fila view;
        LayoutInflater inflator = activity.getLayoutInflater();
        if(convertView == null){
            view = new Fila();
            ItemObject item = arrayitems.get(position);
            convertView = inflator.inflate(R.layout.drawer_list_item, null);

            view.titulo_item = (TextView) convertView.findViewById(R.id.txt_item);
            view.titulo_item.setText(item.getTitulo());

            view.icono = (ImageView) convertView.findViewById(R.id.icon);
            view.icono.setImageResource(item.getIcono());
            convertView.setTag(view);
        }
        return convertView;
    }
}
