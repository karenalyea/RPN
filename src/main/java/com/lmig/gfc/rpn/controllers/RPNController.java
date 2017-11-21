package com.lmig.gfc.rpn.controllers;

import java.util.Stack;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lmig.gfc.rpn.models.AddNumbers;
import com.lmig.gfc.rpn.models.DivideNumbers;
import com.lmig.gfc.rpn.models.ExponentNumbers;
import com.lmig.gfc.rpn.models.MinusNumbers;
import com.lmig.gfc.rpn.models.MultiplyNumbers;
import com.lmig.gfc.rpn.models.PushUndoer;
import com.lmig.gfc.rpn.models.TwoNumberCalculation;
import com.lmig.gfc.rpn.models.Undoer;
import com.lmig.gfc.rpn.models.oneArgumentUndoer;

@Controller
public class RPNController {
	
	private Stack<Double> stack;
	private Stack<Undoer> undoers;
	
	public RPNController() { 
		stack = new Stack<Double>();
		undoers = new Stack<Undoer>();
	}

	@GetMapping("/")
	//we are redirecting everything back to GetMapping to all adds will be here
	public ModelAndView showCalculator() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("stack", stack);
		mv.addObject("hasOneOrMoreNumbers", stack.size() >=1);
		mv.addObject("hasTwoOrMoreNumbers",stack.size() >=2);
		mv.addObject("hasUndoer", undoers.size() > 0);
		mv.setViewName("RPN");
		return mv;
	}

	@PostMapping("/enter")
	public ModelAndView pushNumberOntoStack(double value) {
	//double value does not allow for null 
		stack.push(value);
		undoers.push(new PushUndoer());
	
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv; 
	}

	@PostMapping("/add")
	public ModelAndView addTwoNumbers() {
		AddNumbers adder = new AddNumbers(stack);
		return doOperation(adder);
	}
	
	@PostMapping("/minus")
	public ModelAndView minusTwoNumbers() {
		MinusNumbers minus = new MinusNumbers(stack);
		return doOperation(minus);
	}
	
	@PostMapping("/divide")
	public ModelAndView divideTwoNumbers() {
		DivideNumbers divide = new DivideNumbers(stack);
		return doOperation(divide);
	}
	
	@PostMapping("/multiply")
	public ModelAndView multiplyTwoNumbers() {
		MultiplyNumbers multiply = new MultiplyNumbers(stack);
		return doOperation(multiply);
		
	}

	@PostMapping("/exponent")
	public ModelAndView Exponent() {
		ExponentNumbers exponent = new ExponentNumbers(stack);
		return doOperation(exponent);
		
	}
	@PostMapping("/abs")
	public ModelAndView abs() {
		double value = stack.pop();
		undoers.push(new oneArgumentUndoer(value));
	//	double result = Math.abs(value);
	//	if (value < 0) {
			value = -1 * value; 
			stack.push(value);
			
	//	}
				
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}
	
	@PostMapping("/undo")
	public ModelAndView undo() {
		Undoer undoer = undoers.pop();
		undoer.undo(stack);
				
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}
	
	private ModelAndView doOperation(TwoNumberCalculation calcy) {
		calcy.goDoIt();
		undoers.push(calcy);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}
}
