package com.toanvu.assimentandroidnangcao.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;


import com.toanvu.assimentandroidnangcao.course_activity.Android;
import com.toanvu.assimentandroidnangcao.course_activity.JavaWeb;
import com.toanvu.assimentandroidnangcao.R;
import com.toanvu.assimentandroidnangcao.course_activity.LTC;
import com.toanvu.assimentandroidnangcao.course_activity.PHP;
import com.toanvu.assimentandroidnangcao.course_activity.React_Native;

public class Enrollment_Activity extends AppCompatActivity {
    private ImageView ltJavaWeb;
    private ImageView ltPHP;
    private ImageView ltAndroid;
    private ImageView ltReactNative;
    private ImageView ltC;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enrollment_activity);
        anhXa();


        ltJavaWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(Enrollment_Activity.this);
                builder.setTitle("Tuỳ chọn xem hoặc mua khoá học");
                builder.setNegativeButton("Giới thiệu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(Enrollment_Activity.this);
                        builder1.setTitle("Giới thiệu qua khoá học");
                        builder1.setMessage(R.string.JavaWeb);

                        builder1.setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder1.show();
                    }
                });
                builder.setPositiveButton("Mua khoá học", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent javaWeb = new Intent(Enrollment_Activity.this, JavaWeb.class);
                        startActivity(javaWeb);
                    }
                });


                builder.show();



            }
        });

        ltPHP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(Enrollment_Activity.this);
                builder.setTitle("Tuỳ chọn xem hoặc mua khoá học");
                builder.setNegativeButton("Giới thiệu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(Enrollment_Activity.this);
                        builder1.setTitle("Giới thiệu qua khoá học");
                        builder1.setMessage(R.string.php);

                        builder1.setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder1.show();
                    }
                });
                builder.setPositiveButton("Mua khoá học", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent javaWeb = new Intent(Enrollment_Activity.this, PHP.class);
                        startActivity(javaWeb);
                    }
                });


                builder.show();



            }
        });


        ltAndroid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(Enrollment_Activity.this);
                builder.setTitle("Tuỳ chọn xem hoặc mua khoá học");
                builder.setNegativeButton("Giới thiệu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(Enrollment_Activity.this);
                        builder1.setTitle("Giới thiệu qua khoá học");
                        builder1.setMessage(R.string.android);

                        builder1.setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder1.show();
                    }
                });
                builder.setPositiveButton("Mua khoá học", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent javaWeb = new Intent(Enrollment_Activity.this, Android.class);
                        startActivity(javaWeb);
                    }
                });


                builder.show();



            }
        });


        ltReactNative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(Enrollment_Activity.this);
                builder.setTitle("Tuỳ chọn xem hoặc mua khoá học");
                builder.setNegativeButton("Giới thiệu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(Enrollment_Activity.this);
                        builder1.setTitle("Giới thiệu qua khoá học");
                        builder1.setMessage(R.string.react_native);

                        builder1.setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder1.show();
                    }
                });
                builder.setPositiveButton("Mua khoá học", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent javaWeb = new Intent(Enrollment_Activity.this, React_Native.class);
                        startActivity(javaWeb);
                    }
                });


                builder.show();



            }
        });


        ltC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(Enrollment_Activity.this);
                builder.setTitle("Tuỳ chọn xem hoặc mua khoá học");
                builder.setNegativeButton("Giới thiệu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(Enrollment_Activity.this);
                        builder1.setTitle("Giới thiệu qua khoá học");
                        builder1.setMessage(R.string.c);

                        builder1.setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder1.show();
                    }
                });
                builder.setPositiveButton("Mua khoá học", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent javaWeb = new Intent(Enrollment_Activity.this, LTC.class);
                        startActivity(javaWeb);
                    }
                });


                builder.show();



            }
        });
    }

    private void anhXa() {
        ltJavaWeb = (ImageView) findViewById(R.id.ltJavaWeb);
        ltPHP = (ImageView) findViewById(R.id.ltPHP);
        ltAndroid = (ImageView) findViewById(R.id.ltAndroid);
        ltReactNative = (ImageView) findViewById(R.id.ltReactNative);
        ltC = (ImageView) findViewById(R.id.ltC);
    }
}
