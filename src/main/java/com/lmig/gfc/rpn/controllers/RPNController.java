package com.lmig.gfc.rpn.controllers;

import java.util.Stack;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lmig.gfc.rpn.models.AbsoluterOfOneNumber;
import com.lmig.gfc.rpn.models.AddNumbers;
import com.lmig.gfc.rpn.models.DivideNumbers;
import com.lmig.gfc.rpn.models.ExponentNumbers;
import com.lmig.gfc.rpn.models.Godoer;
import com.lmig.gfc.rpn.models.MinusNumbers;
import com.lmig.gfc.rpn.models.MultiplyNumbers;
import com.lmig.gfc.rpn.models.ItDoesThePushing;



@Controller
public class RPNController {
	
	private Stack<Double> stack;
	private Stack<Godoer> undoers;
	private Stack<Godoer> redoers;
	
	public RPNController() { 
		stack = new Stack<Double>();
		undoers = new Stack<Godoer>();
		redoers = new Stack<Godoer>();
	}

	@GetMapping("/")
	//we are redirecting everything back to GetMapping to all adds will be here
	public ModelAndView showCalculator() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("stack", stack);
		mv.addObject("hasOneOrMoreNumbers", stack.size() >=1);
		mv.addObject("hasTwoOrMoreNumbers",stack.size() >=2);
		mv.addObject("hasUndoer", undoers.size() > 0);
		mv.addObject("hasRedoer", redoers.size() > 0);
		mv.setViewName("RPN");
		return mv;
	}

	@PostMapping("/enter")
	public ModelAndView pushNumberOntoStack(double value) {
	//double value does not allow for null 
		ItDoesThePushing pusher = new ItDoesThePushing(stack, value);
		return doOperation(pusher);
		 
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
	public ModelAndView absoluteValue() {
		AbsoluterOfOneNumber absoluter = new AbsoluterOfOneNumber(stack);
		return doOperation(absoluter);
		
	}
	
	@PostMapping("/undo")
	public ModelAndView undo() {
		Godoer undoer = undoers.pop();
		undoer.undo(stack);
		redoers.push(undoer);
				
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}
	
	@PostMapping("/redo")
	public ModelAndView redo() {
		Godoer godoer = redoers.pop();
		godoer.goDoIt();
		undoers.push(godoer);
				
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}
	
		
	private ModelAndView doOperation(Godoer calcy) {
		//does both doer and and undoer
		//calcy know what function it is suppose to do
		calcy.goDoIt();
		undoers.push(calcy);
		redoers.clear();
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}
}
