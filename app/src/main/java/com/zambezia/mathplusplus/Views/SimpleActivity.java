package com.zambezia.mathplusplus.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.zambezia.mathplusplus.Defs.CalculatorConstants;
import com.zambezia.mathplusplus.Controllers.Calculator;
import com.zambezia.mathplusplus.Enums;
import com.zambezia.mathplusplus.R;
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

    private DrawerLayout drawer;
    private Button one, two, three, four, five, six, seven, eight, nine, zero, plus, minus, div, mul, equal, leftparentheses, rightparentheses, ac,
            point;

    private TextView input, output, modeview, trigonoModeView;
    private Calculator calculator;

    String[] expression = { CalculatorConstants.POWER_e, "30" };

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        //Calculator instance and UI code hooking
        init();
        SetListeners();
        calculator = new Calculator(this);

        //Setting up toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_simple);
        setSupportActionBar(toolbar);

        //Setting up FloatingActionButton
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Implement CLICK functionality
            }
        });

        //Setting up DrawerLayout and NavigationView
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout_simple);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
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
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_scientific_calculator){
            Intent intent = new Intent(this,ScientificActivity.class);
            startActivity(intent);
            PreferenceHelperSingleton.getInstance().setCurrentCalculatorType(Enums.CURRENT_CALCULATOR_TYPE.scientific);
            finish();
        }
        else if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void init()
    {

        one = (Button) findViewById(R.id.buttonOne);
        two = (Button) findViewById(R.id.buttontwo);
        three = (Button) findViewById(R.id.buttonThree);
        four = (Button) findViewById(R.id.buttonFour);
        five = (Button) findViewById(R.id.buttonFive);
        six = (Button) findViewById(R.id.buttonSix);
        seven = (Button) findViewById(R.id.buttonSeven);
        eight = (Button) findViewById(R.id.buttonEight);
        nine = (Button) findViewById(R.id.buttonNine);
        zero = (Button) findViewById(R.id.buttonZero);
        point = (Button) findViewById(R.id.buttonpoint);
        plus = (Button) findViewById(R.id.buttonPlus);
        minus = (Button) findViewById(R.id.buttonMinus);
        mul = (Button) findViewById(R.id.buttonMul);
        div = (Button) findViewById(R.id.buttonDiv);
        equal = (Button) findViewById(R.id.buttonEqual);
        leftparentheses = (Button) findViewById(R.id.buttonLeftparentheses);
        rightparentheses = (Button) findViewById(R.id.buttonRightparentheses);
        ac = (Button) findViewById(R.id.buttonClear);

        input = (TextView) findViewById(R.id.modeTextView);
        modeview = (TextView) findViewById(R.id.statusMemoryView);
        output = (TextView) findViewById(R.id.consoleTextView);

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

        if (func == null || func == "")
            func = b.getText().toString();

        return func;
    }

    @Override
    public void displayExpression(String expression) {
        input.setText(expression);
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

        double val=0.0;
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
