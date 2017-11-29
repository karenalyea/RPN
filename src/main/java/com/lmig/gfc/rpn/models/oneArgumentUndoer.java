package com.lmig.gfc.rpn.models;

import java.util.Stack;

public class OneArgumentUndoer implements Undoer {

		private double first;
			
		public OneArgumentUndoer(double first) {
			this.first = first;
		}
		
		@Override
		public void undo(Stack<Double> stack) {
			stack.pop();
			parentUndo(stack);
			
		}
//protected can go on method or data.  better to go on method
		protected void parentUndo(Stack<Double> stack ) {
			stack.push(first);
			//i'll do my undo and child does his undo.  child tells what to do
		}
}
