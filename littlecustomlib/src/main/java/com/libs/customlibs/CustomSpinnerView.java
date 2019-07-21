package com.libs.customlibs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.google.gson.Gson;
import com.libs.customlibs.spinner_dailog.CustomSpinnerModel;
import com.libs.customlibs.spinner_dailog.SpinnerListCallBack;
import com.libs.customlibs.spinner_dailog.SpinnerPopupList;

import java.util.ArrayList;
import java.util.List;

public class CustomSpinnerView extends LinearLayout implements SpinnerListCallBack.PopUpCallBack {
    private Context context;
    private ImageView spinnerImageView;
    private TextView spinnerTextView;
    private ImageView spinnerArrowImg;
    private LinearLayout spinner_Content;
    private boolean addImageIconStatus = false;
    private boolean addTextIconStatus = false;
    private String fontType;
    private List<CustomSpinnerModel> allDataList = new ArrayList<>();
    private TypedArray customSpinner;
    private TypedArray generalStyle;

    @SuppressLint("CustomViewStyleable")
    public CustomSpinnerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.spinner_content, this);
        this.context = context;
        spinner_Content = findViewById(R.id.spinner_Content);
        spinnerImageView = findViewById(R.id.spinnerImg);
        spinnerArrowImg = findViewById(R.id.spinnerArrow);
        spinnerTextView = findViewById(R.id.spinnerTxt);

        customSpinner = context.obtainStyledAttributes(attrs,
                R.styleable.customSpinner);
        generalStyle = context.obtainStyledAttributes(attrs,
                R.styleable.generalStyle);
        initPageData();
    }

    private void initPageData() {

        if (customSpinner != null) {
            addImageIconStatus = customSpinner.getBoolean(R.styleable.customSpinner_setImage, false);
            addTextIconStatus = customSpinner.getBoolean(R.styleable.customSpinner_hideText, false);
            spinnerArrowImg.setColorFilter(customSpinner.getColor(R.styleable.customSpinner_setArrowColor,
                    context.getResources().getColor(R.color.colorAccent)));
            setAddTextIconStatus(addTextIconStatus);
            setAddImageIconStatus(addImageIconStatus);
            Drawable drawable = customSpinner.getDrawable(R.styleable.customSpinner_setArrowIcon);
            if (drawable != null) {
                spinnerArrowImg.setImageDrawable(drawable);
            } else {
                spinnerArrowImg.setImageResource(R.drawable.ic_arrow_icon);
            }
        }
        if (generalStyle != null) {
            spinnerTextView.setText(generalStyle.getString(R.styleable.generalStyle_text));
            spinnerTextView.setTextColor(generalStyle.getColor(R.styleable.generalStyle_textColor,
                    context.getResources().getColor(R.color.colorAccent)));
            fontType = generalStyle.getString(R.styleable.generalStyle_fontType);
            if (CustomFontsUtilits.vaildFontType(fontType)) {
                spinnerTextView.setTypeface(UiUtil.getFont(context, fontType));
            }

        }

        initClickAction();
    }

    private void initClickAction() {
        spinner_Content.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomMSG();
                openSpinnerListAdater();
            }
        });

    }


    public boolean isAddImageIconStatus() {
        return addImageIconStatus;
    }

    public void setAddImageIconStatus(boolean addImageIconStatus) {
        this.addImageIconStatus = addImageIconStatus;
        if (addImageIconStatus) {
            spinnerImageView.setVisibility(VISIBLE);
        } else {
            spinnerImageView.setVisibility(GONE);
        }

    }

    public boolean isAddTextIconStatus() {
        return addTextIconStatus;
    }

    public void setAddTextIconStatus(boolean addTextIconStatus) {
        this.addTextIconStatus = addTextIconStatus;

        if (addTextIconStatus) {
            spinnerTextView.setVisibility(View.GONE);
        } else {
            spinnerTextView.setVisibility(VISIBLE);
        }

    }

    private void showCustomMSG() {
        Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();
    }

    private void openSpinnerListAdater() {
        FragmentActivity fragmentActivity = (FragmentActivity) context;
        SpinnerPopupList spinnerPopupList = new SpinnerPopupList();
        Bundle bundle = new Bundle();
        bundle.putString("data_list", new Gson().toJson(getAllDataList()));
        bundle.putBoolean("show_img", addImageIconStatus);
        bundle.putString("fontType", fontType);
        spinnerPopupList.setArguments(bundle);
        spinnerPopupList.show(fragmentActivity.getSupportFragmentManager(), "tag");
    }

    public List<CustomSpinnerModel> getAllDataList() {
        return allDataList;
    }

    public void setAllDataList(List<CustomSpinnerModel> allDataList) {
        this.allDataList = allDataList;
    }

    @Override
    public void popUp_ChooseCustomCountry(int postion, CustomSpinnerModel customSpinnerModel) {
        spinnerTextView.setText(customSpinnerModel.getSpinnerText());
    }

}
