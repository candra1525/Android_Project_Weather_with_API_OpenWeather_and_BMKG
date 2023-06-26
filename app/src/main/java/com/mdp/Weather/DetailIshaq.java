package com.mdp.Weather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class DetailIshaq extends AppCompatActivity
{

    private LinearLayout llIGIshaq, llGithubIshaq, llBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_ishaq);

        llBack = findViewById(R.id.ll_back_tentang_ishaq);
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        llIGIshaq = findViewById(R.id.ll_igishaq);
        llIGIshaq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.instagram.com/ishaq_maula.na/")));
            }
        });



        llGithubIshaq = findViewById(R.id.ll_githubishaq);
        llGithubIshaq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://github.com/MuhammadIshaqMaulana")));
            }
        });
    }
}