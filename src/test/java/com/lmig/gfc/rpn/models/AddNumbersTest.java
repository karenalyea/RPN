package com.lmig.gfc.rpn.models;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.util.EmptyStackException;
import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

public class AddNumbersTest {

	private Stack<Double> stack;
	private AddNumbers adder;

	@Before
	public void setUp() {
		stack = new Stack<Double>();
		adder = new AddNumbers(stack);
	}

	@Test
	public void goDoIT_adds_and_pushes_to_stack() {
		// Arrange
		stack.push(234.0);
		stack.push(-2.0);

		// Act
		adder.goDoIt();

		// Assert
		assertThat(stack).hasSize(1);
		assertThat(stack.peek()).isEqualTo(232.0);
	}

	@Test
	public void undo_returns_the_stack_to_the_previous_state() {
		// Arrange
		stack.push(234.0);
		stack.push(-2.0);
		adder.goDoIt();

		// Act
		adder.undo(stack);

		// Assert
		// assertThat(stack.peek()).isEqualTo(4.0);
		assertThat(stack).hasSize(2);
		assertThat(stack.pop()).isEqualTo(-2.0);
		assertThat(stack.pop()).isEqualTo(234.0);
	}

	@Test
	public void empty_stack_causes_goDoIt_to_throw_EmptyStackException() {
		// Arrange
		// already arranged since stack is empty

		try {
			// Act
			adder.goDoIt();

			// Assert
			fail("Did not throw an EmptyStackException");
		} catch (EmptyStackException ese) {
		}
	}

	@Test
	public void null_stack_causes_NullPointerException_in_goDoIt() {

		// Arrange (has to override original set up)
		adder = new AddNumbers(null);

		try {

			// Act
			adder.goDoIt();

			// Assert
			fail("Somehow this did not throw a NPE.");
		} catch (NullPointerException npe) {
		}
	}
}
