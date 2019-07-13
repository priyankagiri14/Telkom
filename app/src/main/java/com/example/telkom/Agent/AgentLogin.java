package com.example.telkom.Agent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.telkom.Customer.CustomerInfo;
import com.example.telkom.Dashboard.Maindashboard;
import com.example.telkom.R;

public class AgentLogin extends AppCompatActivity implements View.OnClickListener {

    EditText fname,lname,pswrd;
    Button login;
    String firstname,lastname,password;
    public void onCreate(Bundle savedInstancestate) {
        super.onCreate(savedInstancestate);
        setContentView(R.layout.agent_login);
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
            Toast.makeText(this,"Please enter the value",Toast.LENGTH_SHORT).show();
        }
        else if(!(fname.getText().toString().equals("pankaj"))||!(lname.getText().toString().equals("batyal"))||!(pswrd.getText().toString().equals("123456"))){
            Toast.makeText(this,"Invalid login",Toast.LENGTH_SHORT).show();
        }
        else{
            Intent i=new Intent(this, Maindashboard.class);
            startActivity(i);
        }
    }
}
