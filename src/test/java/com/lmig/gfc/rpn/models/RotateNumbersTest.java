package com.lmig.gfc.rpn.models;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.util.EmptyStackException;
import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

public class RotateNumbersTest {

	private Stack<Double> stack;
	private RotateNumbers rotater;
	
	@Before
	public void setUp() {
		stack = new Stack<Double>();
		rotater = new RotateNumbers(stack);
	}
	
	@Test
	public void goDoIT_swaps_the_3_most_recent_and_pushes_to_stack() {
		// Arrange
		stack.push(1.0);
		stack.push(2.0);
		stack.push(3.0);
		stack.push(4.0);
		stack.push(5.0);
		stack.push(6.0);

		// Act
		rotater.goDoIt();

		// Assert
		assertThat(stack).hasSize(6);
		assertThat(stack.pop()).isEqualTo(4.0);
		assertThat(stack.pop()).isEqualTo(6.0);
		assertThat(stack.pop()).isEqualTo(5.0);
	}

	@Test
	public void undo_returns_the_stack_to_the_previous_state() {
		// Arrange
		stack.push(1.0);
		stack.push(2.0);
		stack.push(3.0);
		stack.push(4.0);
		stack.push(5.0);
		stack.push(6.0);
		rotater.goDoIt();

		// Act
		rotater.undo(stack);

		// Assert
		assertThat(stack).hasSize(6);
		assertThat(stack.pop()).isEqualTo(6.0);
		assertThat(stack.pop()).isEqualTo(5.0);
		assertThat(stack.pop()).isEqualTo(4.0);
	}

	@Test
	public void empty_stack_causes_goDoIt_to_throw_EmptyStackException() {
		// Arrange
		// already arranged since stack is empty

		try {
			// Act
			rotater.goDoIt();

			// Assert
			fail("Did not throw an EmptyStackException");
		} catch (EmptyStackException ese) {
		}
	}

	@Test
	public void null_stack_causes_NullPointerException_in_goDoIt() {

		// Arrange (has to override original set up)
		rotater = new RotateNumbers(null);

		try {

			// Act
			rotater.goDoIt();

			// Assert
			fail("Somehow this did not throw a NPE.");
		} catch (NullPointerException npe) {
		}
	}
	
}
