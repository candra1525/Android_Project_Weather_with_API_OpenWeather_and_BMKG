package com.mdp.Weather;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;


import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    private RelativeLayout rlAtas;
    private LinearLayout llBawah;
    private TextView tvKota, tvTanggalJam, tvTemperatureSekarang, tv_temperature_min_max, tvKeteranganIconCuaca;
    private LottieAnimationView loCold, loHot, loEmotePolusiBaik, loEmotePolusiSangatBaik,
            loEmotePolusiBuruk, loEmotePolusiSangatBuruk, loEmotePolusiSedang;

    private LottieAnimationView loHembusanAngin, loArahAngin, loTekDarat, loTekLaut;
    private ImageView ivIconCuaca;
    private LinearLayout llTerbit, llTembenam, llAngin, llTekanan, llKelembaban, llPolusiUdara, llTentang, llSearch, llPerkiraan5Hari, llJarakPandang, llGempaBumi;
    private TextView tvTerbit, tvTembenam, tvAngin, tvTekanan, tvKelembaban, tvPolusiUdara, tvJarakPandang;
    private String lang = "id";
    private String apiKey = "YOUR API KEY";
    private double longitude;
    private double latitude;
    private String city = "";
    private DecimalFormat df = new DecimalFormat("#.##");
    private ProgressBar pbApp;
    private TextView tvSembunyikan;
    private TextView tvKM, tvHM, tvDAM, tvM, tvDM, tvCM, tvMM;
    private LinearLayout llDetailTerbit, llDetailTembenam, llDetailAngin, llDetailTekanan, llDetailKelembaban, llDetailPolusiUdara, llDetailJarakPandang;

    private TextView tvJudulTerbit, tvJudulTembenam, tvJudulAngin, tvJudulTekanan, tvJudulKelembaban, tvJudulPolusiUdara, tvJudulJarakPandang;
    private ImageView ivTerbit, ivTembenam, ivAngin, ivTekanan, ivKelembaban, ivPolusiUdara, ivJarakPandang;

    private TextView tvWaktuTerbit, tvLatitudeTerbit, tvLongitudeTerbit;
    private TextView tvWaktuTembenam, tvLatitudeTembenam, tvLongitudeTembenam;
    private TextView tvKecepatanAngin, tvArahAngin, tvArahAnginDerajat, tvKecepatanHembusanAngin;
    private TextView tvTekananDarat2, tvTekananLaut2;
    private TextView tvKelembaban2, tvkemendungan2;
    private TextView tvCO, tvNO, tvNO2, tvO3, tvSO2, tvPM25, tvPM10, tvNH3;

    //  Deklarasi untuk mendapatkan Koordinat Longitude dan Latitude
    private static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;

    //konstruktor, jika perlu
    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        city = intent.getStringExtra("chosenCountry");
        setContentView(R.layout.activity_main);

//      Pemanggilan ID
        tvKota = findViewById(R.id.tv_kota);
        tvTanggalJam = findViewById(R.id.tv_tanggal_jam);
        tvTemperatureSekarang = findViewById(R.id.tv_temperature_sekarang);
        tv_temperature_min_max = findViewById(R.id.tv_temperature_min_max);
        tvKeteranganIconCuaca = findViewById(R.id.tv_keterangan_icon_cuaca);
        loCold = findViewById(R.id.lo_animated_temperature_cold);
        loHot = findViewById(R.id.lo_animated_temperature_hot);
        ivIconCuaca = findViewById(R.id.iv_icon_cuaca);
        tvTerbit = findViewById(R.id.tv_terbit);
        tvTembenam = findViewById(R.id.tv_tembenam);
        tvAngin = findViewById(R.id.tv_angin);
        tvTekanan = findViewById(R.id.tv_tekanan);
        tvKelembaban = findViewById(R.id.tv_kelembaban);
        tvkemendungan2 = findViewById(R.id.tv_kemendungan2);
        tvPolusiUdara = findViewById(R.id.tv_polusi_udara);
        tvJarakPandang = findViewById(R.id.tv_jarak_pandang);
        rlAtas = findViewById(R.id.rl_atas);
        llBawah = findViewById(R.id.ll_bawah);
        pbApp = findViewById(R.id.pb_app);
        rlAtas = findViewById(R.id.rl_atas);
        llBawah = findViewById(R.id.ll_bawah);

        llDetailTerbit = findViewById(R.id.ll_detail_terbit);
        llDetailTembenam = findViewById(R.id.ll_detail_tembenam);
        llDetailAngin = findViewById(R.id.ll_detail_angin);
        llDetailTekanan = findViewById(R.id.ll_detail_tekanan);
        llDetailKelembaban = findViewById(R.id.ll_detail_kelembaban);
        llDetailPolusiUdara = findViewById(R.id.ll_detail_polusi);
        llDetailJarakPandang = findViewById(R.id.ll_detail_jarak_pandang);

        tvKM = findViewById(R.id.tv_km);
        tvHM = findViewById(R.id.tv_hm);
        tvDAM = findViewById(R.id.tv_dam);
        tvM = findViewById(R.id.tv_m);
        tvDM = findViewById(R.id.tv_dm);
        tvCM = findViewById(R.id.tv_cm);
        tvMM = findViewById(R.id.tv_mm);

        tvJudulTerbit = findViewById(R.id.tv_judul_terbit);
        tvJudulTembenam = findViewById(R.id.tv_judul_tembenam);
        tvJudulAngin = findViewById(R.id.tv_judul_angin);
        tvJudulTekanan = findViewById(R.id.tv_judul_tekanan);
        tvJudulKelembaban = findViewById(R.id.tv_judul_kelembaban);
        tvJudulPolusiUdara = findViewById(R.id.tv_judul_polusi_udara);
        tvJudulJarakPandang = findViewById(R.id.tv_judul_jarak_pandang);

        ivTerbit = findViewById(R.id.iv_terbit);
        ivTembenam = findViewById(R.id.iv_tembenam);
        ivAngin = findViewById(R.id.iv_angin);
        ivTekanan = findViewById(R.id.iv_tekanan);
        ivKelembaban = findViewById(R.id.iv_kelembaban);
        ivPolusiUdara = findViewById(R.id.iv_polusi_udara);
        ivJarakPandang = findViewById(R.id.iv_jarak_pandang);

        tvWaktuTerbit = findViewById(R.id.tv_waktu_terbit);
        tvLatitudeTerbit = findViewById(R.id.tv_latitude_terbit);
        tvLongitudeTerbit = findViewById(R.id.tv_longitude_terbit);

        tvWaktuTembenam = findViewById(R.id.tv_waktu_tembenam);
        tvLatitudeTembenam = findViewById(R.id.tv_latitude_tembenam);
        tvLongitudeTembenam = findViewById(R.id.tv_longitude_tembenam);

        tvKecepatanAngin = findViewById(R.id.tv_kecepatan_angin);
        tvKecepatanHembusanAngin = findViewById(R.id.tv_speed_hembusan_angin);
        tvArahAngin = findViewById(R.id.tv_arah_angin);
        tvArahAnginDerajat = findViewById(R.id.tv_degree_angin);

        tvKelembaban2 = findViewById(R.id.tv_kelembaban2);

        tvTekananDarat2 = findViewById(R.id.tv_tekanan_darat2);
        tvTekananLaut2 = findViewById(R.id.tv_tekanan_laut2);
        loEmotePolusiBaik = findViewById(R.id.lt_emote_polusi_baik);
        loEmotePolusiSangatBaik = findViewById(R.id.lt_emote_polusi_sangat_baik);
        loEmotePolusiBuruk = findViewById(R.id.lt_emote_polusi_buruk);
        loEmotePolusiSangatBuruk = findViewById(R.id.lt_emote_polusi_sangat_buruk);
        loEmotePolusiSedang = findViewById(R.id.lt_emote_polusi_sedang);

        loArahAngin = findViewById(R.id.iv_arah_angin);
        loHembusanAngin = findViewById(R.id.iv_hembusan_angin);
        loTekDarat = findViewById(R.id.lo_tekanan_darat);
        loTekLaut = findViewById(R.id.lo_tekanan_laut);

        tvCO = findViewById(R.id.tv_co);
        tvNO = findViewById(R.id.tv_no);
        tvNO2 = findViewById(R.id.tv_no2);
        tvO3 = findViewById(R.id.tv_o3);
        tvSO2 = findViewById(R.id.tv_so2);
        tvPM25 = findViewById(R.id.tv_pm2_5);
        tvPM10 = findViewById(R.id.tv_pm10);
        tvNH3 = findViewById(R.id.tv_nh3);


        // TextView Sembunyikan Detail
        tvSembunyikan = findViewById(R.id.tv_sembunyikan);
        tvSembunyikan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                invisibleItem();
                visibleItem();
                ubahKembaliAll();
                tvSembunyikan.setVisibility(View.GONE);
            }
        });

//      Linear Layout Search
        llSearch = findViewById(R.id.ll_search);
        llSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
            }
        });

//      Linear Layout Terbit
        llTerbit = findViewById(R.id.ll_terbit);
        llTerbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              Visibility
                tvSembunyikan.setVisibility(View.VISIBLE);
                ubahKembaliAll();
                invisibleItem();
                visibleItem();
                ubahKetikaPilih(llTerbit, ivTerbit, tvJudulTerbit, tvTerbit);
                llDetailTerbit.setVisibility(View.VISIBLE);
            }
        });

//      Linear Layout Tembenam
        llTembenam = findViewById(R.id.ll_tembenam);
        llTembenam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              Visibility
                tvSembunyikan.setVisibility(View.VISIBLE);
                ubahKembaliAll();
                invisibleItem();
                visibleItem();
                ubahKetikaPilih(llTembenam, ivTembenam, tvJudulTembenam, tvTembenam);
                llDetailTembenam.setVisibility(View.VISIBLE);
            }
        });

//      Linear Layout Angin
        llAngin = findViewById(R.id.ll_angin);
        llAngin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              Visibility
                tvSembunyikan.setVisibility(View.VISIBLE);
                ubahKembaliAll();
                invisibleItem();
                visibleItem();
                ubahKetikaPilih(llAngin, ivAngin, tvJudulAngin, tvAngin);
                llDetailAngin.setVisibility(View.VISIBLE);
                loArahAngin.playAnimation();
                loHembusanAngin.playAnimation();
            }
        });

//      Linear Layout Tekanan
        llTekanan = findViewById(R.id.ll_tekanan);
        llTekanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              Visibility
                tvSembunyikan.setVisibility(View.VISIBLE);
                ubahKembaliAll();
                invisibleItem();
                visibleItem();
                ubahKetikaPilih(llTekanan, ivTekanan, tvJudulTekanan, tvTekanan);
                llDetailTekanan.setVisibility(View.VISIBLE);
                loTekDarat.playAnimation();
                loTekLaut.playAnimation();
            }
        });

//      Linear Layout Kelembaban
        llKelembaban = findViewById(R.id.ll_kelembaban);
        llKelembaban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              Visibility
                tvSembunyikan.setVisibility(View.VISIBLE);
                ubahKembaliAll();
                invisibleItem();
                visibleItem();
                ubahKetikaPilih(llKelembaban, ivKelembaban, tvJudulKelembaban, tvKelembaban);
                llDetailKelembaban.setVisibility(View.VISIBLE);
            }
        });

//      Linear Layout Polusi Udara
        llPolusiUdara = findViewById(R.id.ll_polusi_udara);
        llPolusiUdara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              Visibility
                tvSembunyikan.setVisibility(View.VISIBLE);
                ubahKembaliAll();
                invisibleItem();
                visibleItem();
                ubahKetikaPilih(llPolusiUdara, ivPolusiUdara, tvJudulPolusiUdara, tvPolusiUdara);
                llDetailPolusiUdara.setVisibility(View.VISIBLE);
                loEmotePolusiSangatBaik.setVisibility(View.VISIBLE);

                loEmotePolusiBaik.playAnimation();
                loEmotePolusiSangatBaik.playAnimation();
                loEmotePolusiBuruk.playAnimation();
                loEmotePolusiSangatBuruk.playAnimation();
                loEmotePolusiSedang.playAnimation();
            }
        });

//      Linear Layout Perkiraan 5 Hari
        llPerkiraan5Hari = findViewById(R.id.ll_perkiraan_lima_hari);
        llPerkiraan5Hari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent limaHari = new Intent(MainActivity.this, Perkiraan_lima_hari.class);
                limaHari.putExtra("varLokasi", city);
                startActivity(limaHari);
            }
        });

//      Linear Layout Jarak Pandang
        llJarakPandang = findViewById(R.id.ll_jarak_pandang);
        llJarakPandang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvSembunyikan.setVisibility(View.VISIBLE);
                ubahKembaliAll();
                invisibleItem();
                visibleItem();
                ubahKetikaPilih(llJarakPandang, ivJarakPandang, tvJudulJarakPandang, tvJarakPandang);
                llDetailJarakPandang.setVisibility(View.VISIBLE);
            }
        });

//      Linear Layout Gempa Bumi
        llGempaBumi = findViewById(R.id.ll_gempa_bumi);
        llGempaBumi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, GempaBumi.class));
            }
        });

//      Linear Layout Tentang
        llTentang = findViewById(R.id.ll_tentang);
        llTentang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CloneAbout.class));
            }
        });


        rlAtas.setVisibility(View.VISIBLE);
        llBawah.setVisibility(View.VISIBLE);
        pbApp.setVisibility(View.GONE);
        getInformasiWeather();
        getPolusiUdara();
        refresh();
    }

    //  Invisible Item
    private void invisibleItem() {
        llDetailTerbit.setVisibility(View.GONE);
        llDetailTembenam.setVisibility(View.GONE);
        llDetailAngin.setVisibility(View.GONE);
        llDetailTekanan.setVisibility(View.GONE);
        llDetailKelembaban.setVisibility(View.GONE);
        llDetailPolusiUdara.setVisibility(View.GONE);
        llDetailJarakPandang.setVisibility(View.GONE);
    }

    //  Visible Item
    private void visibleItem() {
        llTerbit.setVisibility(View.VISIBLE);
        llTembenam.setVisibility(View.VISIBLE);
        llAngin.setVisibility(View.VISIBLE);
        llTekanan.setVisibility(View.VISIBLE);
        llKelembaban.setVisibility(View.VISIBLE);
        llPolusiUdara.setVisibility(View.VISIBLE);
        llJarakPandang.setVisibility(View.VISIBLE);
        llPerkiraan5Hari.setVisibility(View.VISIBLE);
        llTentang.setVisibility(View.VISIBLE);
    }

    //  Ubah semua item dengan backgroud seperti semula
    private void ubahKembaliAll() {
        ubahKembali(llTerbit, ivTerbit, tvJudulTerbit, tvTerbit);
        ubahKembali(llTembenam, ivTembenam, tvJudulTembenam, tvTembenam);
        ubahKembali(llAngin, ivAngin, tvJudulAngin, tvAngin);
        ubahKembali(llTekanan, ivTekanan, tvJudulTekanan, tvTekanan);
        ubahKembali(llKelembaban, ivKelembaban, tvJudulKelembaban, tvKelembaban);
        ubahKembali(llPolusiUdara, ivPolusiUdara, tvJudulPolusiUdara, tvPolusiUdara);
        ubahKembali(llJarakPandang, ivJarakPandang, tvJudulJarakPandang, tvJarakPandang);
    }

    private void ubahKetikaPilih(LinearLayout ll, ImageView iv, TextView tv1, TextView tv2) {
        ll.setBackgroundColor(Color.parseColor("#417EA9"));
        iv.setColorFilter(Color.parseColor("#FFFFFF"));
        tv1.setTextColor(Color.parseColor("#FFFFFF"));
        tv2.setTextColor(Color.parseColor("#FFFFFF"));
    }

    private void ubahKembali(LinearLayout ll, ImageView iv, TextView tv1, TextView tv2) {
        ll.setBackgroundColor(Color.parseColor("#3CF1EBF1"));
        iv.setColorFilter(Color.parseColor("#417EA9"));
        tv1.setTextColor(Color.parseColor("#417EA9"));
        tv2.setTextColor(Color.parseColor("#417EA9"));
    }

    //  Refresh ketika di Scroll
    private void refresh() {
        final SwipeRefreshLayout swipeBawahRefresh = findViewById(R.id.swipe_refresh);
        swipeBawahRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPolusiUdara();
                getInformasiWeather();
                Toast.makeText(MainActivity.this, "Data Berhasil Diperbarui", Toast.LENGTH_SHORT).show();
                ;
                swipeBawahRefresh.setRefreshing(false);
            }
        });
    }

    //  Mendapatkan data Cuaca dengan menggunakan Kota
    private void getInformasiWeather() {

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

//      Api mendapatkan Cuaca
        String urlWeather = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey + "&lang=" + lang + "&units=metric";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlWeather, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
//                  Kota
                    tvKota.setText(city);

//                  Temperature
                    int temperature = response.getJSONObject("main").getInt("temp");
                    tvTemperatureSekarang.setText(temperature + " °C");

//                  Temperature Min Max
                    int temperature_min = response.getJSONObject("main").getInt("temp_min");
                    int temperature_max = response.getJSONObject("main").getInt("temp_max");
                    tv_temperature_min_max.setText(temperature_min + " °C - " + temperature_max + " °C");

//                  Icon Cuaca
                    JSONObject obj = response.getJSONArray("weather").getJSONObject(0);
                    String iconapi = obj.getString("icon");
                    Picasso.get()
                            .load("https://openweathermap.org/img/wn/" + iconapi + "@2x.png")
                            .resize(1080, 1080)
                            .into(ivIconCuaca);

                    String bahasa = response.getJSONObject("sys").getString("country");
                    long sunrise = response.getJSONObject("sys").getLong("sunrise");
                    long sunset = response.getJSONObject("sys").getLong("sunset");

//                  Terbit
                    tvTerbit.setText(new SimpleDateFormat("HH:mm", Locale.forLanguageTag(bahasa)).format(new Date(sunrise * 1000)) + " WIB");
                    tvWaktuTerbit.setText(new SimpleDateFormat("HH:mm", Locale.forLanguageTag(bahasa)).format(new Date(sunrise * 1000)) + " WIB");

//                  Tembenam
                    tvTembenam.setText(new SimpleDateFormat("HH:mm", Locale.forLanguageTag(bahasa)).format(new Date(sunset * 1000)) + " WIB");
                    tvWaktuTembenam.setText(new SimpleDateFormat("HH:mm", Locale.forLanguageTag(bahasa)).format(new Date(sunset * 1000)) + " WIB");

//                  Tanggal & Waktu
                    long time = response.getLong("dt");
                    tvTanggalJam.setText(new SimpleDateFormat("dd MMMM yyyy", Locale.forLanguageTag(bahasa)).format(Calendar.getInstance().getTime()) + ", " + new SimpleDateFormat("HH:mm", Locale.forLanguageTag(bahasa)).format(new Date(time * 1000)) + " WIB");


//                  Deskripsi Cuaca
                    String desc = obj.getString("description");
                    String descCapitalize = desc.substring(0, 1).toUpperCase();
                    String descLowercase = desc.substring(1);
                    tvKeteranganIconCuaca.setText(descCapitalize + descLowercase);

//                  Kelembaban
                    int kelembaban = response.getJSONObject("main").getInt("humidity");
                    tvKelembaban.setText(kelembaban + " %");
                    tvKelembaban2.setText(kelembaban + " %");
//                  Tekanan
                    int tekanan = response.getJSONObject("main").getInt("pressure");
                    tvTekanan.setText(tekanan + " hPa");
                    tvTekananDarat2.setText(tekanan + " hPa");

//                  Kecepatan Angin
                    double kecepatanangin = response.getJSONObject("wind").getDouble("speed");
                    tvAngin.setText(kecepatanangin + " M/Dtk");
                    tvKecepatanAngin.setText(kecepatanangin + " M/Dtk");

                    //                  Kecepatan Hembusan Angin
                    double kecepatanhembusanangin = response.getJSONObject("wind").getDouble("gust");
                    tvKecepatanHembusanAngin.setText(kecepatanhembusanangin + " M/Dtk");

                    int derajat = response.getJSONObject("wind").getInt("deg");
                    tvArahAnginDerajat.setText(String.valueOf(derajat));
                    String arah;
                    if (derajat == 0 || derajat == 360) {
                        arah = "Utara";
                    } else if (derajat > 0 && derajat < 90) {
                        arah = "Timur Laut";
                    } else if (derajat == 90) {
                        arah = "Timur";
                    } else if (derajat > 90 && derajat < 180) {
                        arah = "Tenggara";
                    } else if (derajat == 180) {
                        arah = "Selatan";
                    } else if (derajat > 180 && derajat < 270) {
                        arah = "Tenggara";
                    } else if (derajat == 270) {
                        arah = "Barat";
                    } else if (derajat > 270 && derajat < 360) {
                        arah = "Barat Laut";
                    } else {
                        arah = "-";
                    }

                    tvArahAngin.setText(arah);


//                  Ganti Icon jika suhu >/< 25 derajat
                    if (temperature >= 25) {
                        loHot.setVisibility(View.VISIBLE);
                        loCold.setVisibility(View.GONE);
                    } else if (temperature < 25) {
                        loHot.setVisibility(View.GONE);
                        loCold.setVisibility(View.VISIBLE);
                    }

//                  Latitude
                    latitude = response.getJSONObject("coord").getDouble("lat");
                    tvLatitudeTerbit.setText(String.valueOf(latitude));
                    tvLatitudeTembenam.setText(String.valueOf(latitude));

//                  Longitude
                    longitude = response.getJSONObject("coord").getDouble("lon");
                    tvLongitudeTerbit.setText(String.valueOf(longitude));
                    tvLongitudeTembenam.setText(String.valueOf(longitude));

//                  Jarak Pandang
                    double jarakPandang = response.getDouble("visibility");
                    double convertKM = Double.parseDouble(df.format(jarakPandang / 1000));
                    if (convertKM == 10) {
                        tvJarakPandang.setText("Maks " + convertKM + " Km");

                    }
                    tvJarakPandang.setText(convertKM + " Km");

                    double km, hm, dam, m, dm, cm, mm;
                    km = convertKM;
                    hm = convertKM * 10;
                    dam = convertKM * 100;
                    m = convertKM * 1000;
                    dm = convertKM * 10000;
                    cm = convertKM * 100000;
                    mm = convertKM * 1000000;

                    tvKM.setText(String.valueOf(km));
                    tvHM.setText(String.valueOf(hm));
                    tvDAM.setText(String.valueOf(dam));
                    tvM.setText(String.valueOf(m));
                    tvDM.setText(String.valueOf(dm));
                    tvCM.setText(String.valueOf(cm));
                    tvMM.setText(String.valueOf(mm));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                getInformasiWeatherKoordinat();
            }
        });
        getPolusiUdara();
        requestQueue.add(jsonObjectRequest);
    }


    //  Mendapatkan data Cuaca dengan menggunakan Koordinat
    private void getInformasiWeatherKoordinat() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            onGPS();
        } else {
            getLocation();
        }
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

//      Api mendapatkan Cuaca
        String urlWeatherKoordinat = "https://api.openweathermap.org/data/2.5/weather?lat=" + latitude + "&lon=" + longitude + "&appid=" + apiKey + "&lang=" + lang + "&units=metric";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlWeatherKoordinat, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
//                  Kota
                    city = response.getString("name");
                    tvKota.setText(city);

//                  Temperature
                    int temperature = response.getJSONObject("main").getInt("temp");
                    tvTemperatureSekarang.setText(temperature + " °C");

//                  Temperature Min Max
                    int temperature_min = response.getJSONObject("main").getInt("temp_min");
                    int temperature_max = response.getJSONObject("main").getInt("temp_max");
                    tv_temperature_min_max.setText(temperature_min + " °C - " + temperature_max + " °C");

//                  Icon Cuaca
                    JSONObject obj = response.getJSONArray("weather").getJSONObject(0);
                    String iconapi = obj.getString("icon");
                    Picasso.get()
                            .load("https://openweathermap.org/img/wn/" + iconapi + "@2x.png")
                            .resize(50, 50)
                            .into(ivIconCuaca);


                    String bahasa = response.getJSONObject("sys").getString("country");
                    long sunrise = response.getJSONObject("sys").getLong("sunrise");
                    long sunset = response.getJSONObject("sys").getLong("sunset");

//                  Terbit
                    tvTerbit.setText(new SimpleDateFormat("HH:mm", Locale.forLanguageTag(bahasa)).format(new Date(sunrise * 1000)) + " WIB");
                    tvWaktuTerbit.setText(new SimpleDateFormat("HH:mm", Locale.forLanguageTag(bahasa)).format(new Date(sunrise * 1000)) + " WIB");

//                  Tembenam
                    tvTembenam.setText(new SimpleDateFormat("HH:mm", Locale.forLanguageTag(bahasa)).format(new Date(sunset * 1000)) + " WIB");
                    tvWaktuTembenam.setText(new SimpleDateFormat("HH:mm", Locale.forLanguageTag(bahasa)).format(new Date(sunset * 1000)) + " WIB");

//                  Tanggal & Waktu
                    long time = response.getLong("dt");
                    tvTanggalJam.setText(new SimpleDateFormat("dd MMMM yyyy", Locale.forLanguageTag(bahasa)).format(Calendar.getInstance().getTime()) + ", " + new SimpleDateFormat("HH:mm", Locale.forLanguageTag(bahasa)).format(new Date(time * 1000)) + " WIB");


//                  Deskripsi Cuaca
                    String desc = obj.getString("description");
                    String descCapitalize = desc.substring(0, 1).toUpperCase();
                    String descLowercase = desc.substring(1);
                    tvKeteranganIconCuaca.setText(descCapitalize + descLowercase);

//                  Kelembaban
                    int kelembaban = response.getJSONObject("main").getInt("humidity");
                    tvKelembaban.setText(kelembaban + " %");
                    tvKelembaban2.setText(kelembaban + " %");

//                    Kemendungan
                    int kemendungan = response.getJSONObject("clouds").getInt("all");
                    tvkemendungan2.setText(kemendungan + " %");

//                  Tekanan
                    int tekanan = response.getJSONObject("main").getInt("pressure");
                    tvTekanan.setText(tekanan + " hPa");

                    int tekanandarat = response.getJSONObject("main").getInt("grnd_level");
                    tvTekananDarat2.setText(tekanandarat + " hPa");

                    int tekananlaut = response.getJSONObject("main").getInt("sea_level");
                    tvTekananLaut2.setText(tekananlaut + " hPa");

//                  Kecepatan Angin
                    double kecepatanangin = response.getJSONObject("wind").getDouble("speed");
                    tvAngin.setText(kecepatanangin + " M/Dtk");
                    tvKecepatanAngin.setText(kecepatanangin + " M/Dtk");

                    //                  Kecepatan Hembusan Angin
                    double kecepatanhembusanangin = response.getJSONObject("wind").getDouble("gust");
                    tvKecepatanHembusanAngin.setText(kecepatanhembusanangin + " M/Dtk");

                    int derajat = response.getJSONObject("wind").getInt("deg");
                    tvArahAnginDerajat.setText(String.valueOf(derajat));
                    String arah;
                    if (derajat == 0 || derajat == 360) {
                        arah = "Utara";
                    } else if (derajat > 0 && derajat < 90) {
                        arah = "Timur Laut";
                    } else if (derajat == 90) {
                        arah = "Timur";
                    } else if (derajat > 90 && derajat < 180) {
                        arah = "Tenggara";
                    } else if (derajat == 180) {
                        arah = "Selatan";
                    } else if (derajat > 180 && derajat < 270) {
                        arah = "Tenggara";
                    } else if (derajat == 270) {
                        arah = "Barat";
                    } else if (derajat > 270 && derajat < 360) {
                        arah = "Barat Laut";
                    } else {
                        arah = "-";
                    }

                    tvArahAngin.setText(arah);

//                  Ganti Icon jika suhu >/< 25 derajat
                    if (temperature >= 25) {
                        loHot.setVisibility(View.VISIBLE);
                        loCold.setVisibility(View.GONE);
                    } else if (temperature < 25) {
                        loHot.setVisibility(View.GONE);
                        loCold.setVisibility(View.VISIBLE);
                    }

//                  Latitude
                    tvLatitudeTerbit.setText(String.valueOf(latitude));
                    tvLatitudeTembenam.setText(String.valueOf(latitude));

//                  Longitude
                    tvLongitudeTerbit.setText(String.valueOf(longitude));
                    tvLongitudeTembenam.setText(String.valueOf(longitude));

//                  Jarak Pandang
                    double jarakPandang = response.getDouble("visibility");
                    double convertKM = Double.parseDouble(df.format(jarakPandang / 1000));
                    if (convertKM == 10) {
                        tvJarakPandang.setText("Maks " + convertKM + " Km");

                    }
                    tvJarakPandang.setText(convertKM + " Km");

                    double km, hm, dam, m, dm, cm, mm;
                    km = convertKM;
                    hm = convertKM * 10;
                    dam = convertKM * 100;
                    m = convertKM * 1000;
                    dm = convertKM * 10000;
                    cm = convertKM * 100000;
                    mm = convertKM * 1000000;

                    tvKM.setText(String.valueOf(km));
                    tvHM.setText(String.valueOf(hm));
                    tvDAM.setText(String.valueOf(dam));
                    tvM.setText(String.valueOf(m));
                    tvDM.setText(String.valueOf(dm));
                    tvCM.setText(String.valueOf(cm));
                    tvMM.setText(String.valueOf(mm));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        getPolusiUdara();
        requestQueue.add(jsonObjectRequest);
    }

    //  Mendapatkan data polusi Udara
    private void getPolusiUdara() {
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        //      Api mendapatkan Polusi Udara yang memerlukan koordinat kota
        String urlPolusi = "http://api.openweathermap.org/data/2.5/air_pollution?lat=" + latitude + "&lon=" + longitude + "&appid=" + apiKey;

        JsonObjectRequest jsonObjectRequestPolusi = new JsonObjectRequest(Request.Method.GET, urlPolusi, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    int polusiUdara = response.getJSONArray("list").getJSONObject(0).getJSONObject("main").getInt("aqi");
                    double co = response.getJSONArray("list").getJSONObject(0).getJSONObject("components").getDouble("co");
                    double no = response.getJSONArray("list").getJSONObject(0).getJSONObject("components").getDouble("no");
                    double no2 = response.getJSONArray("list").getJSONObject(0).getJSONObject("components").getDouble("no2");
                    double o3 = response.getJSONArray("list").getJSONObject(0).getJSONObject("components").getDouble("o3");
                    double so2 = response.getJSONArray("list").getJSONObject(0).getJSONObject("components").getDouble("so2");
                    double pm25 = response.getJSONArray("list").getJSONObject(0).getJSONObject("components").getDouble("pm2_5");
                    double pm10 = response.getJSONArray("list").getJSONObject(0).getJSONObject("components").getDouble("pm10");
                    double nh3 = response.getJSONArray("list").getJSONObject(0).getJSONObject("components").getDouble("nh3");

                    tvCO.setText(String.valueOf(df.format(co)) + " μg/m3");
                    tvNO.setText(String.valueOf(df.format(no)) + " μg/m3");
                    tvNO2.setText(String.valueOf(df.format(no2)) + " μg/m3");
                    tvO3.setText(String.valueOf(df.format(o3)) + " μg/m3");
                    tvSO2.setText(String.valueOf(df.format(so2)) + " μg/m3");
                    tvPM25.setText(String.valueOf(df.format(pm25)) + " μg/m3");
                    tvPM10.setText(String.valueOf(df.format(pm10)) + " μg/m3");
                    tvNH3.setText(String.valueOf(df.format(nh3)) + " μg/m3");

                    String ketPolusi = "";
                    if (polusiUdara == 1) {
                        ketPolusi = "Sangat\nRendah";
                        loEmotePolusiSangatBaik.setVisibility(View.VISIBLE);
                        loEmotePolusiBuruk.setVisibility(View.GONE);
                        loEmotePolusiSangatBuruk.setVisibility(View.GONE);
                        loEmotePolusiSedang.setVisibility(View.GONE);
                        loEmotePolusiBaik.setVisibility(View.GONE);
                    } else if (polusiUdara == 2) {
                        ketPolusi = "Rendah";
                        loEmotePolusiSangatBaik.setVisibility(View.GONE);
                        loEmotePolusiBuruk.setVisibility(View.GONE);
                        loEmotePolusiSangatBuruk.setVisibility(View.GONE);
                        loEmotePolusiSedang.setVisibility(View.GONE);
                        loEmotePolusiBaik.setVisibility(View.VISIBLE);
                    } else if (polusiUdara == 3) {
                        ketPolusi = "Medium";
                        loEmotePolusiSangatBaik.setVisibility(View.GONE);
                        loEmotePolusiBuruk.setVisibility(View.GONE);
                        loEmotePolusiSangatBuruk.setVisibility(View.GONE);
                        loEmotePolusiSedang.setVisibility(View.VISIBLE);
                        loEmotePolusiBaik.setVisibility(View.GONE);
                    } else if (polusiUdara == 4) {
                        ketPolusi = "Tinggi";
                        loEmotePolusiSangatBaik.setVisibility(View.GONE);
                        loEmotePolusiBuruk.setVisibility(View.VISIBLE);
                        loEmotePolusiSangatBuruk.setVisibility(View.GONE);
                        loEmotePolusiSedang.setVisibility(View.GONE);
                        loEmotePolusiBaik.setVisibility(View.GONE);
                    } else if (polusiUdara == 5) {
                        ketPolusi = "Sangat\nTinggi";
                        loEmotePolusiSangatBaik.setVisibility(View.GONE);
                        loEmotePolusiBuruk.setVisibility(View.GONE);
                        loEmotePolusiSangatBuruk.setVisibility(View.VISIBLE);
                        loEmotePolusiSedang.setVisibility(View.GONE);
                        loEmotePolusiBaik.setVisibility(View.GONE);
                    }

                    tvPolusiUdara.setText(ketPolusi);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonObjectRequestPolusi);
    }

    //  Mendapatkan lokasi
    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(MainActivity.this
                , Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            Location LocationGps = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Location LocationNetwork = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Location LocationPassive = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

            if (LocationGps != null) {
                latitude = LocationGps.getLatitude();
                longitude = LocationGps.getLongitude();
            } else if (LocationNetwork != null) {
                latitude = LocationNetwork.getLatitude();
                longitude = LocationNetwork.getLongitude();
            } else if (LocationPassive != null) {
                latitude = LocationPassive.getLatitude();
                longitude = LocationPassive.getLongitude();
            } else {
                Toast.makeText(this, "Lokasi tidak bisa didapatkan ...", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //  Mengaktifkan GPS di handphone
    private void onGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Aktifkan GPS").setCancelable(false).setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
