package com.lmig.gfc.rpn.models;

import java.util.Stack;

public class TwoArgumentUndoer extends oneArgumentUndoer {

	private double second;
	
	public TwoArgumentUndoer(double first, double second) {
		super(first);
		this.second = second;
		
	}
	
	public void undo(Stack<Double> stack) {
		stack.pop();
		stack.push(second);
		super.parentUndo(stack);
	}
	protected void parentUndo(Stack<Double> stack) {
		stack.push(second);
		super.parentUndo(stack);
		//this is needed in case we have a 3rd number undoer.
		//needs to be added for each child in case new kids come along
	}
}
