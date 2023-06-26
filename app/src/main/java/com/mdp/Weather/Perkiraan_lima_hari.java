package com.mdp.Weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Perkiraan_lima_hari extends AppCompatActivity {

    private LinearLayout llBackMenu;
    TextView tvLokasi;
    private String lokasi;
    private TextView tvTanggal, tvDesc, tvTemp, tvTempMax, tvTempMin, tvKemendungan, tvProbHujan, tvKecepatanAngin, tvArahAngin, tvTekananLaut, tvTekananDarat, tvKelembaban, tvJarakPandang;
    private ImageView ivIcon;
    private ArrayList<WeatherModal> weatherModalArrayList = new ArrayList<>();
    private WeatherAdapter weatherAdapter;
    private RecyclerView rvWeather;
    private DecimalFormat df = new DecimalFormat("#.##");
    private String apiKey = "3b88d1c2c59b0a0b7e4a98660010c9c4", lang = "id";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perkiraan_lima_hari);



        // Deklarasi
        tvLokasi = findViewById(R.id.tv_lokasi);

        // Set Lokasi atas
        Intent loc = getIntent();
        lokasi = loc.getStringExtra("varLokasi");

        tvTanggal = findViewById(R.id.tv_tanggal);
        tvDesc = findViewById(R.id.tv_desc);
        tvTemp =findViewById(R.id.tv_temp);
        tvTempMax = findViewById(R.id.tv_max_temp);
        tvTempMin = findViewById(R.id.tv_min_temp);
        tvKemendungan = findViewById(R.id.tv_mendung);
        tvProbHujan = findViewById(R.id.tv_hujan);
        tvKecepatanAngin = findViewById(R.id.tv_wind_speed);
        tvArahAngin = findViewById(R.id.tv_arah);
        tvTekananLaut = findViewById(R.id.tv_tekanan_laut);
        tvTekananDarat = findViewById(R.id.tv_tekanan_darat);
        tvKelembaban = findViewById(R.id.tv_lembab);
        tvJarakPandang = findViewById(R.id.tv_pandang);
        ivIcon = findViewById(R.id.iv_icon);

        rvWeather = findViewById(R.id.rv_ket_5_hari);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvWeather.setLayoutManager(mLayoutManager);
        rvWeather.setHasFixedSize(true);


        weatherModalArrayList = new ArrayList<>();
        weatherAdapter = new WeatherAdapter(weatherModalArrayList, this);
        rvWeather.setAdapter(weatherAdapter);




        // Back Menu
        llBackMenu = findViewById(R.id.ll_backtoMenu);
        llBackMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    getWeatherForecast();

    }

    private void getWeatherForecast()
    {
        String weatherForecast = "https://api.openweathermap.org/data/2.5/forecast?q="+lokasi+"&appid="+apiKey+"&lang="+lang + "&units=metric";
        tvLokasi.setText(lokasi);

        RequestQueue requestQueue = Volley.newRequestQueue(Perkiraan_lima_hari.this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, weatherForecast, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                weatherModalArrayList.clear();
                try {
                    JSONArray list = response.getJSONArray("list");
                    for(int i = 0; i < list.length(); i++)
                    {
                        JSONObject l = list.getJSONObject(i);

                        String tanggal = l.getString("dt_txt");

                        JSONObject obj = l.getJSONArray("weather").getJSONObject(0);
                        String desc = obj.getString("description");
                        String uppercase = desc.substring(0,1).toUpperCase();
                        String lowecase = desc.substring(1);
                        String descTemp = uppercase + lowecase;

                        String temp = l.getJSONObject("main").getString("temp");

                        JSONObject obj2 = l.getJSONArray("weather").getJSONObject(0);
                        String iconTemp = obj2.getString("icon");

                        String tempMin = l.getJSONObject("main").getString("temp_min");

                        String tempMax = l.getJSONObject("main").getString("temp_max");

                        String kemendungan = l.getJSONObject("clouds").getString("all");

                        String probabilitasHujan = l.getString("pop");

                        String kecepatanAngin = l.getJSONObject("wind").getString("speed");

                        String arahAngin = l.getJSONObject("wind").getString("deg");

                        String tekananLaut = l.getJSONObject("main").getString("sea_level");

                        String tekananDarat = l.getJSONObject("main").getString("grnd_level");

                        String kelembaban  = l.getJSONObject("main").getString("humidity");

                        String jarakPandang = l.getString("visibility");

                        weatherModalArrayList.add(new WeatherModal(tanggal, descTemp, temp, iconTemp, tempMin, tempMax, kemendungan, probabilitasHujan, kecepatanAngin, arahAngin, tekananLaut, tekananDarat, kelembaban, jarakPandang));
                    }
                    weatherAdapter.notifyDataSetChanged();
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Perkiraan_lima_hari.this, "Please Insert Valid City Name ...", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonObjectRequest);

    }
}