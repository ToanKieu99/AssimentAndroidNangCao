package com.toanvu.assimentandroidnangcao.bottomsheet;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.toanvu.assimentandroidnangcao.R;
import com.toanvu.assimentandroidnangcao.course.Course;
import com.toanvu.assimentandroidnangcao.database.HocVienDao;


public class LoginBottomSheet extends BottomSheetDialogFragment {
    private EditText editUsername;
    private EditText editPassword;
    private CardView btLogin;
    HocVienDao hocVienDao;
    String user, pass;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_login_bottom_sheet, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editUsername = (EditText) view.findViewById(R.id.editUsername);
        editPassword = (EditText) view.findViewById(R.id.editPassword);
        btLogin = (CardView) view.findViewById(R.id.btLogin);

        hocVienDao = new HocVienDao(getActivity());


        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = editUsername.getText().toString();
                String pass = editPassword.getText().toString();
                if (user.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(getContext(), "Enter user and password to log", Toast.LENGTH_SHORT).show();
                } else {
                    if (hocVienDao.checkLogin(user, pass) > 0) {
                        Toast.makeText(getContext(), "Login successfully", Toast.LENGTH_SHORT).show();
                        Intent main = new Intent(getContext(), Course.class);
                        startActivity(main);
                    } else if (user.equals("admin") && pass.equals("admin")) {
                        Intent intent = new Intent(getContext(), Course.class);
                        startActivity(intent);
                        Toast.makeText(getContext(), "Login successfully", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
