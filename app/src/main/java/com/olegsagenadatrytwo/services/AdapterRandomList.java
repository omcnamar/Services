package com.olegsagenadatrytwo.services;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class AdapterRandomList extends RecyclerView.Adapter<AdapterRandomList.ViewHolder> {

    ArrayList<String> randomStrings = new ArrayList<>();
    public void setList(ArrayList<String> randomStrings) {
        this.randomStrings = randomStrings;
    }

    public AdapterRandomList(ArrayList<String> randomStrings) {
        this.randomStrings = randomStrings;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_for_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvRandomText.setText(randomStrings.get(position));
    }

    @Override
    public int getItemCount() {
        return randomStrings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvRandomText;

        public ViewHolder(View itemView) {
            super(itemView);

            tvRandomText  = (TextView)  itemView.findViewById(R.id.tvRandomText);

        }
    }
}
