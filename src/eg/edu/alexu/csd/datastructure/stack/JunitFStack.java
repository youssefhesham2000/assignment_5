package eg.edu.alexu.csd.datastructure.stack;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class JunitFStack {

	@Test
	void test() {
		Stack s=new Stack();
		ExpressionEvaluator x=new ExpressionEvaluator();
		s.push(10);
		s.push(20);
		//test size
		assertEquals(s.size(),2);
		//test peek
		assertEquals(s.peek(),20);
		//test pop
		assertEquals(s.pop(),20);
		//test isEmpty
		assertEquals(s.isEmpty(),false);
		s.pop();
		String Expected="Stack is Empty";
		String Expected2="Empty Stack";
		String v=x.infixToPostfix(" -5 + 11   ^ 2 / 5  - 3  ");
		System.out.println(v);
		System.out.println(x.evaluate("2 20 * 2 / 3 4 + 3 2 ^ * + 6 - 15 +"));
		
		try {
			s.pop();
			}
			catch (RuntimeException e) {
				assertTrue(e.getMessage().contains(Expected));
			}
		try {
			s.peek();
			}
			catch (RuntimeException e) {
				assertTrue(e.getMessage().contains(Expected2));
			}

		
	}

}
