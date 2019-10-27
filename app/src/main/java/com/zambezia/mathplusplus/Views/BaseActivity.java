package com.zambezia.mathplusplus.Views;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.zambezia.mathplusplus.CalculatorType;
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
        //setContentView(R.layout.activity_base);
    }


//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.app_menu, menu);
//        return true;
//    }
}
