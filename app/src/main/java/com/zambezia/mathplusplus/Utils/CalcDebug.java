package com.zambezia.mathplusplus.Utils;

/**
 * Created by Abdul.Basir on 4/19/2013.
 */
import java.util.List;
import java.util.Stack;

import android.util.Log;
//import android.util.Pair;
import android.support.v4.util.Pair;

public class CalcDebug {

	public static final String STRING_DEBUG = "CALC-Debug";
	public static final String STRING_LOG = "CALC-Log";
	public static final String STRING_INFO = "CALC-Info";
	public static final String STRING_ERROR = "CALC-Error";
	
	public static boolean ENABLE_LOG = false;
	public static boolean ENABLE_DEBUG = true;
	
	public enum LOG_TYPE
	{
		Debug,
		Log,
		Info,
		Error
	}
	
	public static void Log(String message)
	{
		if (ENABLE_LOG) 
			Log(message, LOG_TYPE.Log);
	}
	public static void Debug(String message)
	{
		if (ENABLE_DEBUG)
			Log(message, LOG_TYPE.Debug);
	}
	public static void Info(String message)
	{
		Log(message, LOG_TYPE.Info);
	}
	public static void Error(String message)
	{
		Log(message, LOG_TYPE.Error);
	}
	
	
	public static void Log(String message, LOG_TYPE type)
	{
		String category = "";
		
		if (LOG_TYPE.Debug == type)
			category = STRING_DEBUG;
		else if (LOG_TYPE.Log == type)
			category = STRING_LOG;
		else if (LOG_TYPE.Info == type)
			category = STRING_INFO;
		else if (LOG_TYPE.Error == type)
			category = STRING_ERROR;
			
		StackTraceElement[] stackTraceElement = Thread.currentThread().getStackTrace();
		if (stackTraceElement.length < 4) return;

		StackTraceElement e = stackTraceElement[4];
		String t = String.format("%s-%s() > %s", getShortClassName(e.getClassName()), e.getMethodName(), message);
		Log.d(category, t);
	}
	
	public static void printList(List<String> list)
	{
		String []array = new String[list.size()];
		list.toArray(array);
		printArray(array);
	}
	
	public static void printArray(String[] notations)
	{
		printArray("", notations);
	}

	public static void printArray(Pair<String,Integer>[] notations)
	{
		printArray("", notations);
	}

	public static void printArray(String arrayName, Pair<String,Integer>[] notations)
	{
		String val = "";
		val += arrayName;
		for (Pair<String,Integer> item : notations) {
			val += item.first + " ";
		}
		Debug("val = " + val);
	}
	
	public static void printArray(String arrayName, String[] notations)
	{
		String val = "";
		val += arrayName;
		for (String string : notations) {
			val += string + " ";
		}
		Debug("val = " + val);
	}
	
	public static void printStack(Stack<String> notations)
	{
		printStack("", notations);
	}



	public static void printStack(String stackName, Stack<String> notations)
	{
		String val = "";
		val += stackName;
		for (Object obj : notations) {
			val += obj.toString() + " ";
		}
		Debug("val = " + val);
	}

	//Private helper function for this class
	private static String getShortClassName(String className)
	{
	    String value = "";
	    int indexOfLastDot = className.lastIndexOf('.');
	    value = className.substring(indexOfLastDot + 1);
	    return value;
	}
}
