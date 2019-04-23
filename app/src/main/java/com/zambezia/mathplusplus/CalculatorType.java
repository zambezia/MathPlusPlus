package com.zambezia.mathplusplus;

 public enum CalculatorType
 {
     SIMPLE("Simple",0),
     SMARTSCIENTIFIC("SmartScientific", 1),
     SCIENTIFIC("Scientific", 2);

     private String stringValue;
     private int intValue;
     private CalculatorType(String toString, int value) {
         stringValue = toString;
         intValue = value;
     }


     public static CalculatorType stringToEnum(String str) {
         if(str.equals("Scientific"))
             return SCIENTIFIC;
         else if(str.equals("SmartScientific"))
             return SMARTSCIENTIFIC;
         else
             return SIMPLE;
     }
     @Override
     public String toString() {
         return stringValue;
     }
}
