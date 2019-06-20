package com.zambezia.mathplusplus.Controllers;

import com.zambezia.mathplusplus.Models.ButtonModeValues;
import com.zambezia.mathplusplus.Models.Operator;
import com.zambezia.mathplusplus.Models.OperatorList;
import com.zambezia.mathplusplus.Utils.CalcDebug;
import com.zambezia.mathplusplus.Utils.CalculatorBrain;
import com.zambezia.mathplusplus.Defs.CalculatorConstants;
import com.zambezia.mathplusplus.Utils.CalculatorMemory;
import com.zambezia.mathplusplus.Expression.ExpressionCreator;
import com.zambezia.mathplusplus.Expression.ExpressionEval;
import com.zambezia.mathplusplus.Expression.ExpressionInput;
import com.zambezia.mathplusplus.Views.IView;

import static com.zambezia.mathplusplus.Defs.CalculatorConstants.GetActualOperator;

/**
 * Controller class for the View MainActivity View	
 * @author Abdul.Basir
 * @since 4/19/2013
 *
 */
public class Calculator {

	public double result=0.0;
	public enum InputMode{ SHIFT, ALPHA, NORMAL };
	public enum MemoryInputMode{RECALL, STORE, NORMAL};
	
	private MemoryInputMode memoryInputMode;
	public MemoryInputMode getMemoryInputMode() {
		return memoryInputMode;
	}

	public void setMemoryInputMode(MemoryInputMode memoryInputMode) {
		this.memoryInputMode = memoryInputMode;
		this.inputMode = InputMode.ALPHA;
		calcView.displayModeStatus(getModeviewText());
	}
	
	private InputMode inputMode;
	public InputMode getInputMode() {
		return inputMode;
	}

	public void setInputMode(InputMode inputMode) {
		this.inputMode = inputMode;
		if (this.inputMode == InputMode.ALPHA)
		{
			clearMemoryInputMode();
			setHypEnabled(false);
		}
		else if(this.inputMode == InputMode.SHIFT)
		{
			clearMemoryInputMode();
		}
		calcView.displayModeStatus(getModeviewText());
	}
	
	boolean calculationDone = false;
	
	public void clearInputMode(){
		CalcDebug.Debug("Cleared");
		this.inputMode = InputMode.NORMAL;
	}
	
	public void clearMemoryInputMode(){
		CalcDebug.Debug("Cleared");
		this.memoryInputMode = MemoryInputMode.NORMAL;
	}
	
	public boolean memoryInputModeEngaged()
	{
		if (this.memoryInputMode == MemoryInputMode.NORMAL) {
			return false;
		}
		else {
			return true;
		}
	}
	
	private boolean hypEnabled;
	public boolean isHypEnabled() {
		return hypEnabled;
	}

	public void setHypEnabled(boolean hypEnabled) {
		CalcDebug.Debug("Cleared");
		this.hypEnabled = hypEnabled;
		if (hypEnabled && this.getInputMode() == InputMode.ALPHA)
		{
			this.clearInputMode();
		}
		calcView.displayModeStatus(getModeviewText());
	}
	
	public enum TrigonometricMode
	{
		DEG,
		RAD,
		GRAD
	};
	
	private TrigonometricMode trigonoMode = TrigonometricMode.DEG;
	public void toggleTrigonoMode() {
		
		if (trigonoMode == TrigonometricMode.DEG) {
			this.trigonoMode = TrigonometricMode.RAD;
			CalculatorBrain.setMode(CalculatorBrain.Mode.RADIAN);
		}
		else if(trigonoMode == TrigonometricMode.RAD) {
			this.trigonoMode = TrigonometricMode.GRAD;
			CalculatorBrain.setMode(CalculatorBrain.Mode.GRADIAN);
		}
		else if (trigonoMode == TrigonometricMode.GRAD) {
			this.trigonoMode = TrigonometricMode.DEG;
			CalculatorBrain.setMode(CalculatorBrain.Mode.DEGREES);
		}
		
		this.displayTrigonometricMode();
	}
	
	CalculatorMemory mem;
	/**
	 * List based implementation used to solve the expression
	 */
	ExpressionCreator expCreator;
	/**
	 * The expression used to display the mathematical representation of user input
	 * on the screen
	 */
	ExpressionInput mathexp;
	
	/**
	 * The class responsible for performing infix to post-fix and then performs
	 * calculation
	 */
	ExpressionEval expEval;
	
	IView calcView;
	
	//Temp
	private ButtonModeValues buttonvalues;
	
	public Calculator(IView view) {
		this.mem = new CalculatorMemory();
		this.expCreator = new ExpressionCreator(this);
		this.mathexp = new ExpressionInput();
		this.expEval = new ExpressionEval();
		this.buttonvalues = new ButtonModeValues();
		
		inputMode = InputMode.NORMAL;
		memoryInputMode = MemoryInputMode.NORMAL;
		hypEnabled = false;
		 
		this.calcView = view;
	}
	
	public void setMem(double val, String memName){
		this.mem.setMemory(val, memName);
	}
	
	public double getMem(String memName){
		double v = mem.getMemory(memName);
		return v;
	}
	
	public double operateOnMemory(double val, boolean add){
		if (add){
			return this.mem.addM(val);
		}
		else{
			return this.mem.subtractM(val);
		}
	}
	
	public void clearExpression() {
		this.expCreator.clear();
		
	}
	
	public void addToExpression(String s) {
		boolean isExpressionAppended =false;
		//append last valid result if it is the start of a new input expression and the entry is an operator
		if(this.expCreator.getExprList().size() == 0) {
			if( OperatorList.isOperator(s)) {
				double oldResult = calcView.getDisplayOutputDouble();
				// not zero means there is some valid value in output that needs to be added
				if (oldResult != 0.0) {
					//check for unary and in that case prepend the operator
					if(OperatorList.getOperatorType(s) != Operator.OperatorType.UNARY)
					{
						this.expCreator.appendTokens(calcView.getDisplayOutputString());
						this.expCreator.addToken(s);
					}
					else
					{
						this.expCreator.addToken(s);
						this.expCreator.appendTokens(calcView.getDisplayOutputString());
					}
					isExpressionAppended =true;
					calcView.displayOutput("0.");
				}
			}
		}
		if(!isExpressionAppended)
			this.expCreator.addToken(s);
	}
	
	/**
	 * Clears out the mathematical expression input from calculator display
	 */
	public void clearMath() {
		this.mathexp.clear();
	}

	public void removeLastEntry() {
		this.mathexp.removeLastEntry();
		this.expCreator.removeLastEntry();
        calcView.displayExpression(this.mathexp.getOutput());
	}

	public void removeFirstEntry() {
		this.mathexp.removeFirstEntry();
		this.expCreator.removeFirstEntry();
		calcView.displayExpression(this.mathexp.getOutput());
	}

	public void appendMath(String s) {
		boolean isExpressionAppended =false;
		//append last valid result if it is the start of a new input expression and the entry is an operator
		if(this.mathexp.getOutput() == "" && OperatorList.isOperator(s)) {
			double oldResult = calcView.getDisplayOutputDouble();
			// not zero means there is some valid value in output that needs to be added
			if (oldResult != 0.0) {
				//check for unary and in that case prepend the operator
				if(OperatorList.getOperatorType(s) != Operator.OperatorType.UNARY) {
					this.mathexp.append(calcView.getDisplayOutputString());
					this.mathexp.append(s);
				}
				else {
					this.mathexp.append(s);
					this.mathexp.append(calcView.getDisplayOutputString());
				}
				isExpressionAppended = true;
			}
		}
		if(!isExpressionAppended)
			this.mathexp.append(s);
		calcView.displayExpression(this.mathexp.getOutput());
	}
	
	public double append(String memName)
	{
		double d = getMem(memName);
		expCreator.addDouble(d);
		return d;
	}
	
	public double solveExpression(String outMessage)
	{
		double dval = 0.0;
		try{
		result = dval = expEval.solve(this.expCreator.getExprList());
        this.appendMath(outMessage);
        calcView.displayOutput(Double.toString(dval));
        calculationDone = true;
		} catch (Exception exp) {
			calcView.displayOutput("Error");
			return dval;
		}
		return dval;
	}
	
	private void resetSpecialFunctions()
	{
		this.clearInputMode();
		this.calcView.displayModeStatus(this.getModeviewText());
	}
	
	private void displayTrigonometricMode() 
	{
		calcView.displayTrigonometricMode(this.trigonoMode.toString());
	}
	
	public void newCalculation()
	{
		this.clearMath();
		this.clearExpression();
		//calcView.displayOutput("0.");
		calcView.displayExpression("");
		resetSpecialFunctions();
		calculationDone = false;
	}
	public void newCalculationWithResetDisplayOutput()
	{
		newCalculation();
		calcView.displayOutput("0.");
	}
	
	public void commandHanlder(String func)
	{
		String inputvalue = "";
		double ans = 0.0;

		if (this.getInputMode() == InputMode.SHIFT)
		{
			inputvalue = buttonvalues.getModeValue(func, CalculatorConstants.SECOND_FUN);
		}
		else if (this.getInputMode() == InputMode.ALPHA || this.memoryInputModeEngaged())
		{
			inputvalue = buttonvalues.getModeValue(func, CalculatorConstants.ALPHA);
		}
		else
		{
			func = GetActualOperator(func);
			inputvalue = func;
		}

		//do not clear input screen once '=' is pressed in case back s[cae or delete is pressed afterwards
        if (calculationDone && !inputvalue.equals(CalculatorConstants.BACKSPACE) && !inputvalue.equals(CalculatorConstants.DELETE) ){ this.newCalculation(); }


        if (inputvalue == null || inputvalue.equals("")) {
			CalcDebug.Debug("Strange, inputvalue empty...");
			resetSpecialFunctions();
			return;
		}

		CalcDebug.Debug(String.format("func=%s; inputvalue=%s;", func, inputvalue));
		
		if (this.isHypEnabled())
		{
			inputvalue = buttonvalues.hypOfFunction(inputvalue);
		}
		
		if (CalculatorMemory.isMemoryRegister(inputvalue))
		{
			if (this.getMemoryInputMode() == MemoryInputMode.RECALL)
			{
				this.appendMath(inputvalue);
				this.append(inputvalue);
				calcView.displayOutput(Double.toString(this.getMem(inputvalue)));
			}
			else if (this.getMemoryInputMode() == MemoryInputMode.STORE)
			{
				ans = solveExpression("→" + inputvalue);
				this.setMem(ans, inputvalue);
			}
		}
		else if(inputvalue.equals(CalculatorConstants.MEM_ADD))
		{
			ans = solveExpression("→" + inputvalue);
			this.operateOnMemory(ans, true);
		}
		else if(inputvalue.equals(CalculatorConstants.MEM_SUB))
		{
			ans = solveExpression("→" + inputvalue);
			this.operateOnMemory(ans, false);
		}
		else if(inputvalue.equals(CalculatorConstants.BACKSPACE)) {
			this.removeLastEntry();
		}
        else if(inputvalue.equals(CalculatorConstants.DELETE)) {
            this.removeFirstEntry();
        }
		else
		{
			this.appendMath(inputvalue);
			this.addToExpression(inputvalue);
		}
		resetSpecialFunctions();
	}
	
	public String getModeviewText()
	{
		String val = "";
		String hyp = "";
		String alpha = "";
		String shift = "";
		String mem = "";
		
		String formatter = "%3s%3s%5s%5s";
		
		if (inputMode == InputMode.ALPHA){
			alpha = "A";
		}
		else if(inputMode == InputMode.SHIFT){
			shift = "S";
		}
		
		if (memoryInputMode == MemoryInputMode.RECALL){
			mem = CalculatorConstants.RCL;
		}
		else if (memoryInputMode == MemoryInputMode.STORE){
			mem = CalculatorConstants.STO;
		}
		
		if (isHypEnabled()){
			hyp = "hyp";
		}
		
		val = String.format(formatter, alpha, shift, hyp, mem);
		calcView.displayModeStatus(val);
		
		return val;
	}
	
}
