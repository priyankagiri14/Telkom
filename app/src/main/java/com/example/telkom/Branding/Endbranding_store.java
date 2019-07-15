package com.example.telkom.Branding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.appizona.yehiahd.fastsave.FastSave;
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
        String name=FastSave.getInstance().getString("store",null);
        textView.setText("Your branding has started for the " +name);

    }

    @Override
    public void onClick(View v) {
        Intent i=new Intent(this, Branding_storeslist.class);
        startActivity(i);
        FastSave.getInstance().deleteValue("started");
        FastSave.getInstance().deleteValue("store");
        finish();
    }
}
