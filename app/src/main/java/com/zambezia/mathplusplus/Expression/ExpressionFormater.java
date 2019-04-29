package com.zambezia.mathplusplus.Expression;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

/**
 * Preprocessor for mathematical expression, which validates infix notation before sending to the
 * class ExpressionEval
 * @author Abdul.Basir
 * @since 4/19/2013
 *
 */
public class ExpressionFormater 
{
	public String [] format(List<String> exprlist)
	{
		List<String> list = new ArrayList<String>();
		String concatvalues = "";

		
		for(int i=0; i<exprlist.size(); i++)
		{
			String string = exprlist.get(i);
			
			if(isNumber(string.charAt(0)) && string.length() ==1)
			{
				concatvalues += string.charAt(0);
			}
			else
			{
				if(!(concatvalues.equals("")))
			        list.add(concatvalues);
			 list.add(exprlist.get(i));
			 concatvalues = "";
			}

			
		}
		
		if(!(concatvalues.equals("")))
	        list.add(concatvalues);
		
	//point issue: if user enter only point like .*9 then we consider point as .0*9	
			for(int i=0; i<list.size(); i++)
			{
				
				if(list.get(i).equals("."))
				{
					list.remove(i);
					list.add(i, ".0");
				}
			}
		 
		 String[] explist = list.toArray(new String[list.size()]);
		 
		 for(int i=0 ; i<explist.length; i++)
			{
				Log.v("value at "+i, ""+list.get(i));
			}
		 
		return explist;
	}

	
	
	  public boolean isNumber(char c)
	  {
		  if(c == '.')
			  return true;
		  else
			  return Character.isDigit(c);
	  }
}
