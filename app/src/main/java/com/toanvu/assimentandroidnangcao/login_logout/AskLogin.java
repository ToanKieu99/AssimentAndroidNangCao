package com.toanvu.assimentandroidnangcao.login_logout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.toanvu.assimentandroidnangcao.R;
import com.toanvu.assimentandroidnangcao.bottomsheet.LoginBottomSheet;
import com.toanvu.assimentandroidnangcao.course.Course;

import java.util.Arrays;

public class AskLogin extends AppCompatActivity {
    private CardView hoilogin, registration;
    private LoginButton loginButton;

    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_login_ask);

        hoilogin = (CardView) findViewById(R.id.hoilogin);
        registration = findViewById(R.id.registration);
        loginButton = (LoginButton) findViewById(R.id.login_buttonAs);


        hoilogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(AskLogin.this, Login.class);
//                startActivity(intent);
                LoginBottomSheet loginBottomSheet = new LoginBottomSheet();
                loginBottomSheet.show(getSupportFragmentManager(),"LoginBottomSheet");
            }
        });

        loginButton.setReadPermissions(Arrays.asList("public_profile", "email"));
        setLogin_button();

        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Registrantion.class);
                startActivity(intent);
            }
        });


    }

    private void setLogin_button() {
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent intent = new Intent(AskLogin.this, Course.class);
                startActivity(intent);
                Toast.makeText(AskLogin.this, "Login successfully", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    protected void onStart() {
        LoginManager.getInstance().logOut();
        super.onStart();
    }
}
