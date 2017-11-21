package com.lmig.gfc.rpn.models;

import java.util.Stack;

public class AddNumbers extends TwoNumberCalculation {
	
	public AddNumbers(Stack<Double> stack) {
		super(stack);
	}
	
	@Override	
	//overrides the dummy method in TwoNumberCalculation
	protected double doMath(double first, double second) {
		return first + second;
	}
}
