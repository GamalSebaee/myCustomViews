package com.libs.customlibs.spinner_dailog;

public interface SpinnerListCallBack {
    interface AdapterCallBack {
        void chooseCustomCountry(int postion,CustomSpinnerModel customSpinnerModel);
    }

    interface PopUpCallBack {
        void popUp_ChooseCustomCountry(int postion,CustomSpinnerModel customSpinnerModel);
    }
}
