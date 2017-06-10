package DoublyLinkedList;

import List.IPosition;

public class DoublyNode <T> implements IDoublyNode<T>,IPosition<T>{

	private T element;
	private DoublyNode<T> previous;
	private DoublyNode<T> next;

	/**
	 * Initializes a new Node's attributes
	 * @param previous The node before this one
	 * @param next The node after this one
	 * @param element The element stored in this node
	 */
	public DoublyNode(DoublyNode<T> previous,DoublyNode<T> next, T element)
	{
		this.previous = previous;
		this.next = next;
		this.element = element;
	}

	public DoublyNode<T> getPrevious() {
		// TODO Auto-generated method stub
		return this.previous;
	}

	public DoublyNode<T> getNext() {
		// TODO Auto-generated method stub
		return this.next;
	}

	public T getElement() {
		if(next == null)
			throw new IllegalStateException("Position no longer valid");
		return this.element;

	}

	public void setPrevious(DoublyNode<T> prev) {
		// TODO Auto-generated method stub
		this.previous = prev;
	}

	public void setNext(DoublyNode<T> next) {
		// TODO Auto-generated method stub
		this.next = next;
	}

	public String toString()
	{
		return this.element.toString() + ", ";
	}

	

	


}
