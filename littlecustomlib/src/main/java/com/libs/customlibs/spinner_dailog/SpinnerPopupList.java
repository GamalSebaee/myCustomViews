package com.libs.customlibs.spinner_dailog;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.libs.customlibs.R;

import java.util.ArrayList;
import java.util.List;

public class SpinnerPopupList extends DialogFragment implements SpinnerListCallBack.AdapterCallBack {
    private SpinnerListCallBack.PopUpCallBack popUpCallBack;
    private boolean show_img = false;
    private RecyclerView country_listrecyclerView;
    private String fontType;
    private EditText etSearchContent;
    private List<CustomSpinnerModel> allSpinnerDataList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogStyle);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_country_list_dialog, container, false);
        initPage(view);

        return view;
    }

    private void initPage(View view) {
        country_listrecyclerView = view.findViewById(R.id.country_list);
        etSearchContent = view.findViewById(R.id.etSearchContent);
        country_listrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        popUpCallBack = (SpinnerListCallBack.PopUpCallBack) getActivity();
        if (getArguments() != null) {
            String listDataStr = getArguments().getString("data_list");
            show_img = getArguments().getBoolean("show_img", false);
            fontType = getArguments().getString("fontType", null);
            TypeToken<List<CustomSpinnerModel>> dataList = new TypeToken<List<CustomSpinnerModel>>() {
            };
            Gson gson = new Gson();
            List<CustomSpinnerModel> countryList = gson.fromJson(listDataStr, dataList.getType());
            allSpinnerDataList.clear();
            if (countryList != null) {
                allSpinnerDataList.addAll(countryList);
            }
            setAllDataList(allSpinnerDataList);
        }

        setSearchListener();
    }

    private void setSearchListener() {
        etSearchContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                List<CustomSpinnerModel> allSearchData = new ArrayList<>();
                for (CustomSpinnerModel customSpinnerModel : allSpinnerDataList) {
                    if (customSpinnerModel != null && customSpinnerModel.getSpinnerText().toLowerCase().trim().startsWith(editable.toString().toLowerCase().trim())) {
                        allSearchData.add(customSpinnerModel);
                    }
                }
                setAllDataList(allSearchData);
            }
        });

    }

    private void setAllDataList(List<CustomSpinnerModel> countryList) {
        SpinnerListAdapter countryListAdapter = new SpinnerListAdapter(getActivity(), countryList,
                show_img, fontType, this);
        country_listrecyclerView.setAdapter(countryListAdapter);
    }


    @Override
    public void chooseCustomCountry(int postion, CustomSpinnerModel customSpinnerModel) {
        popUpCallBack.popUp_ChooseCustomCountry(postion, customSpinnerModel);
        dismiss();
    }
}
