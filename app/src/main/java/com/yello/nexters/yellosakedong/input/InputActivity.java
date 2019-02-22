package com.yello.nexters.yellosakedong.input;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;


import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.yello.nexters.yellosakedong.R;
import com.yello.nexters.yellosakedong.main.MainActivity;
import com.yello.nexters.yellosakedong.output.OutputActivity;
import java.util.ArrayList;
import java.util.List;

import gun0912.tedbottompicker.TedBottomPicker;
import io.reactivex.disposables.Disposable;


public class InputActivity extends Activity {

    private ImageView iv_image;
    private List<Uri> selectedUriList;
    private Uri selectedUri;
    private Disposable singleImageDisposable;
    private Disposable multiImageDisposable;
    private ViewGroup mSelectedImagesContainer;
    private RequestManager requestManager;



    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        ImageButton img = (ImageButton) findViewById(R.id.imageButton);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), galleryaddActivity.class);
                startActivityForResult(intent, 1);
                finish();

            }
        });





                Button btn1 = (Button) findViewById(R.id.regi);
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), OutputActivity.class);
                        startActivityForResult(intent, 1);
                        finish();

                        // Dontbe stupid and stay calm and write some code


                    }
                });


            }}




