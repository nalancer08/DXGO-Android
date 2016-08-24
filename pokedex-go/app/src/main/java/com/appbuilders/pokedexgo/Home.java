package com.appbuilders.pokedexgo;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

public class Home extends ActionBarActivity {

    protected Puppeteer puppeteer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //requestWindowFeature(Window.FEATURE_NO_TITLE); // Remove title bar
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); // Make activity fullscreen

        Typeface font = Typeface.createFromAsset( getAssets(), "fontawesome-webfont.ttf" );

        final Context context = this;

        puppeteer = new Puppeteer(this);
        SfPanel content = puppeteer.createLayout(Puppeteer.PUPPET_HEADER_FOOTER);

        EditText text = new EditText(this);
        SfPanel panel = new SfPanel();
        text.setBackgroundColor(Color.rgb(240, 240, 240));
        panel.setView(text);
        panel.setSize(-100, 200);
        //content.append(panel);
        //puppeteer.baseLayout.addView(text);
        //content.update(this);

        puppeteer.panelFooter.setPadding(0, 0, 0, 0);

        Button button = null;
        button = (Button) puppeteer.addMenuButton(getResources().getString(R.string.fa_bars), 0, -25, Puppeteer.PUPPET_MENU_RIGHT, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Click en menu", Toast.LENGTH_SHORT).show();
            }
        }).getView();

        button.setTypeface(font);
        button.setBackgroundColor(Color.rgb(50, 50, 50));
        button.setTextColor(Color.WHITE);

        button = (Button) puppeteer.addFooterButton(getResources().getString(R.string.fa_user), 0, -25, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Click en 1", Toast.LENGTH_SHORT).show();
            }
        }).getView();

        button.setTypeface(font);
        button.setBackgroundColor(Color.rgb(50, 50, 50));
        button.setTextColor(Color.WHITE);

        button = (Button) puppeteer.addFooterButton(getResources().getString(R.string.fa_warning), 0, -25, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, "Click en 2", Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(MainActivity.this, TertuliaActivity.class);
                //startActivity(intent);
            }
        }).setMargin(-10, 0, 0, 0).setSize(-25, puppeteer.panelFooter.frame.height + 10).getView();

        button.setTypeface(font);
        button.setBackgroundColor(Color.rgb(152, 50, 50));
        button.setTextColor(Color.WHITE);

        button = (Button) puppeteer.addFooterButton(getResources().getString(R.string.fa_cog), 0, -25, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Click en 3", Toast.LENGTH_SHORT).show();
            }
        }).getView();

        button.setTypeface(font);
        button.setBackgroundColor(Color.rgb(50, 50, 50));
        button.setTextColor(Color.WHITE);

        SfPanel panel1 = new SfPanel();
        SfPanel panel2 = new SfPanel();
        SfPanel panel3 = new SfPanel();
        SfPanel panel4 = new SfPanel();
        SfPanel panel5 = new SfPanel();
        SfPanel panel6 = new SfPanel();
        SfPanel panel7 = new SfPanel();

        View view1 = new View(context);
        View view2 = new View(context);
        View view3 = new View(context);
        View view4 = new View(context);
        View view5 = new View(context);
        View view6 = new View(context);
        View view7 = new View(context);

        view1.setBackgroundColor(Color.RED);
        view2.setBackgroundColor(Color.BLUE);
        view3.setBackgroundColor(Color.CYAN);
        view4.setBackgroundColor(Color.YELLOW);
        view5.setBackgroundColor(Color.MAGENTA);
        view6.setBackgroundColor(Color.GRAY);
        view7.setBackgroundColor(Color.GREEN);

        panel1.setView(view1);
        panel2.setView(view2);
        panel3.setView(view3);
        panel4.setView(view4);
        panel5.setView(view5);
        panel6.setView(view6);
        panel7.setView(view7);
        panel1.setSize(-30, 60).setKey("panel1").setMargin(0, 5, 5, 0);
        panel2.setSize(-30, 60).setKey("panel2").setMargin(0, 5, 5, 0);
        panel3.setSize(-30, 30).setKey("panel3").setMargin(0, 5, 5, 0);
        panel4.setSize(-30, 150).setKey("panel4").setMargin(0, 5, 5, 0);
        panel5.setSize(-30, 30).setKey("panel5").setMargin(0, 5, 5, 0);
        panel6.setSize(-30, 30).setKey("panel6").setMargin(0, 5, 5, 0);
        panel7.setSize(-30, 150).setKey("panel7").setMargin(0, 5, 5, 0);

        /*

        puppeteer.baseLayout.addView(view1);
        puppeteer.baseLayout.addView(view2);
        puppeteer.baseLayout.addView(view3);
        puppeteer.baseLayout.addView(view4);
        puppeteer.baseLayout.addView(view5);
        puppeteer.baseLayout.addView(view6);
        puppeteer.baseLayout.addView(view7);

        content.append(panel1);
        content.append(panel2);
        content.append(panel3);
        content.append(panel4);
        content.append(panel5);
        content.append(panel6);
        content.append(panel7);
        content.update(this);

        */

        AbsoluteLayout subBaseLayout = new AbsoluteLayout(context);
        ScrollView scrollView = new ScrollView(context);
        View view = new View(context);
        scrollView.setBackgroundColor(Color.BLUE);
        view.setBackgroundColor(Color.LTGRAY);

        SfPanel scrollPanel = new SfPanel();
        SfPanel innerPanel = new SfPanel();

        puppeteer.baseLayout.addView(scrollView);
        scrollView.addView(subBaseLayout);
        subBaseLayout.addView(view);
        subBaseLayout.addView(view1);
        subBaseLayout.addView(view2);
        subBaseLayout.addView(view3);
        subBaseLayout.addView(view4);
        subBaseLayout.addView(view5);
        subBaseLayout.addView(view6);
        subBaseLayout.addView(view7);

        scrollPanel.setView(scrollView);
        innerPanel.setView(view);

        scrollPanel.setSize(-100, 200).append(innerPanel).setKey("scroll");
        innerPanel.setSize(-100, 0).setKey("children");

        innerPanel.append(panel1);
        innerPanel.append(panel2);
        innerPanel.append(panel3);
        innerPanel.append(panel4);
        innerPanel.append(panel5);
        innerPanel.append(panel6);
        innerPanel.append(panel7);

        scrollPanel.scrollHost = true;

        content.append(scrollPanel);
        content.update(context);
    }
}
