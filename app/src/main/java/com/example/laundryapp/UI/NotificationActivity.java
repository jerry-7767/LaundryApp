package com.example.laundryapp.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.laundryapp.ADAPTER.AddressAdapter;
import com.example.laundryapp.ADAPTER.NotificationAdapter;
import com.example.laundryapp.DATA.AddressDatabaseHelper;
import com.example.laundryapp.DATA.LaundryCatgData;
import com.example.laundryapp.DATA.NotificationData;
import com.example.laundryapp.POJO.AddressPojo;
import com.example.laundryapp.POJO.LaundryCatgPojo;
import com.example.laundryapp.POJO.NotificationPojo;
import com.example.laundryapp.R;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<NotificationPojo> notificationPojos;
    private NotificationAdapter notificationAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        recyclerView = findViewById(R.id.recyclervw_notification);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        notificationPojos = new ArrayList<>();
        for (int i = 0; i< NotificationData.notification_name.length; i++){
            notificationPojos.add(new NotificationPojo(
                    NotificationData.notification_name[i]
            ));
        }

        notificationAdapter = new NotificationAdapter(this,notificationPojos);
        recyclerView.setAdapter(notificationAdapter);

    }
}