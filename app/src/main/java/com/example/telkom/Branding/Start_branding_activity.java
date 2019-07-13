package com.example.telkom.Branding;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.telkom.R;

import net.alhazmy13.mediapicker.Image.ImagePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Start_branding_activity extends AppCompatActivity implements View.OnClickListener {
    private TextView textViewDate, textViewTime;
    private TextView textViewStoreName;
    private List<String> mPath;
    String storename;

    private EditText editTextLocation;
    private Button buttonCapture, buttonStartBranding;
    private String filePath;
    private LocationManager locationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtiy_start_branding_layout);
        textViewStoreName=findViewById(R.id.textViewStoreName);
        buttonStartBranding=findViewById(R.id.buttonStartBranding);
        buttonStartBranding.setOnClickListener(this);
        editTextLocation=findViewById(R.id.editTextLocation);
        initViews();
        initDateTime();
        storename = Branding_storeslist.storename;
        textViewStoreName.setText(storename);
        //getLocation();

    }

    /*--------- getting location -----------*/



    /*--------- getting date and time -----------*/

    private void initDateTime() {
        Calendar calendar = Calendar.getInstance();
//date format is:  "Date-Month-Year Hour:Minutes am/pm"
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy"); //Date and time
        String currentDate = sdf.format(calendar.getTime());
        //getting current time
        SimpleDateFormat sds = new SimpleDateFormat(" hh:mm a"); //time
        String currentTime = sds.format(calendar.getTime());


//Day of Name in full form like,"Saturday", or if you need the first three characters you have to put "EEE" in the date format and your result will be "Sat".
        SimpleDateFormat sdf_ = new SimpleDateFormat("EEEE");
        Date date = new Date();
        String dayName = sdf_.format(date);
        textViewDate.setText("" + dayName + " " + currentDate + "");
        textViewTime.setText(currentTime);

    }

    private void initViews() {
        textViewStoreName = (TextView) findViewById(R.id.textViewStoreName);
        //textViewStoreName.setText(); get from spinner id
        textViewDate = (TextView) findViewById(R.id.textViewDate);
        textViewTime = (TextView) findViewById(R.id.textViewTime);
        editTextLocation = (EditText) findViewById(R.id.editTextLocation);
        buttonCapture = (Button) findViewById(R.id.buttonCapture);
        buttonStartBranding = (Button) findViewById(R.id.buttonStartBranding);
        buttonCapture.setOnClickListener(this);
        buttonStartBranding.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonCapture) {

            getImageFromCamera();

        }
        else if(v.getId()==R.id.buttonStartBranding)
        {
            if(filePath==null)
            {
                Toast.makeText(this, "please capture the image", Toast.LENGTH_SHORT).show();
            }
            else {
                Intent i=new Intent(this,Endbranding_store.class);
                startActivity(i);
            }
        }
    }
    /*--------- getting image from camera -----------*/

    private void getImageFromCamera () {
        Log.d("startbranding", "getting image from camera");

        Log.d("startbranding", "IMAGE CHOOSER STARTED");
        new ImagePicker.Builder(this)
                .mode(ImagePicker.Mode.CAMERA)
                .compressLevel(ImagePicker.ComperesLevel.HARD)
                .extension(ImagePicker.Extension.PNG)
                .scale(600, 600)
                .allowMultipleImages(false)
                .enableDebuggingMode(true)
                .build();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ImagePicker.IMAGE_PICKER_REQUEST_CODE && resultCode == RESULT_OK) {


            //for driving license proof
            mPath = data.getStringArrayListExtra(ImagePicker.EXTRA_IMAGE_PATH);
            filePath = mPath.get(0);
            Log.d("startBranding", "onActivityResult: " + filePath);
        }
    }



}



