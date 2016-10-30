package com.zambezia.mathplusplus.Models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.zambezia.mathplusplus.Utils.CalcDebug;
import com.zambezia.mathplusplus.Utils.CalcDebug.LOG_TYPE;
import com.zambezia.mathplusplus.Defs.CalculatorConstants;
import com.zambezia.mathplusplus.Models.Operator.Associativity;
import com.zambezia.mathplusplus.Models.Operator.OperatorType;
import com.zambezia.mathplusplus.Utils.OperatorComparator;

/**
 * List of all the valid operator(operations) which math engine could handle, any new command should
 * be registered and added here to extend the functionality of Math engine.
 * @author Abdul.Basir
 * @since 4/19/2013
 *
 */
public class OperatorList {

	private static List<Operator> operatorList = null;
	private static final int PRECEDANCE1 = 0;//Highest precedence
	private static final int PRECEDANCE2 = 5;
	private static final int PRECEDANCE3 = 10;
	private static final int PRECEDANCE4 = 15;
	private static final int PRECEDANCE5 = 20;
	private static final int PRECEDANCE6 = 25;
	
	public static List<Operator> getOperatorList() {

		initOperatorList();
		return operatorList;
	}

	public OperatorList() {
		
		init();
	
	}

	private static void init() {
		
		initOperatorList();

	}

	private static void initOperatorList() {

		if (operatorList != null) { //Operator list and their order already defined
			
			return;
		}
		
		CalcDebug.Debug("initOperatorList()");
		operatorList = new ArrayList<Operator>();
				
		getOperatorList().add(new Operator(CalculatorConstants.NEGATION, OperatorList.PRECEDANCE2, OperatorType.UNARY, Associativity.RIGHT));
		
		getOperatorList().add(new Operator(CalculatorConstants.SQUARE, OperatorList.PRECEDANCE2, OperatorType.UNARY, Associativity.RIGHT));
		getOperatorList().add(new Operator(CalculatorConstants.CUBE, OperatorList.PRECEDANCE2, OperatorType.UNARY, Associativity.RIGHT));
		getOperatorList().add(new Operator(CalculatorConstants.QUAD, OperatorList.PRECEDANCE2, OperatorType.UNARY, Associativity.RIGHT));
		getOperatorList().add(new Operator(CalculatorConstants.SQUARE_ROOT, OperatorList.PRECEDANCE2, OperatorType.UNARY, Associativity.RIGHT));
		getOperatorList().add(new Operator(CalculatorConstants.CUBE_ROOT, OperatorList.PRECEDANCE2, OperatorType.UNARY, Associativity.RIGHT));
		getOperatorList().add(new Operator(CalculatorConstants.QUAD_ROOT, OperatorList.PRECEDANCE2, OperatorType.UNARY, Associativity.RIGHT));
		getOperatorList().add(new Operator(CalculatorConstants.N_POWER, OperatorList.PRECEDANCE2, OperatorType.BINARY, Associativity.RIGHT));
		getOperatorList().add(new Operator(CalculatorConstants.EXP, OperatorList.PRECEDANCE2, OperatorType.BINARY, Associativity.RIGHT));
		getOperatorList().add(new Operator(CalculatorConstants.N_ROOT, OperatorList.PRECEDANCE2, OperatorType.BINARY, Associativity.RIGHT));
		getOperatorList().add(new Operator(CalculatorConstants.INV, OperatorList.PRECEDANCE2, OperatorType.UNARY, Associativity.RIGHT));
		//Logrithams and Power of 10 and e.
		getOperatorList().add(new Operator(CalculatorConstants.LOG, OperatorList.PRECEDANCE2, OperatorType.UNARY, Associativity.RIGHT));
		getOperatorList().add(new Operator(CalculatorConstants.NATURAL_LOG, OperatorList.PRECEDANCE2, OperatorType.UNARY, Associativity.RIGHT));
		getOperatorList().add(new Operator(CalculatorConstants.POWER_10, OperatorList.PRECEDANCE2, OperatorType.UNARY, Associativity.RIGHT));
		getOperatorList().add(new Operator(CalculatorConstants.POWER_e, OperatorList.PRECEDANCE2, OperatorType.UNARY, Associativity.RIGHT));
		getOperatorList().add(new Operator(CalculatorConstants.NCR, OperatorList.PRECEDANCE2, OperatorType.BINARY, Associativity.RIGHT));
		getOperatorList().add(new Operator(CalculatorConstants.NPR, OperatorList.PRECEDANCE2, OperatorType.BINARY, Associativity.RIGHT));
		getOperatorList().add(new Operator(CalculatorConstants.FACTORIAL, OperatorList.PRECEDANCE2, OperatorType.UNARY, Associativity.RIGHT));
		
		
		//Trigonometric functions.
		getOperatorList().add(new Operator(CalculatorConstants.SIN, OperatorList.PRECEDANCE2, OperatorType.UNARY, Associativity.RIGHT));
		getOperatorList().add(new Operator(CalculatorConstants.COS, OperatorList.PRECEDANCE2, OperatorType.UNARY, Associativity.RIGHT));
		getOperatorList().add(new Operator(CalculatorConstants.TAN, OperatorList.PRECEDANCE2, OperatorType.UNARY, Associativity.RIGHT));
		getOperatorList().add(new Operator(CalculatorConstants.SINI, OperatorList.PRECEDANCE2, OperatorType.UNARY, Associativity.RIGHT));
		getOperatorList().add(new Operator(CalculatorConstants.COSI, OperatorList.PRECEDANCE2, OperatorType.UNARY, Associativity.RIGHT));
		getOperatorList().add(new Operator(CalculatorConstants.TANI, OperatorList.PRECEDANCE2, OperatorType.UNARY, Associativity.RIGHT));
		getOperatorList().add(new Operator(CalculatorConstants.SINH, OperatorList.PRECEDANCE2, OperatorType.UNARY, Associativity.RIGHT));
		getOperatorList().add(new Operator(CalculatorConstants.COSH, OperatorList.PRECEDANCE2, OperatorType.UNARY, Associativity.RIGHT));
		getOperatorList().add(new Operator(CalculatorConstants.TANH, OperatorList.PRECEDANCE2, OperatorType.UNARY, Associativity.RIGHT));
		getOperatorList().add(new Operator(CalculatorConstants.SINHI, OperatorList.PRECEDANCE2, OperatorType.UNARY, Associativity.RIGHT));
		getOperatorList().add(new Operator(CalculatorConstants.COSHI, OperatorList.PRECEDANCE2, OperatorType.UNARY, Associativity.RIGHT));
		getOperatorList().add(new Operator(CalculatorConstants.TANHI, OperatorList.PRECEDANCE2, OperatorType.UNARY, Associativity.RIGHT));
				
		
		getOperatorList().add(new Operator(CalculatorConstants.DIVIDE, OperatorList.PRECEDANCE4, OperatorType.BINARY, Associativity.LEFT));
		getOperatorList().add(new Operator(CalculatorConstants.MULTIPLY, OperatorList.PRECEDANCE4, OperatorType.BINARY, Associativity.LEFT));
		getOperatorList().add(new Operator(CalculatorConstants.MOD, OperatorList.PRECEDANCE4, OperatorType.BINARY, Associativity.LEFT));
		getOperatorList().add(new Operator(CalculatorConstants.PLUS, OperatorList.PRECEDANCE5, OperatorType.BINARY, Associativity.LEFT));
		getOperatorList().add(new Operator(CalculatorConstants.MINUS, OperatorList.PRECEDANCE5, OperatorType.BINARY, Associativity.LEFT));
				
		//getOperatorList().add(new Operator(CalculatorConstants.LEFT_PARAN, OperatorList.PRECEDANCE6));
		//getOperatorList().add(new Operator(CalculatorConstants.RIGHT_PARAN, OperatorList.PRECEDANCE6));

		// CalcDebug.Log(this.toString());

		OperatorComparator cmp = new OperatorComparator();
		Collections.sort(getOperatorList(), cmp);

		// CalcDebug.Log(this.toString());
	}

	public static Operator getOperator(String operator)
	{
	    Operator val = null;
	    for (Operator elem : getOperatorList()) {

		if (elem.getOperator().equalsIgnoreCase(operator)) {
			val = elem;
			break;
		}
	    }
	    return val;
	}
	
	/*
	 * Gets the list order of the operator specified by the string operator
	 * 
	 * @param operator the name of the operator, could be +, sin, ^ etc.
	 * 
	 * @return The order stored in the list of the operators.
	 */
	public static int getOperatorOrder(String operator) {

		int val = -1;
		
		Operator op = getOperator(operator);
		if (op == null)
		    return val;
		
		val = op.getOrder();
		
		CalcDebug.Debug(String.format("Operator's %s order is %d", operator,
				val));
		return val;
	}

	public static OperatorType getOperatorType(String operator)
	{
		OperatorType val = OperatorType.BINARY;
		
		Operator op = getOperator(operator);
		if (op == null)
		    return val;
		
		val = op.getType();
		
		CalcDebug.Debug(String.format("Operator's %s type is %s", operator,
				val.toString()));
		
		return val;
	}
	
	public static Associativity getOperatorAssociativity(String token)
	{
	    Associativity val = Associativity.LEFT;
	    val = getOperator(token).getAssociativity();
	    return val;
	}
	
	public static boolean isOperator(String token) {
	
	    	Operator operator = null;
	    	boolean val = true;
	    	
	    	operator = getOperator(token);
		if (operator == null)//token not found in operator list
		    val = false;
		
		return val;
	}

	public static boolean isParanthesis(String token)
	{
	    if (isRightParentheis(token) || isLeftParenthesis(token))
		return true;
	    else
		return false;
	}
	
	public static boolean isRightParentheis(String token)
	{
	    if (token.equals(")"))
		return true;
	    else
		return false;
	}
	
	public static boolean isLeftParenthesis(String token)
	{
	    if (token.equals("("))
		return true;
	    else
		return false;
	}
	
	public static boolean isFunction(String token)
	{
	    //TODO: have to implement for functions defined by user
	    return false;
	}
	
	public static boolean isArgumentSeparator(String token)
	{
	    //TODO: have to implement for function parameter separator like func(x, y)
	    return false;
	}
	
	@Override
	public String toString() {
		String val = "";
		StringBuilder builder = new StringBuilder();
		CalcDebug.Log("OperatorList Status...");

		for (Operator elem : getOperatorList()) {
			CalcDebug.Log(elem.toString(), LOG_TYPE.Log);
			builder.append(elem.toString());
		}

		return val;
	}
}
