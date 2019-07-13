package com.example.telkom.Branding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.telkom.Dashboard.Maindashboard;
import com.example.telkom.R;

public class Endbranding_store extends AppCompatActivity implements View.OnClickListener {

    TextView textView;
    Button button;
    public void onCreate(Bundle savedInstancestate) {

        super.onCreate(savedInstancestate);
        setContentView(R.layout.endday);
        textView=findViewById(R.id.brandingstart);
        button=findViewById(R.id.enddaybtn);
        button.setOnClickListener(this);
        textView.setText("Your branding has started for the " +Branding_storeslist.storename);

    }

    @Override
    public void onClick(View v) {
        Intent i=new Intent(this, Maindashboard.class);
        startActivity(i);
        finish();
    }
}
