package com.yello.nexters.yellosakedong.output.viewholder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yello.nexters.yellosakedong.R;
import com.yello.nexters.yellosakedong.input.InputActivity;
import com.yello.nexters.yellosakedong.output.OutputActivity;

public class Output404 extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_output_404_page);
        TextView btn1=(TextView) findViewById(R.id.add);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InputActivity.class);
                startActivityForResult(intent, 1);
                finish();

            }
        });
    }


}
