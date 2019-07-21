package com.libs.customlibs.spinner_dailog;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.libs.customlibs.CustomFontsUtilits;
import com.libs.customlibs.R;
import com.libs.customlibs.UiUtil;

import java.util.List;

public class SpinnerListAdapter extends RecyclerView.Adapter<SpinnerListAdapter.ViewHolder> {
    private List<CustomSpinnerModel> list;
    private Context context;
    private boolean show_img;
    private SpinnerListCallBack.AdapterCallBack adapterCallBack;
    private String fontType;

    // Provide a suitable constructor (depends on the kind of dataset)
    SpinnerListAdapter(Context context,
                       List<CustomSpinnerModel> list, boolean show_img, String fontType, SpinnerListCallBack.AdapterCallBack adapterCallBack) {
        this.list = list;
        this.context = context;
        this.show_img = show_img;
        this.fontType = fontType;
        this.adapterCallBack = adapterCallBack;

    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public SpinnerListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                            int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.spinner_row_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final SpinnerListAdapter.ViewHolder holder, final int position) {
        final CustomSpinnerModel resposnse = list.get(position);

        holder.country_txt.setText(resposnse.getSpinnerText());

        holder.spinner_Content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterCallBack.chooseCustomCountry(position, resposnse);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        LinearLayout spinner_Content;
        TextView country_txt;
        ImageView spinnerImg;


        ViewHolder(View v) {
            super(v);
            spinner_Content = v.findViewById(R.id.spinner_Content);
            country_txt = v.findViewById(R.id.spinnerTxt);
            spinnerImg = v.findViewById(R.id.spinnerImg);
            if (show_img) {
                spinnerImg.setVisibility(View.VISIBLE);
            } else {
                spinnerImg.setVisibility(View.GONE);
            }
            if (CustomFontsUtilits.vaildFontType(fontType)) {
                country_txt.setTypeface(UiUtil.getFont(context,fontType));
            }

        }
    }


}
