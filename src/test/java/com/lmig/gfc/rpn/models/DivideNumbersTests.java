package com.lmig.gfc.rpn.models;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.util.EmptyStackException;
import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

public class DivideNumbersTests {

	private Stack<Double> stack;
	private DivideNumbers divider;

	@Before
	public void setUp() {
		stack = new Stack<Double>();
		divider = new DivideNumbers(stack);
	}

	@Test
	public void goDoIT_divides_and_pushes_to_stack() {
		// Arrange
		stack.push(4.0);
		stack.push(2.0);

		// Act (call the method - usually only one line)
		divider.goDoIt();

		// Assert
		assertThat(stack).hasSize(1);
		assertThat(stack.peek()).isEqualTo(2.0);
	}

	@Test
	public void undo_returns_the_stack_to_the_previous_state() {
		// Arrange
		stack.push(4.0);
		stack.push(2.0);
		divider.goDoIt();

		// Act
		divider.undo(stack);

		// Assert
		// assertThat(stack.peek()).isEqualTo(4.0);
		assertThat(stack).hasSize(2);
		assertThat(stack.pop()).isEqualTo(2.0);
		assertThat(stack.pop()).isEqualTo(4.0);
	}

	@Test
	public void empty_stack_causes_goDoIt_to_throw_EmptyStackException() {
		// Arrange
		// already arranged since stack is empty

		try {
			// Act
			divider.goDoIt();

			// Assert
			fail("Did not throw an EmptyStackException");
		} catch (EmptyStackException ese) {
		}
	}

	@Test
	public void null_stack_causes_NullPointerException_in_goDoIt() {

		// Arrange (has to override original set up)
		divider = new DivideNumbers(null);

		try {

			// Act
			divider.goDoIt();

			// Assert
			fail("Somehow this did not throw a NPE.");
		} catch (NullPointerException npe) {
		}
	}
}
