package com.systemboy.proyecto.proyecto;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private DrawerLayout drawerLayoutt;
    private ListView Navlist;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar toolbar;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] navigationDrawerItems;
    private ArrayList<ItemObject> NavItems;
    private TypedArray NavIcons;
    NavigationAdapter NavAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitle = mDrawerTitle = getTitle();
        toolbar = (Toolbar) findViewById(R.id.activity_my_toolbar);
        setSupportActionBar(toolbar);

        navigationDrawerItems = getResources().getStringArray(R.array.navigation_drawer_items);
        drawerLayoutt = (DrawerLayout) findViewById(R.id.drawer_layout);
        Navlist = (ListView) findViewById(R.id.left_drawer);

        //listView.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, navigationDrawerItems));
        Navlist.setOnItemClickListener(new DrawerItemClickListener());

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayoutt, toolbar, R.string.app_name, R.string.app_name);
        drawerLayoutt.setDrawerListener(actionBarDrawerToggle);

        NavIcons = getResources().obtainTypedArray(R.array.navigation_drawer_icons);

        NavItems = new ArrayList<>();

        NavItems.add(new ItemObject(navigationDrawerItems[0], NavIcons.getResourceId(0, -1)));
        NavItems.add(new ItemObject(navigationDrawerItems[1], NavIcons.getResourceId(1, -1)));
        NavItems.add(new ItemObject(navigationDrawerItems[2], NavIcons.getResourceId(2, -1)));
        NavItems.add(new ItemObject(navigationDrawerItems[3], NavIcons.getResourceId(3, -1)));



        NavAdapter = new NavigationAdapter(this, NavItems);
        Navlist.setAdapter(NavAdapter);


        // enable ActionBar app icon to behave as action to toggle nav drawer
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        if (savedInstanceState == null) {
            selectItem(0);
        }


    }

    private class DrawerItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);

        }
    }


    private void selectItem(int position) {
        // update the main content by replacing fragments
        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = new FragmentHoy();
                break;
            case 1:
                fragment = new FragmentCalendario();
                break;
            case 2:
                fragment = new FragmentTareas();
                break;
            case 3:
                fragment = new FragmentPlanificador();
                break;
        }
        FragmentManager fragmentManager = getFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        // update selected item and title, then close the drawer
        Navlist.setItemChecked(position, true);
        setTitle(navigationDrawerItems[position]);
        drawerLayoutt.closeDrawer(Navlist);
    }


    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setIcon(android.R.color.transparent);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event){

        if(keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0){
            finish();
        }
        return true;

}
}
