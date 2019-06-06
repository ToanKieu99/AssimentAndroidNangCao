package com.toanvu.assimentandroidnangcao.model;

import java.util.Date;

public class KhoaHoc {

    String id,nameKH;
    Date thoigianHK;
    String chitiet;
    public KhoaHoc() {
    }

    public KhoaHoc(String id, String nameKH, Date thoigianHK, String chitiet) {
        this.id = id;
        this.nameKH = nameKH;
        this.thoigianHK = thoigianHK;
        this.chitiet = chitiet;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameKH() {
        return nameKH;
    }

    public void setNameKH(String nameKH) {
        this.nameKH = nameKH;
    }

    public String getChitiet() {
        return chitiet;
    }

    public void setChitiet(String chitiet) {
        this.chitiet = chitiet;
    }

    public Date getThoigianHK() {
        return thoigianHK;
    }

    public void setThoigianHK(Date thoigianHK) {
        this.thoigianHK = thoigianHK;
    }

    @Override
    public String toString() {
        return "KhoaHoc{" +
                "id='" + id + '\'' +
                ", nameKH='" + nameKH + '\'' +
                ", thoigianHK=" + thoigianHK +
                ", chitiet='" + chitiet + '\'' +
                '}';
    }
}
