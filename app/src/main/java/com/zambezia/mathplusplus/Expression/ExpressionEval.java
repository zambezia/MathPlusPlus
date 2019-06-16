package com.zambezia.mathplusplus.Expression;

import android.util.Pair;

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

import static com.zambezia.mathplusplus.Defs.CalculatorConstants.IsUnaryOperatorOnRight;

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
	private Pair<String,Integer>[] postfixExpression;
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

		postfixExpression = new Pair[postFixNotation.size()];
		int index = 0;
		for (String elem : postFixNotation) {

			postfixExpression[index++] = new Pair(elem,index);
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


	    Stack<Pair<String,Integer>> stack = new Stack<Pair<String,Integer>>();
	    ArrayList<Pair<String,Integer>> exp = new ArrayList<Pair<String,Integer>>();

		Pair<String,Integer> token=null;
	    for (int index=0;index< expression.length ;index++)
	    {
			token = new Pair<String,Integer>(expression[index],index);
		if (OperatorList.isOperator(token.first))
		{
		    Operator o1 = OperatorList.getOperator(token.first);
		    Operator o2 = null;
		    while (!stack.empty() && OperatorList.isOperator(stack.peek().first))
		    {
			o2 = OperatorList.getOperator(stack.peek().first);
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
		else if(OperatorList.isLeftParenthesis(token.first))
		{
		    stack.push(token);
		}
		else if (OperatorList.isRightParentheis(token.first))
		{
		    while(!OperatorList.isLeftParenthesis(stack.peek().first))
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
	    	token = stack.pop();
		if (OperatorList.isParanthesis(token.first))
		{
		    this.error = Error.MISMATCHED_PARANTHESIS;
		    return "";
		}
		exp.add(token);
	    }
	    
	    postfixExpression = new Pair[exp.size()];
	    int index = 0;
	    for (Pair<String,Integer> elem : exp)
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

		Stack<Pair<String,Integer>> operationStack = new Stack<Pair<String,Integer>>();

		CalcDebug.printArray(postfixExpression);

		Pair<String, Integer> pairOperandItem= null;
		Integer smalIndex=-1;
		for (Pair<String,Integer> elem : postfixExpression) {

			CalcDebug.Debug("elem = " + elem);

				//operationStack.push(elem);
				if (!OperatorList.isOperator(elem.first)) {
					operationStack.push(elem);
			} else {
				double arg0 = 0.0;
				double arg1 = 0.0;
				double arg2 = 0.0;

				OperatorType t = OperatorList.getOperatorType(elem.first);
				if (t == OperatorType.CONST)
				{
					val = CalculatorBrain.performCalculation(elem.first);
				}
				else if (t == OperatorType.BINARY) {
//					if(operationStack.size() != 2)
//                        throw new RuntimeException();
					arg1 = Double.parseDouble(operationStack.pop().first);
					pairOperandItem = operationStack.pop();
					arg0 = Double.parseDouble(pairOperandItem.first);
					smalIndex = pairOperandItem.second;
					val = CalculatorBrain.performCalculation(elem.first, arg0, arg1);
				} else if (t == OperatorType.UNARY)
				{
//					if(operationStack.size() != 1)
//                        throw new RuntimeException();
					pairOperandItem = operationStack.pop();
					arg0 = Double.parseDouble(pairOperandItem.first);
					//if it is not one of the unary operators that are supposed to be on the right like factorial(!),square(²) and are one of the
					//operators supposed to be on the left like sin,cos and the operand index is less than the operator e.g. sin60 and not 60sin.
					//second case if it is one of the unary operators that are supposed to be on the right like factorial(!),square(²) and not one of the
					//operators supposed to be on the left like sin,cos and the operand index is greater than the operator e.g. 3! and not !3.
					if ((!IsUnaryOperatorOnRight(elem.first) && pairOperandItem.second < elem.second) ||
							(IsUnaryOperatorOnRight(elem.first) && pairOperandItem.second > elem.second))
						arg0 = Double.parseDouble("Invalid expression");
					smalIndex = pairOperandItem.second;
					val = CalculatorBrain.performCalculation(elem.first, arg0);
				} else if (t == OperatorType.TERNARY) {
//					if(operationStack.size() != 3)
//                        throw new RuntimeException();
					arg2 = Double.parseDouble(operationStack.pop().first);
					arg1 = Double.parseDouble(operationStack.pop().first);
					pairOperandItem = operationStack.pop();
					arg0 = Double.parseDouble(pairOperandItem.first);
					smalIndex = pairOperandItem.second;
					val = CalculatorBrain.performCalculation(elem.first, arg0, arg1,
							arg2);
				}
				operationStack.push(new Pair<String, Integer>(Double.toString(val),smalIndex));
			}

			//CalcDebug.printStack(operationStack);
		}

		val = Double.parseDouble(operationStack.pop().first);

		CalcDebug.Debug("val = " + val);

		return val;
	}

	public double solve(List<String> expression) {
        double val=-1;
		ExpressionFormater formatter = new ExpressionFormater();
		this.infixToPostFix2(formatter.format(expression));
		val = this.evalutatExpression();
		return val;
	}
	
	public String[] getInfixExpression() {
		return infixExpression;
	}

	public void setInfixExpression(String[] expression) {
		this.infixExpression = expression;
	}

}
