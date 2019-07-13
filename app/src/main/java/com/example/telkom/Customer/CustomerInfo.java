package com.example.telkom.Customer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.telkom.MainActivity;
import com.example.telkom.R;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.RectangularBounds;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.util.Arrays;
import java.util.List;


public class CustomerInfo extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {


    Button save_details;
    EditText fname, lname, mobile,placesFragment;
    RadioGroup age,gender,nationality,race;
    String agevalue,gendervalue,nationalityvalue,racevalue;
    RadioButton age1, age2, age3, age4, age5,male,female,sa,fvisit,fresid,white,indian,coloured,black;
    Spinner spinnerprovince;
    private String province_name=null;
    String TAG="Spinner name";
    private double north_lat,north_long,south_lat,south_long;
    private int AUTOCOMPLETE_REQUEST_CODE = 1;
    private List<Place.Field> placeField = Arrays.asList(Place.Field.ID, Place.Field.NAME,Place.Field.ADDRESS);
    private PlacesClient placesClient;
    String address;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_info);
        save_details = findViewById(R.id.save_details);
        fname = findViewById(R.id.firstname);
        lname = findViewById(R.id.lastname);
        mobile = findViewById(R.id.phonenum);
        //age radiogroup
        age = findViewById(R.id.agerg);
        age1 = findViewById(R.id.age1);
        age2 = findViewById(R.id.age2);
        age3 = findViewById(R.id.age3);
        age4 = findViewById(R.id.age4);
        age5 = findViewById(R.id.age5);
        age.setOnCheckedChangeListener(this);

        //gender radiogroup
        gender=findViewById(R.id.genderrg);
        gender.setOnCheckedChangeListener(this);
        male=findViewById(R.id.male);
        female=findViewById(R.id.female);


        //nationality radiogroup
        nationality=findViewById(R.id.nationalityrg);
        nationality.setOnCheckedChangeListener(this);
        sa=findViewById(R.id.sa);
        fvisit=findViewById(R.id.fvist);
        fresid=findViewById(R.id.fresid);

        //race radiogroup
        race=findViewById(R.id.racerg);
        race.setOnCheckedChangeListener(this);
        white=findViewById(R.id.white);
        indian=findViewById(R.id.indian);
        coloured=findViewById(R.id.coloured);
        black=findViewById(R.id.black);

        //Spinner
        spinnerprovince=findViewById(R.id.spinner_province);
        placesFragment=findViewById(R.id.places_autocomplete_fragment);

        //button
        save_details.setOnClickListener(this);


        initSpinnerProvinces();
        initPlaces();
        placesFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupPlacesAutoComplete();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                placesFragment.setText(place.getAddress());
                address=placesFragment.getText().toString();
                Log.i(TAG, "Place: " + place.getName() + " "+place.getAddress() );
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the error.
                Status status = Autocomplete.getStatusFromIntent(data);
                Log.i(TAG, status.getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }

    }

    private void initPlaces() {
        Places.initialize(this, getString(R.string.places_api_key));
        placesClient = Places.createClient(this);
    }
    private void setupPlacesAutoComplete() {

        // Start the autocomplete intent.
        Intent intent = new Autocomplete.IntentBuilder(
                AutocompleteActivityMode.OVERLAY, placeField)
                .setCountry("ZA")
                .setTypeFilter(TypeFilter.CITIES)
                .setLocationRestriction(RectangularBounds.newInstance(
                        //south west lat long
                        new LatLng(south_lat,south_long),
                        //north east lat long
                        new LatLng(north_lat,north_long)
                ))
                .build(this);
        startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);
        Log.d(TAG, "setupPlacesAutoComplete: "+south_lat+" "+south_long+"\n"+north_lat+" "+north_long);
    }

    private void initSpinnerProvinces() {
        String[] provinces=getResources().getStringArray(R.array.spinner_provinces);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item
                ,provinces);
        spinnerprovince.setAdapter(arrayAdapter);
        spinnerprovince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0)
                {
                    placesFragment.setEnabled(false);
                    placesFragment.setText("");
                    province_name=null;
                }
                if(position==1)
                {    //eastern cape cordinates
                    south_lat=-34.2136378;
                    south_long=22.7357412;
                    north_lat=-30.0018012;
                    north_long=30.1947187;
                    province_name=parent.getSelectedItem().toString();
                    Log.d(TAG, "onItemSelected: spinner name is: "+province_name+" "+position);
                    placesFragment.setEnabled(true);
                }
                if(position==2)
                {    //free state cordinates
                    south_lat=-30.69408;
                    south_long=24.3466211;
                    north_lat=-26.6687389;
                    north_long=29.7851298;
                    province_name=parent.getSelectedItem().toString();
                    Log.d(TAG, "onItemSelected: spinner name is: "+province_name+" "+position);
                    placesFragment.setEnabled(true);
                }
                if(position==3)
                {
                    //gauteng cordinates
                    south_lat=-26.92383;
                    south_long=27.1563401;
                    north_lat=-25.1096099;
                    north_long=29.0984187;
                    province_name=parent.getSelectedItem().toString();
                    Log.d(TAG, "onItemSelected: spinner name is: "+province_name+" "+position);
                    placesFragment.setEnabled(true);
                }
                if(position==4)
                {
                    //limpopo cordinates
                    south_lat=-25.4227899;
                    south_long=26.4075388;
                    north_lat=-22.1250298;
                    north_long=31.8838412;
                    province_name=parent.getSelectedItem().toString();
                    Log.d(TAG, "onItemSelected: spinner name is: "+province_name+" "+position);
                    placesFragment.setEnabled(true);
                }

                if(position==5)
                {
                    //mpumalanga cordinates
                    south_lat=-27.5061488;
                    south_long=28.2434599;
                    north_lat=-23.9812388;
                    north_long=32.0335878;
                    province_name=parent.getSelectedItem().toString();
                    Log.d(TAG, "onItemSelected: spinner name is: "+province_name+" "+position);
                    placesFragment.setEnabled(true);

                }

                if(position==6)
                {
                    //kwazulu cordinates
                    south_lat=-31.0853648;
                    south_long=28.8734801;
                    north_lat=-26.80442;
                    north_long=32.8909911;
                    province_name=parent.getSelectedItem().toString();
                    Log.d(TAG, "onItemSelected: spinner name is: "+province_name+" "+position);
                    placesFragment.setEnabled(true);

                }
                if(position==7)
                {
                    //north west cordinates
                    south_lat=-28.1132199;
                    south_long=22.6290299;
                    north_lat=-24.6366288;
                    north_long=28.2983488;
                    province_name=parent.getSelectedItem().toString();
                    Log.d(TAG, "onItemSelected: spinner name is: "+province_name+" "+position);
                    placesFragment.setEnabled(true);
                }
                if(position==8)
                {
                    //northern cape cordinates
                    south_lat=-32.9449877;
                    south_long=16.4518911;
                    north_lat=-24.76586;
                    north_long=25.54933;
                    province_name=parent.getSelectedItem().toString();
                    Log.d(TAG, "onItemSelected: spinner name is: "+province_name+" "+position);
                    placesFragment.setEnabled(true);
                }
                if(position==9)
                {
                    //western cape cordinates
                    south_lat=-47.1313489;
                    south_long=17.7575637;
                    north_lat=-30.4302599;
                    north_long=38.2216904;
                    province_name=parent.getSelectedItem().toString();
                    Log.d(TAG, "onItemSelected: spinner name is: "+province_name+" "+position);
                    placesFragment.setEnabled(true);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.age1:
                // do operations specific to this selection
                agevalue = age1.getText().toString();
                Log.d("age", agevalue);
                break;
            case R.id.age2:
                // do operations specific to this selection
                agevalue = age2.getText().toString();
                Log.d("age", agevalue);
                break;
            case R.id.age3:
                // do operations specific to this selection
                agevalue = age3.getText().toString();
                Log.d("age", agevalue);
                break;
            case R.id.age4:
                // do operations specific to this selection
                agevalue = age4.getText().toString();
                Log.d("age", agevalue);
                break;
            case R.id.age5:
                // do operations specific to this selection
                agevalue = age5.getText().toString();
                break;
            case R.id.male:
                gendervalue=male.getText().toString();
                break;
            case R.id.female:
                gendervalue=female.getText().toString();
                break;
            case R.id.sa:
                nationalityvalue=sa.getText().toString();
                break;
            case R.id.fvist:
                nationalityvalue=fvisit.getText().toString();
                break;
            case R.id.fresid:
                nationalityvalue=fresid.getText().toString();
                break;
            case R.id.white:
                racevalue=white.getText().toString();
                break;
            case R.id.indian:
                racevalue=indian.getText().toString();
                break;
            case R.id.coloured:
                racevalue=coloured.getText().toString();
                break;
            case R.id.black:
                racevalue=black.getText().toString();
                break;
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getBaseContext(), CustomerSurvey.class);
        intent.putExtra("Customer fname", fname.getText().toString());
        intent.putExtra("Customer lname", lname.getText().toString());
        intent.putExtra("Customer mob", mobile.getText().toString());
        intent.putExtra("age",agevalue);
        intent.putExtra("gender",gendervalue);
        intent.putExtra("nationality",nationalityvalue);
        intent.putExtra("race",racevalue);
        intent.putExtra("province",province_name);
        intent.putExtra("address",address);
        startActivity(intent);
        Log.d("province",province_name +address);

    }

}