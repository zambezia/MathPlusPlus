package com.zambezia.mathplusplus.Utils;

/**
 * Memory registers of the calculator
 * @author Abdul.Basir
 * @since 4/19/2013
 *
 */
public class CalculatorMemory {
	
	public static String[] MEM_REGISTER_ADDRESSES = 
		{"A", "B", "C", "D", "E", "F", "M", "X", "Y"};
	
	private double _a;
	
	private double _b;
	private double _c;
	private double _d;
	private double _e;
	private double _f;
	private double _x;
	private double _y;
	private double _m;
	
	public CalculatorMemory(){
		_a = _b = _c = _d = _e = _f = _x = _y = _m = 0.0;
	}
	
	public static boolean isMemoryRegister(String token)
	{
		for (int i = 0; i < MEM_REGISTER_ADDRESSES.length; i++) {
			if (MEM_REGISTER_ADDRESSES[i] == token){
				return true;
			}
		}
		return false;
	}
	
	public double getMemory(String memName)	{
		
		double val = 0.0;
		
		if (memName == "A"){
			val = this._a;
		}
		else if (memName == "B"){
			val = this._b;
		}
		else if (memName == "C"){
			val = this._c;
		}
		else if (memName == "D"){
			val = this._d;
		}
		else if (memName == "E"){
			val = this._e;
		}
		else if (memName == "F"){
			val = this._f;
		}
		else if (memName == "X"){
			val = this._x;
		}
		else if (memName == "Y"){
			val = this._y;
		}
		else if (memName == "M"){
			val = this._m;
		}
		
		return val;
	}
	
	public void setMemory(double val, String memName){
		
		if (memName == "A"){
			this._a = val;
		}
		else if (memName == "B"){
			this._b = val;
		}
		else if (memName == "C"){
			this._c = val;
		}
		else if (memName == "D"){
			this._d = val;
		}
		else if (memName == "E"){
			this._e = val;
		}
		else if (memName == "F"){
			this._f = val;
		}
		else if (memName == "X"){
			this._x = val;
		}
		else if (memName == "Y"){
			this._y = val;
		}
		else if (memName == "M"){
			this._m = val;
		}
	}
	
	public double addM(double ans){
		this._m += ans;
		return this._m;
	}
	
	public double subtractM(double ans){
		this._m = ans-this._m;
		return this._m;
	}
}
