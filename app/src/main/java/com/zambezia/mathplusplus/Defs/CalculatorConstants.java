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
	public static final String PLUS = "+";
	public static final String MINUS = "-";
	public static final String MULTIPLY = "*";
	public static final String DIVIDE = "÷";
	public static final String NEGATION = "±";
	public static final String MOD = "Mod";
	public static final String LEFT_PARAN = "(";
	public static final String RIGHT_PARAN = ")";
	public static final String SIN = "sin";
	public static final String COS = "cos";
	public static final String TAN = "tan";
	public static final String SINI = "sin⁻¹";
	public static final String COSI = "cos⁻¹";
	public static final String TANI = "tan⁻¹";
	public static final String HYP = "hyp";
	public static final String HYPI = "hyp⁻¹";
	public static final String SINH = "sinh";
	public static final String COSH = "cosj";
	public static final String TANH = "tanh";
	public static final String SINHI = "sinh⁻¹";
	public static final String COSHI = "cosh⁻¹";
	public static final String TANHI = "tan⁻¹";
	public static final String FACTORIAL = GetActualOperator("n!");
	
	public static final String SQUARE = GetActualOperator("x²");
	public static final String CUBE = GetActualOperator("x³");
	public static final String QUAD = GetActualOperator("x⁴");
	public static final String SQUARE_ROOT = "²√";
	public static final String CUBE_ROOT = "³√";
	public static final String QUAD_ROOT = "⁴√";
	public static final String N_ROOT = GetActualOperator("ⁿ√x");
	public static final String N_POWER = GetActualOperator("xⁿ");
	public static final String EXP = "e";
	public static final String PI = "π";
	public static final String LOG = "log";
	public static final String NATURAL_LOG = "ln";
	public static final String POWER_10 = "10ⁿ";
	public static final String POWER_e_n = "eⁿ";
	public static final String POWER_e_1  = "e¹";
	public static final String NCR = "C";
	public static final String NPR = "P";
	public static final String INV =  "x⁻¹";
	public static final String ABS = "abs";
	public static final String AND = "and";
	public static final String OR = "or";
	public static final String XOR = "xor";
	public static final String NOT = "not";
	public static final String BACKSPACE = "⌫";
	public static final String DELETE = "⌦";
	
	public static String[] OPERATORS = {PLUS, MINUS, MULTIPLY, DIVIDE, NEGATION, MOD, LEFT_PARAN,
		RIGHT_PARAN, SINHI, COSHI, TANHI, SINH, COSH, TANH, SINI, COSI, TANI, SIN, COS, TAN, FACTORIAL,
		SQUARE, CUBE, QUAD, SQUARE_ROOT, CUBE_ROOT, QUAD_ROOT, N_ROOT, N_POWER, EXP, LOG, NATURAL_LOG,
		POWER_10, POWER_e_n, NCR, NPR, AND, OR, XOR, NOT, ABS, INV};
	
	
	//Digit Constants
	public static final String ONE =  "1";
	public static final String TWO = "2";
	public static final String THREE = "3";
	public static final String FOUR = "4";
	public static final String FIVE = "5";
	public static final String SIX = "6";
	public static final String SEVEN = "7";
	public static final String EIGHT = "8";
	public static final String NINE = "9";
	public static final String ZERO = "0";
	public static final String POINT = ".";
	
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
