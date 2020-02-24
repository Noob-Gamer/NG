package com.example.apmarkertadmin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apmarkertadmin.R;
import com.example.apmarkertadmin.model.Home;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private List<Home> homeList;
    private OnItemClickListener listener;

    public HomeAdapter(Context context, List<Home> homeList) {
        this.context = context;
        this.homeList = homeList;
        inflater= LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.home_ui,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if(homeList != null){
            Home home = homeList.get(position);
            holder.home_img.setImageResource(home.getImg());
            holder.home_text.setText(home.getName());
        }
        else {
            holder.home_img.setImageResource(0);
            holder.home_text.setText("No data");
        }
    }

    @Override
    public int getItemCount() {
        return homeList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView home_img;
        private TextView home_text;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            home_img = itemView.findViewById(R.id.img_home);
            home_text = itemView.findViewById(R.id.txt_home);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(homeList.get(position));
                    }
                }
            });
        }
    }
    public interface OnItemClickListener {
        void onItemClick(Home home);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
