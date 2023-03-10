package com.example.bttrenlop3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txt_black, txt_red, txt_blue, txt_green, txt_count;
    Button btn_count, btn_reset;
    private int mCount = 0;
    int color;
    Boolean saved;
    private String sharedPrefFile = "com.example.bttrenlop3";

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        saved = sharedPreferences.getBoolean("remember_choice", false);
        System.out.println("Save your choice: " + saved);
        if(saved == true){

            SharedPreferences mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
            SharedPreferences.Editor preferencesEditor = mPreferences.edit();
            preferencesEditor.putInt("count", mCount);
            preferencesEditor.putInt("color", color);
            preferencesEditor.clear();
            preferencesEditor.apply();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(getApplicationContext(), PreferenceActivity.class);
        switch (item.getItemId()){
            case R.id.item_preferences:
                intent.putExtra("setting", "setting");
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PreferenceManager.setDefaultValues(this, R.xml.preference, false);

        txt_black = findViewById(R.id.btn_black);
        txt_red = findViewById(R.id.btn_red);
        txt_blue = findViewById(R.id.btn_blue);
        txt_green = findViewById(R.id.btn_green);
        txt_count = findViewById(R.id.txt_count);
        btn_count = findViewById(R.id.btn_count);
        btn_reset = findViewById(R.id.btn_reset);
        SharedPreferences mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        if (savedInstanceState == null){
            mCount = mPreferences.getInt("count", mCount);
            txt_count.setText(String.valueOf(mCount));
            txt_count.setBackgroundColor(mPreferences.getInt("color", color));
        }


        txt_black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_count.setBackgroundColor(Color.BLACK);
                color = Color.BLACK;
            }
        });
        txt_red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_count.setBackgroundColor(Color.RED);
                color = Color.RED;
            }
        });
        txt_blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_count.setBackgroundColor(Color.BLUE);
                color = Color.BLUE;
            }
        });
        txt_green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_count.setBackgroundColor(Color.GREEN);
                color = Color.GREEN;
            }
        });

        btn_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCount++;
                txt_count.setText(String.valueOf(mCount));
            }
        });
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCount = 0;
                txt_count.setText(String.valueOf(mCount));
            }
        });
    }

}