package com.fend.temanmuslim.model;

public class AsmaulHusnaModel {

    String index, latin, arabic, translation_id;

    public AsmaulHusnaModel(String index, String latin, String arabic, String translation_id) {
        this.index = index;
        this.latin = latin;
        this.arabic = arabic;
        this.translation_id = translation_id;
    }


    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getLatin() {
        return latin;
    }

    public void setLatin(String latin) {
        this.latin = latin;
    }

    public String getArabic() {
        return arabic;
    }

    public void setArabic(String arabic) {
        this.arabic = arabic;
    }

    public String getTranslation_id() {
        return translation_id;
    }

    public void setTranslation_id(String translation_id) {
        this.translation_id = translation_id;
    }
}