package eg.edu.alexu.csd.datastructure.stack;

public class Stack implements IStack {
	public class Node{
		private Object Element;
		private Node Next;
		public Node(Object element,Node next) {
			Element=element;
			Next=next;
		}
		public Object getObj() {
			return Element;
		}
		public Node getNext() {
			return Next;
		}
	}
	static int size=0;
	boolean underFlowFlag;
	private Node top;
	
	@Override
	public Object pop() {
		// TODO Auto-generated method stub
		size--;
		if(isEmpty()) {
			throw new RuntimeException("Stack is Empty");
		}
		else {
			Object temp=top.getObj();
			top=top.getNext();
			return temp;
		}
	}

	@Override
	public Object peek() {
		// TODO Auto-generated method stub
		if(isEmpty()) {
			throw new RuntimeException("Empty Stack");
		}
		else {
			return top.getObj();
		}
	}

	@Override
	public void push(Object element) {
		// TODO Auto-generated method stub
		size++;
		if(!isEmpty()) {
			Node newNode=new Node(element,top);
			top =newNode;
		}
		else{
			Node newNode=new Node(element,null);
			top=newNode;
		}
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if (top==null) {
			return true;
		}
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

}
