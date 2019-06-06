package com.toanvu.assimentandroidnangcao.Scocial;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.toanvu.assimentandroidnangcao.R;

import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Scocial extends AppCompatActivity {
//    private ProfilePictureView imageProfilePicture;
//    private LoginButton loginButton;
//    private Button btDangXuat;
//    private TextView tvName;
//    private TextView tvEmail;
//    private TextView tvFirstName;
//    private Button btCacChuNang;
    CallbackManager callbackManager;
    private CardView btShareLink;
    ImageView thoat;



    //    String email,name,firstname;
    ShareDialog shareDialog;
    ShareLinkContent shareLinkContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_scocial);

        btShareLink = (CardView) findViewById(R.id.btShareLink);
        thoat = findViewById(R.id.thoatShare);
        shareDialog = new ShareDialog(Scocial.this);

        btShareLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ShareDialog.canShow(ShareLinkContent.class)){
                    shareLinkContent = new ShareLinkContent.Builder()
                            .setContentTitle("")
                            .setContentDescription("")
                            .setContentUrl(Uri.parse("https://www.youtube.com/watch?v=y49lVm0XXVE"))
                            .build();
                }
                shareDialog.show(shareLinkContent);
            }
        });

        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



//        imageProfilePicture = (ProfilePictureView) findViewById(R.id.imageProfilePicture);
//        loginButton = (LoginButton) findViewById(R.id.login_button);
//        btDangXuat = (Button) findViewById(R.id.btDangXuat);
//        tvName = (TextView) findViewById(R.id.tvName);
//        tvEmail = (TextView) findViewById(R.id.tvEmail);
//        tvFirstName = (TextView) findViewById(R.id.tvFirstName);
//        btCacChuNang = (Button) findViewById(R.id.btCacChuNang);
//        try {
//            PackageInfo info = getPackageManager().getPackageInfo(
//                    "com.toanvu.assimentandroidnangcao",
//                    PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//
//        } catch (NoSuchAlgorithmException e) {
//
//        }

//        btCacChuNang.setVisibility(View.INVISIBLE);
//        btDangXuat.setVisibility(View.INVISIBLE);
//        tvEmail.setVisibility(View.INVISIBLE);
//        tvName.setVisibility(View.INVISIBLE);
//        tvFirstName.setVisibility(View.INVISIBLE);
//        loginButton.setReadPermissions(Arrays.asList("public_profile","email"));
//        setLogin_button();
//        setLoout_button();
    }


//    private void setLoout_button() {
//        btDangXuat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                LoginManager.getInstance().logOut();
//
//                btCacChuNang.setVisibility(View.INVISIBLE);
//                btDangXuat.setVisibility(View.INVISIBLE);
//                tvEmail.setVisibility(View.INVISIBLE);
//                tvName.setVisibility(View.INVISIBLE);
//                tvFirstName.setVisibility(View.INVISIBLE);
//                tvEmail.setText("");
//                tvFirstName.setText("");
//                tvName.setText("");
//                imageProfilePicture.setProfileId(null);
//                loginButton.setVisibility(View.VISIBLE);
//            }
//        });
//    }
//
//    private void setLogin_button() {
//        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//
//                loginButton.setVisibility(View.INVISIBLE);
//
//                btCacChuNang.setVisibility(View.VISIBLE);
//                btDangXuat.setVisibility(View.VISIBLE);
//                tvEmail.setVisibility(View.VISIBLE);
//                tvName.setVisibility(View.VISIBLE);
//                tvFirstName.setVisibility(View.VISIBLE);
//                result();
//            }
//
//            @Override
//            public void onCancel() {
//
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//
//            }
//        });
//    }
//
//    private void result() {
//        GraphRequest graphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
//            @Override
//            public void onCompleted(JSONObject object, GraphResponse response) {
//                Log.d("JSON",response.getJSONObject().toString());
//                try {
//                    name = object.getString("name");
//                    firstname = object.getString("first_name");
//                    imageProfilePicture.setProfileId(Profile.getCurrentProfile().getId());
//
//                    tvName.setText(name);
//                    tvFirstName.setText(firstname);
//                }catch (Exception e){
//
//                }
//            }
//        });
//        Bundle parameters = new Bundle();
//        parameters.putString("fields", "name,email,first_name");
//        graphRequest.setParameters(parameters);
//        graphRequest.executeAsync();
//
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        callbackManager.onActivityResult(requestCode, resultCode, data);
//    }
//
//    @Override
//    protected void onStart() {
//        LoginManager.getInstance().logOut();
//        super.onStart();
//    }
}
