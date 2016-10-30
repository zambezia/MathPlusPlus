package com.zambezia.mathplusplus.Models;

/**
 * Operand holding class
 * @author Abdul.Basir
 * @since 4/19/2013
 *
 */
public class Operand {
	
	public static boolean isNumber(char value)
	{
		boolean rValue = false;
		
		if (value == '.')
			rValue = true;
		else
		{
			rValue = Character.isDigit(value);
		}
		return rValue;
	}

}
