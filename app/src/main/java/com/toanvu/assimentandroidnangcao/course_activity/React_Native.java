package com.toanvu.assimentandroidnangcao.course_activity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.toanvu.assimentandroidnangcao.R;
import com.toanvu.assimentandroidnangcao.database.KhoaHocJavaWebDao;
import com.toanvu.assimentandroidnangcao.login_logout.Registrantion;
import com.toanvu.assimentandroidnangcao.model.KhoaHoc;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class React_Native extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private EditText editMaKHRN;
    private EditText editNameKHRN;
    private EditText editDateKHRN;
    private EditText editChitietRN;

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    KhoaHocJavaWebDao khoaHocJavaWebDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_react__native);

        editMaKHRN = (EditText) findViewById(R.id.editMaKHRN);
        editNameKHRN = (EditText) findViewById(R.id.editNameKHRN);
        editDateKHRN = (EditText) findViewById(R.id.editDateKHRN);
        editChitietRN = (EditText) findViewById(R.id.editChitietRN);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar cal = new GregorianCalendar(year, month, day);
        setDate(cal);
    }

    public int validation() {
        String user = editMaKHRN.getText().toString();
        String pas = editNameKHRN.getText().toString();
        String name = editDateKHRN.getText().toString();
        String id = editChitietRN.getText().toString();


        if (user.isEmpty() || pas.isEmpty() ||
                name.isEmpty() || id.isEmpty()) {
            return -1;
        }
        return 1;
    }

    private void setDate(final Calendar calendar) {
        editDateKHRN.setText(sdf.format(calendar.getTime()));
    }

    public void DangKyRN(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(React_Native.this);
        builder.setTitle("Would you like to register for this course for 200$?");
        builder.setIcon(R.drawable.icon_dolar);
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                khoaHocJavaWebDao = new KhoaHocJavaWebDao(getApplicationContext());
                try {
                    if (validation() < 0) {
                        Toast.makeText(getApplicationContext(), "Enter full to register", Toast.LENGTH_SHORT).show();
                    } else {
                        KhoaHoc kh = new KhoaHoc(
                                editMaKHRN.getText().toString(),
                                editNameKHRN.getText().toString(),
                                sdf.parse(editDateKHRN.getText().toString()),
                                editChitietRN.getText().toString());

                        if (khoaHocJavaWebDao.insertKhoaHoc(kh) > 0) {
                            Toast.makeText(getApplicationContext(), "Sign Up Success", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "registration failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (Exception ex) {
                    Log.e("Error", ex.toString());
                }
            }
        });

        builder.show();

    }

    public void DateRN(View view) {
        Registrantion.DatePickerFragment fragment = new Registrantion.DatePickerFragment();
        fragment.show(getSupportFragmentManager(), "Date");
    }
}
