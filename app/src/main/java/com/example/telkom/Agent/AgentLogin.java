package com.example.telkom.Agent;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.appizona.yehiahd.fastsave.FastSave;
import com.example.telkom.Branding.Endbranding_store;
import com.example.telkom.Customer.CustomerInfo;
import com.example.telkom.Dashboard.Maindashboard;
import com.example.telkom.R;

import lolodev.permissionswrapper.callback.OnRequestPermissionsCallBack;
import lolodev.permissionswrapper.wrapper.PermissionWrapper;

public class AgentLogin extends AppCompatActivity implements View.OnClickListener {

    EditText fname,lname,pswrd;
    Button login;
    String firstname,lastname,password;

    public void onCreate(Bundle savedInstancestate) {
        super.onCreate(savedInstancestate);
        setContentView(R.layout.agent_login);
        requestPermission();
    }
    private void requestPermission() {
        /*----------------------------Requesting Permissions---------------------------------*/
        new PermissionWrapper.Builder(this)
                .addPermissions(new String[]{Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION})
                //enable rationale message with a custom message
                .addPermissionRationale("Pleas Enable permissions to continue")
                //show settings dialog,in this case with default message base on requested permission/s
                .addPermissionsGoSettings(true)
                //enable callback to know what option was choosed
                .addRequestPermissionsCallBack(new OnRequestPermissionsCallBack() {
                    @Override
                    public void onGrant() {
                        Log.i("agebtlogin", "Permission was granted.");
                        initViews();
                        checkbranding();


                    }

                    @Override
                    public void onDenied(String permission) {
                        Log.i("agebtlogin", "Permission was not granted.");
                    }
                }).build().request();

    }

    private void checkbranding() {
        boolean iscExists = FastSave.getInstance().isKeyExists("started");
        if(iscExists)
        {
            Intent i=new Intent(this, Endbranding_store.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        }

    }

    private void initViews() {
        login=findViewById(R.id.loginbtn);
        login.setOnClickListener(this);
        fname=findViewById(R.id.firstname);
        lname=findViewById(R.id.lastname);
        pswrd=findViewById(R.id.password);
        firstname=fname.getText().toString();
        lastname=lname.getText().toString();
        password=pswrd.getText().toString();
    }

    @Override
    public void onClick(View v) {
        if(fname.length()==0||lname.length()==0||pswrd.length()==0){
            Toast.makeText(this,"Please enter the credentials",Toast.LENGTH_SHORT).show();
        }
        else if(!(fname.getText().toString().equalsIgnoreCase("pankaj"))||!(lname.getText().toString().equals("batyal"))||!(pswrd.getText().toString().equals("123456"))){
            Toast.makeText(this,"Invalid login",Toast.LENGTH_SHORT).show();
        }
        else{
            Intent i=new Intent(this, Maindashboard.class);startActivity(i);
    }
}}
