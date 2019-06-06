package com.toanvu.assimentandroidnangcao.detail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.toanvu.assimentandroidnangcao.R;
import com.toanvu.assimentandroidnangcao.database.KhoaHocPHPDao;

public class LearningKeyInformation extends AppCompatActivity {
    private TextView tvMaKH;
    private TextView tvNameKH;
    private TextView tvNgayKH;
    private TextView tvchitiet;
    KhoaHocPHPDao khoaHocPHPDao;
    String strMa,strName,strNgay,strChiTiet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learning_key_information_detail);

        tvMaKH = (TextView) findViewById(R.id.tvMaKH);
        tvNameKH = (TextView) findViewById(R.id.tvNameKH);
        tvNgayKH = (TextView) findViewById(R.id.tvNgayKH);
        tvchitiet = (TextView) findViewById(R.id.tvchitiet);

        khoaHocPHPDao = new KhoaHocPHPDao(this);
        Intent i1 = getIntent();
        Bundle b1 = i1.getExtras();
        strMa = b1.getString("MAKH");
        strName = b1.getString("NAMEKH");
        strNgay = b1.getString("NGAYKH");
        strChiTiet = b1.getString("GIOITHIEU");

        tvMaKH.setText(strMa);
        tvNameKH.setText(strName);
        tvNgayKH.setText(strNgay);
        tvchitiet.setText(strChiTiet);
    }
}
