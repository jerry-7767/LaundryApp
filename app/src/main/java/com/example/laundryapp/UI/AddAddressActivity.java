package com.example.laundryapp.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laundryapp.DATA.AddressDatabaseHelper;
import com.example.laundryapp.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class AddAddressActivity extends AppCompatActivity {

    private ImageView img_back;
    private RadioGroup rg_place;
    private TextInputEditText room,street,area,city;
    private MaterialButton save_btn;
    private AddressDatabaseHelper databaseHelper;
    private RadioButton radioButton;
    private String place,address,cityname,areaname,streetname,roomno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);

        img_back = findViewById(R.id.imgvw_back);
        rg_place = findViewById(R.id.radio_group_place);
        room = findViewById(R.id.edtxt_roomno);
        street = findViewById(R.id.edtxt_street);
        area = findViewById(R.id.edtxt_area_name);
        city = findViewById(R.id.edtxt_city_name);
        save_btn = findViewById(R.id.btn_save_address);

        databaseHelper = new AddressDatabaseHelper(this);

        room.addTextChangedListener(addressTextWatcher);
        street.addTextChangedListener(addressTextWatcher);
        area.addTextChangedListener(addressTextWatcher);
        city.addTextChangedListener(addressTextWatcher);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rg_place.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int id = group.getCheckedRadioButtonId();
                radioButton = (RadioButton) group.findViewById(id);
                place = radioButton.getText().toString();
                Toast.makeText(AddAddressActivity.this, radioButton.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        save_btn.setEnabled(false);

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roomno = room.getText().toString().trim();
                streetname = street.getText().toString().trim();
                areaname = area.getText().toString().trim();
                cityname = city.getText().toString().trim();
                    if (!roomno.isEmpty()){
                        if (!streetname.isEmpty()){
                            if (!areaname.isEmpty()){
                                if (!cityname.isEmpty()){
                                    address = roomno+" "+streetname+" "+areaname+" "+cityname;
                                    databaseHelper.insertdata(place, address);
                                    Intent intent = new Intent(AddAddressActivity.this, AddressActivity.class);
                                    startActivity(intent);
                                    Toast.makeText(AddAddressActivity.this, "Address Save Successfully!!", Toast.LENGTH_SHORT).show();
                                    finish();
                                }else {
                                    city.setError("Enter City name");
                                }
                            }else {
                                area.setError("Enter Area name");
                            }
                        }else {
                            street.setError("Enter Street name");
                        }
                    }else {
                        room.setError("Enter Room no");
                    }
            }
        });
    }

    private TextWatcher addressTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
//            roomno = room.getText().toString().trim();
//            streetname = street.getText().toString().trim();
//            areaname = area.getText().toString().trim();
//            cityname = city.getText().toString().trim();
            save_btn.setEnabled(true);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}