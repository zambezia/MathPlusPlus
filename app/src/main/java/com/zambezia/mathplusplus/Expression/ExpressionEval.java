package com.zambezia.mathplusplus.Expression;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.zambezia.mathplusplus.Models.Operator;
import com.zambezia.mathplusplus.Models.Operator.Associativity;
import com.zambezia.mathplusplus.Models.Operator.OperatorType;
import com.zambezia.mathplusplus.Models.OperatorList;
import com.zambezia.mathplusplus.Utils.OperatorComparator.OPERATOR_PRIORITY;
import com.zambezia.mathplusplus.Utils.CalcDebug;
import com.zambezia.mathplusplus.Utils.CalculatorBrain;

/**
 * Expression transformation mechanism is handled here, all infix to postfix conversion is performed
 * here and the converted expression is solved using CalculatorBrain
 * @author Abdul.Basir
 * @since 4/19/2013
 *
 */

public class ExpressionEval {

	public enum Error
	{ MISMATCHED_PARANTHESIS, SYNTAX_ERROR, TOO_FEW_ARGUMENTS, TOO_MANY_ARGUMENTS, SUCCESS }
	
    	private String[] infixExpression;
	/**
	 * The calculated expression frim infix notation. this expression will be
	 * used to evaluate and output the calculation result.
	 */
	private String[] postfixExpression;
	private double answer = 0.0;
	public double getAnswer() {
		return answer;
	}

	public void setAnswer(double answer) {
		this.answer = answer;
	}
	
	public boolean calculationDone = false;
	
	public Error error = Error.SUCCESS;
	
	public String infixToPostFix(String[] expression) {

		CalcDebug.Debug("infixToPostFix(Stringp[])");

		CalcDebug.printArray("expression", expression);
		
		if (expression == null || expression.length <= 0)
			return "";

		this.setInfixExpression(expression);

		String val = "";
		Stack<String> postFixNotation = new Stack<String>();
		Stack<String> operatorStack = new Stack<String>();
		String st = "";

		for (String elem : expression) {

			CalcDebug.Debug("elem = " + elem);

			if (OperatorList.isOperator(elem)) {

				if (elem.equals("(")) {

					operatorStack.push(elem);

				} else if (elem.equals(")")) {

					st = operatorStack.pop().toString();
					while (!(st.equals("("))) {
						postFixNotation.push(st);
						st = operatorStack.pop().toString();
					}

				} else { // this is the case of operators

					if (operatorStack.isEmpty()) {

						CalcDebug
								.Debug("operatorStack Empty, pushing expression element elem = "
										+ elem);
						operatorStack.push(elem);

					} else {

						Operator opFromExpression = new Operator(elem);
						Operator opFromStack = new Operator(
								operatorStack.peek());

						CalcDebug.Debug(String.format(
								"opFromExpression = %s & opFromStack = %s",
								opFromExpression.getOperator(),
								opFromStack.getOperator()));

						if (opFromExpression.compare(opFromStack) == OPERATOR_PRIORITY.GREATER) {

							operatorStack.push(opFromExpression.getOperator());

						} else {

							// Pop from operator stack and push to expression
							// until the operator
							// stack has the lower precedence operator on top.
							while (true) {

								if (operatorStack.isEmpty()) {
									operatorStack.push(opFromExpression
											.getOperator());
									break;
								}

								opFromStack = new Operator(operatorStack.peek());

								if (opFromExpression.compare(opFromStack) == OPERATOR_PRIORITY.SMALLER
										|| opFromExpression
												.compare(opFromStack) == OPERATOR_PRIORITY.EQUAL) {

									postFixNotation.push(opFromStack
											.getOperator());
									operatorStack.pop();

								} else {

									operatorStack.push(opFromExpression
											.getOperator());
									break;
								}

							}

						}

					}

				}

			} else { // This is operand

				postFixNotation.push(elem);

			}

			// Printing the state of postFixNotation stack and operator stack a
			// the end of every iteration
			CalcDebug.Debug("postFixNotation");
			CalcDebug.printStack(postFixNotation);
			CalcDebug.Debug("operatorStack");
			CalcDebug.printStack(operatorStack);

		}

		while (!operatorStack.isEmpty()) {

			postFixNotation.push(operatorStack.pop());

		}

		postfixExpression = new String[postFixNotation.size()];
		int index = 0;
		for (String elem : postFixNotation) {

			postfixExpression[index++] = elem;

		}

		return val;
	}
	
	public String infixToPostFix2(List<String> express)
	{
		String val = "";
		
		String[] exp = express.toArray(new String[express.size()]);
		val = infixToPostFix2(exp);
		
		return val;
	}
		
	public String infixToPostFix2(String[] expression)
	{
	    String val = "";
	    
	    if (expression == null || expression.length <= 0)
		return "";
	    
	    Stack<String> stack = new Stack<String>();
	    ArrayList<String> exp = new ArrayList<String>();
	    
	    for (String token : expression)
	    {
		if (OperatorList.isOperator(token))
		{
		    Operator o1 = OperatorList.getOperator(token);
		    Operator o2 = null;
		    while (!stack.empty() && OperatorList.isOperator(stack.peek()))
		    {
			o2 = OperatorList.getOperator(stack.peek());
			if (	(o1.getAssociativity() == Associativity.LEFT && o1.compare2(o2) <= 0 )
				|| (o1.getAssociativity() == Associativity.RIGHT && o1.compare2(o2) < 0)
				)
			{
			    exp.add(stack.pop());
			    continue;
			}
			break;
		    }
		    stack.push(token);
		}
		else if(OperatorList.isLeftParenthesis(token))
		{
		    stack.push(token);
		}
		else if (OperatorList.isRightParentheis(token))
		{
		    while(!OperatorList.isLeftParenthesis(stack.peek()))
		    {
			if (stack.empty())
			{
			    this.error = Error.MISMATCHED_PARANTHESIS;
			    return ""; //Mismatched paranthesis
			}
			exp.add(stack.pop());
		    }
		    
		    stack.pop();//Pop off the left paranthesis but not move to output
		}
		else //Operands
		{
		    exp.add(token);
		}
	    }
	    
	    //Moving remaining operators from stack to output queue
	    while(!stack.empty())
	    {
		String s = stack.pop();
		if (OperatorList.isParanthesis(s))
		{
		    this.error = Error.MISMATCHED_PARANTHESIS;
		    return "";
		}
		exp.add(s);
	    }
	    
	    postfixExpression = new String[exp.size()];
	    int index = 0;
	    for (String elem : exp) 
	    {
		postfixExpression[index++] = elem;
	    }
	    
	    CalcDebug.printArray(postfixExpression);
	    
	    return val;
	}
	
	public double evalutatExpression() {

		CalcDebug.Debug("evaluateExpression()");

		double val = 0.0;
		if (postfixExpression == null || postfixExpression.length <= 0)
			return val;

		Stack<String> operationStack = new Stack<String>();

		CalcDebug.printArray(postfixExpression);

		for (String elem : postfixExpression) {

			CalcDebug.Debug("elem = " + elem);

			if (!OperatorList.isOperator(elem)) {
				operationStack.push(elem);
			} else {
				double arg0 = 0.0;
				double arg1 = 0.0;
				double arg2 = 0.0;

				OperatorType t = OperatorList.getOperatorType(elem);
				if (t == OperatorType.BINARY) {
					arg1 = Double.parseDouble(operationStack.pop());
					arg0 = Double.parseDouble(operationStack.pop());
					val = CalculatorBrain.performCalculation(elem, arg0, arg1);
				} else if (t == OperatorType.UNARY) {
					arg0 = Double.parseDouble(operationStack.pop());
					val = CalculatorBrain.performCalculation(elem, arg0);
				} else if (t == OperatorType.TERNARY) {
					arg2 = Double.parseDouble(operationStack.pop());
					arg1 = Double.parseDouble(operationStack.pop());
					arg0 = Double.parseDouble(operationStack.pop());
					val = CalculatorBrain.performCalculation(elem, arg0, arg1,
							arg2);
				}
				operationStack.push(Double.toString(val));
			}

			CalcDebug.printStack(operationStack);
		}

		val = Double.parseDouble(operationStack.pop());

		CalcDebug.Debug("val = " + val);

		return val;
	}

	public double solve(List<String> expression) {
		
		ExpressionFormater formatter = new ExpressionFormater();
		this.infixToPostFix2(formatter.format(expression));
		double val = this.evalutatExpression();
		return val;
	}
	
	public String[] getInfixExpression() {
		return infixExpression;
	}

	public void setInfixExpression(String[] expression) {
		this.infixExpression = expression;
	}

}
