package eg.edu.alexu.csd.datastructure.stack;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner S=new Scanner(System.in);
		System.out.println("Enter the infix");
		String infix=S.nextLine();
		ExpressionEvaluator EV=new ExpressionEvaluator();
		String postfix=EV.infixToPostfix(infix);
		System.out.println("Postfix:");
		System.out.println(postfix);
		int value=EV.evaluate(postfix);
		System.out.println("value:");
		System.out.print(value);
	}

}
