package com.example.tacademy.pedokio;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * Created by Tacademy on 2017-08-07.
 */

public class SearchViewActivity extends Activity {
    ImageButton sbtn;
    EditText sedt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);
        sbtn = (ImageButton)findViewById(R.id.sbtn);
        sedt = (EditText)findViewById(R.id.sedt);
        sbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SearchListActivity.class);
                startActivity(intent);
            }
        });
        int color = Color.parseColor("#FFFFFF");

        sedt.getBackground().setColorFilter(color, PorterDuff.Mode.SRC_IN);





    }
}

