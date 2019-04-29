package com.zambezia.mathplusplus.Utils;


import com.zambezia.mathplusplus.Defs.CalculatorConstants;

/**
 * Main calculation brain which performs all the arithmetics and mathematics
 * @author Abdul.Basir
 * @since 4/19/2013
 *
 */
public class CalculatorBrain {

	public enum Mode {
		DEGREES, RADIAN, GRADIAN
	}

	private int calcStatus;
	public static Mode mode = Mode.DEGREES;

	public CalculatorBrain() {

	}

	public static double performCalculation(String operation, double arg0) {

		CalcDebug.Debug("performCalculation(string, double)");

		double val = 0.0;
		if (operation.equalsIgnoreCase(CalculatorConstants.SIN)) {
			val = Math.sin(convert(arg0));
		} else if (operation.equalsIgnoreCase(CalculatorConstants.COS)) {
			val = Math.cos(convert(arg0));
		} else if (operation.equalsIgnoreCase(CalculatorConstants.TAN)) {
			val = Math.tan(convert(arg0));
		} else if (operation.equalsIgnoreCase(CalculatorConstants.SINI)) {
			val = convertBack(Math.asin(arg0));
		} else if (operation.equalsIgnoreCase(CalculatorConstants.COSI)) {
			val = convertBack(Math.acos(arg0));
		} else if (operation.equalsIgnoreCase(CalculatorConstants.TANI)) {
			val = convertBack(Math.atan(arg0));
		} else if (operation.equalsIgnoreCase(CalculatorConstants.SINH)) {
			val = Math.sinh(arg0);
		} else if (operation.equalsIgnoreCase(CalculatorConstants.COSH)) {
			val = Math.cosh(arg0);
		} else if (operation.equalsIgnoreCase(CalculatorConstants.TANH)) {
			val = Math.tanh(arg0);
		} else if (operation.equalsIgnoreCase(CalculatorConstants.SINHI)) {
			val = sinhi(arg0);
		} else if (operation.equalsIgnoreCase(CalculatorConstants.COSHI)) {
			val = coshi(arg0);
		} else if (operation.equalsIgnoreCase(CalculatorConstants.TANHI)) {
			val = tanhi(arg0);
		} else if (operation.equalsIgnoreCase(CalculatorConstants.SQUARE)) {
			val = Math.pow(arg0, 2.0);
		} else if (operation.equalsIgnoreCase(CalculatorConstants.CUBE)) {
			val = Math.pow(arg0, 3.0);
		} else if (operation.equalsIgnoreCase(CalculatorConstants.QUAD)) {
			val = Math.pow(arg0, 4.0);
		} else if (operation.equalsIgnoreCase(CalculatorConstants.SQUARE_ROOT)) {
			val = Math.pow(arg0, 1 / 2.0);
		} else if (operation.equalsIgnoreCase(CalculatorConstants.CUBE_ROOT)) {
			val = Math.pow(arg0, 1 / 3.0);
		} else if (operation.equalsIgnoreCase(CalculatorConstants.QUAD_ROOT)) {
			val = Math.pow(arg0, 1 / 4.0);
		} else if (operation.equalsIgnoreCase(CalculatorConstants.LOG)) {
			val = Math.log10(arg0);
		} else if (operation.equalsIgnoreCase(CalculatorConstants.NATURAL_LOG)) {
			val = Math.log(arg0);
		} else if (operation.equalsIgnoreCase(CalculatorConstants.POWER_10)) {
			val = Math.pow(10, arg0);
		} else if (operation.equalsIgnoreCase(CalculatorConstants.POWER_e)) {
			val = Math.pow(Math.E, arg0);
		} else if (operation.equalsIgnoreCase(CalculatorConstants.INV)) {
			val = 1.0 / arg0;
		}
		else if(operation.equalsIgnoreCase(CalculatorConstants.FACTORIAL)){
			val = Factorial(arg0);
		}
		else if(operation.equalsIgnoreCase(CalculatorConstants.NEGATION))
		{
		    val = arg0 * -1.0;
		}

		CalcDebug.Debug("val = " + val);

		return val;
	}

	public static double performCalculation(String operation)
	{
		CalcDebug.Debug("performCalculation(string)");
		double val = 0.0;

		if (operation.equalsIgnoreCase(CalculatorConstants.PI))
			val = Math.PI;
		else if(operation.equalsIgnoreCase(CalculatorConstants.POWER_e_1))
			val = Math.E;


		CalcDebug.Debug("val = " + val);
		return val;
	}
	public static double performCalculation(String operation, double arg0, double arg1) {

		CalcDebug.Debug("performCalculation(string, double, double)");
		double val = 0.0;

		if (operation.equalsIgnoreCase(CalculatorConstants.PLUS)) {
			val = arg0 + arg1;
		} else if (operation.equalsIgnoreCase(CalculatorConstants.MINUS)) {
			val = arg0 - arg1;
		} else if (operation.equalsIgnoreCase(CalculatorConstants.MULTIPLY)) {
			val = arg0 * arg1;
		} else if (operation.equalsIgnoreCase(CalculatorConstants.DIVIDE)) {
			val = arg0 / arg1;
		} else if (operation.equalsIgnoreCase(CalculatorConstants.MOD)) {
			val = arg0 % arg1;
		} else if (operation.equalsIgnoreCase(CalculatorConstants.N_POWER)) {
			val = Math.pow(arg0, arg1);
		}else if (operation.equalsIgnoreCase(CalculatorConstants.EXP)) {
			val = arg0 * Math.pow(10, arg1);
		}
		else if (operation.equalsIgnoreCase(CalculatorConstants.N_ROOT)) {
			val = Math.pow(arg1, 1/arg0);
		}
		else if(operation.equalsIgnoreCase(CalculatorConstants.NPR)){
			val = NPR(arg0, arg1);
		}
		else if(operation.equalsIgnoreCase(CalculatorConstants.NCR)){
			val = NCR(arg0, arg1);
		}

		CalcDebug.Debug("val = " + val);

		return val;
	}

	private static double convert(double arg0)
	{
	    double val = 0.0;
	    
	    if (getMode() == Mode.DEGREES)
			val = Math.toRadians(arg0);
	    else if (getMode() == Mode.GRADIAN)
			val = Math.PI * arg0 / 200;

	    return val;
	}

	private static double convertBack(double arg0)
	{
		double val = 0.0;

		if (getMode() == Mode.DEGREES)
			val = Math.toDegrees(arg0);
		else if (getMode() == Mode.GRADIAN)
			val = 200 * arg0 / Math.PI;

		return val;
	}
	
	public static double performCalculation(String operation, double arg0,
			double arg1, double arg2) {

		CalcDebug.Debug("performCalculation(string, double, double, double)");
		double val = 0.0;
		return val;

	}

	public static double NCR(double n, double r)
	{
		if(n < r) return -1;
		double val = 0.0;
		val = Factorial(n)/(Factorial(n-r)*Factorial(r));
		return val;
	}
	
	public static double NPR(double n, double r)
	{
		if(n < r) return -1;
		double val = 0.0;
		val = Factorial(n)/Factorial(n-r);
		return val;
	}
	
	public static double Factorial(double n)
	{
		if(n == 1)
			return 1;
		else
			return n * Factorial(n-1);
	}
	public static double sinhi(double x) {
		double val = 0.0;
		val = Math.log(x + Math.sqrt(Math.pow(x, 2) + 1.0));
		return val;
	}

	public static double coshi(double x) {
		double val = 0.0;
		val = Math.log(x + Math.sqrt(Math.pow(x, 2) - 1.0));
		return val;
	}

	public static double tanhi(double x) {
		double val = 0.0;
		val = 0.5 * Math.log((1 + x) / (1 - x));
		return val;
	}

	public int getCalcStatus() {
		return calcStatus;
	}

	public void setCalcStatus(int calcStatus) {
		this.calcStatus = calcStatus;
	}

	public static Mode getMode() {
		return mode;
	}

	public static void setMode(Mode m) {
		mode = m;
	}

}
