package com.example.apmarkertadmin.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.sip.SipSession;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.example.apmarkertadmin.Activity.ViewActivity;
import com.example.apmarkertadmin.R;
import com.example.apmarkertadmin.model.Data;

import java.util.List;

public class DisplayAdapter extends RecyclerSwipeAdapter<DisplayAdapter.MyViewHold> {
    private Context mContext;
    private LayoutInflater inflater;
    private List<Data>  dataList;

    public DisplayAdapter(Context mContext, List<Data> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;
        inflater = LayoutInflater.from(mContext);
    }



    @NonNull
    @Override
    public MyViewHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.display_data, parent, false);
        return new MyViewHold(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHold holder, final int position) {
        if(dataList != null){
            Data data =  dataList.get(position);
            holder.name.setText(data.getName());
            holder.region.setText(data.getAddress().getRegion());
            holder.township.setText(data.getAddress().getTownShip());
            holder.address.setText(data.getAddress().getDetailaddress());
            holder.date.setText(data.getDate());
            holder.phnum.setText(data.getPhNum());
            holder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
            holder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, holder.swipeLayout.findViewById(R.id.bottom_wraper));
            holder.swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener() {
                @Override
                public void onStartOpen(SwipeLayout layout) {

                }

                @Override
                public void onOpen(SwipeLayout layout) {

                }

                @Override
                public void onStartClose(SwipeLayout layout) {

                }

                @Override
                public void onClose(SwipeLayout layout) {

                }

                @Override
                public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {

                }

                @Override
                public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {

                }
            });

            holder.swipeLayout.getSurfaceView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // Toast.makeText(mContext, " Click : " + item.getName() + " \n" + item.getEmailId(), Toast.LENGTH_SHORT).show();
                }
            });

            holder.Share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(view.getContext(), "Clicked on Share " + holder.name.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });

            holder.Edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(view.getContext(), "Clicked on Edit  " + holder.name.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });

            holder.Delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemManger.removeShownLayouts(holder.swipeLayout);
                    dataList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, dataList.size());
                    mItemManger.closeAllItems();
                    Toast.makeText(v.getContext(), "Deleted " + holder.name.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });

            mItemManger.bindView(holder.itemView, position);

        }
        else {
            Toast.makeText(mContext,"Null Value Return", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
    public Data getStudentAt(int position) {
        return dataList.get(position);
    }
    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }

    public class MyViewHold extends RecyclerView.ViewHolder {
        private CardView cardView;
        private TextView name,region,address,date,township,phnum;
        public SwipeLayout swipeLayout;
        private TextView Share,Delete,Edit;
        public MyViewHold(@NonNull View itemView) {
            super(itemView);
            swipeLayout = itemView.findViewById(R.id.swipe);
            cardView = itemView.findViewById(R.id.card);
            name = itemView.findViewById(R.id.txt_Name);
            phnum = itemView.findViewById(R.id.txt_ph);
            Share = itemView.findViewById(R.id.Share);
            Delete = itemView.findViewById(R.id.Delete);
            Edit = itemView.findViewById(R.id.Edit);
            region = itemView.findViewById(R.id.txt_Region);
            township = itemView.findViewById(R.id.txt_township);
            address = itemView.findViewById(R.id.txt_address);
            date = itemView.findViewById(R.id.txt_date);
           /* itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Intent intent = new Intent(v.getContext(), ViewActivity.class);
                    intent.putExtra("name", dataList.get(position).getName());
                    intent.putExtra("region", dataList.get(position).getRegion());
                    v.getContext().startActivity(intent);
                }
            });*/
        }
    }
}
