package com.yello_sakedong.nexters.yellosakedong.main.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yello_sakedong.nexters.yellosakedong.R;
import com.yello_sakedong.nexters.yellosakedong.splash.SplashActivity;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView=(TextView)findViewById(R.id.text_View);
        textView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        if (isKeyboardShown(textView.getRootView())) {
                            Toast.makeText(getApplicationContext(),"ㅁㅇㄴㄹ",Toast.LENGTH_SHORT ).show();

                        }
                        else{
                            Toast.makeText(getApplicationContext(),"dhjf",Toast.LENGTH_SHORT ).show();
                            
                        }
                    }
                }
        );
        Button btn1 = findViewById(R.id.button6);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
                startActivityForResult(intent, 1);
                finish();
            }
        });

        Log.d("","fff");



    }
    boolean isKeyboardShown(View rootView){

        Rect r=new Rect();
        rootView.getWindowVisibleDisplayFrame(r);
        DisplayMetrics dm=rootView.getResources().getDisplayMetrics();
        Integer heightDiff=rootView.getBottom()-r.bottom;
        return heightDiff> 128*dm.density;
    };
}
