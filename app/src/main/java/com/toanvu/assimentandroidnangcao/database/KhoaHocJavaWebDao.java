package com.toanvu.assimentandroidnangcao.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.toanvu.assimentandroidnangcao.model.KhoaHoc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class KhoaHocJavaWebDao {
    private SQLiteDatabase db;
    private DatabaseOpenHelper dbHelper;
    public static final String TABLE_NAME = "KhoaHoc";
    public static final String SQL_KHOA_HOC_JAVAWEB = "CREATE TABLE KhoaHoc(id text primary key, nameJV text, thoigianJV date, chitietJV text)";

    public static final String TAG = "KhoaHocJavaWebDao";

    SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");


    public KhoaHocJavaWebDao(Context context) {
        dbHelper = new DatabaseOpenHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    ///insert
    public int insertKhoaHoc(KhoaHoc kh) {
        ContentValues values = new ContentValues();
        values.put("id", kh.getId());
        values.put("nameJV", kh.getNameKH());
        values.put("thoigianJV", sp.format(kh.getThoigianHK()));
        values.put("chitietJV", kh.getChitiet());

        try {
            if (db.insert(TABLE_NAME, null, values) == -1) {
                return -1;
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return 1;
    }

    public List<KhoaHoc> getALLKhoaHoc() throws ParseException {
        List<KhoaHoc> dskh = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            KhoaHoc kh = new KhoaHoc();
            kh.setId(c.getString(0));
            kh.setNameKH(c.getString(1));
            kh.setThoigianHK(sp.parse(c.getString(2)));
            kh.setChitiet(c.getString(3));
            dskh.add(kh);
            Log.d("//====", kh.toString());
            c.moveToNext();
        }
        c.close();
        return dskh;
    }

    ///Update
    public int updateinfoKhoaHoc(String id, String name, String thoigian, String chitiet) {
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("nameJV", name);
        values.put("thoigianJV", thoigian);
        values.put("chitietJV", chitiet);
        int result = db.update(TABLE_NAME, values, "id=?", new String[]{id});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    ///delete
    public int deleteKhoaHoc(String id) {
        int result = db.delete(TABLE_NAME, "id=?", new String[]{id});
        if (result == 0)
            return -1;
        return 1;
    }
}
