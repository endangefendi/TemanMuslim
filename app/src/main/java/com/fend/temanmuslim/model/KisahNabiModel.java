package com.fend.temanmuslim.model;

public class KisahNabiModel {
    int id;
    String name;
    String thn_kelahiran;
    int usia;
    int icon;
    String description;
    String tmp;

    public KisahNabiModel(int id, String name, String thn_kelahiran, int usia, String description, String tmp,  int icon) {
        this.id = id;
        this.name = name;
        this.thn_kelahiran = thn_kelahiran;
        this.usia = usia;
        this.description = description;
        this.tmp = tmp;
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThn_kelahiran() {
        return thn_kelahiran;
    }

    public void setThn_kelahiran(String thn_kelahiran) {
        this.thn_kelahiran = thn_kelahiran;
    }

    public int getUsia() {
        return usia;
    }

    public void setUsia(int usia) {
        this.usia = usia;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTmp() {
        return tmp;
    }

    public void setTmp(String tmp) {
        this.tmp = tmp;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}