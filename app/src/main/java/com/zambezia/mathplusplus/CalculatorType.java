package com.zambezia.mathplusplus;

 public enum CalculatorType
 {
     SIMPLE("Simple",0),
     SCIENTIFIC("Scientific", 1);

     private String stringValue;
     private int intValue;
     private CalculatorType(String toString, int value) {
         stringValue = toString;
         intValue = value;
     }


     public static CalculatorType stringToEnum(String str) {
         if(str.equals("Scientific"))
             return SCIENTIFIC;
         else
             return SIMPLE;
     }
     @Override
     public String toString() {
         return stringValue;
     }
}
