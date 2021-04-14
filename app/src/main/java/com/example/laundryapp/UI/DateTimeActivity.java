package com.example.laundryapp.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.laundryapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

public class DateTimeActivity extends AppCompatActivity {

    public static String final_date;
    private RelativeLayout nextBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time);

        nextBT=findViewById(R.id.btn_1nxt);

        nextBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DateTimeActivity.this, PaymentActivity.class);
                startActivity(intent);
                finish();
            }
        });

        /* starts before 1 month from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.DAY_OF_WEEK, 0);

        /* ends after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);

        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(DateTimeActivity.this,R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .build();

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                Calendar cal = horizontalCalendar.getDateAt(position);

                cal.add(Calendar.DATE,0);
                SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");

                final_date = format1.format(cal.getTime());
                Toast.makeText(DateTimeActivity.this, "Selected Date:-"+final_date , Toast.LENGTH_SHORT).show();

                String selectedDateStr = DateFormat.format("EEE, MMM d, yyyy", date).toString();
                 final_date = DateFormat.format("dd/MM/yyyy", date).toString();

                Toast.makeText(DateTimeActivity.this, final_date + " selected!", Toast.LENGTH_SHORT).show();

            }
        });


    }
}