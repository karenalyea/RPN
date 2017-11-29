package com.lmig.gfc.rpn.models;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.util.EmptyStackException;
import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

public class MinusNumbersTest {

	private Stack<Double> stack;
	private MinusNumbers minus;

	@Before
	public void setUp() {
		stack = new Stack<Double>();
		minus = new MinusNumbers(stack);
	}

	@Test
	public void goDoIT_minuses_and_pushes_to_stack() {
		// Arrange
		stack.push(123.0);
		stack.push(3.0);

		// Act
		minus.goDoIt();

		// Assert
		assertThat(stack).hasSize(1);
		assertThat(stack.peek()).isEqualTo(120.0);
	}

	@Test
	public void undo_returns_the_stack_to_the_previous_state() {
		// Arrange
		stack.push(123.0);
		stack.push(3.0);
		minus.goDoIt();

		// Act
		minus.undo(stack);

		// Assert
		assertThat(stack).hasSize(2);
		assertThat(stack.pop()).isEqualTo(3.0);
		assertThat(stack.pop()).isEqualTo(123.0);
	}

	@Test
	public void empty_stack_causes_goDoIt_to_throw_EmptyStackException() {
		// Arrange
		// already arranged since stack is empty

		try {
			// Act
			minus.goDoIt();

			// Assert
			fail("Did not throw an EmptyStackException");
		} catch (EmptyStackException ese) {
		}
	}

	@Test
	public void null_stack_causes_NullPointerException_in_goDoIt() {

		// Arrange (has to override original set up)
		minus = new MinusNumbers(null);

		try {

			// Act
			minus.goDoIt();

			// Assert
			fail("Somehow this did not throw a NPE.");
		} catch (NullPointerException npe) {
		}
	}
	
}
