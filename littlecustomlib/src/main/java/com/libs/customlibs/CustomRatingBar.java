package com.libs.customlibs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

public class CustomRatingBar extends LinearLayout {
    private Object rateValue = "0";

    public CustomRatingBar(Context context) {
        super(context);
    }

    public CustomRatingBar(final Context context, @androidx.annotation.Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.rating_bar_content, this);

        final ImageView imageView = findViewById(R.id.image);
        TextView textView = findViewById(R.id.caption);
        final LinearLayout ratingContainer = findViewById(R.id.ratingContainer);

        @SuppressLint("CustomViewStyleable") TypedArray attributes = context.obtainStyledAttributes(attrs,
                R.styleable.SmartNumericTextView);

        int numberStars = attributes.getInteger(0, R.styleable.SmartNumericTextView_num_stars);

        double rateSendedTo= Double.parseDouble(attributes.getString(R.styleable.SmartNumericTextView_valueRate));
        textView.setText(attributes.getString(R.styleable.SmartNumericTextView_text) + " : " +
                numberStars + " : " + rateSendedTo);
        for (int i = 0; i < numberStars; i++) {
            final ImageView imageView_STar = new ImageView(context);
            ViewGroup.LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
            ((LayoutParams) layoutParams).leftMargin = 5;
            ((LayoutParams) layoutParams).rightMargin = 5;
            imageView_STar.setLayoutParams(layoutParams);

            imageView_STar.setImageResource(R.drawable.ic_rate_icon);
            if (i < rateSendedTo) {
                imageView_STar.setColorFilter(ContextCompat.getColor(context, R.color.trans_color), android.graphics.PorterDuff.Mode.SRC_IN);
            } else {
                imageView_STar.setColorFilter(ContextCompat.getColor(context, R.color.trans_color2), android.graphics.PorterDuff.Mode.SRC_IN);
            }

            int tagValue = i;
            imageView_STar.setTag("" + (tagValue++));
            imageView_STar.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    setRateValue(imageView_STar.getTag());
                    setRateIconsList(context, ratingContainer, imageView_STar.getTag());
                    //Toast.makeText(context, "tag value : " + imageView_STar.getTag(), Toast.LENGTH_SHORT).show();
                }
            });
            ratingContainer.addView(imageView_STar);

        }
        attributes.recycle();
    }

    private void setRateIconsList(Context context, LinearLayout linearLayout, Object tagVlue) {
        int newRate = Integer.parseInt("" + tagVlue);
        for (int jj = 0; jj < linearLayout.getChildCount(); jj++) {
            ImageView imageView = (ImageView) linearLayout.getChildAt(jj);
            if (jj <= newRate) {
                imageView.setColorFilter(ContextCompat.getColor(context, R.color.trans_color), android.graphics.PorterDuff.Mode.SRC_IN);
            } else {
                imageView.setColorFilter(ContextCompat.getColor(context, R.color.trans_color2), android.graphics.PorterDuff.Mode.SRC_IN);
            }
        }
    }

    public Object getRateValue() {
        return rateValue;
    }

    private void setRateValue(Object rateValue) {
        this.rateValue = rateValue;
    }
}
