package com.example.telkom.Dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


import com.example.telkom.Branding.Branding_storeslist;
import com.example.telkom.Customer.CustomerInfo;
import com.example.telkom.R;

public class Maindashboard  extends AppCompatActivity implements View.OnClickListener {


    CardView customer,hourly,branding;
public void onCreate(Bundle  savedInstancestate) {

    super.onCreate(savedInstancestate);
    setContentView(R.layout.main_dashboard);
    customer=findViewById(R.id.Customerinfo);
    hourly=findViewById(R.id.Hourlyupdates);
    branding=findViewById(R.id.Branding);
    branding.setOnClickListener(this);
    customer.setOnClickListener(this);
}

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.Customerinfo){
            Intent i=new Intent(this,CustomerInfo.class);
            startActivity(i);
        }
        else if(v.getId()==R.id.Branding)
        {
            Intent intent=new Intent(this, Branding_storeslist.class);
            startActivity(intent);
            finish();

        }
    }
}
