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
public class SmartScientificActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, IView {

    private AdView mAdView;
    private DrawerLayout drawer;
    private Button one, two, three, four, five, six, seven, eight, nine, zero, plus, minus, div, mul, equal,
            leftparentheses, rightparentheses,
            square, cube, squareRoot, cubeRoot, quadRoot, nRoot, ac, point, sin, cos, tan,  sinInv, cosInv, tanInv, sinH, cosH, tanH,  sinHInv, cosHInv, tanHInv,
            log, ln, pow, exp, exp1, inv, negation, fact, backSpace, delete;
    private Button trigMode, pi, _10ExpN;

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
            LinearLayout layout = findViewById(R.id.secondaryOperationsTableLayout);
            float textSizeSecondary = PreferenceHelperSingleton.getInstance().getSmartScientificCalculatorSecondaryTextSize();
            if (textSizeSecondary == -1) {
                float scaledDensity = getResources().getDisplayMetrics().scaledDensity;
                textSizeSecondary = textResizeHelper.getCorrectedButtonTextSize(layout, scaledDensity, 0.6F);
                PreferenceHelperSingleton.getInstance().setSmartScientificCalculatorSecondaryTextSize(textSizeSecondary);
            }
            textResizeHelper.setCorrectedButtonTextSize(textSizeSecondary, layout);

            layout = findViewById(R.id.primaryOperationsTableLayout);
            float textSizePrimary = PreferenceHelperSingleton.getInstance().getSmartScientificCalculatorPrimaryTextSize();
            if (textSizePrimary == -1) {
                float scaledDensity = getResources().getDisplayMetrics().scaledDensity;
                textSizePrimary = textResizeHelper.getCorrectedButtonTextSize(layout, scaledDensity, 0.30F);
                PreferenceHelperSingleton.getInstance().setSmartScientificCalculatorPrimaryTextSize(textSizePrimary);
            }
            textResizeHelper.setCorrectedButtonTextSize(textSizePrimary, layout);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_scientific);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //Calculator instance and UI code hooking
        init();
        SetListeners();
        calculator = new Calculator(this);

        //Setting up toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_smart_scientific);
        setSupportActionBar(toolbar);

        //Setting up DrawerLayout and NavigationView
        drawer = findViewById(R.id.drawer_layout_smart_scientific);

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

        if (id == R.id.nav_simple_calculator) {
            Intent intent = new Intent(this,SimpleActivity.class);
            startActivity(intent);
            PreferenceHelperSingleton.getInstance().setCurrentCalculatorType(CalculatorType.SIMPLE);
            finish();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void init()
    {

        one =  findViewById(R.id.buttonOne);
        two =  findViewById(R.id.buttontwo);
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
        negation = findViewById(R.id.buttonNegation);
        equal = findViewById(R.id.buttonEqual);
        leftparentheses = findViewById(R.id.buttonLeftparentheses);
        rightparentheses = findViewById(R.id.buttonRightparentheses);
        square = findViewById(R.id.buttonSquare);
        squareRoot = findViewById(R.id.buttonSquareRoot);
        cubeRoot = findViewById(R.id.buttonCubeRoot);
        quadRoot = findViewById(R.id.buttonQuadRoot);
        nRoot = findViewById(R.id.buttonNRoot);
        cube = findViewById(R.id.buttonCube);
        ac = findViewById(R.id.buttonClear);
        sin = findViewById(R.id.buttonSin);
        cos = findViewById(R.id.buttonCos);
        tan = findViewById(R.id.buttontan);
        sinInv = findViewById(R.id.buttonSinInv);
        cosInv = findViewById(R.id.buttonCosInv);
        tanInv = findViewById(R.id.buttonTanInv);
        sinH = findViewById(R.id.buttonSinH);
        cosH = findViewById(R.id.buttonCosH);
        tanH = findViewById(R.id.buttontanH);
        sinHInv = findViewById(R.id.buttonSinHInv);
        cosHInv = findViewById(R.id.buttonCosHInv);
        tanHInv = findViewById(R.id.buttonTanHInv);
        log = findViewById(R.id.buttonLog);
        ln = findViewById(R.id.buttonLn);
        pow = findViewById(R.id.buttonPow);
        inv = findViewById(R.id.buttonInv);
        trigMode = findViewById(R.id.buttonTrigMode);
        pi = findViewById(R.id.buttonPI);
        exp = findViewById(R.id.buttonExp);
        _10ExpN = findViewById(R.id.button_10ExpN);
        exp1 = findViewById(R.id.buttonExp1);
        fact = findViewById(R.id.buttonFact);
        backSpace = findViewById(R.id.buttonBackSpace);
        delete = findViewById(R.id.buttonDelete);
        input = findViewById(R.id.modeTextView);
        output = findViewById(R.id.consoleTextView);
        modeview = findViewById(R.id.statusMemoryView);
        trigonoModeView = findViewById(R.id.trigonoModeView);
        horizontalScrollView = findViewById(R.id.horizontalScrollView);
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
        inv.setOnClickListener(handler1_OnClickListener );
        squareRoot.setOnClickListener(handler1_OnClickListener);
        cubeRoot.setOnClickListener(handler1_OnClickListener);
        quadRoot.setOnClickListener(handler1_OnClickListener);
        nRoot.setOnClickListener(handler1_OnClickListener);
        square.setOnClickListener(handler1_OnClickListener);
        cube.setOnClickListener(handler1_OnClickListener);
        log.setOnClickListener(handler1_OnClickListener);
        ln.setOnClickListener(handler1_OnClickListener);
        sin.setOnClickListener(handler1_OnClickListener);
        cos.setOnClickListener(handler1_OnClickListener);
        tan.setOnClickListener(handler1_OnClickListener);
        sinInv.setOnClickListener(handler1_OnClickListener);
        cosInv.setOnClickListener(handler1_OnClickListener);
        tanInv.setOnClickListener(handler1_OnClickListener);
        sinH.setOnClickListener(handler1_OnClickListener);
        cosH.setOnClickListener(handler1_OnClickListener);
        tanH.setOnClickListener(handler1_OnClickListener);
        sinHInv.setOnClickListener(handler1_OnClickListener);
        cosHInv.setOnClickListener(handler1_OnClickListener);
        tanHInv.setOnClickListener(handler1_OnClickListener);
        pi.setOnClickListener(handler1_OnClickListener);
        exp.setOnClickListener(handler1_OnClickListener);
        exp1.setOnClickListener(handler1_OnClickListener);
        _10ExpN.setOnClickListener(handler1_OnClickListener);
        fact.setOnClickListener(handler1_OnClickListener);
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
