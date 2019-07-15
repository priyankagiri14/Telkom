package com.example.telkom.Branding;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import com.appizona.yehiahd.fastsave.FastSave;
import com.example.telkom.Customer.CustomerSurvey;
import com.example.telkom.R;

import java.util.ArrayList;
import java.util.List;


public class Branding_storeslist extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    AppCompatSpinner spinner;
    Button next;
    public static String storename;
    int storeid;
    SpinnerAdapter spinnerAdapter;
    public void onCreate(Bundle savedInstancestate) {
        super.onCreate(savedInstancestate);
        setContentView(R.layout.stores_list);
        spinner=findViewById(R.id.spinner_stores);
        next=findViewById(R.id.next);
        next.setOnClickListener(this);
        spinner.setOnItemSelectedListener(this);
        List<String> list=new ArrayList<>();
        list.add("Store A");
        list.add("Store B");
        list.add("Store C");
        list.add("Store D");
        ArrayAdapter<String>dataAdapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,list);
        dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        storename=spinner.getSelectedItem().toString();
        storeid=spinner.getSelectedItemPosition();
        Log.d("Storename" ,storename +storeid);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this,"Please Select any value",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        Intent i=new Intent(this, Start_branding_activity.class);
        i.putExtra("store_name",storename);
        startActivity(i);

    }
}
