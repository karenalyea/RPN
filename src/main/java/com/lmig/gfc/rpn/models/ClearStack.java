package com.lmig.gfc.rpn.models;

import java.util.Stack;

public class ClearStack implements Godoer {
	
	private Stack<Double> stack;
	
	Stack<Double> tempStack = new Stack<Double>();
	
	public ClearStack(Stack<Double> stack) {
		this.stack = stack;
	}
	
	@Override
	public void undo(Stack<Double> stack) {
		stack.addAll(tempStack);
		tempStack.clear();
	}

	@Override
	public void goDoIt() {
		tempStack.addAll(stack);
		stack.clear();
		
	}

	

}
