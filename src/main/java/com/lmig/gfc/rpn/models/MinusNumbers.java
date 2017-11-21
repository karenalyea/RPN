package com.lmig.gfc.rpn.models;

import java.util.Stack;

public class MinusNumbers extends TwoNumberCalculation {

	public MinusNumbers(Stack<Double> stack) {
		super(stack);
	}
	
	@Override	
	protected double doMath(double first, double second) {
		return second - first;
	}
}
