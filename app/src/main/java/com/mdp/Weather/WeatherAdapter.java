package com.mdp.Weather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder>
{
    private ArrayList<WeatherModal> weatherModalArrayList;
    private Context context;
    private ImageView ivIcon;


    public WeatherAdapter(ArrayList<WeatherModal> weatherModalArrayList, Context context)
    {
        this.weatherModalArrayList = weatherModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public WeatherAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.list_cuaca, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherAdapter.ViewHolder holder, int position)
    {
        WeatherModal modal = weatherModalArrayList.get(position);

        // Hari Tanggal
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat outputTanggal = new SimpleDateFormat("dd MMMM yyyy");
        SimpleDateFormat outputWaktu = new SimpleDateFormat("HH:mm");

        try
        {
            Date date = input.parse(modal.getTanggal());
            holder.tvTanggal.setText(outputTanggal.format(date) + ", " + outputWaktu.format(date));
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        // Desc
        holder.tvDesc.setText(modal.getDescTemp());
        // Icon
        Picasso.get()
                .load("https://openweathermap.org/img/wn/" + modal.getIconTemp() + "@2x.png")
                .resize(50, 50)
                .into(ivIcon);

        //Temp
        holder.tvTemp.setText(modal.getTemp() + " °C");

        // Temp Min
        holder.tvTempMin.setText(modal.getTempMin() + " °C");

        // Temp Max
        holder.tvTempMax.setText(modal.getTempMax() + " °C");

        // Kemendungan
        holder.tvKemendungan.setText(modal.getKemendungan() + " %");

        // Probabilitas Hujan
        holder.tvProbHujan.setText(modal.getProbabititasHujan() + " %");

        // Kecepatan Angin
        holder.tvKecepatanAngin.setText(modal.getKecepatanAngin() + " Meter / Detik");

        // Arah Angin
        int derajat = Integer.parseInt(modal.getArahAngin());
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
        holder.tvArahAngin.setText(arah);

        // Tekanan Laut
        holder.tvTekananLaut.setText(modal.getTekananLaut() + " hPa");

        // Tekanan Darat
        holder.tvTekananDarat.setText(modal.getTekananDarat() + " hPa");

        // Kelembaban
        holder.tvKelembaban.setText(modal.getKelembaban() + " %");

        // Jarak Pandang
        holder.tvJarakPandang.setText(modal.getJarakPandang() + " Km");


        //show/hide detail
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int isVisible = holder.ll_bawah_detail.getVisibility();

                if(isVisible == View.VISIBLE){
                    holder.ll_bawah_detail.setVisibility(View.GONE);
                }
                else{
                    holder.ll_bawah_detail.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return weatherModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {

        private TextView tvTanggal, tvDesc, tvTemp, tvTempMax, tvTempMin, tvKemendungan,
                tvProbHujan, tvKecepatanAngin, tvArahAngin, tvTekananLaut, tvTekananDarat, tvKelembaban, tvJarakPandang;

        private LinearLayout ll_bawah_detail ;
        private RelativeLayout rl_atas_button;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            tvTanggal = itemView.findViewById(R.id.tv_tanggal);
            tvDesc = itemView.findViewById(R.id.tv_desc);
            tvTemp = itemView.findViewById(R.id.tv_temp);
            tvTempMax = itemView.findViewById(R.id.tv_max_temp);
            tvTempMin = itemView.findViewById(R.id.tv_min_temp);
            tvKemendungan = itemView.findViewById(R.id.tv_mendung);
            tvProbHujan = itemView.findViewById(R.id.tv_hujan);
            tvKecepatanAngin = itemView.findViewById(R.id.tv_wind_speed);
            tvArahAngin = itemView.findViewById(R.id.tv_arah);
            tvTekananLaut = itemView.findViewById(R.id.tv_tekanan_laut);
            tvTekananDarat = itemView.findViewById(R.id.tv_tekanan_darat);
            tvKelembaban = itemView.findViewById(R.id.tv_lembab);
            tvJarakPandang = itemView.findViewById(R.id.tv_pandang);
            ivIcon = itemView.findViewById(R.id.iv_icon);
            rl_atas_button = itemView.findViewById(R.id.rl_sisi_atas);
            ll_bawah_detail = itemView.findViewById(R.id.ll_sisi_bawah);
        }
    }
}
