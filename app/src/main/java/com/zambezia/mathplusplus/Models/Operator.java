package com.zambezia.mathplusplus.Models;

import com.zambezia.mathplusplus.Utils.OperatorComparator.OPERATOR_PRIORITY;
import com.zambezia.mathplusplus.Utils.CalcDebug;

/**
 * All valid and legit operators are handled by this class, this stores the operator mode as well
 * whether it is Unary, Binary or Ternary, it also store operator associativity whether right or left
 * and holds the arbitrary number for operator precedence.
 * @author Abdul.Basir
 * @since 4/19/2013
 *
 */
public class Operator
{

    public enum OperatorType
    {
	UNARY, BINARY, TERNARY,CONST
    }

    public enum Associativity
    {
	LEFT, RIGHT
    }

    private OperatorType type;

    public OperatorType getType()
    {
	return type;
    }

    public void setType(OperatorType type)
    {
	this.type = type;
    }

    private String operator;
    private int order;

    private Associativity associativity;
    public Associativity getAssociativity()
    {
	return associativity;
    }
    public void setAssociativity(Associativity associativity)
    {
	this.associativity = associativity;
    }
    
    public Operator(String operator)
    {

	this.setOperator(operator);
	this.setOrder(OperatorList.getOperatorOrder(operator));
	this.setType(OperatorList.getOperatorType(operator));
	this.setAssociativity(OperatorList.getOperatorAssociativity(operator));
    }

    public Operator(String operator, int order)
    {

	this.setOperator(operator);
	this.setOrder(order);
	this.setType(OperatorList.getOperatorType(operator));

    }

    public Operator(String operator, int order, OperatorType t)
    {
	init(operator, order, t, Associativity.LEFT);
    }
    
    public Operator(String operator, int order, OperatorType t, Associativity a)
    {
	init(operator, order, t, a);
    }
    
    void init(String operator, int order, OperatorType t, Associativity a)
    {
	this.setOperator(operator);
	this.setOrder(order);
	this.setType(t);
	this.setAssociativity(a);
    }
    
    public String getOperator()
    {

	return operator;
    }

    public void setOperator(String operator)
    {

	this.operator = operator;
    }

    public int getOrder()
    {

	return order;
    }

    public void setOrder(int order)
    {

	this.order = order;

    }

    public OPERATOR_PRIORITY compare(Operator arg0)
    {

	OPERATOR_PRIORITY val;

	if (this.order < arg0.order)
	    val = OPERATOR_PRIORITY.GREATER;
	else if (this.order > arg0.order)
	    val = OPERATOR_PRIORITY.SMALLER;
	else
	    val = OPERATOR_PRIORITY.EQUAL;

	CalcDebug.Debug(String.format("Comparing Operators; %s with %s; %s", this.operator, arg0.operator, val.toString()));

	return val;
    }
    
    public int compare2(Operator arg0)
    {
	int val = -(this.order - arg0.order); //Negation is required because highest precedance uses biggest number.
	CalcDebug.Debug(String.format("Comparing Operators; %s with %s; %d", this.operator, arg0.operator, val));
	return val;
    }

    @Override
    public String toString()
    {

	String val = String.format("Operator = %s : Order %s", getOperator(), getOrder());
	return val;
    }

}
