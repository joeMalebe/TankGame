package Stack;

import DoublyLinkedList.DoublyList;

public class Stack<T> implements IStack<T> {
	private DoublyList<T> list;
	
	
	public Stack()
	{
		list = new DoublyList<T>();
		
	}

	public T pop() {
		// TODO Auto-generated method stub
		return list.removeLast();
	}

	public void push(T element) {
		list.addLast(element);
	}

	public T peek() {
		
		return list.getLast().getElement() ;
	}

	public int size() {
		return list.size();
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	
	
}
