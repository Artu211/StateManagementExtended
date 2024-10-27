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
import android.content.res.ColorStateList;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    private static final String KEY_COUNT = "count";
    private static final String KEY_INPUT = "input1";
    private static final String KEY_CHECK = "check1";
    private static final String KEY_TEXT_VISIBLE = "textVisible";
    private static final String KEY_SWITCH = "switch1";
    private static final String KEY_DARK_MODE = "darkMode";

    private TextView textViewCount;
    private int count = 0;
    private Switch switcher;
    private EditText input;
    private TextView textViewOpcjaZaznaczona;
    private CheckBox checkbox;
    private LinearLayout main;
    private Button buttonIncrement;
    private boolean isDarkMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewCount = findViewById(R.id.text);
        buttonIncrement = findViewById(R.id.button);
        switcher = findViewById(R.id.switcher);
        main = findViewById(R.id.main_layout);
        input = findViewById(R.id.input);
        checkbox = findViewById(R.id.check);
        textViewOpcjaZaznaczona = findViewById(R.id.inform);

        // Przywracanie stanów po obrocie ekranu aplikacji
        if (savedInstanceState != null) {
            count = savedInstanceState.getInt(KEY_COUNT);
            input.setText(savedInstanceState.getString(KEY_INPUT));
            checkbox.setChecked(savedInstanceState.getBoolean(KEY_CHECK));
            switcher.setChecked(savedInstanceState.getBoolean(KEY_SWITCH));
            isDarkMode = savedInstanceState.getBoolean(KEY_DARK_MODE, false);

            // Przywracanie widoczności textViewOpcjaZaznaczona
            boolean isTextVisible = savedInstanceState.getBoolean(KEY_TEXT_VISIBLE, false);
            textViewOpcjaZaznaczona.setVisibility(isTextVisible ? View.VISIBLE : View.GONE);
        }
        updateCountText();
        applyTheme();

        // Słuchacz dla guzika gdy go się kliknie zwiększa się count
        buttonIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                updateCountText();
            }
        });

        // Słuchacz dla przełącznika trybu ciemnego
        switcher.setOnCheckedChangeListener((buttonView, isChecked) -> {
            isDarkMode = isChecked;
            applyTheme();
        });

        // Słuchacz dla checkboxa
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                textViewOpcjaZaznaczona.setVisibility(isChecked ? View.VISIBLE : View.GONE);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_COUNT, count);
        outState.putString(KEY_INPUT, input.getText().toString());
        outState.putBoolean(KEY_CHECK, checkbox.isChecked());
        outState.putBoolean(KEY_TEXT_VISIBLE, textViewOpcjaZaznaczona.getVisibility() == View.VISIBLE);
        outState.putBoolean(KEY_SWITCH, switcher.isChecked());
        outState.putBoolean(KEY_DARK_MODE, isDarkMode);
    }

    private void updateCountText() {
        textViewCount.setText("Licznik: " + count);
    }

    // Funkcja zmieniająca motyw
    private void applyTheme() {
        if (isDarkMode) {
            main.setBackgroundColor(Color.BLACK);
            textViewCount.setTextColor(Color.WHITE);
            input.setTextColor(Color.WHITE);
            input.setHintTextColor(Color.GRAY);
            input.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
            checkbox.setTextColor(Color.WHITE);
            textViewOpcjaZaznaczona.setTextColor(Color.WHITE);
            switcher.setTextColor(Color.WHITE);
            switcher.setThumbTintList(ColorStateList.valueOf(Color.WHITE));
            switcher.setTrackTintList(ColorStateList.valueOf(Color.GRAY));
        } else {
            main.setBackgroundColor(Color.WHITE);
            textViewCount.setTextColor(Color.BLACK);
            input.setTextColor(Color.BLACK);
            checkbox.setTextColor(Color.BLACK);
            textViewOpcjaZaznaczona.setTextColor(Color.BLACK);
            buttonIncrement.setTextColor(Color.WHITE);
            switcher.setTextColor(Color.BLACK);
        }
    }
}
