package com.olegsagenadatrytwo.services;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdapterRandomObjects extends RecyclerView.Adapter<AdapterRandomObjects.ViewHolderRandom> {

    ArrayList<TV> randomTVList = new ArrayList<>();

    public void setList(ArrayList<TV> randomTVList) {
        this.randomTVList = randomTVList;
    }

    public AdapterRandomObjects(ArrayList<TV> randomTVList) {
        this.randomTVList = randomTVList;
    }

    @Override
    public ViewHolderRandom onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_for_tv_adapter, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "", Toast.LENGTH_SHORT).show();
            }
        });
        return new ViewHolderRandom(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderRandom holder, final int position) {
        holder.tvColor.setText(randomTVList.get(position).getColor());
        holder.tvDefinition.setText(randomTVList.get(position).getDefinition());
        holder.tvSize.setText(randomTVList.get(position).getSize());
        holder.tvCurrentChanel.setText(randomTVList.get(position).getCurrentChanel());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RemoteViews contentView = new RemoteViews(v.getContext().getPackageName(), R.layout.layout_for_tv_notification);
                contentView.setTextViewText(R.id.tvColor, randomTVList.get(position).getColor());
                contentView.setTextViewText(R.id.tvDefinition, randomTVList.get(position).getDefinition());
                contentView.setTextViewText(R.id.tvSize, randomTVList.get(position).getSize());
                contentView.setTextViewText(R.id.tvCurrentChanel, randomTVList.get(position).getCurrentChanel());

                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(v.getContext())

                        .setSmallIcon(R.mipmap.ic_launcher)

                        .setContent(contentView);

                Intent intent1 = new Intent(v.getContext(), MainActivity.class);

                PendingIntent pendingIntent = PendingIntent.getActivity(v.getContext(), 0, intent1, 0);

                mBuilder.setContentIntent(pendingIntent);

                NotificationManager mNotificationManager = (NotificationManager) v.getContext().getSystemService(v.getContext().NOTIFICATION_SERVICE);

                mNotificationManager.notify(001, mBuilder.build());
            }
        });
    }

    @Override
    public int getItemCount() {
        return randomTVList.size();
    }

    public class ViewHolderRandom extends RecyclerView.ViewHolder {

        private TextView tvColor;
        private TextView tvDefinition;
        private TextView tvSize;
        private TextView tvCurrentChanel;

        public ViewHolderRandom(View itemView) {
            super(itemView);
            tvColor  = (TextView)  itemView.findViewById(R.id.tvColor);
            tvDefinition  = (TextView)  itemView.findViewById(R.id.tvDefinition);
            tvSize  = (TextView)  itemView.findViewById(R.id.tvSize);
            tvCurrentChanel  = (TextView)  itemView.findViewById(R.id.tvCurrentChanel);

        }
    }
}
