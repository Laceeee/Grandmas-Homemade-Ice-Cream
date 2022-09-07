package com.laci.grandmasicecream;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExtrasItemAdapter extends RecyclerView.Adapter<ExtrasItemAdapter.ViewHolder> {

    private ArrayList<ExtrasModel> Extras;
    private Context myContext;

    ExtrasItemAdapter(Context context, ArrayList<ExtrasModel> Extras) {
        this.Extras = Extras;
        this.myContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(myContext).inflate(R.layout.extras_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ExtrasItemAdapter.ViewHolder holder, int position) {
        ExtrasModel currentItem = Extras.get(position);

        holder.bindTo(currentItem);
    }

    @Override
    public int getItemCount() {
        return Extras.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView price;
        private TextView category;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.itemName);
            price = itemView.findViewById(R.id.itemPrice);
            category = itemView.findViewById(R.id.category);

            itemView.findViewById(R.id.itemToCart).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }

        public void bindTo(ExtrasModel currentItem) {

            name.setText(currentItem.getName());
            price.setText((int)(currentItem.getPrice()) + " €");
            category.setText(currentItem.getType());

        }
    }


}
