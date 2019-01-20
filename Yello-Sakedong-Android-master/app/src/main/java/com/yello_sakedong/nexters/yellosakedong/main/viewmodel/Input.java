package com.yello_sakedong.nexters.yellosakedong.main.viewmodel;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.MultiAutoCompleteTextView;

import com.yello_sakedong.nexters.yellosakedong.R;

public class Input extends AppCompatActivity {
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input1);
        MultiAutoCompleteTextView simpleMultiAutoCompleteTextView = (MultiAutoCompleteTextView) findViewById(R.id.simpleMultiAutoCompleteTextView);
// 자동완성 기능
        ArrayAdapter<String> versionNames = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        simpleMultiAutoCompleteTextView.setAdapter(versionNames);

// 음식 이름 array
        simpleMultiAutoCompleteTextView.setThreshold(1);
        simpleMultiAutoCompleteTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        simpleMultiAutoCompleteTextView.getText().toString();

    }
}
