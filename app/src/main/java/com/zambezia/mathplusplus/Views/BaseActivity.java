package com.zambezia.mathplusplus.Views;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.zambezia.mathplusplus.CalculatorType;
import com.zambezia.mathplusplus.R;
import com.zambezia.mathplusplus.singleton.PreferenceHelperSingleton;


public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent;
        if (PreferenceHelperSingleton.getInstance().getCurrentCalculatorType() == CalculatorType.SMARTSCIENTIFIC) {
            intent = new Intent(this,SmartScientificActivity.class); 
        }
        else {
            intent = new Intent(this,SimpleActivity.class);
        }
        startActivity(intent);
        finish();  
        super.onCreate(savedInstanceState);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        //setContentView(R.layout.activity_base);
    }
}
