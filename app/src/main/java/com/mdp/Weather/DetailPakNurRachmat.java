package com.mdp.Weather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class DetailPakNurRachmat extends AppCompatActivity
{

    private LinearLayout llIGPak, llGithubPak, llBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pak_nur_rachmat);

        llBack = findViewById(R.id.ll_back_tentang_pak);
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        llIGPak = findViewById(R.id.ll_igpak);
        llIGPak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.instagram.com/nurrachmat/")));
            }
        });

        llGithubPak = findViewById(R.id.ll_githubpak);
        llGithubPak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://github.com/nurrachmat")));
            }
        });
    }
}