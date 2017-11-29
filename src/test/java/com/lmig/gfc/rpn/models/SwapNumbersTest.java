package com.lmig.gfc.rpn.models;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.util.EmptyStackException;
import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

public class SwapNumbersTest {

	private Stack<Double> stack;
	private SwapNumbers swapper;
	
	@Before
	public void setUp() {
		stack = new Stack<Double>();
		swapper = new SwapNumbers(stack);
	}
	
	@Test
	public void goDoIT_swaps_the_most_recent_with_the_next_most_recent_and_pushes_to_stack() {
		// Arrange
		stack.push(50.0);
		stack.push(25.0);

		// Act
		swapper.goDoIt();

		// Assert
		assertThat(stack).hasSize(2);
		assertThat(stack.pop()).isEqualTo(50.0);
		assertThat(stack.pop()).isEqualTo(25.0);
	}

	@Test
	public void undo_returns_the_stack_to_the_previous_state() {
		// Arrange
		stack.push(50.0);
		stack.push(25.0);
		swapper.goDoIt();

		// Act
		swapper.undo(stack);

		// Assert
		assertThat(stack).hasSize(2);
		assertThat(stack.pop()).isEqualTo(25.0);
		assertThat(stack.pop()).isEqualTo(50.0);
	}

	@Test
	public void empty_stack_causes_goDoIt_to_throw_EmptyStackException() {
		// Arrange
		// already arranged since stack is empty

		try {
			// Act
			swapper.goDoIt();

			// Assert
			fail("Did not throw an EmptyStackException");
		} catch (EmptyStackException ese) {
		}
	}

	@Test
	public void null_stack_causes_NullPointerException_in_goDoIt() {

		// Arrange (has to override original set up)
		swapper = new SwapNumbers(null);

		try {

			// Act
			swapper.goDoIt();

			// Assert
			fail("Somehow this did not throw a NPE.");
		} catch (NullPointerException npe) {
		}
	}
	
}
