package com.zambezia.mathplusplus.Models;

/**
 * Select the mode associated with calculator command button
 * @author Abdul.Basir
 * @since 4/19/2013
 *
 */
public class ButtonMode 
{
	private String secondfunmode, alphamode;

	public void setModes(String second, String alpha)
	{
		secondfunmode = second;
		alphamode = alpha;
	}
	
	public String getSecondFunMode()
	{
		return secondfunmode;
	}
	public String getAlphaMode()
	{
		return alphamode;
	}
	
}
