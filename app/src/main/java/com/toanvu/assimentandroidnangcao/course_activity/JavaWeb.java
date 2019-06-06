package com.toanvu.assimentandroidnangcao.course_activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
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

public class JavaWeb extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private EditText editMaKHJavaWeb;
    private EditText editNameKHJavaWeb;
    private EditText editDateKHJavaWeb;
    private EditText editChitiet;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    KhoaHocJavaWebDao khoaHocJavaWebDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.java_web);

        editMaKHJavaWeb = (EditText) findViewById(R.id.editMaKHJavaWeb);
        editNameKHJavaWeb = (EditText) findViewById(R.id.editNameKHJavaWeb);
        editDateKHJavaWeb = (EditText) findViewById(R.id.editDateKHJavaWeb);
        editChitiet = (EditText) findViewById(R.id.editChitiet);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar cal = new GregorianCalendar(year, month, day);
        setDate(cal);
    }




    public static class DatePickerFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) getActivity(), year, month, day);
        }
    }

    private void setDate(final Calendar calendar) {
        editDateKHJavaWeb.setText(sdf.format(calendar.getTime()));
    }

    public void Date(View view) {
        Registrantion.DatePickerFragment fragment = new Registrantion.DatePickerFragment();
        fragment.show(getSupportFragmentManager(), "Date");
    }

    public int validation() {
        String user = editMaKHJavaWeb.getText().toString();
        String pas = editNameKHJavaWeb.getText().toString();
        String name = editDateKHJavaWeb.getText().toString();
        String id = editChitiet.getText().toString();


        if (user.isEmpty() || pas.isEmpty() ||
                name.isEmpty() || id.isEmpty()) {
            return -1;
        }
        return 1;
    }

    public void DangKy(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(JavaWeb.this);
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
                                editMaKHJavaWeb.getText().toString(),
                                editNameKHJavaWeb.getText().toString(),
                                sdf.parse(editDateKHJavaWeb.getText().toString()),
                                editChitiet.getText().toString());

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
}
