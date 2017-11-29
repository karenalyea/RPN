package com.lmig.gfc.rpn.models;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.util.EmptyStackException;
import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

public class AbsoluterOfOneNumberTests {

	private Stack<Double> stack;
	private AbsoluterOfOneNumber abs;

	@Before
	public void setUp() {
		stack = new Stack<Double>();
		abs = new AbsoluterOfOneNumber(stack);
	}

	@Test
	public void goDoIT_replaces_neg_number_on_stack_with_postivie_number() {
		// Arrange
		stack.push(-4.5);

		// Act (call the method - usually only one line)
		abs.goDoIt();

		// Assert

		assertThat(stack.peek()).isEqualTo(4.5);
	}

	@Test
	public void goDoIT_leaves_pos_number_on_stack_postivie() {
		// Arrange
		stack.push(4.5);

		// Act (call the method - usually only one line)
		abs.goDoIt();

		// Assert

		assertThat(stack.peek()).isEqualTo(4.5);
	}

	@Test
	public void undo_returns_the_stack_to_the_previous_state() {
		// Arrange
		stack.push(-999.0);
		abs.goDoIt();

		// Act
		abs.undo(stack);

		// Assert
		assertThat(stack.peek()).isEqualTo(-999.0);
	}

	@Test
	public void empty_stack_causes_goDoIt_to_throw_EmptyStackException() {
		// Arrange
		// already arranged since stack is empty

		try {
			// Act
			abs.goDoIt();

			// Assert
			fail("Did not throw an EmptyStackException");
		} catch (EmptyStackException ese) {
		}

	}
	// @Test
	// public void
	// empty_stack_causes_goDoIt_to_throw_EmptyStackException_another_way() {
	// //Arrange
	// boolean exceptionCaught = false;
	//
	// try {
	// //Act
	// abs.goDoIt();
	// } catch(EmptyStackException ese) {
	// exceptionCaught = true;
	// }
	//
	// //Assert
	//
	// assertThat(exceptionCaught).isTrue();
	// }
	//
	
	@Test
	public void null_stack_causes_NullPointerException_in_goDoIt() {
		
		//Arrange (has to override original set up)
		abs = new AbsoluterOfOneNumber(null);
		
		try {
		
		//Act
			abs.goDoIt();
		
		//Assert
			fail("Somehow this did not throw a NPE.");
		} catch (NullPointerException npe) {}
	}
}
