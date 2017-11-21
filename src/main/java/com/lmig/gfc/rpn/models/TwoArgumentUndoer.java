package com.lmig.gfc.rpn.models;

import java.util.Stack;

public class TwoArgumentUndoer  
	extends oneArgumentUndoer {
//don't need to add implement since we are a child of OAU

	private double second;
	
	public TwoArgumentUndoer(double first, double second) {
		//call a constructor on the base/parent class
		super(first);
		this.second = second;
		
	}
	
	public void undo(Stack<Double> stack) {
		stack.pop();
		parentUndo(stack);
	}
	protected void parentUndo(Stack<Double> stack) {
		stack.push(second);
		super.parentUndo(stack);
		//this is doing the undo process in oneArgUndoer
		//without the super, it would get in a loop
		//this is needed in case we have a 3rd number undoer.
		//needs to be added for each child in case new kids come along
	}
}
