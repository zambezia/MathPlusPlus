package com.zambezia.mathplusplus.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

import com.zambezia.mathplusplus.App;
import com.zambezia.mathplusplus.CalculatorType;
import com.zambezia.mathplusplus.R;
import com.zambezia.mathplusplus.helper.PreferenceHelper;
import com.zambezia.mathplusplus.singleton.PreferenceHelperSingleton;


public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent;
        if (PreferenceHelperSingleton.getInstance().getCurrentCalculatorType() == CalculatorType.SCIENTIFIC) {
            intent = new Intent(this,ScientificActivity.class);
        }
        else if (PreferenceHelperSingleton.getInstance().getCurrentCalculatorType() == CalculatorType.SMARTSCIENTIFIC) {
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
