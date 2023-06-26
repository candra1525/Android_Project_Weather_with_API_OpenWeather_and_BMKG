package com.mdp.Weather;

import androidx.appcompat.app.AppCompatActivity;

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
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class GempaBumi extends AppCompatActivity {

    private TextView tvTanggal, tvJam, tvLintang, tvBujur, tvMagnitudo, tvKedalaman, tvWilayah, tvPotensi, tvDirasakan;
    private ImageView ivGempa;
    String iconapi;

    private LinearLayout llKembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gempa_bumi);

        tvTanggal = findViewById(R.id.tv_tanggal_gempa);
        tvJam = findViewById(R.id.tv_jam_gempa);
        tvLintang = findViewById(R.id.tv_lintang_gempa);
        tvBujur = findViewById(R.id.tv_bujur_gempa);
        tvMagnitudo = findViewById(R.id.tv_magnitudo_gempa);
        tvKedalaman = findViewById(R.id.tv_kedalaman_gempa);
        tvWilayah = findViewById(R.id.tv_wilayah_gempa);
        tvPotensi = findViewById(R.id.tv_potensi_gempa);
        tvDirasakan = findViewById(R.id.tv_dirasakan_gempa);


        llKembali = findViewById(R.id.ll_kembali);
        llKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        ivGempa = findViewById(R.id.iv_lokasigempa);
        ivGempa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GempaBumi.this, GambarAreaGempa.class));
            }
        });
        getInfoGempa();
    }

    private void getInfoGempa() {
        RequestQueue req = Volley.newRequestQueue(GempaBumi.this);
        String apiGempa = "https://data.bmkg.go.id/DataMKG/TEWS/autogempa.json";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, apiGempa, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject objek = response.getJSONObject("Infogempa").getJSONObject("gempa");

                    String tanggal = objek.getString("Tanggal");
                    tvTanggal.setText(String.valueOf(tanggal));

                    String jam = objek.getString("Jam");
                    tvJam.setText(jam);

                    String lintang = objek.getString("Lintang");
                    tvLintang.setText(lintang);

                    String bujur = objek.getString("Bujur");
                    tvBujur.setText(bujur);

                    String magnitudo = objek.getString("Magnitude");
                    tvMagnitudo.setText(magnitudo);

                    String kedalaman = objek.getString("Kedalaman");
                    tvKedalaman.setText(kedalaman);

                    String wilayah = objek.getString("Wilayah");
                    tvWilayah.setText(wilayah);

                    String potensi = objek.getString("Potensi");
                    tvPotensi.setText(potensi);

                    String dirasakan = objek.getString("Dirasakan");
                    tvDirasakan.setText(dirasakan);

                    iconapi = objek.getString("Shakemap");

                    Picasso.get()
                            .load("https://data.bmkg.go.id/DataMKG/TEWS/" + iconapi)
                            .resize(1080, 1080)
                            .into(ivGempa);

                } catch (JSONException e) {
                    Toast.makeText(GempaBumi.this, "Error", Toast.LENGTH_SHORT).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(GempaBumi.this, "Error 2", Toast.LENGTH_SHORT).show();
            }
        });

        req.add(jsonObjectRequest);
    }
}
