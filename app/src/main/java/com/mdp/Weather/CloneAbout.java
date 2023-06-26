package com.mdp.Weather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class CloneAbout extends AppCompatActivity
{

    private LinearLayout llCandra, llIshaq, llPakNurRachmat, llBack;
    private ImageView ivAndroid, ivUMDP, ivOpenWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clone_about);

        llBack = findViewById(R.id.ll_back_menu);
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        llCandra = findViewById(R.id.ll_candra);
        llCandra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CloneAbout.this, DetailCandra.class));
            }
        });
        llIshaq = findViewById(R.id.ll_ishaq);
        llIshaq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CloneAbout.this, DetailIshaq.class));
            }
        });
        llPakNurRachmat = findViewById(R.id.ll_pak_nurrachmat);
        llPakNurRachmat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CloneAbout.this, DetailPakNurRachmat.class));
            }
        });

        ivAndroid = findViewById(R.id.iv_android);
        ivAndroid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://developer.android.com/")));
            }
        });
        ivUMDP = findViewById(R.id.iv_umdp);
        ivUMDP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://mdp.ac.id/")));
            }
        });
        ivOpenWeather = findViewById(R.id.iv_openweather);
        ivOpenWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://openweathermap.org/")));
            }
        });
    }
}