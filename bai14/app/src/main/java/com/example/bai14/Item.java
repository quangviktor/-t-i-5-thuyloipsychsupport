package com.example.bai14;

public class Item {
    private String maso;
    private String tieude;
    private int thich; // 0: unlike, 1: like

    public Item(String maso, String tieude, int thich) {
        this.maso = maso;
        this.tieude = tieude;
        this.thich = thich;
    }

    // Getters and Setters
    public String getMaso() {
        return maso;
    }

    public void setMaso(String maso) {
        this.maso = maso;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    public int getThich() {
        return thich;
    }

    public void setThich(int thich) {
        this.thich = thich;
    }
}
