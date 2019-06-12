package com.zambezia.mathplusplus.Models;

/**
 * Map maintaining the two auxiliary command associate with the button
 * @author Abdul.Basir
 * @since 4/19/2013
 *
 */
import com.zambezia.mathplusplus.Defs.CalculatorConstants;

import java.util.HashMap;
import java.util.Map;

public class ButtonModeValues
{
    private Map<String, ButtonMode> modevalues;

    public ButtonModeValues()
    {
	init();
    }

    public void init()
    {

	modevalues = new HashMap<String, ButtonMode>();
	ButtonMode mode = new ButtonMode();
	mode.setModes(CalculatorConstants.SINI, CalculatorConstants.AND);
	modevalues.put(CalculatorConstants.SIN, mode);

	mode = new ButtonMode();
	mode.setModes(CalculatorConstants.COSI, CalculatorConstants.OR);
	modevalues.put(CalculatorConstants.COS, mode);

	mode = new ButtonMode();
	mode.setModes(CalculatorConstants.TANI, CalculatorConstants.XOR);
	modevalues.put(CalculatorConstants.TAN, mode);

	mode = new ButtonMode();
	mode.setModes(CalculatorConstants.HYPI, CalculatorConstants.NOT);
	modevalues.put(CalculatorConstants.HYP, mode);

	mode = new ButtonMode();
	mode.setModes(CalculatorConstants.N_ROOT, "A");
	modevalues.put(CalculatorConstants.N_POWER, mode);

	mode = new ButtonMode();
	mode.setModes(CalculatorConstants.QUAD_ROOT, "B");
	modevalues.put(CalculatorConstants.SQUARE_ROOT, mode);

	mode = new ButtonMode();
	mode.setModes(CalculatorConstants.INV, "C");
	modevalues.put(CalculatorConstants.SQUARE, mode);

	mode = new ButtonMode();
	mode.setModes(CalculatorConstants.CUBE_ROOT, "D");
	modevalues.put(CalculatorConstants.CUBE, mode);

	mode = new ButtonMode();
	mode.setModes(CalculatorConstants.POWER_10, "E");
	modevalues.put(CalculatorConstants.LOG, mode);

	mode = new ButtonMode();
	mode.setModes(CalculatorConstants.POWER_e_n, "F");
	modevalues.put(CalculatorConstants.NATURAL_LOG, mode);
	
	mode = new ButtonMode();
	mode.setModes("", "X");
	modevalues.put(CalculatorConstants.RCL, mode);

	mode = new ButtonMode();
	mode.setModes("", "Y");
	modevalues.put(CalculatorConstants.STO, mode);

	mode = new ButtonMode();
	mode.setModes(CalculatorConstants.MEM_SUB, CalculatorConstants.MEM);
	modevalues.put(CalculatorConstants.MEM_ADD, mode);

	
	mode = new ButtonMode();
	mode.setModes("", "");
	modevalues.put(CalculatorConstants.ONE, mode);

	mode = new ButtonMode();
	mode.setModes("", "");
	modevalues.put(CalculatorConstants.TWO, mode);

	mode = new ButtonMode();
	mode.setModes("", "");
	modevalues.put(CalculatorConstants.THREE, mode);

	mode = new ButtonMode();
	mode.setModes(CalculatorConstants.FACTORIAL, "");
	modevalues.put(CalculatorConstants.FOUR, mode);

	mode = new ButtonMode();
	mode.setModes(CalculatorConstants.NCR, "");
	modevalues.put(CalculatorConstants.FIVE, mode);

	mode = new ButtonMode();
	mode.setModes(CalculatorConstants.NPR, "");
	modevalues.put(CalculatorConstants.SIX, mode);

	mode = new ButtonMode();
	mode.setModes("", "");
	modevalues.put(CalculatorConstants.SEVEN, mode);

	mode = new ButtonMode();
	mode.setModes("", "");
	modevalues.put(CalculatorConstants.EIGHT, mode);

	mode = new ButtonMode();
	mode.setModes("", "");
	modevalues.put(CalculatorConstants.NINE, mode);

	mode = new ButtonMode();
	mode.setModes("", "");
	modevalues.put(CalculatorConstants.ZERO, mode);

    }

    public String getModeValue(String key, String modetype)
    {
	ButtonMode mode = modevalues.get(key);
	String value = null;

	if (modetype.equals("2ndF"))
	{
	    value = mode.getSecondFunMode();
	}
	else if (modetype.equals("Alpha"))
	{
	    value = mode.getAlphaMode();
	}

	return value;
    }

    public String hypOfFunction(String function)
    {
	String value = "";
	if (function.equals(CalculatorConstants.SIN))
	{
	    value = CalculatorConstants.SINH;
	}
	else if (function.equals(CalculatorConstants.SINI))
	{
	    value = CalculatorConstants.SINHI;
	}
	else if (function.equals(CalculatorConstants.COS))
	{
	    value = CalculatorConstants.COSH;
	}
	else if (function.equals(CalculatorConstants.COSI))
	{
	    value = CalculatorConstants.COSHI;
	}
	else if (function.equals(CalculatorConstants.TAN))
	{
	    value = CalculatorConstants.TANH;
	}
	else if (function.equals(CalculatorConstants.TANI))
	{
	    value = CalculatorConstants.TANHI;
	}
	return value;
    }
}
