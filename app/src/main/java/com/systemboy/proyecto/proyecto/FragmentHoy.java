package com.systemboy.proyecto.proyecto;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by edwinhernandez on 31/05/15.
 */
public class FragmentHoy extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_hoy, container, false);

        return v;
    }
}
