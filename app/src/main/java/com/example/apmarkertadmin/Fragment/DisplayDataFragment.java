package com.example.apmarkertadmin.Fragment;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.apmarkertadmin.Activity.ViewActivity;
import com.example.apmarkertadmin.R;
import com.example.apmarkertadmin.adapter.DisplayAdapter;
import com.example.apmarkertadmin.adapter.RecyclerTouchListener;
import com.example.apmarkertadmin.model.Address;
import com.example.apmarkertadmin.model.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayDataFragment extends Fragment {
 private RecyclerView recyclerView;
 private List<Data> dataList;

 private DisplayAdapter displayAdapter;
    private boolean swipeBack = false;

    public DisplayDataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_display_data, container, false);
        dataList = new ArrayList<>();
        dataList.add(new Data("U James","1.8.2019","09403725", new Address("Yangon","Kamaryut","Yadanar Street")));
        dataList.add(new Data("U Bimgo","11.8.2019","09406725", new Address("Yangon","Kamaryut","Yadanar Street")));
        dataList.add(new Data("U Htut","22.8.2019","0946725", new Address("Yangon","Kamaryut","Yadanar Street")));
        return view;
    }
 private void requestionPermission(){
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE},1);
 }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.View_Recycler);
        displayAdapter = new DisplayAdapter(getActivity(),dataList);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(displayAdapter);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,  ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                final int position =viewHolder.getAdapterPosition();
                ArrayList<Data> data = new ArrayList<>();
                data.add(displayAdapter.getStudentAt(position));
                //Data data = dataList.get(position);
                String phnum = "tel:" + data.get(0).getPhNum();
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse(phnum));
                if(ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(getContext(), "Please Grant Permission", Toast.LENGTH_LONG).show();
                    requestionPermission();
                }
                else {
                    startActivity(intent);
                    displayAdapter = new DisplayAdapter(getActivity(), dataList);
                    recyclerView.setAdapter(displayAdapter);
                }
            }

            @Override
            public int convertToAbsoluteDirection(int flags, int layoutDirection) {
                if (swipeBack) {
                    swipeBack = false;
                    return 0;
                }
                return super.convertToAbsoluteDirection(flags, layoutDirection);
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                        .addSwipeRightBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark))
                        .addSwipeRightActionIcon(R.drawable.ic_local_phone_black_24dp)
                        .create()
                        .decorate();
               // adapter.mItemManger.bindView(viewHolder.itemView, viewHolder.getAdapterPosition());
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }

        }).attachToRecyclerView(recyclerView);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
               Intent intent = new Intent(view.getContext(), ViewActivity.class);
                intent.putExtra("name", dataList.get(position).getName());
                intent.putExtra("region", dataList.get(position).getAddress().getRegion());
               startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }
}
