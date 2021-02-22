package com.fend.temanmuslim.model;

public class AyatKursiModel {

    String tafsir, translation, arabic, latin;

    public AyatKursiModel(String tafsir, String translation, String arabic, String latin){
        this.tafsir=tafsir;
        this.arabic=arabic;
        this.latin=latin;
        this.translation=translation;
    }

    public String getTafsir() {
        return tafsir;
    }

    public void setTafsir(String tafsir) {
        this.tafsir = tafsir;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getArabic() {
        return arabic;
    }

    public void setArabic(String arabic) {
        this.arabic = arabic;
    }

    public String getLatin() {
        return latin;
    }

    public void setLatin(String latin) {
        this.latin = latin;
    }
}
