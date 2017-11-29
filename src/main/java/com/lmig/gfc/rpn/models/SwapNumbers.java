package com.lmig.gfc.rpn.models;

import java.util.Stack;

public class SwapNumbers implements  Godoer{

	private Stack<Double> stack;
		
	public SwapNumbers(Stack<Double> stack) {
		this.stack = stack;
	}

	@Override
	public void undo(Stack<Double> stack) {
		double first = stack.pop();
		double second = stack.pop();
		stack.push(first);
		stack.push(second);		
	}

	@Override
	public void goDoIt() {
		double first = stack.pop();
		double second = stack.pop();
		stack.push(first);
		stack.push(second);		
		
	}
	
}
