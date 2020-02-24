package com.example.apmarkertadmin.Fragment;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.apmarkertadmin.R;
import com.example.apmarkertadmin.adapter.HomeAdapter;
import com.example.apmarkertadmin.model.Home;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment{

    public HomeFragment() {
        // Required empty public constructor
    }
    String name,da;
    private NavController navController;
    private RecyclerView recyclerView;
    private HomeAdapter adapter;
    private List<Home> homeList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_home, container, false);

    return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        recyclerView = view.findViewById(R.id.recycler_home);
        homeList = new ArrayList<>();
        homeList.add(new Home(0,getResources().getString(R.string.add_data), R.drawable.ic_language_black_24dp));
        homeList.add(new Home(1,getResources().getString(R.string.view_data), R.drawable.ic_language_black_24dp));
        homeList.add(new Home(2,getResources().getString(R.string.history), R.drawable.ic_language_black_24dp));
        homeList.add(new Home(3,getResources().getString(R.string.info), R.drawable.ic_language_black_24dp));
        homeList.add(new Home(4,getResources().getString(R.string.help), R.drawable.ic_language_black_24dp));
        homeList.add(new Home(5,getResources().getString(R.string.logout), R.drawable.ic_language_black_24dp));
        try{
            name=getActivity().getIntent().getStringExtra("EXTRA");
            new AddDataClass().execute();
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        adapter = new HomeAdapter(getContext(), homeList);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setLayoutFrozen(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),layoutManager.getOrientation()));
        recyclerView.setAdapter(adapter);
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        recyclerView.stopScroll();
        adapter.setOnItemClickListener(new HomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Home home) {
                switch (home.getId()){
                    case 0:
                        navController.navigate(R.id.action_homeFragment_to_addDetailFragment);
                        break;
                    case 1:
                        navController.navigate(R.id.action_homeFragment_to_displayDataFragment);
                        break;
                    case 2:
                        navController.navigate(R.id.action_homeFragment_to_historyFragment);
                        break;
                    case 3:
                        navController.navigate(R.id.action_homeFragment_to_infoFragment);
                        break;
                    case 4:
                        navController.navigate(R.id.action_homeFragment_to_helpFragment);
                        break;
                    case 5:
                    Toast.makeText(getContext(),"Logout",Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        });
    }


    class AddDataClass extends AsyncTask<Void, Void, Void>{

    @Override
    protected Void doInBackground(Void... voids) {
        try {
             da = getActivity().getIntent().getStringExtra("NAME");
            for(int i = 0 ; i< 1; i++){
                switch (name){
                    case "openFragment":
                        try {
                            navController.navigate(R.id.action_homeFragment_to_addDetailFragment);
                            break;
                        }catch (IllegalArgumentException e){
                            e.printStackTrace();
                        }
                }
            }
        }catch (NullPointerException e){
            e.printStackTrace();
            da = null;
        }
        return null;
    }
}
}
