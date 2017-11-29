package com.lmig.gfc.rpn.models;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.util.EmptyStackException;
import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

public class ExponentNumbersTest {
	
	private Stack<Double> stack;
	private ExponentNumbers exponent;

	@Before
	public void setUp() {
		stack = new Stack<Double>();
		exponent = new ExponentNumbers(stack);
	}

	@Test
	public void goDoIT_figures_the_exponent_between_2_numbers_and_pushes_to_stack() {
		// Arrange
		stack.push(2.0);
		stack.push(2.0);

		// Act
		exponent.goDoIt();

		// Assert
		assertThat(stack).hasSize(1);
		assertThat(stack.peek()).isEqualTo(4.0);
	}

	@Test
	public void undo_returns_the_stack_to_the_previous_state() {
		// Arrange
		stack.push(2.0);
		stack.push(2.0);
		exponent.goDoIt();

		// Act
		exponent.undo(stack);

		// Assert
		assertThat(stack).hasSize(2);
		assertThat(stack.pop()).isEqualTo(2.0);
		assertThat(stack.pop()).isEqualTo(2.0);
	}

	@Test
	public void empty_stack_causes_goDoIt_to_throw_EmptyStackException() {
		// Arrange
		// already arranged since stack is empty

		try {
			// Act
			exponent.goDoIt();

			// Assert
			fail("Did not throw an EmptyStackException");
		} catch (EmptyStackException ese) {
		}
	}

	@Test
	public void null_stack_causes_NullPointerException_in_goDoIt() {

		// Arrange (has to override original set up)
		exponent = new ExponentNumbers(null);

		try {

			// Act
			exponent.goDoIt();

			// Assert
			fail("Somehow this did not throw a NPE.");
		} catch (NullPointerException npe) {
		}
	}
	
	

}
