package com.zambezia.mathplusplus.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.text.method.ScrollingMovementMethod;
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

import com.zambezia.mathplusplus.CalculatorType;
import com.zambezia.mathplusplus.Defs.CalculatorConstants;
import com.zambezia.mathplusplus.Controllers.Calculator;
import com.zambezia.mathplusplus.R;
import com.zambezia.mathplusplus.singleton.PreferenceHelperSingleton;

/**
 * Main view of the app, hosts the calculator button commands and allow user to interact with Math
 * engine
 * @author Abdul.Basir
 * @since 4/19/2013
 *
 */
public class ScientificActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, IView {

    private DrawerLayout drawer;
    private Button one, two, three, four, five, six, seven, eight, nine, zero, plus, minus, div, mul, equal, leftparentheses, rightparentheses, square, cube, squareRoot, ac,
            point, sin, cos, tan, log, ln, secondfun, alpha, hyp, pow, negation, exp;
    private Button trigMode;
    private Button store;
    private Button recall;
    private Button memory;

    private TextView input, output, modeview, trigonoModeView;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scientific);
        //Calculator instance and UI code hooking
        init();
        SetListeners();
        calculator = new Calculator(this);

        //Setting up toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_scientific);
        setSupportActionBar(toolbar);

//        //Setting up FloatingActionButton
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //TODO: Implement CLICK functionality
//            }
//        });

        //Setting up DrawerLayout and NavigationView
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout_scientific);

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

        if (id == R.id.nav_simple_calculator) {
            Intent intent = new Intent(this,SimpleActivity.class);
            startActivity(intent);
            PreferenceHelperSingleton.getInstance().setCurrentCalculatorType(CalculatorType.SIMPLE);
            finish();
        }
        else if (id == R.id.nav_smart_scientific_calculator) {
            Intent intent = new Intent(this,SmartScientificActivity.class);
            startActivity(intent);
            PreferenceHelperSingleton.getInstance().setCurrentCalculatorType(CalculatorType.SMARTSCIENTIFIC);
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
        negation = (Button) findViewById(R.id.buttonNegation);
        equal = (Button) findViewById(R.id.buttonEqual);
        leftparentheses = (Button) findViewById(R.id.buttonLeftparentheses);
        rightparentheses = (Button) findViewById(R.id.buttonRightparentheses);
        square = (Button) findViewById(R.id.buttonSquare);
        squareRoot = (Button) findViewById(R.id.buttonSquareRoot);
        cube = (Button) findViewById(R.id.buttonCube);
        ac = (Button) findViewById(R.id.buttonClear);
        sin = (Button) findViewById(R.id.buttonSin);
        cos = (Button) findViewById(R.id.buttonCos);
        tan = (Button) findViewById(R.id.buttontan);
        sin = (Button) findViewById(R.id.buttonSin);
        log = (Button) findViewById(R.id.buttonLog);
        ln = (Button) findViewById(R.id.buttonLn);
        secondfun = (Button) findViewById(R.id.buttonSecondfun);
        alpha = (Button) findViewById(R.id.buttonAlpha);
        hyp = (Button) findViewById(R.id.buttonHp);
        pow = (Button) findViewById(R.id.buttonPow);
        exp = (Button) findViewById(R.id.buttonEXP);
        store = (Button) findViewById(R.id.buttonStore);
        recall = (Button) findViewById(R.id.buttonRecall);
        memory = (Button) findViewById(R.id.buttonMemory);
        trigMode = (Button) findViewById(R.id.buttonTrigMode);

        input = (TextView) findViewById(R.id.modeTextView);
        output = (TextView) findViewById(R.id.consoleTextView);
        modeview = (TextView) findViewById(R.id.statusMemoryView);
        trigonoModeView = (TextView)findViewById(R.id.trigonoModeView);
        input.setMovementMethod(new ScrollingMovementMethod());

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
        negation.setOnClickListener(handler1_OnClickListener);
        leftparentheses.setOnClickListener(handler1_OnClickListener);
        rightparentheses.setOnClickListener(handler1_OnClickListener);
        pow.setOnClickListener(handler1_OnClickListener );
        squareRoot.setOnClickListener(handler1_OnClickListener);
        square.setOnClickListener(handler1_OnClickListener);
        cube.setOnClickListener(handler1_OnClickListener);
        log.setOnClickListener(handler1_OnClickListener);
        ln.setOnClickListener(handler1_OnClickListener);
        sin.setOnClickListener(handler1_OnClickListener);
        cos.setOnClickListener(handler1_OnClickListener);
        tan.setOnClickListener(handler1_OnClickListener);
        exp.setOnClickListener(handler1_OnClickListener);

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

        secondfun.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View arg0)
            {
                calculator.setInputMode(Calculator.InputMode.SHIFT);
                calculator.getModeviewText();
            }
        });

        alpha.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View arg0)
            {
                calculator.setInputMode(Calculator.InputMode.ALPHA);
            }
        });

        hyp.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View arg0)
            {
                calculator.setHypEnabled(true);
            }

        });

        recall.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (calculator.getMemoryInputMode() == Calculator.MemoryInputMode.RECALL ||
                        calculator.getMemoryInputMode() == Calculator.MemoryInputMode.STORE)
                {
                    calculator.commandHanlder(CalculatorConstants.RCL);
                    calculator.clearMemoryInputMode();
                    return;
                }
                calculator.setMemoryInputMode(Calculator.MemoryInputMode.RECALL);
            }
        });

        store.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (calculator.getMemoryInputMode() == Calculator.MemoryInputMode.RECALL ||
                        calculator.getMemoryInputMode() == Calculator.MemoryInputMode.STORE)
                {
                    calculator.commandHanlder(CalculatorConstants.STO);
                    calculator.clearMemoryInputMode();
                    return;
                }
                calculator.setMemoryInputMode(Calculator.MemoryInputMode.STORE);
            }
        });

        memory.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                calculator.commandHanlder(CalculatorConstants.MEM_ADD);
                calculator.clearMemoryInputMode();
                return;
            }
        });

        trigMode.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0)
            {
                calculator.toggleTrigonoMode();
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
