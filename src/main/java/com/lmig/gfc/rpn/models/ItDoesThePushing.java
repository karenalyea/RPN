package com.lmig.gfc.rpn.models;

import java.util.Stack;

public class ItDoesThePushing implements Godoer, Undoer {
	
	private Stack<Double> stack;
	private double valueToPush;
	
	public ItDoesThePushing(Stack<Double> stack, double valueToPush) {
		this.stack = stack;
		this.valueToPush = valueToPush;
		
	}

	//override gets generated automatically and correctly brings in the fields
	@Override
	public void undo(Stack<Double> stack) {
		stack.pop();
		
	}

	@Override
	public void goDoIt() {
		stack.push(valueToPush);
		
	}
	

}
