package com.toanvu.assimentandroidnangcao.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.toanvu.assimentandroidnangcao.model.HocVien;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HocVienDao {
    private SQLiteDatabase db;
    private DatabaseOpenHelper dbHelper;
    public static final String TABLE_NAME = "HocVien";
    public static final String SQL_HOC_VIEN = "CREATE TABLE HocVien(id text primary key,username text,password text,name text" +
            ", gender text, birthday date, email text, phone text, placeofbirth text)";
    public static final String TAG = "HocVienDao";

    SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");

    public HocVienDao(Context context) {
        dbHelper = new DatabaseOpenHelper(context);
        db = dbHelper.getWritableDatabase();
    }


    ///insert
    public int insertHocVien(HocVien hv) {
        ContentValues values = new ContentValues();
        values.put("id",hv.getId());
        values.put("username",hv.getUsername());
        values.put("password",hv.getPassword());
        values.put("name",hv.getName());
        values.put("gender",hv.getGender());
        values.put("birthday",sp.format(hv.getBirthday()));
        values.put("email",hv.getEmail());
        values.put("phone",hv.getPhone());
        values.put("placeofbirth",hv.getPlaceofbirth());

        try {
            if (db.insert(TABLE_NAME,null,values) == -1){
                return -1;
            }
        }catch (Exception e){
            Log.e(TAG,e.toString());
        }
        return 1;
    }

    public List<HocVien> getALLHocVien() throws ParseException {
        List<HocVien> dshv = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME,null,null,null,null,null,null);
        c.moveToFirst();
        while (c.isAfterLast() == false){
            HocVien hv = new HocVien();
            hv.setId(c.getString(0));
            hv.setUsername(c.getString(1));
            hv.setPassword(c.getString(2));
            hv.setName(c.getString(3));
            hv.setGender(c.getString(4));
            hv.setBirthday(sp.parse(c.getString(5)));
            hv.setEmail(c.getString(6));
            hv.setPhone(c.getString(7));
            hv.setPlaceofbirth(c.getString(8));
            dshv.add(hv);
            Log.d("//====",hv.toString());
            c.moveToNext();
        }
        c.close();
        return dshv;
    }


    ///Update
    public int updateinfoHocVien(String username,String pass, String name, String id, String gender,
                                    String birthday,String email, String phone, String placeofbirth) {
        ContentValues values = new ContentValues();
        values.put("username",username);
        values.put("password",pass);
        values.put("name",name);
        values.put("id",id);
        values.put("gender",gender);
        values.put("birthday",birthday);
        values.put("email",email);
        values.put("phone",phone);
        values.put("placeofbirth",placeofbirth);
        int result = db.update(TABLE_NAME, values, "id=?", new String[]{id});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    ///delete
    public int deleteHocVien(String id) {
        int result = db.delete(TABLE_NAME, "id=?", new String[]{id});
        if (result == 0)
            return -1;
        return 1;
    }

    //check login
    public int checkLogin(String username, String password) {
        ContentValues values = new ContentValues();
        values.put("username",username);
        values.put("password",password);
        int result = db.update(TABLE_NAME,values, "username=? AND password=?", new
                String[]{username, password});
        if (result == 0) return -1;
        return 1;
    }
}
