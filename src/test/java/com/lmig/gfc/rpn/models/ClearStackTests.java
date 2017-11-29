package com.lmig.gfc.rpn.models;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

public class ClearStackTests {

	private Stack<Double> stack;
	private ClearStack clear;

	@Before
	public void setUp() {
		stack = new Stack<Double>();
		clear = new ClearStack(stack);
	}

	@Test
	public void goDoIT_clears_the_stack() {
		// Arrange
		stack.push(1.0);
		stack.push(2.0);
		stack.push(3.0);
		stack.push(4.0);

		// Act
		clear.goDoIt();

		// Assert
		assertThat(stack).isEmpty();
		}

	@Test
	public void undo_returns_the_stack_to_the_previous_state() {
		// Arrange
		stack.push(1.0);
		stack.push(2.0);
		stack.push(3.0);
		stack.push(4.0);
		clear.goDoIt();

		// Act
		clear.undo(stack);

		// Assert
		// assertThat(stack.peek()).isEqualTo(4.0);
		assertThat(stack).hasSize(4);
		assertThat(stack.pop()).isEqualTo(4.0);
		assertThat(stack.pop()).isEqualTo(3.0);
		assertThat(stack.pop()).isEqualTo(2.0);
		assertThat(stack.pop()).isEqualTo(1.0);
	}



	@Test
	public void null_stack_causes_NullPointerException_in_goDoIt() {

		// Arrange (has to override original set up)
		clear = new ClearStack(null);

		try {

			// Act
			clear.goDoIt();

			// Assert
			fail("Somehow this did not throw a NPE.");
		} catch (NullPointerException npe) {
		}
	}
}
