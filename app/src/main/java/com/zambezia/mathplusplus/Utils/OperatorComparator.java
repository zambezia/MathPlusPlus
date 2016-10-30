package com.zambezia.mathplusplus.Utils;

import com.zambezia.mathplusplus.Models.Operator;

import java.util.Comparator;

/**
 * Rule for operator comparison
 * @author Abdul.Basir
 * @since 4/19/2013
 *
 */
public class OperatorComparator implements Comparator<Operator> {

	public enum OPERATOR_PRIORITY
	{
		GREATER,
		EQUAL,
		SMALLER
	}
	
	@Override
	public int compare(Operator arg0, Operator arg1) {

		int val = 0;
		// if operand 1 passed is less than the operand 2
		// then actually the precedance of operand1 is higher
		if (arg0.getOrder() < arg1.getOrder())
			val = 1;
		else if (arg0.getOrder() == arg1.getOrder())
			val = 0;
		else
			val = -1;

		return val;
	}

}
