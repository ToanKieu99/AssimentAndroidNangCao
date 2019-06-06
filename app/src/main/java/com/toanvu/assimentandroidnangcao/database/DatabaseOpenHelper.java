package com.toanvu.assimentandroidnangcao.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseOpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "HTHT";
    public static final int VERISON = 1;

    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, VERISON);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(HocVienDao.SQL_HOC_VIEN);
        db.execSQL(KhoaHocJavaWebDao.SQL_KHOA_HOC_JAVAWEB);
        db.execSQL(KhoaHocPHPDao.SQL_KHOA_HOC_PHP);
        db.execSQL(LichHocDao.SQL_LICH_HOC);
        db.execSQL(DiemDanhDao.SQL_DIEM_DANH);
        db.execSQL(DiemDao.SQL_DIEM);
        db.execSQL(ThongBaoDao.SQL_THONG_BAO);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" Drop table if exists " + HocVienDao.TABLE_NAME);
        db.execSQL(" Drop table if exists " + KhoaHocJavaWebDao.TABLE_NAME);
        db.execSQL(" Drop table if exists " + KhoaHocPHPDao.TABLE_NAME);
        db.execSQL(" Drop table if exists " + LichHocDao.TABLE_NAME);
        db.execSQL(" Drop table if exists " + DiemDanhDao.TABLE_NAME);
        db.execSQL(" Drop table if exists " + DiemDao.TABLE_NAME);
        db.execSQL(" Drop table if exists " + ThongBaoDao.TABLE_NAME);

        onCreate(db);

    }
}
