package com.fend.temanmuslim.model;

public class DoaModel {

    String id, title, arabic, latin, translation;

    public DoaModel(String id, String title, String arabic, String latin, String translation){
        this.id=id;
        this.title=title;
        this.arabic=arabic;
        this.latin=latin;
        this.translation=translation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }
}
