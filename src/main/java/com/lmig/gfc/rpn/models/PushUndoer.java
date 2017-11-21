package com.lmig.gfc.rpn.models;

import java.util.Stack;

public class PushUndoer implements Undoer {

	//override gets generated automatically and correctly brings in the fields
	@Override
	public void undo(Stack<Double> stack) {
		stack.pop();
		
	}
	

}
