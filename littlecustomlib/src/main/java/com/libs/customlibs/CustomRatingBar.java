package com.libs.customlibs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.Objects;

public class CustomRatingBar extends LinearLayout {
    private Object rateValue = "0";
    private double rateSendedTo = 0;
    private int numberStars = 0;
    private Context context;
    private LinearLayout ratingContainer;
    private TypedArray customRatingBar;
    private Drawable starIcon_active=null;
    private Drawable starIcon_notActive=null;

    @SuppressLint("CustomViewStyleable")
    public CustomRatingBar(final Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.rating_bar_content, this);
        this.context = context;
        ratingContainer = findViewById(R.id.ratingContainer);
        customRatingBar = context.obtainStyledAttributes(attrs,
                R.styleable.customRatingBar);
        initPageContent();
    }

    private void initPageContent() {
        numberStars = customRatingBar.getInteger(R.styleable.customRatingBar_num_stars, 0);
        starSize = customRatingBar.getDimension(R.styleable.customRatingBar_starSize, 32);
        starIcon_active=customRatingBar.getDrawable(R.styleable.customRatingBar_starIcon_active);
        starIcon_notActive=customRatingBar.getDrawable(R.styleable.customRatingBar_starIcon_notActive);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            rateSendedTo = Double.parseDouble(Objects.requireNonNull(customRatingBar.getString(R.styleable.customRatingBar_valueRate)));
        }
        setRatingContent();
        customRatingBar.recycle();
    }

    float starSize=0;

    private void setRatingContent() {
        for (int i = 0; i < numberStars; i++) {
            final ImageView imageView_STar = new ImageView(context);
            LayoutParams layoutParams = new LayoutParams((int) starSize, (int)starSize);
            layoutParams.leftMargin = 5;
            layoutParams.rightMargin = 5;
            imageView_STar.setLayoutParams(layoutParams);

            if(starIcon_active != null){
                imageView_STar.setImageDrawable(starIcon_active);
            }else{
                imageView_STar.setImageResource(R.drawable.ic_rate_icon);
            }


            if (i < rateSendedTo) {
                if(starIcon_active != null){
                    imageView_STar.setImageDrawable(starIcon_active);
                }else{
                    imageView_STar.setImageResource(R.drawable.ic_rate_icon);
                    imageView_STar.setColorFilter(ContextCompat.getColor(context, R.color.trans_color), android.graphics.PorterDuff.Mode.SRC_IN);
                }
            } else {
                if(starIcon_notActive != null){
                    imageView_STar.setImageDrawable(starIcon_notActive);
                }else{
                    imageView_STar.setImageResource(R.drawable.ic_rate_icon);
                    imageView_STar.setColorFilter(ContextCompat.getColor(context, R.color.trans_color2), android.graphics.PorterDuff.Mode.SRC_IN);
                }
            }

            int tagValue = i;
            imageView_STar.setTag("" + (tagValue++));
            final int finalTagValue = tagValue;
            imageView_STar.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    setRateValue(finalTagValue);
                    setRateIconsList(context, ratingContainer, imageView_STar.getTag());
                }
            });
            ratingContainer.addView(imageView_STar);

        }
    }

    private void setRateIconsList(Context context, LinearLayout linearLayout, Object tagVlue) {
        int newRate = Integer.parseInt("" + tagVlue);
        for (int jj = 0; jj < linearLayout.getChildCount(); jj++) {
            ImageView imageView = (ImageView) linearLayout.getChildAt(jj);
            if (jj <= newRate) {
                if(starIcon_active != null){
                    imageView.setImageDrawable(starIcon_active);
                }else{
                    imageView.setImageResource(R.drawable.ic_rate_icon);
                    imageView.setColorFilter(ContextCompat.getColor(context, R.color.trans_color), android.graphics.PorterDuff.Mode.SRC_IN);
                }
            } else {
                if(starIcon_notActive != null){
                    imageView.setImageDrawable(starIcon_notActive);
                }else{
                    imageView.setImageResource(R.drawable.ic_rate_icon);
                    imageView.setColorFilter(ContextCompat.getColor(context, R.color.trans_color2),
                            android.graphics.PorterDuff.Mode.SRC_IN);
                }

            }
        }
    }

    public int getNumberStars() {
        return numberStars;
    }

    public void setNumberStars(int numberStars) {
        this.numberStars = numberStars;
        setRatingContent();
    }

    public Object getRateValue() {
        return rateValue;
    }

    private void setRateValue(Object rateValue) {
        this.rateValue = rateValue;
    }
}
