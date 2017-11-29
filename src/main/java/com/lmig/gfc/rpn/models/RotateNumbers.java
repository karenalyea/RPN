package com.lmig.gfc.rpn.models;

import java.util.Stack;

public class RotateNumbers implements Godoer{
	
	private Stack<Double> stack;
	
	
	public RotateNumbers(Stack<Double> stack) {
		this.stack = stack;
	}

	@Override
	public void undo(Stack<Double> stack) {
		double third = stack.pop();
		double first = stack.pop();
		double second = stack.pop();
		stack.push(third);
		stack.push(second);
		stack.push(first);
	}

	@Override
	public void goDoIt() {
		double first = stack.pop();
		double second = stack.pop();
		double third = stack.pop();
		stack.push(second);
		stack.push(first);
		stack.push(third);
		
		
	}

}
