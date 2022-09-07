package com.laci.grandmasicecream;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class IceCreamItemAdapeter extends RecyclerView.Adapter<IceCreamItemAdapeter.ViewHolder> {

    private ArrayList<IceCreamModel> IceCreams;
    private Context myContext;

    IceCreamItemAdapeter(Context context, ArrayList<IceCreamModel> IcecreamsData) {
        this.IceCreams = IcecreamsData;
        this.myContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(myContext).inflate(R.layout.main_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(IceCreamItemAdapeter.ViewHolder holder, int position) {
        IceCreamModel currentItem = IceCreams.get(position);
        
        holder.bindTo(currentItem);
    }

    @Override
    public int getItemCount() {
        return IceCreams.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView status;
        private ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.itemName);
            status = itemView.findViewById(R.id.itemPrice);
            image = itemView.findViewById(R.id.itemImage);


        }

        public void bindTo(IceCreamModel currentItem) {

            name.setText(currentItem.getName());

            if (currentItem.getStatus().equals("available")) {
                status.setText((int)(currentItem.getPrice()) + " €");
            }
            else if (currentItem.getStatus().equals("unavailable")) {
                status.setText("Nem is volt");
            }
            else {
                status.setText("Kifogyott");
            }

            Glide.with(myContext).load(currentItem.getImageUrl()).placeholder(R.drawable.placeholder).into(image);

        }

    }
}
