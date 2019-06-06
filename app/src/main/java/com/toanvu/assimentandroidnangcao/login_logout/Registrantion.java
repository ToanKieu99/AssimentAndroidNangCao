package com.toanvu.assimentandroidnangcao.login_logout;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;
import com.toanvu.assimentandroidnangcao.R;
import com.toanvu.assimentandroidnangcao.course.Course;
import com.toanvu.assimentandroidnangcao.database.HocVienDao;
import com.toanvu.assimentandroidnangcao.model.HocVien;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Registrantion extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private TextInputEditText editUsernameDK;
    private TextInputEditText editPasswordDK;
    private TextInputEditText editName;
    private TextInputEditText editID;
    private TextInputEditText editGender;
    private TextInputEditText editNgayThang;
    private TextInputEditText editEmail;
    private TextInputEditText editPhone;
    private TextInputEditText editPlaceBirth;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    List<HocVien> hocVienList;
    HocVienDao hocVienDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrantion);
        anhXa();
    }


    private void anhXa() {
        editUsernameDK = (TextInputEditText) findViewById(R.id.editUsernameDK);
        editPasswordDK = (TextInputEditText) findViewById(R.id.editPasswordDK);
        editName = (TextInputEditText) findViewById(R.id.editName);
        editID = (TextInputEditText) findViewById(R.id.editID);
        editGender = (TextInputEditText) findViewById(R.id.editGender);
        editNgayThang = (TextInputEditText) findViewById(R.id.editNgayThang);
        editEmail = (TextInputEditText) findViewById(R.id.editEmail);
        editPhone = (TextInputEditText) findViewById(R.id.editPhone);
        editPlaceBirth = (TextInputEditText) findViewById(R.id.editPlaceBirth);
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
        editNgayThang.setText(sdf.format(calendar.getTime()));
    }

    public void Registrantion(final View view) {
        hocVienDao = new HocVienDao(Registrantion.this);


        try {
            if (validation() < 0) {

                Toast.makeText(getApplicationContext(), "Enter full to register", Toast.LENGTH_SHORT).show();
            } else {
                HocVien user = new HocVien(
                        editID.getText().toString(),editUsernameDK.getText().toString(), editPasswordDK.getText().toString(),
                        editName.getText().toString(),
                        editGender.getText().toString(), sdf.parse(editNgayThang.getText().toString()),
                        editEmail.getText().toString(), editPhone.getText().toString(),
                        editPlaceBirth.getText().toString());

                if (hocVienDao.insertHocVien(user) > 0) {
                    Toast.makeText(getApplicationContext(), "Sign Up Success", Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Do you want to use this account?");
                    builder.setIcon(R.drawable.icon_logina);
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(Registrantion.this, "You have canceled your login\nPlease login to login to the app", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Registrantion.this, Course.class);
                            startActivity(intent);

                            Toast.makeText(Registrantion.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();

                } else {
                    Toast.makeText(getApplicationContext(), "Registration failed", Toast.LENGTH_SHORT).show();
                }
            }

        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }

    public int validation() {
        String user = editUsernameDK.getText().toString();
        String pas = editPasswordDK.getText().toString();
        String name = editName.getText().toString();
        String id = editID.getText().toString();
        String gender = editGender.getText().toString();
        String birth = editNgayThang.getText().toString();
        String email = editEmail.getText().toString();
        String phone = editPhone.getText().toString();
        String PlaceBirth = editPlaceBirth.getText().toString();


        if (user.isEmpty() || pas.isEmpty() ||
                name.isEmpty() || id.isEmpty() ||
                gender.isEmpty() || birth.isEmpty() ||
                email.isEmpty() || phone.isEmpty() ||
                PlaceBirth.isEmpty()) {
            return -1;
        }
        return 1;
    }

    public void Birthday(View view) {
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getSupportFragmentManager(), "Date");
    }
}
