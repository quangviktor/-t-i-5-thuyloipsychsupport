package com.example.bai21;


public class SanPham {
    private String maSP;
    private String tenSP;
    private String soLuong;
    private String donGia;
    private String thanhTien;
    private String hinh;

    // Constructor
    public SanPham(String maSP, String tenSP, String soLuong, String donGia, String thanhTien, String hinh) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
        this.hinh = hinh;
    }

    // ========== Các phương thức Getters ==========
    public String getMaSP() {
        return maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public String getDonGia() {
        return donGia;
    }

    public String getThanhTien() {
        return thanhTien;
    }

    public String getHinh() {
        return hinh;
    }

    // ========== Các phương thức Setters (Tùy chọn) ==========
    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public void setDonGia(String donGia) {
        this.donGia = donGia;
    }

    public void setThanhTien(String thanhTien) {
        this.thanhTien = thanhTien;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }
}