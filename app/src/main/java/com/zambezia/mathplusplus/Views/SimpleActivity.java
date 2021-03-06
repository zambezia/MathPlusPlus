package com.zambezia.mathplusplus.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zambezia.mathplusplus.CalculatorType;
import com.zambezia.mathplusplus.Defs.CalculatorConstants;
import com.zambezia.mathplusplus.Controllers.Calculator;
import com.zambezia.mathplusplus.R;
import com.zambezia.mathplusplus.helper.TextResizeHelper;
import com.zambezia.mathplusplus.singleton.PreferenceHelperSingleton;

/**
 * Main view of the app, hosts the calculator button commands and allow user to interact with Math
 * engine
 * @author Abdul.Basir
 * @since 4/19/2013
 *
 */
public class SimpleActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, IView {

    private AdView mAdView;
    private DrawerLayout drawer;
    private Button one, two, three, four, five, six, seven, eight, nine, zero, plus, minus, div, mul, equal, leftparentheses, rightparentheses, ac,
            point, backSpace, delete;

    private TextView input, output, modeview, trigonoModeView;
    private HorizontalScrollView horizontalScrollView;
    private Calculator calculator;

    String[] expression = { CalculatorConstants.POWER_e_n, "30" };

    private View.OnClickListener handler1_OnClickListener = new View.OnClickListener(){

        @Override
        public void onClick(View arg0)
        {
            Button b = (Button) arg0;
            String func = getCommand(b);
            calculator.commandHanlder(func);
        }
    };

    @Override
    public void onWindowFocusChanged (boolean hasFocus)
    {
        if(hasFocus) {
            TextResizeHelper textResizeHelper = new TextResizeHelper();
            LinearLayout layout = findViewById(R.id.primaryOperationsTableLayout);
            float textSize = PreferenceHelperSingleton.getInstance().getSimpleCalculatorTextSize();
            if (textSize == -1) {
                float scaledDensity = getResources().getDisplayMetrics().scaledDensity;
                textSize = textResizeHelper.getCorrectedButtonTextSize(layout, scaledDensity, 0.3F);
                PreferenceHelperSingleton.getInstance().setSimpleCalculatorTextSize(textSize);
            }
            textResizeHelper.setCorrectedButtonTextSize(textSize, layout);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //Calculator instance and UI code hooking
        init();
        SetListeners();
        calculator = new Calculator(this);

        //Setting up toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_simple);
        setSupportActionBar(toolbar);

        //Setting up DrawerLayout and NavigationView
        drawer = findViewById(R.id.drawer_layout_simple);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_smart_scientific_calculator)
        {
            Intent intent = new Intent(this,SmartScientificActivity.class);
            startActivity(intent);
            PreferenceHelperSingleton.getInstance().setCurrentCalculatorType(CalculatorType.SMARTSCIENTIFIC);
            finish();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void init()
    {

        one = findViewById(R.id.buttonOne);
        two = findViewById(R.id.buttontwo);
        three = findViewById(R.id.buttonThree);
        four = findViewById(R.id.buttonFour);
        five = findViewById(R.id.buttonFive);
        six = findViewById(R.id.buttonSix);
        seven = findViewById(R.id.buttonSeven);
        eight = findViewById(R.id.buttonEight);
        nine = findViewById(R.id.buttonNine);
        zero = findViewById(R.id.buttonZero);
        point = findViewById(R.id.buttonpoint);
        plus = findViewById(R.id.buttonPlus);
        minus = findViewById(R.id.buttonMinus);
        mul = findViewById(R.id.buttonMul);
        div = findViewById(R.id.buttonDiv);
        equal = findViewById(R.id.buttonEqual);
        leftparentheses = findViewById(R.id.buttonLeftparentheses);
        rightparentheses = findViewById(R.id.buttonRightparentheses);
        ac = findViewById(R.id.buttonClear);

        input = findViewById(R.id.modeTextView);
        modeview = findViewById(R.id.statusMemoryView);
        output = findViewById(R.id.consoleTextView);
        horizontalScrollView = findViewById(R.id.horizontalScrollView);
        backSpace = findViewById(R.id.buttonBackSpace);
        delete = findViewById(R.id.buttonDelete);
    }

    public void SetListeners()
    {
        one.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                calculator.appendMath(CalculatorConstants.ONE);
                calculator.addToExpression(CalculatorConstants.ONE);
            }

        });

        zero.setOnClickListener(handler1_OnClickListener);
        one.setOnClickListener(handler1_OnClickListener);
        two.setOnClickListener(handler1_OnClickListener);
        three.setOnClickListener(handler1_OnClickListener);
        four.setOnClickListener(handler1_OnClickListener);
        five.setOnClickListener(handler1_OnClickListener);
        six.setOnClickListener(handler1_OnClickListener);
        seven.setOnClickListener(handler1_OnClickListener);
        eight.setOnClickListener(handler1_OnClickListener);
        nine.setOnClickListener(handler1_OnClickListener);
        plus.setOnClickListener(handler1_OnClickListener);
        minus.setOnClickListener(handler1_OnClickListener);
        mul.setOnClickListener(handler1_OnClickListener);
        div.setOnClickListener(handler1_OnClickListener);
        leftparentheses.setOnClickListener(handler1_OnClickListener);
        rightparentheses.setOnClickListener(handler1_OnClickListener);
        backSpace.setOnClickListener(handler1_OnClickListener);
        delete.setOnClickListener(handler1_OnClickListener);
        point.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                calculator.addToExpression(".");
            }

        });

        equal.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                calculator.solveExpression("");
            }

        });

        ac.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View arg0)
            {
                calculator.newCalculationWithResetDisplayOutput();
            }

        });

    }

    private String getCommand(Button b)
    {
        String func = "";

        if (b.getTag() != null)//Tag could be null for some buttons
            func = b.getTag().toString();

        if (func.equals(""))
            func = b.getText().toString();

        return func;
    }

    @Override
    public void displayExpression(String expression) {
        input.setText(expression);
        horizontalScrollView.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
    }

    @Override
    public void displayOutput(String result) {
        this.output.setText(result);
    }

    @Override
    public String getDisplayOutputString() {
        return this.output.getText().toString();
    }

    @Override
    public double getDisplayOutputDouble() {

        double val;
        try{
            val = Double.parseDouble(getDisplayOutputString());
        }
        catch(Exception exp)
        {
            val=0.0;
        }
        return val;
    }
    @Override
    public void displayModeStatus(String status) {
        this.modeview.setText(status);
    }

    @Override
    public void displayTrigonometricMode(String mode) {
        this.trigonoModeView.setText(mode);
    }
}
