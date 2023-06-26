package com.mdp.Weather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
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

public class GambarAreaGempa extends AppCompatActivity {

    private ImageView ivGempa;
    private String icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gambar_area_gempa);

        ivGempa = findViewById(R.id.iv_gempa);

        getIcon();
    }


    private void getIcon() {
        RequestQueue req = Volley.newRequestQueue(GambarAreaGempa.this);
        String apiGempa = "https://data.bmkg.go.id/DataMKG/TEWS/autogempa.json";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, apiGempa, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject objek = response.getJSONObject("Infogempa").getJSONObject("gempa");

                    icon = objek.getString("Shakemap");

                    Picasso.get()
                            .load("https://data.bmkg.go.id/DataMKG/TEWS/" + icon)
                            .resize(1080, 1080)
                            .into(ivGempa);

                } catch (JSONException e) {
                    Toast.makeText(GambarAreaGempa.this, "Error", Toast.LENGTH_SHORT).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(GambarAreaGempa.this, "Error 2", Toast.LENGTH_SHORT).show();
            }
        });

        req.add(jsonObjectRequest);
    }
}