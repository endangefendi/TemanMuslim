package com.fend.temanmuslim.model;

public class NiatSholatModel {

    String id, name, arabic, latin, terjemahan;

    public NiatSholatModel(String id, String name, String arabic, String latin, String terjemahan){
        this.id=id;
        this.name=name;
        this.arabic=arabic;
        this.latin=latin;
        this.terjemahan=terjemahan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTerjemahan() {
        return terjemahan;
    }

    public void setTerjemahan(String terjemahan) {
        this.terjemahan = terjemahan;
    }
}
