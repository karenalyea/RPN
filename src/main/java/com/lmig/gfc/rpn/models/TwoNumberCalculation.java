package com.lmig.gfc.rpn.models;

import java.util.Stack;

public abstract class TwoNumberCalculation implements Godoer, Undoer {
// abstract - know process but doesn't have enough info to do anything
	//removes ability to call new.  have to call new on one of the kids
	
	//these are no longer needed to be seen by kids
	private Stack<Double> stack;
	private Undoer undoer;

	public TwoNumberCalculation(Stack<Double> stack) {
		this.stack = stack;
	}
	
	public void goDoIt() {
		double first = stack.pop();
		double second = stack.pop();
		double result = doMath(first, second);
		stack.push(result);
		undoer = new TwoArgumentUndoer(first, second);
	}
	
	//this is a dummy method.  this is done in the children, so need
	//to put in a placeholder
	protected double doMath(double first, double second) {
		return 0;
	}

	@Override
	public void undo(Stack<Double> stack) {
		undoer.undo(stack);
	}

}