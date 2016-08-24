package com.appbuilders.pokedexgo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.EditText;
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
        AbsoluteLayout subBaseLayout = new AbsoluteLayout(this);

        // CREAMOS
        this.window = new SfPanel();


        // CREO EL VIEW
        View backGround = new View(context);

        // LE DOY COLOR DE FONDO
        backGround.setBackgroundColor(Color.CYAN);

        // PONEMOS EL VIEW EN EL PANEL
        this.window.setView(backGround);

        // DOY TAMAÑOS A TODOO
        this.window.setSize(-100, -100);


        // AGREGAMOS A LA PANTALLA
        subBaseLayout.addView(backGround);

        // PONER ESTO
        window.update(this);

        // MAGIA
        //Activity activity = (Activity) context;
        this.setContentView(subBaseLayout);



    }

}
