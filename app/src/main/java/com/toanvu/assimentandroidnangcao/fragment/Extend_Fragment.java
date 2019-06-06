package com.toanvu.assimentandroidnangcao.fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.login.widget.ProfilePictureView;
import com.toanvu.assimentandroidnangcao.R;
import com.toanvu.assimentandroidnangcao.Scocial.Scocial;
import com.toanvu.assimentandroidnangcao.activity.Enrollment_Activity;
import com.toanvu.assimentandroidnangcao.bottomsheet.Information_BottomSheet;
import com.toanvu.assimentandroidnangcao.database.HocVienDao;
import com.toanvu.assimentandroidnangcao.maps.MapsActivity;
import com.toanvu.assimentandroidnangcao.model.HocVien;
import com.toanvu.assimentandroidnangcao.model.KhoaHoc;
import com.toanvu.assimentandroidnangcao.news.News;

import java.util.List;

public class Extend_Fragment extends Fragment {
    private ProfilePictureView imageProfilePicture;
    private TextView tvNameDangNhap;
    private CardView btThongTinCaNhan;
    private CardView btTinTuc;
    private CardView btDKKhoahoc;
    private CardView btkhenthuong;
    private CardView btLogOut;
    private CardView btMaps;
    private CardView btCocial;


    List<HocVien> arViens;
    HocVienDao hocVienDao;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.extend_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhXa(view);


        btThongTinCaNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Information_BottomSheet informationBottomSheet = new Information_BottomSheet();
                informationBottomSheet.show(getFragmentManager(), "InformationBottomSheet");
            }
        });

        btDKKhoahoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent enrollment = new Intent(getContext(), Enrollment_Activity.class);
                startActivity(enrollment);

            }
        });



        btTinTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tintuc = new Intent(getContext(),News.class);
                startActivity(tintuc);
            }
        });

        btMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent maps = new Intent(getContext(),MapsActivity.class);
                startActivity(maps);
            }
        });


    btCocial.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getContext(),Scocial.class);
            startActivity(intent);
        }
    });

    btLogOut.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Bạn có chắc chắn muốn thoát không?");
            builder.setIcon(R.drawable.dangxuat);
            builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.show();
        }
    });
    }


    private void anhXa(View view) {
        imageProfilePicture = (ProfilePictureView) view.findViewById(R.id.imageProfilePicture);
        tvNameDangNhap = (TextView) view.findViewById(R.id.tvNameDangNhap);
        btThongTinCaNhan = (CardView) view.findViewById(R.id.btThongThinCaNhan);
        btTinTuc = (CardView) view.findViewById(R.id.btTinTuc);
        btDKKhoahoc = (CardView) view.findViewById(R.id.btDKKhoahoc);
        btkhenthuong = (CardView) view.findViewById(R.id.btkhenthuong);
        btLogOut = (CardView) view.findViewById(R.id.btLogOut);
        btMaps = (CardView) view.findViewById(R.id.btMaps);
        btCocial = (CardView) view.findViewById(R.id.btCocial);
    }

}
