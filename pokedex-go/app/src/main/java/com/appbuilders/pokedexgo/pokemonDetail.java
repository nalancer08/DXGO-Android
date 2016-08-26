package com.appbuilders.pokedexgo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class pokemonDetail extends ActionBarActivity {

    protected SfPanel window = new SfPanel();
    String name = new String();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // JALAMOS EL ID DEL POKEMON
        Intent myIntent = getIntent(); // gets the previously created intent
        String pokemonId = myIntent.getStringExtra("pokemonId");
        Log.d("DXGO", "ID PASADO: " + pokemonId);

        printScreen();

         // SIEMPRE SIEMPRE CREAMOS ESTA COSA, PARA QUE TODOO LO DEMAS JALE
        final Context context = this;
        AbsoluteLayout windowsCanvas = new AbsoluteLayout(this);
        this.window = new SfPanel();

        // FONDO GENERAL
        windowsCanvas.setBackgroundResource(R.drawable.fire);

        // PONEMOS LA IMAGEN
        name = "go_".concat(String.valueOf(pokemonId));
        int id = context.getResources().getIdentifier(name, "drawable", context.getPackageName());


        // Declaro los contenedores padre
        SfPanel header = new SfPanel();
            SfPanel headerStats = new SfPanel();
                SfPanel menuPanel = new SfPanel();
                SfPanel pcPanel = new SfPanel();
                SfPanel favPanel = new SfPanel();
            SfPanel cpProgressPanel = new SfPanel();
            SfPanel pokemonImagePanel = new SfPanel();
        SfPanel body = new SfPanel();
        SfPanel footer = new SfPanel();
            SfPanel close = new SfPanel();

        // Creamos Views
        Button menu = new Button(context);
        TextView cp = new TextView(context, null,  R.style.PC_Style);
        ImageView favorite = new ImageView(context);
        View fondito = new View(context);
        View fondote = new View(context);

        View bodyView = new View(context);

        CircularSeekBar seekbar = new CircularSeekBar(context);
        seekbar.getProgress();
        seekbar.setProgress(50);
        seekbar.setCircleColor(Color.WHITE);

        ImageView pokemonImage = new ImageView(context);

        // Llenamos los views
        Typeface font = Typeface.createFromAsset( getAssets(), "fontawesome-webfont.ttf" );
        menu.setText(getResources().getString(R.string.fa_bars));
        menu.setTextSize(25);
        menu.setTypeface(font);
        menu.setBackground(null);
        menu.setTextColor(Color.WHITE);

        cp.setText("PC 502");
        cp.setTextAppearance(context, R.style.PC_Style);
        cp.setTextSize(20);
        cp.setTextColor(Color.WHITE);
        cp.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        cp.setGravity(View.TEXT_ALIGNMENT_GRAVITY);

        favorite.setImageResource(R.drawable.white);
        //fondito.setBackgroundColor(Color.RED);
        //fondote.setBackgroundColor(Color.BLUE);

        bodyView.setBackgroundColor(Color.WHITE);

        pokemonImage.setImageResource(id);

        // Asiganamos Views
        header.setView(fondote);
            headerStats.setView(fondito);
                menuPanel.setView(menu);
                pcPanel.setView(cp);
            cpProgressPanel.setView(seekbar);
        favPanel.setView(favorite);

        body.setView(bodyView);
            pokemonImagePanel.setView(pokemonImage);

        // Hacemos appends
        this.window.append(header);
            header.append(headerStats);
                headerStats.append(menuPanel);
                headerStats.append(pcPanel);
                headerStats.append(favPanel);
            header.append(cpProgressPanel);
            body.append(pokemonImagePanel);

        this.window.append(body);



        // DAMOS TAMAÑOS
        this.window.setSize(-100,-100);
        header.setSize(-100, -20);
            headerStats.setSize(-100, -30);
                menuPanel.setSize(-20, -110).setMargin(0, 0, 0, 0);
                pcPanel.setSize(-60, -100).setMargin(30, 0, 0, 0);
                favPanel.setSize(-20, -100);
            cpProgressPanel.setSize(-100,-130).setMargin(0, 0,0,0);

        body.setSize(-90,-60).setMargin(250,0,0,0);
            pokemonImagePanel.setSize(-50, -50).setMargin(-550, 0, 50, 0);

        // AGREGAMOS LOS VIEWS A LA VISTA
        windowsCanvas.addView(fondote);
        windowsCanvas.addView(fondito);
        windowsCanvas.addView(menu);
        windowsCanvas.addView(cp);
        windowsCanvas.addView(favorite);

        windowsCanvas.addView(bodyView);
        windowsCanvas.addView(pokemonImage);
        windowsCanvas.addView(seekbar);

        // PONER ESTO
        window.update(this);

        // MAGIA
        this.setContentView(windowsCanvas);
    }

    void printScreen() {

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        Log.d("DXGO", "PANTALLA = " + width + ", " + height);

    }
}
