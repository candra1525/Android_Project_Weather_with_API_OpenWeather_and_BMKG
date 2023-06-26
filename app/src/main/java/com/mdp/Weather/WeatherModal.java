package com.mdp.Weather;

public class WeatherModal
{
    private String tanggal;
    private String descTemp;
    private String temp;
    private String iconTemp;
    private String tempMin;
    private String tempMax;
    private String kemendungan;
    private String probabititasHujan;
    private String kecepatanAngin;
    private String arahAngin;
    private String tekananLaut;
    private String tekananDarat;
    private String kelembaban;
    private String jarakPandang;

    public WeatherModal(String tanggal, String descTemp, String temp, String iconTemp, String tempMin, String tempMax, String kemendungan, String probabititasHujan, String kecepatanAngin, String arahAngin, String tekananLaut, String tekananDarat, String kelembaban, String jarakPandang)
    {
        this.tanggal = tanggal;
        this.descTemp = descTemp;
        this.temp = temp;
        this.iconTemp = iconTemp;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.kemendungan = kemendungan;
        this.probabititasHujan = probabititasHujan;
        this.kecepatanAngin = kecepatanAngin;
        this.arahAngin = arahAngin;
        this.tekananLaut = tekananLaut;
        this.tekananDarat = tekananDarat;
        this.kelembaban = kelembaban;
        this.jarakPandang = jarakPandang;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getDescTemp() {
        return descTemp;
    }

    public void setDescTemp(String descTemp) {
        this.descTemp = descTemp;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getIconTemp() {
        return iconTemp;
    }

    public void setIconTemp(String iconTemp) {
        this.iconTemp = iconTemp;
    }

    public String getTempMin() {
        return tempMin;
    }

    public void setTempMin(String tempMin) {
        this.tempMin = tempMin;
    }

    public String getTempMax() {
        return tempMax;
    }

    public void setTempMax(String tempMax) {
        this.tempMax = tempMax;
    }

    public String getKemendungan() {
        return kemendungan;
    }

    public void setKemendungan(String kemendungan) {
        this.kemendungan = kemendungan;
    }

    public String getProbabititasHujan() {
        return probabititasHujan;
    }

    public void setProbabititasHujan(String probabititasHujan) {
        this.probabititasHujan = probabititasHujan;
    }

    public String getKecepatanAngin() {
        return kecepatanAngin;
    }

    public void setKecepatanAngin(String kecepatanAngin) {
        this.kecepatanAngin = kecepatanAngin;
    }

    public String getArahAngin() {
        return arahAngin;
    }

    public void setArahAngin(String arahAngin) {
        this.arahAngin = arahAngin;
    }

    public String getTekananLaut() {
        return tekananLaut;
    }

    public void setTekananLaut(String tekananLaut) {
        this.tekananLaut = tekananLaut;
    }

    public String getTekananDarat() {
        return tekananDarat;
    }

    public void setTekananDarat(String tekananDarat) {
        this.tekananDarat = tekananDarat;
    }

    public String getKelembaban() {
        return kelembaban;
    }

    public void setKelembaban(String kelembaban) {
        this.kelembaban = kelembaban;
    }

    public String getJarakPandang() {
        return jarakPandang;
    }

    public void setJarakPandang(String jarakPandang) {
        this.jarakPandang = jarakPandang;
    }
}
