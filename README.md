# myCustomViews
<h1>small lib for custom views</h1>
<p>this is first version </p>
this is libaray used to set rating bar and spinner 
by using this libaray you don't need to set style for rating bar and set custom spinner 
if you need to use custom spinner with custom fontface and with search dailog you need only to implement funcation and set parameter 

to use it 
first add lib to your project 
current tag is v1.0.2
add this inside build.gradle in project level

<h3>   
     repositories {
        maven { url 'https://jitpack.io' }
    }
</h3>
and inside build.gradle into app add this 
<h3>    implementation 'com.github.GamalSebaee:myCustomViews:tag'</h3>

/* to use ratingBar

     <com.libs.customlibs.CustomRatingBar
        android:id="@+id/customRateBar"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="10dp"
        app:num_stars="5"
        app:starSize="50dp"
        android:layout_gravity="center_horizontal"
        app:valueRate="3"/>

        num_stars : number of stars shown in rating bar 
        valueRate : rate value
        starSize : size of star icon defalut is 32 dp
        starIcon_active : this is to set star icon in case of active // opational
        starIcon_notActive : this is to set star icon in case of not active // opational
        
        
        if you need to set rating number from code 
      -  use this 
        customRateBar.setNumberStars(10);
       - to get rating use this 
         customRateBar.getRateValue() 

       <h3>to use spinner</h3>
      set this to xml file
    <com.libs.customlibs.CustomSpinnerView
        android:id="@+id/spinnerList"
        app:setImage="false"
        app:hideText="false"
        android:paddingLeft="5dp"
        android:layout_marginLeft="15dp"
        android:layout_marginRight="15dp"
        android:paddingRight="5dp"
        android:paddingTop="10dp"
        android:paddingBottom="10dp"
        app:textColor="#fff"
        app:setArrowColor="#fff"
        app:text="hello world"
        android:background="@color/colorPrimary"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />
        /// avery row in spinner have 3 views 
          image ,text and indecator icon 
        app:setImage="false"  // if you need your row not have image set this parameter to false or not set it  defalut false
        app:hideText="false" // if you need your row not have text set this parameter to true defalut value false
        app:textColor="#fff" // to set spinner text color defalut black with #000
        app:setArrowColor="#fff" // this to indecator color
        app:setArrowIcon="referance" // this is to set indecator image defalut is arrow down
        app:defalutText="text" //this for hint text shown when no items clicked
        
        // in your activity or fragment
        CustomSpinnerView  spinnerList=findViewById(R.id.spinnerList); //init view 
        spinnerList.setAllDataList(allDataList); // set data 
        List<CustomSpinnerModel> allDataList=new ArrayList<>();
          allDataList // it is list of   
          <h3>and the last step</h3>  
          make your activity of fragment implement (implements SpinnerListCallBack.PopUpCallBack )
          nd override method */
                     
                     
        
        
        



        

