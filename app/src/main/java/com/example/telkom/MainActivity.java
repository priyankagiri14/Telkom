package com.example.telkom;

import android.content.SharedPreferences;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TableLayout tableLayout;
    private EditText editText;
    private Button button;
    public static int dice=0;
    public static int diceroll=0;

    private int[] textviewlist={
            R.id.pointone,
            R.id.pointtwo,
            R.id.pointthree,
            R.id.pointfour,
            R.id.pointfive,
            R.id.pointsix,
            R.id.pointseven,
            R.id.pointeight,
            R.id.pointnine,
            R.id.pointten,
            R.id.pointelvn,
            R.id.pointtwelve,
            R.id.pointthrtn,
            R.id.pointfrtn,
            R.id.pointfftn,
            R.id.pointsxtn,
            R.id.pointsvntn,
            R.id.pointeghtn,
            R.id.pointnintn,
            R.id.pointtwnty,
            R.id.pointtwntyon,
            R.id.pointtwntytwo,
            R.id.pointtwntythre,
            R.id.pointtwntyfor,
            R.id.pointtwntyfv,
            R.id.pointtwntysx,
            R.id.pointtwntysvn,
            R.id.pointtwntyeght,
            R.id.pointtwntynin,
            R.id.pointthirty
    };

    //Dice
    ImageView dice_picture;     //reference to dice picture
    Random rng=new Random();    //generate random numbers
    SoundPool dice_sound;       //For dice sound playing
    int sound_id;               //Used to control sound stream return by SoundPool
    Handler handler;            //Post message to start roll
    Timer timer=new Timer();    //Used to implement feedback to user
    boolean rolling=false;      //Is die rolling?



    TextView textView,textView2,textView3,textView4,textView5,textView6,textView7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //dice
        InitSound();
        //Get a reference to image widget
        dice_picture = (ImageView) findViewById(R.id.imageView);
        dice_picture.setOnClickListener(new HandleClick());
        //link handler to callback
        handler=new Handler(callback);

    }



    //User pressed dice, lets start
    private class HandleClick implements View.OnClickListener {
        public void onClick(View arg0) {
            if (!rolling) {
                rolling = true;
                //Show rolling image
                dice_picture.setImageResource(R.drawable.dice3droll);
                //Start rolling sound
                dice_sound.play(sound_id, 1.0f, 1.0f, 0, 0, 1.0f);
                //Pause to allow image to update
                timer.schedule(new Roll(), 400);
            }
        }
    }

    //New code to initialise sound playback
    void InitSound() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //Use the newer SoundPool.Builder
            //Set the audio attributes, SONIFICATION is for interaction events
            //uses builder pattern
            AudioAttributes aa = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            //default max streams is 1
            //also uses builder pattern
            dice_sound= new SoundPool.Builder().setAudioAttributes(aa).build();

        } else {
            //Running on device earlier than Lollipop
            //Use the older SoundPool constructor
            dice_sound=PreLollipopSoundPool.NewSoundPool();
        }
        //Load the dice sound
        sound_id=dice_sound.load(this,R.raw.shake_dice,1);
    }

    //When pause completed message sent to callback
    class Roll extends TimerTask {
        public void run() {
            handler.sendEmptyMessage(0);
        }
    }

    //Receives message from timer to start dice roll
    Handler.Callback callback = new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            //Get roll result
            //Remember nextInt returns 0 to 5 for argument of 6
            //hence + 1
            switch(rng.nextInt(6)+1) {
                case 1:
                    dice_picture.setImageResource(R.drawable.one);
                    diceroll=1;
                    break;
                case 2:
                    dice_picture.setImageResource(R.drawable.two);
                    diceroll=2;
                    break;
                case 3:
                    dice_picture.setImageResource(R.drawable.three);
                    diceroll=3;
                    break;
                case 4:
                    dice_picture.setImageResource(R.drawable.four);
                    diceroll=4;
                    break;
                case 5:
                    dice_picture.setImageResource(R.drawable.five);
                    diceroll=5;
                    break;
                case 6:
                    dice_picture.setImageResource(R.drawable.six);
                    diceroll=6;
                    break;
                default:
            }


            diceroll=dice+diceroll;//1
            if(diceroll>30){
                diceroll=dice-0;
            }

            SharedPreferences savevalue=getSharedPreferences("0",MODE_PRIVATE);
            //Retreive data from Preference:
            SharedPreferences.Editor editor=savevalue.edit();
            editor.putInt("dice",diceroll);
            editor.apply();
            int dicevalue=savevalue.getInt("dice",0);//initial 1
            dice = savevalue.getInt("dice", 0);//initial 1

            Log.d("value",Integer.toString(diceroll));


            if(diceroll==1)
            {
            /*textView.setVisibility(View.VISIBLE);
            textView.setBackground(getDrawable(R.drawable.buttonbg));*/
                for (int i=1;i<textviewlist.length;i++){
                    findViewById(textviewlist[i]).setVisibility(View.INVISIBLE);
                    findViewById(R.id.pointone).setVisibility(View.VISIBLE);
                    findViewById(R.id.pointone).setBackground(getDrawable(R.drawable.mobslg));
                }

            }
            else if(diceroll==2) {

                for (int i = 1; i < textviewlist.length; i++) {
                    findViewById(textviewlist[i]).setVisibility(View.INVISIBLE);
                    findViewById(R.id.pointtwo).setVisibility(View.VISIBLE);
                    findViewById(R.id.pointtwo).setBackground(getDrawable(R.drawable.mobslg));
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            findViewById(R.id.pointtwo).setVisibility(View.GONE);
                        }
                    }, 1000);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            findViewById(R.id.pointelvn).setVisibility(View.VISIBLE);
                            findViewById(R.id.pointelvn).setBackground(getDrawable(R.drawable.mobslg));
                        }
                    }, 1000);

                }
                dice=11;

            }

            else if(diceroll==3){
                for (int i=0;i<textviewlist.length;i++){
                    findViewById(textviewlist[i]).setVisibility(View.INVISIBLE);
                    findViewById(R.id.pointthree).setVisibility(View.VISIBLE);
                    findViewById(R.id.pointthree).setBackground(getDrawable(R.drawable.mobslg));
                }

            }
            else if(diceroll==4)
            {
                for (int i=0;i<textviewlist.length;i++){
                    findViewById(textviewlist[i]).setVisibility(View.INVISIBLE);
                    findViewById(R.id.pointfour).setVisibility(View.VISIBLE);
                    findViewById(R.id.pointfour).setBackground(getDrawable(R.drawable.mobslg));
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            findViewById(R.id.pointfour).setVisibility(View.GONE);
                        }
                    }, 1000);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            findViewById(R.id.pointtwntythre).setVisibility(View.VISIBLE);
                            findViewById(R.id.pointtwntythre).setBackground(getDrawable(R.drawable.mobslg));
                        }
                    }, 1000);

                }
                dice=23;
            }

            else if(diceroll==5)
            {
                for (int i=0;i<textviewlist.length;i++){
                    findViewById(textviewlist[i]).setVisibility(View.INVISIBLE);
                    findViewById(R.id.pointfive).setVisibility(View.VISIBLE);
                    findViewById(R.id.pointfive).setBackground(getDrawable(R.drawable.mobslg));

                }
            }

            else if(diceroll==6)
            {
                for (int i=0;i<textviewlist.length;i++){
                    findViewById(textviewlist[i]).setVisibility(View.INVISIBLE);
                    findViewById(R.id.pointsix).setVisibility(View.VISIBLE);
                    findViewById(R.id.pointsix).setBackground(getDrawable(R.drawable.mobslg));
                }
            }
            else if(diceroll==7)
            {
                for (int i=0;i<textviewlist.length;i++){
                    findViewById(textviewlist[i]).setVisibility(View.INVISIBLE);
                    findViewById(R.id.pointseven).setVisibility(View.VISIBLE);
                    findViewById(R.id.pointseven).setBackground(getDrawable(R.drawable.mobslg));
                }
            }
            else if(diceroll==8)
            {
                for (int i=0;i<textviewlist.length;i++){
                    findViewById(textviewlist[i]).setVisibility(View.INVISIBLE);
                    findViewById(R.id.pointeight).setVisibility(View.VISIBLE);
                    findViewById(R.id.pointeight).setBackground(getDrawable(R.drawable.mobslg));
                }
            }
            else if(diceroll==9)
            {
                for (int i=0;i<textviewlist.length;i++){
                    findViewById(textviewlist[i]).setVisibility(View.INVISIBLE);
                    findViewById(R.id.pointnine).setVisibility(View.VISIBLE);
                    findViewById(R.id.pointnine).setBackground(getDrawable(R.drawable.mobslg));
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            findViewById(R.id.pointnine).setVisibility(View.GONE);
                        }
                    }, 1000);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            findViewById(R.id.pointsxtn).setVisibility(View.VISIBLE);
                            findViewById(R.id.pointsxtn).setBackground(getDrawable(R.drawable.mobslg));
                        }
                    }, 1000);

                }
                dice=16;
            }

           else if(diceroll==10)
            {
                for (int i=0;i<textviewlist.length;i++){
                    findViewById(textviewlist[i]).setVisibility(View.INVISIBLE);
                    findViewById(R.id.pointten).setVisibility(View.VISIBLE);
                    findViewById(R.id.pointten).setBackground(getDrawable(R.drawable.mobslg));
                }
            }

            else if(diceroll==11)
            {
                for (int i=0;i<textviewlist.length;i++){
                    findViewById(textviewlist[i]).setVisibility(View.INVISIBLE);
                    findViewById(R.id.pointelvn).setVisibility(View.VISIBLE);
                    findViewById(R.id.pointelvn).setBackground(getDrawable(R.drawable.mobslg));
                }

            }
           else if(diceroll==12)
            {
                for (int i=0;i<textviewlist.length;i++){
                    findViewById(textviewlist[i]).setVisibility(View.INVISIBLE);
                    findViewById(R.id.pointtwelve).setVisibility(View.VISIBLE);
                    findViewById(R.id.pointtwelve).setBackground(getDrawable(R.drawable.mobslg));
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            findViewById(R.id.pointtwelve).setVisibility(View.GONE);
                        }
                    }, 1000);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            findViewById(R.id.pointtwntyfv).setVisibility(View.VISIBLE);
                            findViewById(R.id.pointtwntyfv).setBackground(getDrawable(R.drawable.mobslg));
                        }
                    }, 1000);

                }
                dice=25;

            }
           else if(diceroll==13)
            {
                for (int i=0;i<textviewlist.length;i++){
                    findViewById(textviewlist[i]).setVisibility(View.INVISIBLE);
                    findViewById(R.id.pointthrtn).setVisibility(View.VISIBLE);
                    findViewById(R.id.pointthrtn).setBackground(getDrawable(R.drawable.mobslg));
                }
            }
           else if(diceroll==14)
            {
                for (int i=0;i<textviewlist.length;i++){
                    findViewById(textviewlist[i]).setVisibility(View.INVISIBLE);
                    findViewById(R.id.pointfrtn).setVisibility(View.VISIBLE);
                    findViewById(R.id.pointfrtn).setBackground(getDrawable(R.drawable.mobslg));
                }
            }
           else if(diceroll==15)
            {
                for (int i=0;i<textviewlist.length;i++){
                    findViewById(textviewlist[i]).setVisibility(View.INVISIBLE);
                    findViewById(R.id.pointfftn).setVisibility(View.VISIBLE);
                    findViewById(R.id.pointfftn).setBackground(getDrawable(R.drawable.mobslg));
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            findViewById(R.id.pointfftn).setVisibility(View.GONE);
                        }
                    }, 1000);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            findViewById(R.id.pointfive).setVisibility(View.VISIBLE);
                            findViewById(R.id.pointfive).setBackground(getDrawable(R.drawable.mobslg));
                        }
                    }, 1000);

                }
                dice=5;

            }
           else if(diceroll==16)
            {
                for (int i=0;i<textviewlist.length;i++){
                    findViewById(textviewlist[i]).setVisibility(View.INVISIBLE);
                    findViewById(R.id.pointsxtn).setVisibility(View.VISIBLE);
                    findViewById(R.id.pointsxtn).setBackground(getDrawable(R.drawable.mobslg));
                }
            }
            else if(diceroll==17) {
                for (int i = 0; i < textviewlist.length; i++) {
                    findViewById(textviewlist[i]).setVisibility(View.INVISIBLE);
                    findViewById(R.id.pointsvntn).setVisibility(View.VISIBLE);
                    findViewById(R.id.pointsvntn).setBackground(getDrawable(R.drawable.mobslg));
                }
            }
           else if(diceroll==18)
            {
                for (int i=0;i<textviewlist.length;i++){
                    findViewById(textviewlist[i]).setVisibility(View.INVISIBLE);
                    findViewById(R.id.pointeghtn).setVisibility(View.VISIBLE);
                    findViewById(R.id.pointeghtn).setBackground(getDrawable(R.drawable.mobslg));
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            findViewById(R.id.pointeghtn).setVisibility(View.GONE);
                        }
                    }, 1000);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            findViewById(R.id.pointsix).setVisibility(View.VISIBLE);
                            findViewById(R.id.pointsix).setBackground(getDrawable(R.drawable.mobslg));
                        }
                    }, 1000);

                }
                dice=6;
            }
           else if(diceroll==19)
            {
                for (int i=0;i<textviewlist.length;i++){
                    findViewById(textviewlist[i]).setVisibility(View.INVISIBLE);
                    findViewById(R.id.pointnintn).setVisibility(View.VISIBLE);
                    findViewById(R.id.pointnintn).setBackground(getDrawable(R.drawable.mobslg));

                }

            }
            else if(diceroll==20)
            {
                for (int i=0;i<textviewlist.length;i++){
                    findViewById(textviewlist[i]).setVisibility(View.INVISIBLE);
                    findViewById(R.id.pointtwnty).setVisibility(View.VISIBLE);
                    findViewById(R.id.pointtwnty).setBackground(getDrawable(R.drawable.mobslg));
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            findViewById(R.id.pointtwnty).setVisibility(View.GONE);
                        }
                    }, 1000);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            findViewById(R.id.pointseven).setVisibility(View.VISIBLE);
                            findViewById(R.id.pointseven).setBackground(getDrawable(R.drawable.mobslg));
                        }
                    },1000);
                }
                dice=7;
            }
            else if(diceroll==21)
            {
                for (int i=0;i<textviewlist.length;i++){
                    findViewById(textviewlist[i]).setVisibility(View.INVISIBLE);
                    findViewById(R.id.pointtwntyon).setVisibility(View.VISIBLE);
                    findViewById(R.id.pointtwntyon).setBackground(getDrawable(R.drawable.mobslg));
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            findViewById(R.id.pointtwntyon).setVisibility(View.GONE);
                        }
                    }, 1000);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            findViewById(R.id.pointthirty).setVisibility(View.VISIBLE);
                            findViewById(R.id.pointthirty).setBackground(getDrawable(R.drawable.mobslg));
                        }
                    },1000);
                }
                dice=30;
            }
           else if(diceroll==22)
            {
                for (int i=0;i<textviewlist.length;i++){
                    findViewById(textviewlist[i]).setVisibility(View.INVISIBLE);
                    findViewById(R.id.pointtwntytwo).setVisibility(View.VISIBLE);
                    findViewById(R.id.pointtwntytwo).setBackground(getDrawable(R.drawable.mobslg));
                }
            }
           else if(diceroll==23)
            {
                for (int i=0;i<textviewlist.length;i++){
                    findViewById(textviewlist[i]).setVisibility(View.INVISIBLE);
                    findViewById(R.id.pointtwntythre).setVisibility(View.VISIBLE);
                    findViewById(R.id.pointtwntythre).setBackground(getDrawable(R.drawable.mobslg));
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            findViewById(R.id.pointtwntythre).setVisibility(View.GONE);
                        }
                    }, 1000);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            findViewById(R.id.pointfour).setVisibility(View.VISIBLE);
                            findViewById(R.id.pointfour).setBackground(getDrawable(R.drawable.mobslg));
                        }
                    }, 1000);

                }
                dice=4;
            }
          else  if(diceroll==24)
            {
                for (int i=0;i<textviewlist.length;i++){
                    findViewById(textviewlist[i]).setVisibility(View.INVISIBLE);
                    findViewById(R.id.pointtwntyfor).setVisibility(View.VISIBLE);
                    findViewById(R.id.pointtwntyfor).setBackground(getDrawable(R.drawable.mobslg));
                }
            }
           else if(diceroll==25)
            {
                for (int i=0;i<textviewlist.length;i++){
                    findViewById(textviewlist[i]).setVisibility(View.INVISIBLE);
                    findViewById(R.id.pointtwntyfv).setVisibility(View.VISIBLE);
                    findViewById(R.id.pointtwntyfv).setBackground(getDrawable(R.drawable.mobslg));
                }
            }
           else if(diceroll==26)
            {
                for (int i=0;i<textviewlist.length;i++){
                    findViewById(textviewlist[i]).setVisibility(View.INVISIBLE);
                    findViewById(R.id.pointtwntysx).setVisibility(View.VISIBLE);
                    findViewById(R.id.pointtwntysx).setBackground(getDrawable(R.drawable.mobslg));
                }
            }
           else if(diceroll==27)
            {
                for (int i=0;i<textviewlist.length;i++){
                    findViewById(textviewlist[i]).setVisibility(View.INVISIBLE);
                    findViewById(R.id.pointtwntysvn).setVisibility(View.VISIBLE);
                    findViewById(R.id.pointtwntysvn).setBackground(getDrawable(R.drawable.mobslg));
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            findViewById(R.id.pointtwntysvn).setVisibility(View.GONE);
                        }
                    }, 1000);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            findViewById(R.id.pointone).setVisibility(View.VISIBLE);
                            findViewById(R.id.pointone).setBackground(getDrawable(R.drawable.mobslg));
                        }
                    },1000);
                }
                dice=1;
            }
           else if(diceroll==28)
            {
                for (int i=0;i<textviewlist.length;i++){
                    findViewById(textviewlist[i]).setVisibility(View.INVISIBLE);
                    findViewById(R.id.pointtwntyeght).setVisibility(View.VISIBLE);
                    findViewById(R.id.pointtwntyeght).setBackground(getDrawable(R.drawable.mobslg));
                }
            }
           else if(diceroll==29)
            {
                for (int i=0;i<textviewlist.length;i++){
                    findViewById(textviewlist[i]).setVisibility(View.INVISIBLE);
                    findViewById(R.id.pointtwntynin).setVisibility(View.VISIBLE);
                    findViewById(R.id.pointtwntynin).setBackground(getDrawable(R.drawable.mobslg));
                }
            }
           else if(diceroll==30)
            {
                for (int i=0;i<textviewlist.length;i++){
                    findViewById(textviewlist[i]).setVisibility(View.INVISIBLE);
                    findViewById(R.id.pointthirty).setVisibility(View.VISIBLE);
                    findViewById(R.id.pointthirty).setBackground(getDrawable(R.drawable.mobslg));
                }
                Toast.makeText(MainActivity.this,"You win",Toast.LENGTH_SHORT).show();
            }

            rolling=false;  //user can press again
            return true;
        }

    };

    //Clean up
    protected void onPause() {
        super.onPause();
        dice_sound.pause(sound_id);
    }
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
