package com.appbuilders.pokedexgo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

public class Splash extends ActionBarActivity {

    protected SfPanel window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //requestWindowFeature(Window.FEATURE_NO_TITLE); // Remove title bar
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); // Make activity fullscreen

        // SIEMPRE SIEMPRE CREAMOS ESTA COSA, PARA QUE TODOO LO DEMAS JALE
        final Context context = this;
        AbsoluteLayout windowsCanvas = new AbsoluteLayout(this);
        this.window = new SfPanel();

        windowsCanvas.setBackgroundResource(R.drawable.po);


        new CountDownTimer(3500, 1000) {

            public void onTick(long millisUntilFinished) {
                //mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
                String time = String.valueOf(millisUntilFinished / 1000);
                Log.d("POKEDEX GO", "TIEMPO PARA CAMBIO = " + time);
            }

            public void onFinish() {

                Intent intent = new Intent(Splash.this, Home.class);
                startActivity(intent);
            }
        }.start();




        ////

        ImageView splash = new ImageView(context);
        splash.setImageResource(R.drawable.po);

        this.window.setView(splash);

        this.window.setSize(-100, -100);
        this.window.setPadding(0,0,0,0);
        this.window.setOrigin(0,0,0,0);
        //windowsCanvas.addView(splash);


        // PONER ESTO
        window.update(this);

        // MAGIA
        //Activity activity = (Activity) context;
        this.setContentView(windowsCanvas);



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(this, "NO NO NO", Toast.LENGTH_SHORT).show();
    }

}
