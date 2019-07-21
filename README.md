# myCustomViews
<h1>small lib for custom views</h1>
<p>this is first version </p>
this is libaray used to set rating bar and spinner 
by using this libaray you don't need to set style for rating bar and set custom spinner 
if you need to use custom spinner with custom fontface and with search dailog you need only to implement funcation and set parameter 

to use it 
first add lib to your project 
current tag is v1.0.2
<h3>    implementation 'com.github.GamalSebaee:myCustomViews:tag'</h3>

/*to use ratingBar

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
        */
        
        if you need to set rating number from code 
use this 
        customRateBar.setNumberStars(10);
        
        to get rating use this 
                customRateBar.getRateValue()


        

