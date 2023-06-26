package com.mdp.Weather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class DetailCandra extends AppCompatActivity
{
    private LinearLayout llIGCandra, llGithubCandra, llBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_candra);

        llBack = findViewById(R.id.ll_back_tentang_candra);
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        llIGCandra = findViewById(R.id.ll_igcandra);
        llIGCandra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.instagram.com/candracandra1525/")));
            }
        });

        llGithubCandra = findViewById(R.id.ll_githubcandra);
        llGithubCandra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://github.com/candracandra1525")));
            }
        });
    }
}