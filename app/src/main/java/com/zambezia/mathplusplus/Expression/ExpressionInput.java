package com.zambezia.mathplusplus.Expression;

import com.zambezia.mathplusplus.Utils.CalcDebug;

/**
 * Displays the current created expression on the display, the ExpressionEval does not perform
 * on this class to perform user's calculation.
 * @author Abdul.Basir
 * @since 4/19/2016
 */
public class ExpressionInput {

	private String output;

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}
	
	
	public ExpressionInput() {
		this.setOutput("");
	}
	
	public void append(String s) {
		CalcDebug.Debug("s=" + s);
		this.output += s;
	}

	public void prepend(String s) {
		CalcDebug.Debug("s=" + s);
		this.output = s+ this.output;
	}
	
	public void clear() {
		CalcDebug.Debug("clear");
		this.output = "";
	}

	public void removeLastEntry() {
		CalcDebug.Debug("remove last entry");
		if(this.output.length()>0)
			this.output = this.output.substring(0,this.output.length()-1);
	}

    public void removeFirstEntry() {
        CalcDebug.Debug("remove first entry");
        if(this.output.length()>0)
            this.output = this.output.substring(1,this.output.length());
    }
}
