package com.example.apmarkertadmin.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apmarkertadmin.R;
import com.example.apmarkertadmin.adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment {
private ViewPager viewPager;
private TabLayout tabLayout;
private ViewPagerAdapter adapter;

    public InfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        viewPager = view.findViewById(R.id.pager);
        tabLayout =view.findViewById(R.id.tablay);
        adapter = new ViewPagerAdapter(getChildFragmentManager());

        adapter.AddFragment(new MyInfoFragment(),"My Profile");
        adapter.AddFragment(new AdminFragment(), "Admin Info");
        adapter.AddFragment(new UserInfoFragment(), "User Info");
        adapter.AddFragment(new CustomerInfoFragment(),"Customer Info");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
       /* tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(1).setIcon( R.drawable.other);
        tabLayout.getTabAt(2).setIcon( R.drawable.ic_settings_24dp);
        tabLayout.getTabAt(3).setIcon( R.drawable.ic_local_library_24dp);*/
        return view;
    }

}
