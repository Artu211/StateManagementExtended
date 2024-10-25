package com.example.statemanagementapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.graphics.Color;
import android.widget.CompoundButton;
import androidx.lifecycle.ViewModelProvider;
import android.content.res.ColorStateList;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Switch;


public class MainActivity extends AppCompatActivity {
    private static final String KEY_COUNT = "count";
    private static final String KEY_INPUT = "input1";
    private static final String KEY_CHECK = "check1";
    private static final String KEY_SWITCH = "switch1";
    private TextView textViewCount;
    private int count =0;
    private String input2;
    private Boolean check2;
    private Boolean switch2;
    private Switch switcher;
    private EditText input;
    private TextView textViewOpcjaZaznaczona;
    private CheckBox checkbox;
    private LinearLayout main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        textViewCount=findViewById(R.id.text);
        Button buttonIncrement = findViewById(R.id.button);
        if(savedInstanceState !=null){
            count = savedInstanceState.getInt(KEY_COUNT);
            input2 = savedInstanceState.getString(KEY_INPUT);
            check2 = savedInstanceState.getBoolean(KEY_CHECK);
            switch2 = savedInstanceState.getBoolean(KEY_SWITCH);
        }
        updateCountText();

        buttonIncrement.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                count++;
                updateCountText();
            }

        });


        switcher = findViewById(R.id.switcher);
        main = findViewById(R.id.main_layout);
        input = findViewById(R.id.input);
            buttonIncrement.setBackgroundColor(Color.BLUE);
        // Ustawienie słuchacza zmiany stanu i gdy przełączymy to wykona się ten kod i zrobi nam wszystko pod tryb ciemny
        switcher.setOnCheckedChangeListener((buttonView, isChecked) -> {if (isChecked) {
            main.setBackgroundColor(Color.BLACK);
            textViewCount.setTextColor(Color.WHITE);
            input.setTextColor(Color.WHITE);
            input.setHintTextColor(Color.GRAY);
            input.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
            checkbox.setTextColor(Color.WHITE);
            textViewOpcjaZaznaczona.setTextColor(Color.WHITE);
            switcher.setTextColor(Color.WHITE);
            switcher.setThumbTintList(ColorStateList.valueOf(Color.WHITE)); // Kolor główki Switcha
            switcher.setTrackTintList(ColorStateList.valueOf(Color.GRAY));
            buttonIncrement.setTextColor(Color.BLACK);
            buttonIncrement.setBackgroundColor(Color.WHITE);
        } else {
            main.setBackgroundColor(Color.WHITE);
            textViewCount.setTextColor(Color.BLACK);
            input.setTextColor(Color.BLACK);
            checkbox.setTextColor(Color.BLACK);
            textViewOpcjaZaznaczona.setTextColor(Color.BLACK);
            buttonIncrement.setTextColor(Color.WHITE);
            buttonIncrement.setBackgroundColor(Color.BLUE);
            switcher.setTextColor(Color.BLACK);
        }
        });

        checkbox = findViewById(R.id.check);
        textViewOpcjaZaznaczona = findViewById(R.id.inform);

        // ten kod da nam nasluchiwanie na zmiane check czy zaznaczony czy nie
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Jeśli zaznaczony to wyświetli się text
                    textViewOpcjaZaznaczona.setVisibility(View.VISIBLE);
                } else {
                    // Jeśli odznaczony to ukryje text
                    textViewOpcjaZaznaczona.setVisibility(View.GONE);
                }
            }
        });

    }
    @Override
    protected  void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_COUNT, count);
        outState.putString(KEY_INPUT, input2);
        outState.putBoolean(KEY_CHECK, check2);
        outState.putBoolean(KEY_SWITCH, switch2);
    }
    protected void updateCountText(){
        textViewCount.setText("Licznik: "+ count);
    }

}
