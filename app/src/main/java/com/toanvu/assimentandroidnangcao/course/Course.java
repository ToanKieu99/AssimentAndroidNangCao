package com.toanvu.assimentandroidnangcao.course;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.toanvu.assimentandroidnangcao.R;
import com.toanvu.assimentandroidnangcao.fragment.Attendance_Fragment;
import com.toanvu.assimentandroidnangcao.fragment.Extend_Fragment;
import com.toanvu.assimentandroidnangcao.fragment.Notification_Frgment;
import com.toanvu.assimentandroidnangcao.fragment.Points_Fragment;
import com.toanvu.assimentandroidnangcao.fragment.Schedule_Fragment;

public class Course extends AppCompatActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.lichhoc:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new Schedule_Fragment()).commit();
                    return true;

                case R.id.diemdanh:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new Attendance_Fragment()).commit();
                    return true;

                case R.id.diem:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new Points_Fragment()).commit();
                    return true;

                case R.id.caidat:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new Extend_Fragment()).commit();
                    return true;

                case R.id.thongbao:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new Notification_Frgment()).commit();
                    return  true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        QuanLyFragment(new Schedule_Fragment());
    }

    public void QuanLyFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment, fragment);
            fragmentTransaction.commit();
        }
    }

}
