package com.example.apmarkertadmin.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apmarkertadmin.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomerInfoFragment extends Fragment {


    public CustomerInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_customer_info, container, false);
    }

}
