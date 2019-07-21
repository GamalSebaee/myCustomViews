package com.magints.customviews;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.libs.customlibs.CustomRatingBar;
import com.libs.customlibs.CustomSpinnerView;
import com.libs.customlibs.spinner_dailog.CustomSpinnerModel;
import com.libs.customlibs.spinner_dailog.SpinnerListCallBack;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SpinnerListCallBack.PopUpCallBack {
 Button btnViewData;
 CustomSpinnerView spinnerList;

    CustomRatingBar customRateBar;
    List<CustomSpinnerModel> allDataList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customRateBar=findViewById(R.id.customRateBar);
        spinnerList=findViewById(R.id.spinnerList);
        btnViewData=findViewById(R.id.btnViewData);
        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Rating is : "+customRateBar.getRateValue(), Toast.LENGTH_SHORT).show();
            }
        });

        for(int i=0;i<50;i++){
            CustomSpinnerModel customSpinnerModel=new CustomSpinnerModel();
            customSpinnerModel.setSpinnerText("جمال"+i);
            allDataList.add(customSpinnerModel);
        }
        spinnerList.setAllDataList(allDataList);

    }

    @Override
    public void popUp_ChooseCustomCountry(int postion, CustomSpinnerModel customSpinnerModel) {
        spinnerList.popUp_ChooseCustomCountry(postion,customSpinnerModel);
    }

    /*@Override
    public void popUp_ChooseCustomCountry(int postion, CustomSpinnerModel customSpinnerModel) {
        spinnerList.popUp_ChooseCustomCountry(postion,customSpinnerModel);
    }*/
}
