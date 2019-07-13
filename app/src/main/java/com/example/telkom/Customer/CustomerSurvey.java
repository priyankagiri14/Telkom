package com.example.telkom.Customer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.telkom.MainActivity;
import com.example.telkom.model.customerinforesponse;

import androidx.appcompat.app.AppCompatActivity;

import com.example.telkom.R;
import com.example.telkom.webservices.RetrofitClient;
import com.example.telkom.webservices.WebInterface;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerSurvey extends AppCompatActivity implements View.OnClickListener,RadioGroup.OnCheckedChangeListener, Callback<customerinforesponse> {

    EditText freemebundle, ussd;
    RadioGroup radioGroupairtym,radioGroupdata,radioGroupdataused,radioGroupproduct;
    RadioButton rbat1,rbat2,rbat3,rbat4,rbat5,rbdt1,rbdt2,rbdt3,rbdt4,rbdt5,rbwt,rbfb,rbinsta,rbin,rbedu,rbmusic,rbvideo,rbspo,rbbank,rbnews,rbjob,
                rbsnoke,rbnice,rbfree,rbzkhipa;
    Button survey;
    String fname,lname,mobile,age,gender,nationality,race,province,address,airtime,data,dataused,product,freebundle,ussdstring;
    public void onCreate(Bundle savedInstancestate) {

        super.onCreate(savedInstancestate);
        setContentView(R.layout.cutomer_survey);
        freemebundle=findViewById(R.id.freemebundle);
        ussd=findViewById(R.id.ussdcode);
        survey=findViewById(R.id.survey_done);
        survey.setOnClickListener(this);
        fname = getIntent().getStringExtra("Customer fname");
        lname = getIntent().getStringExtra("Customer lname");
        mobile= getIntent().getStringExtra("Customer mob");
        age= getIntent().getStringExtra("age");
        gender= getIntent().getStringExtra("gender");
        nationality=getIntent().getStringExtra("nationality");
        race=getIntent().getStringExtra("race");
        province=getIntent().getStringExtra("province");
        address=getIntent().getStringExtra("address");
        freebundle=freemebundle.getText().toString();
        ussdstring=ussd.getText().toString();



        //radioGroupairtym
        rbat1=findViewById(R.id.rb_50);
        rbat2=findViewById(R.id.rb_51);
        rbat3=findViewById(R.id.rb_101);
        rbat4=findViewById(R.id.rb_151);
        rbat5=findViewById(R.id.rb_200);
        radioGroupairtym=findViewById(R.id.radioGroupAirtime);
        radioGroupairtym.setOnCheckedChangeListener(this);

        //radiogroupdata
        rbdt1=findViewById(R.id.rb_50d);
        rbdt2=findViewById(R.id.rb_51d);
        rbdt3=findViewById(R.id.rb_101d);
        rbdt4=findViewById(R.id.rb_151d);
        rbdt5=findViewById(R.id.rb_200d);
        radioGroupdata=findViewById(R.id.radioGroupData);
        radioGroupdata.setOnCheckedChangeListener(this);

        //radiogroupusedatafor
        rbwt=findViewById(R.id.rb_whtsapp);
        rbfb=findViewById(R.id.rb_facebook);
        rbinsta=findViewById(R.id.rb_internet);
        rbin=findViewById(R.id.rb_education);
        rbedu=findViewById(R.id.rb_music);
        rbmusic=findViewById(R.id.rb_videos);
        rbvideo=findViewById(R.id.rb_sport);
        rbspo=findViewById(R.id.rb_banking);
        rbbank=findViewById(R.id.rb_news);
        rbjob=findViewById(R.id.rb_jobsearch);
        radioGroupdataused=findViewById(R.id.radioGroupuseDatafor);
        radioGroupdataused.setOnCheckedChangeListener(this);


        //radiogroupproduct
        rbsnoke=findViewById(R.id.rb_simsonke);
        rbnice=findViewById(R.id.rb_monice);
        rbfree=findViewById(R.id.rb_freeme);
        rbzkhipa=findViewById(R.id.rb_zkhipha);
        radioGroupproduct=findViewById(R.id.radioGroupproductpurchase);
        radioGroupproduct.setOnCheckedChangeListener(this);


        Log.d("Values " ,fname + " " + lname + " " + mobile +" " +age +gender +" "+nationality+" "+race +" " +province+ " "+ address);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_50:
            airtime=rbat1.getText().toString();
            break;
            case R.id.rb_51:
                airtime=rbat2.getText().toString();
                break;
            case R.id.rb_101:
                airtime=rbat3.getText().toString();
                break;
            case R.id.rb_151:
                airtime=rbat4.getText().toString();
                break;
            case R.id.rb_200:
                airtime=rbat5.getText().toString();
                break;
            case R.id.rb_50d:
                data=rbdt1.getText().toString();
                break;
            case R.id.rb_51d:
                data=rbdt2.getText().toString();
                break;
            case R.id.rb_101d:
                data=rbdt3.getText().toString();
                break;
            case R.id.rb_151d:
                data=rbdt4.getText().toString();
                break;
            case R.id.rb_200d:
                data=rbdt5.getText().toString();
                break;
            case R.id.rb_whtsapp:
                dataused=rbwt.getText().toString();
                break;
            case R.id.rb_facebook:
                dataused=rbfb.getText().toString();
                break;
            case R.id.rb_insta:
                dataused=rbinsta.getText().toString();
                break;
            case R.id.rb_internet:
                dataused=rbin.getText().toString();
                break;
            case R.id.rb_education:
                dataused=rbedu.getText().toString();
                break;
            case R.id.rb_music:
                dataused=rbmusic.getText().toString();
                break;
            case R.id.rb_videos:
                dataused=rbvideo.getText().toString();
                break;
            case R.id.rb_sport:
                dataused=rbspo.getText().toString();
                break;
            case R.id.rb_banking:
                dataused=rbbank.getText().toString();
                break;
            case R.id.rb_news:
                dataused=rbnews.getText().toString();
                break;
            case R.id.rb_jobsearch:
                dataused=rbjob.getText().toString();
                break;
            case R.id.rb_simsonke:
                product=rbsnoke.getText().toString();
                break;
            case R.id.rb_freeme:
                product=rbfree.getText().toString();
                break;
            case R.id.rb_zkhipha:
                product=rbzkhipa.getText().toString();
                break;
        }
    }
    @Override
    public void onClick(View v) {
        if(freemebundle.length()==0||ussd.length()==0){
            Toast.makeText(this,"Enter some value",Toast.LENGTH_SHORT).show();
        }
        else if(!(freemebundle.getText().toString().equals("R29"))||!(ussd.getText().toString().equals("*180#"))){
            Toast.makeText(this,"The answers you given are incorrect. Try again later",Toast.LENGTH_SHORT).show();
        }
        else{
            Log.d("correct","");
           // savecustomerinfo(fname,lname,mobile,age,gender,race,province,address,airtime,data,dataused,product,ussd.getText().toString(),freemebundle.getText().toString());
            Intent i=new Intent(this, MainActivity.class);
            startActivity(i);
        }

    }

    private void savecustomerinfo(String fname, String lname, String mobile, String age, String gender, String race, String province, String address, String airtime, String data, String dataused, String product, String ussdstring, String freebundle) {

        WebInterface webInterface= RetrofitClient.getClient().create(WebInterface.class);
        try {
            JSONObject paramObject = new JSONObject();
            paramObject.put("fname", fname);
            paramObject.put("lname", lname);
            paramObject.put("phone", mobile);
            paramObject.put("age", age);
            paramObject.put("gender", gender);
            paramObject.put("race", race);
            paramObject.put("province", province);
            paramObject.put("subhurb",address);
            paramObject.put("spend_on_airtime", airtime);
            paramObject.put("spend_on_data", data);
            paramObject.put("preference", dataused);
            paramObject.put("product", product);
            paramObject.put("bundle", freebundle);
            paramObject.put("USSD", ussdstring);

            RequestBody body = RequestBody.create(MediaType.parse("text/plain"),(paramObject).toString());

            Call<customerinforesponse> call= webInterface.Customerinfo(body);
            //exeuting the service
            call.enqueue(this);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void onResponse(Call<customerinforesponse> call, Response<customerinforesponse> response) {
        if(response.isSuccessful() && response.code()==200) {
            //if code is 200 and response is successfull means the Client is login successfully
            //stopping progress
            String status = response.body().getStatus().toString();
            Log.d("Status"," "+status);
            Log.d("ClientLoginActivity","FETCHING CLIENT  DETAILS");

            String message=response.body().getMessage();
            Log.d("message", message);
            if(response.body().getMessage()==message){
                Intent i= new Intent(this, MainActivity.class);
                startActivity(i);
            }
        }

        else {

            try {
                JSONObject jObjError = new JSONObject(response.errorBody().string());
                Log.d("ClientLoginActivity",jObjError.getString("message"));
                String message=jObjError.getString("message");
                //stopping progress

            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();

            }
        }

    }


    @Override
    public void onFailure(Call<customerinforesponse> call, Throwable t) {
        //stopping progress
        Toast.makeText(this,t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
    }

}
