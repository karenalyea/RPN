package com.lmig.gfc.rpn.controllers;

import java.util.Stack;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


import com.lmig.gfc.rpn.models.TwoArgumentUndoer;
import com.lmig.gfc.rpn.models.oneArgumentUndoer;

@Controller
public class RPNController {
	
	private Stack<Double> stack;
	private oneArgumentUndoer undoer;
	
	public RPNController() { 
		stack = new Stack<Double>();
	}

	@GetMapping("/")
	//we are redirecting everything back to GetMapping to all adds will be here
	public ModelAndView showCalculator() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("stack", stack);
		mv.addObject("hasTwoOrMoreNumbers",stack.size() >=2);
		mv.addObject("hasUndoer", undoer != null);
		mv.setViewName("RPN");
		return mv;
	}

	@PostMapping("/enter")
	public ModelAndView pushNumberOntoStack(double value) {
	//double value does not allow for null 
		stack.push(value);
		undoer = null;
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}

	@PostMapping("/add")
	public ModelAndView addTwoNumbers() {
		double first = stack.pop();
		double second = stack.pop();
		double result = first + second;
		stack.push(result);
		undoer = new TwoArgumentUndoer(first, second);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}
	@PostMapping("/minus")
	public ModelAndView minusTwoNumbers() {
		double first = stack.pop();
		double second = stack.pop();
		double result = second - first;
		stack.push(result);
		undoer = new TwoArgumentUndoer(first, second);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv; 
	}
	@PostMapping("/abs")
	public ModelAndView redo() {
		double value = stack.pop();
		undoer = new oneArgumentUndoer(value);
	//	double result = Math.abs(value);
		if (value < 0) {
			value = -1 * value; 
			stack.push(value);
			
		}
				
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}
	
	@PostMapping("/undo")
	public ModelAndView undo() {
		undoer.undo(stack);
		undoer = null;
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}
}
