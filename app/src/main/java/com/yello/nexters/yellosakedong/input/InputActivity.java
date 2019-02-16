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


public class InputActivity extends Activity {

    ImageView iv_image;
    Uri selectedUri;
    final ArrayList<Uri> selectedUriList = new ArrayList<>();
    private ViewGroup mSelectedImagesContainer;
    private RequestManager requestManager;


    protected void onCreate(Bundle savedInstanceState) {


        ViewGroup mSelectedImagesContainer;
        RequestManager requestManager;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        ImageButton img=(ImageButton)findViewById(R.id.imageButton);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PermissionListener permissionlistener = new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {

                        TedBottomPicker bottomSheetDialogFragment = new TedBottomPicker.Builder(InputActivity.this)
                                .setOnMultiImageSelectedListener(new TedBottomPicker.OnMultiImageSelectedListener() {
                                    @Override
                                    public void onImagesSelected(ArrayList<Uri> uriList) {
                                    }
                                })
                                //.setPeekHeight(getResources().getDisplayMetrics().heightPixels/2)
                                .setPeekHeight(1600)
                                .showTitle(false)
                                .setCompleteButtonText("Done")
                                .setEmptySelectionText("No Select")
//                                .setSelectedUriList()
                                .create();

//                        bottomSheetDialogFragment.show(getSupportFragmentManager());


                    }

                    @Override
                    public void onPermissionDenied(List<String> deniedPermissions) {

                    }


                    public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                        Toast.makeText(InputActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
                    }


                };

                TedPermission.with(InputActivity.this)
                        .setPermissionListener(permissionlistener)
                        .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                        .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .check();

            }
        });






        Button btn1=(Button)findViewById(R.id.regi);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), OutputActivity.class);
                startActivityForResult(intent, 1);
                finish();

                // Dontbe stupid and stay calm and write some code


            }
        });


    }
    private void showUriList(ArrayList<Uri> uriList) {
        // Remove all views before
        // adding the new ones.
        mSelectedImagesContainer.removeAllViews();

        iv_image.setVisibility(View.GONE);
        mSelectedImagesContainer.setVisibility(View.VISIBLE);

        int wdpx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());
        int htpx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());


        for (Uri uri : uriList) {

//            View imageHolder = LayoutInflater.from(this).inflate(R.layout.image_item, null);
//            ImageView thumbnail = (ImageView) imageHolder.findViewById(R.id.media_image);
//
//            requestManager
//                    .load(uri.toString())
//                    .apply(new RequestOptions().fitCenter())
//                    .into(thumbnail);
//
//            mSelectedImagesContainer.addView(imageHolder);
//
//            thumbnail.setLayoutParams(new FrameLayout.LayoutParams(wdpx, htpx));


        }

    }

}
