package com.magints.customviews;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.libs.customlibs.CustomRatingBar;

public class MainActivity extends AppCompatActivity {
 Button btnViewData;
    CustomRatingBar customRateBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customRateBar=findViewById(R.id.customRateBar);
        btnViewData=findViewById(R.id.btnViewData);
        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Rating is : "+customRateBar.getRateValue(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
