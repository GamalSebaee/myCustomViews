package com.libs.customlibs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
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

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class CustomSpinnerView extends LinearLayout implements SpinnerListCallBack.PopUpCallBack {
    private Context context;
    ImageView spinnerImageView;
    TextView spinnerTextView;
    ImageView spinnerArrowImg;
    private boolean addImageIconStatus = false;
    private String defalutText;
    private String fontType;
    private List<CustomSpinnerModel> allDataList = new ArrayList<>();

    public CustomSpinnerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.spinner_content, this);
        this.context = context;
        LinearLayout spinner_Content = findViewById(R.id.spinner_Content);
        spinnerImageView = findViewById(R.id.spinnerImg);
        spinnerArrowImg = findViewById(R.id.spinnerArrow);
        spinnerTextView = findViewById(R.id.spinnerTxt);
        @SuppressLint("CustomViewStyleable")
        TypedArray attributes = context.obtainStyledAttributes(attrs,
                R.styleable.customSpinner);
        TypedArray attributes2 = context.obtainStyledAttributes(attrs,
                R.styleable.SmartNumericTextView);
        if (attributes != null) {

            addImageIconStatus = attributes.getBoolean(R.styleable.customSpinner_setImage, false);
            boolean addTextIconStatus = attributes.getBoolean(R.styleable.customSpinner_hideText, false);
            if (addImageIconStatus) {
                spinnerImageView.setVisibility(VISIBLE);
            } else {
                spinnerImageView.setVisibility(GONE);
            }

            if (addTextIconStatus) {
                spinnerTextView.setVisibility(View.GONE);
            } else {
                spinnerTextView.setVisibility(VISIBLE);
            }
        }

        spinnerTextView.setText(attributes2.getString(R.styleable.SmartNumericTextView_text));
        spinnerTextView.setTextColor(attributes2.getColor(R.styleable.SmartNumericTextView_textColor, context.getResources().getColor(R.color.colorAccent)));
        if (attributes != null) {
            spinnerArrowImg.setColorFilter(attributes.getColor(R.styleable.customSpinner_setArrowColor,
                    context.getResources().getColor(R.color.colorAccent)));
        }

        fontType = attributes2.getString(R.styleable.SmartNumericTextView_fontType);
        if (fontType != null) {
            spinnerTextView.setTypeface(getFont(fontType));
        }

        if (attributes != null) {
            Drawable drawable = attributes.getDrawable(R.styleable.customSpinner_setArrowIcon);
            if (drawable != null) {
                spinnerArrowImg.setImageDrawable(drawable);
            } else {
                spinnerArrowImg.setImageResource(R.drawable.ic_arrow_icon);
            }

        }

        spinner_Content.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomMSG();
                openSpinnerListAdater();
            }
        });


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

    private static final Hashtable<String, SoftReference<Typeface>> fontCache = new Hashtable<String, SoftReference<Typeface>>();

    private Typeface getFont(String name) {
        synchronized (fontCache) {
            if (fontCache.get(name) != null) {
                SoftReference<Typeface> ref = fontCache.get(name);
                if (ref.get() != null) {
                    return ref.get();
                }
            }

            Typeface typeface = Typeface.createFromAsset(
                    context.getAssets(),
                    /*"AppFonts/" +*/"txt_font.ttf"
            );
            fontCache.put(name, new SoftReference<Typeface>(typeface));

            return typeface;
        }
    }
}
