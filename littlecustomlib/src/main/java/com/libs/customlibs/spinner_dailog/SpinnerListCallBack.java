package com.libs.customlibs.spinner_dailog;

public interface SpinnerListCallBack {
    interface AdapterCallBack {
        /*
        * @params postion - it's selected postion for item insie list of objects
        * @params customSpinnerModel it's selected row model
        * */
        void chooseCustomCountry(int postion,CustomSpinnerModel customSpinnerModel);
    }

    interface PopUpCallBack {
        /*
         * @params postion - it's selected postion for item insie list of objects
         * @params customSpinnerModel it's selected row model
         * */
        void popUp_ChooseCustomCountry(int postion,CustomSpinnerModel customSpinnerModel);
    }
}
