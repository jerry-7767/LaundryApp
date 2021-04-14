package com.example.laundryapp.ADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laundryapp.DATA.AddressDatabaseHelper;
import com.example.laundryapp.POJO.AddressPojo;
import com.example.laundryapp.POJO.NotificationPojo;
import com.example.laundryapp.R;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewholder>{

    public ArrayList<NotificationPojo> notificationPojos;
    private Context context;
    public static RadioButton radioButton = null;
    private static int lastCheckedpos = 0;

    public NotificationAdapter(Context context, ArrayList<NotificationPojo> notificationPojos) {
        this.notificationPojos = notificationPojos;
        this.context = context;
    }

    @NonNull
    @Override
    public NotificationViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_notification_layout,parent, false);
        NotificationViewholder notificationViewholder = new NotificationViewholder(view);
        return notificationViewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewholder holder, int position) {
        holder.textView_notification.setText(notificationPojos.get(position).getNotification());
        holder.radioButton.setChecked(lastCheckedpos == position);
    }

    @Override
    public int getItemCount() {
        return notificationPojos.size();
    }

    public class NotificationViewholder extends RecyclerView.ViewHolder {
        TextView textView_notification;
        public RadioButton radioButton;
        public NotificationViewholder(@NonNull View itemView) {
            super(itemView);
            textView_notification = itemView.findViewById(R.id.txtvw_notification_detail);
            radioButton = itemView.findViewById(R.id.radio_notification_select);

            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lastCheckedpos = getAdapterPosition();
                    notifyDataSetChanged();
                    Toast.makeText(context, "you selected"+textView_notification.getText(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
