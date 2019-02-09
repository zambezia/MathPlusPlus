package com.zambezia.mathplusplus.Expression;

import com.zambezia.mathplusplus.Controllers.Calculator;
import com.zambezia.mathplusplus.Defs.CalculatorConstants;
import com.zambezia.mathplusplus.Utils.CalcDebug;

import java.util.ArrayList;
import java.util.List;

/**
 * ExpressionEval will operate on this class to perform calculation on expression input by user.
 * This is in memory class and not used to display expression in calculator input area.
 * Model class handling for mathematical expression entered by user from calculator keyboard
 * PreCondition: Before using this class the instance to Calculator must be initialized and send
 * in the constructor of this class
 * @author Abdul.Basir
 * @since 4/19/2016
 */
public class ExpressionCreator {

	//Instance of calculator controller
	private Calculator calculator;
	
	private List<String> exprList;
	public List<String> getExprList() {
		return exprList;
	}
	
	public void setExprList(List<String> exprlist) {
		this.exprList = exprlist;
	}
	
	public void addToken(String t) {
		CalcDebug.Debug("t=" + t);
		if (t == "."){
			handlePoint();
			return;
		}
		
		this.exprList.add(t);
	}

	public void appendTokens(String t) {
		CalcDebug.Debug("t=" + t);
		if (t == "."){
			handlePoint();
			return;
		}
		char[] arr = t.toCharArray();
		for(int i=0;i<arr.length;i++)
			this.exprList.add(Character.toString(arr[i]));
	}

	public void prependTokens(String t) {
		CalcDebug.Debug("t=" + t);
		if (t == "."){
			handlePoint();
			return;
		}
		char[] arr = t.toCharArray();
		for(int i=0;i<arr.length;i++)
			this.exprList.add(i,Character.toString(arr[i]));
	}

	public ExpressionCreator(Calculator _calcualtor) {
		this.calculator = _calcualtor;
		this.exprList = new ArrayList<String>();
	}
	
	public void clear() {
		this.exprList.clear();
	}
	
	private void handlePoint() {
		
		if (exprList.size() >= 1)
		{
			if (!(exprList.get(exprList.size() - 1).equals(".")))
			{
				//This call is rout to Expression input through controller
				calculator.appendMath(CalculatorConstants.POINT);
				exprList.add(CalculatorConstants.POINT);
			}
		}
		else
		{
			calculator.appendMath(CalculatorConstants.POINT);
			exprList.add(CalculatorConstants.POINT);
		}
	
	}
	
	public void addDouble(double val) {
		
		String t = Double.toString(val);
		
		for (int i = 0; i < t.length(); i++) {
			exprList.add(Character.toString(t.charAt(i)));
		}
	}
}