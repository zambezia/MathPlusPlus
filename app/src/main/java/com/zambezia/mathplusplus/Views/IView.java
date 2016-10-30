package com.zambezia.mathplusplus.Views;

/**
 * Interface to define operatoin contracts for UI
 * @author Abdul.Basir
 * @since 4/19/2013
 *
 */
public interface IView {
	
	public void displayExpression(String expression);
	public void displayOutput(String result);
	public void displayModeStatus(String status);
	public void displayTrigonometricMode(String mode);
}
