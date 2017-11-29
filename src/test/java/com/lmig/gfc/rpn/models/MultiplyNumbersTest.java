package com.lmig.gfc.rpn.models;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.util.EmptyStackException;
import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

public class MultiplyNumbersTest {

	private Stack<Double> stack;
	private MultiplyNumbers multiplier;

	@Before
	public void setUp() {
		stack = new Stack<Double>();
		multiplier = new MultiplyNumbers(stack);
	}

	@Test
	public void goDoIT_multiplies_and_pushes_to_stack() {
		// Arrange
		stack.push(1.0);
		stack.push(1.0);

		// Act
		multiplier.goDoIt();

		// Assert
		assertThat(stack).hasSize(1);
		assertThat(stack.peek()).isEqualTo(1.0);
	}

	@Test
	public void undo_returns_the_stack_to_the_previous_state() {
		// Arrange
		stack.push(1.0);
		stack.push(1.0);
		multiplier.goDoIt();

		// Act
		multiplier.undo(stack);

		// Assert
		assertThat(stack).hasSize(2);
		assertThat(stack.pop()).isEqualTo(1.0);
		assertThat(stack.pop()).isEqualTo(1.0);
	}

	@Test
	public void empty_stack_causes_goDoIt_to_throw_EmptyStackException() {
		// Arrange
		// already arranged since stack is empty

		try {
			// Act
			multiplier.goDoIt();

			// Assert
			fail("Did not throw an EmptyStackException");
		} catch (EmptyStackException ese) {
		}
	}

	@Test
	public void null_stack_causes_NullPointerException_in_goDoIt() {

		// Arrange (has to override original set up)
		multiplier = new MultiplyNumbers(null);

		try {

			// Act
			multiplier.goDoIt();

			// Assert
			fail("Somehow this did not throw a NPE.");
		} catch (NullPointerException npe) {
		}
	}
	
	
}
