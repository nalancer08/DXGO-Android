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

        // CREAMOS EL CONTENEDOR PADRE
        this.window = new SfPanel();

        // CREAMOS PANELES HIJOS
        //SfPanel cuadro1 = new SfPanel();
        //SfPanel cuadro2 = new SfPanel();

        // CREO EL VIEW
        View backGround = new View(context);
        ///View c1 = new View(context);
        //ImageView c2 = new ImageView(context);

        // LE DOY COLOR DE FONDO
        backGround.setBackgroundColor(Color.CYAN);
        //c1.setBackgroundColor(Color.RED);

        // LENO VIEWS (LOS QUE SE PUEDAN)
        //c2.setImageResource(R.drawable.go_1);

        // PONEMOS EL VIEW EN EL PANEL
        this.window.setView(backGround);
        //cuadro1.setView(c1);
        //cuadro2.setView(c2);

        // DOY TAMAÑOS A TODOO
        this.window.setSize(-100, -100);
        //cuadro1.setSize(-90, -50);
        //cuadro2.setSize(-40, -25);

        // HACEMOS APPENDS
        //this.window.append(cuadro1);
        //this.window.append(cuadro2);

        // AGREGAMOS A LA PANTALLA
        windowsCanvas.addView(backGround);
        //windowsCanvas.addView(c1);
        //windowsCanvas.addView(c2);

        String name = new String();
        int id;

        for ( int i = 1; i<=9; i++ ) {
            name = "go_".concat(String.valueOf(i));

            SfPanel pokemonPanel = new SfPanel();
            ImageView pokemonView = new ImageView(context);

            id = context.getResources().getIdentifier(name, "drawable", context.getPackageName());
            pokemonView.setImageResource(id);
            //pokemonView.setBackgroundColor(Color.GRAY);
            pokemonView.setBackgroundResource(R.drawable.border);

            pokemonPanel.setView(pokemonView);
            this.window.append(pokemonPanel);
            pokemonPanel.setSize(-32,-15);
            pokemonPanel.setMargin(5,5,10,0);
            pokemonPanel.setKey(name);
            windowsCanvas.addView(pokemonView);
        }

        // PONER ESTO
        window.update(this);

        // MAGIA
        //Activity activity = (Activity) context;
        this.setContentView(windowsCanvas);



    }

}
