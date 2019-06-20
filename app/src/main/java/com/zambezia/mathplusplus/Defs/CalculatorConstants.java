package com.zambezia.mathplusplus.Defs;

import com.zambezia.mathplusplus.App;
import com.zambezia.mathplusplus.R;

import java.util.HashMap;
import java.util.Map;

/**
 * String literal to display on the View
 * @author Abdul.Basir
 * @since 4/19/2013
 *
 */
public class CalculatorConstants {

    private static Map<String,String> mapOperatorDisplayToActual = new HashMap<String, String>(){{
        put("n!","!");
        put("x²","²");
        put("x³","³");
        put("x⁴","⁴");
        put("xⁿ","ⁿ");
        put("ⁿ√x","√");
    }};

    private static Map<String,String> mapOperatorActualToDisplay = new HashMap<String, String>(){{
        put("!","n!");
        put("²","x²");
        put("³","x³");
        put("⁴","x⁴");
        put("ⁿ","xⁿ");
        put("√","ⁿ√x");
    }};

    public static boolean IsUnaryOperatorOnRight(String actualSymbol)
    {
        boolean isOpRight= false;
        if(mapOperatorActualToDisplay.containsKey(actualSymbol))
            isOpRight = true;
        return isOpRight;
    }
	public static String GetActualOperator(String displaySymbol)
	{
        if(mapOperatorDisplayToActual.containsKey(displaySymbol))
            displaySymbol = mapOperatorDisplayToActual.get(displaySymbol);
		return displaySymbol;
	}
	//Operator Constatnts
	public static final String PLUS = App.R().getString(R.string.symbol_plus);
	public static final String MINUS = App.R().getString(R.string.symbol_minus);
	public static final String MULTIPLY = App.R().getString(R.string.symbol_multiply);
	public static final String DIVIDE = App.R().getString(R.string.symbol_divide);
	public static final String NEGATION = App.R().getString(R.string.symbol_negation);
	public static final String MOD = "Mod";
	public static final String LEFT_PARAN = "(";
	public static final String RIGHT_PARAN = ")";
	public static final String SIN = App.R().getString(R.string.symbol_sin);
	public static final String COS = App.R().getString(R.string.symbol_cos);
	public static final String TAN = App.R().getString(R.string.symbol_tan);
	public static final String SINI = App.R().getString(R.string.symbol_sin_inv);
	public static final String COSI = App.R().getString(R.string.symbol_cos_inv);
	public static final String TANI = App.R().getString(R.string.symbol_tan_inv);
	public static final String HYP = "hyp";
	public static final String HYPI = "hyp⁻¹";
	public static final String SINH = App.R().getString(R.string.symbol_sinh);
	public static final String COSH = App.R().getString(R.string.symbol_cosh);
	public static final String TANH = App.R().getString(R.string.symbol_tanh);
	public static final String SINHI = App.R().getString(R.string.symbol_sinh_inv);
	public static final String COSHI = App.R().getString(R.string.symbol_cosh_inv);
	public static final String TANHI = App.R().getString(R.string.symbol_tanh_inv);
	public static final String FACTORIAL = GetActualOperator(App.R().getString(R.string.symbol_factorial));
	
	public static final String SQUARE = GetActualOperator(App.R().getString(R.string.symbol_square));
	public static final String CUBE = GetActualOperator(App.R().getString(R.string.symbol_cube));
	public static final String QUAD = GetActualOperator(App.R().getString(R.string.symbol_quad));
	public static final String SQUARE_ROOT = App.R().getString(R.string.symbol_square_root);
	public static final String CUBE_ROOT = App.R().getString(R.string.symbol_cube_root);
	public static final String QUAD_ROOT = App.R().getString(R.string.symbol_quad_root);
	public static final String N_ROOT = GetActualOperator(App.R().getString(R.string.symbol_n_root));
	public static final String N_POWER = GetActualOperator(App.R().getString(R.string.symbol_pow_n));
	public static final String EXP = "e";
	public static final String PI = App.R().getString(R.string.symbol_pi);
	public static final String LOG = App.R().getString(R.string.symbol_log);
	public static final String NATURAL_LOG = App.R().getString(R.string.symbol_ln);
	public static final String POWER_10 = App.R().getString(R.string.symbol_pow_10);
	public static final String POWER_e_n = App.R().getString(R.string.symbol_e_n);
	public static final String POWER_e_1  =  App.R().getString(R.string.symbol_e_1);
	public static final String NCR = "C";
	public static final String NPR = "P";
	public static final String INV =  App.R().getString(R.string.symbol_inv);
	public static final String ABS = "abs";
	public static final String AND = "and";
	public static final String OR = "or";
	public static final String XOR = "xor";
	public static final String NOT = "not";
	public static final String BACKSPACE = App.R().getString(R.string.symbol_back_space);
	public static final String DELETE = App.R().getString(R.string.symbol_delete);
	
	public static String[] OPERATORS = {PLUS, MINUS, MULTIPLY, DIVIDE, NEGATION, MOD, LEFT_PARAN,
		RIGHT_PARAN, SINHI, COSHI, TANHI, SINH, COSH, TANH, SINI, COSI, TANI, SIN, COS, TAN, FACTORIAL,
		SQUARE, CUBE, QUAD, SQUARE_ROOT, CUBE_ROOT, QUAD_ROOT, N_ROOT, N_POWER, EXP, LOG, NATURAL_LOG,
		POWER_10, POWER_e_n, NCR, NPR, AND, OR, XOR, NOT, ABS, INV};
	
	
	//Digit Constants
	public static final String ONE =  App.R().getString(R.string.symbol_1);
	public static final String TWO = App.R().getString(R.string.symbol_2);
	public static final String THREE = App.R().getString(R.string.symbol_3);
	public static final String FOUR = App.R().getString(R.string.symbol_4);
	public static final String FIVE = App.R().getString(R.string.symbol_5);
	public static final String SIX = App.R().getString(R.string.symbol_6);
	public static final String SEVEN = App.R().getString(R.string.symbol_7);
	public static final String EIGHT = App.R().getString(R.string.symbol_8);
	public static final String NINE =App.R().getString(R.string.symbol_9);
	public static final String ZERO = App.R().getString(R.string.symbol_0);
	public static final String POINT = App.R().getString(R.string.symbol_point);
	
	//All these constants for functions does not get displayed in
	//Mathematical expression.
	
	//Mode constats
	public static final String SECOND_FUN = "2ndF";
	public static final String ALPHA = "Alpha";
	
	//Memory functions constants
	public static final String RCL = "RCL";
	public static final String STO = "STO"; 
	public static final String MEM_ADD = "M+";		
	public static final String MEM_SUB = "M-";
	public static final String MEM = "M";
}
