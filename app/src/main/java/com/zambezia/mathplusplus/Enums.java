package com.zambezia.mathplusplus;

public class Enums {

    public static enum CURRENT_CALCULATOR_TYPE {simple,scientific};

    public static CURRENT_CALCULATOR_TYPE toEnumVal(int val)
    {

        if(val == 2)
            return CURRENT_CALCULATOR_TYPE.scientific;
        else
            return CURRENT_CALCULATOR_TYPE.simple;
    }
}
