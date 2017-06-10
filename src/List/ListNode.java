package List;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class ListNode<E> implements IListNode<E> ,IPosition<E>{

	ListNode<E> next;
	ListNode<E> prev;
	E element;

	public ListNode(ListNode<E> next,ListNode<E> prev, E e)
	{
		this.next = next;
		this.prev = prev;
		element = e;
	}
	public E getElement() throws IllegalStateException {
		/*if(next == null)
		{
			throw new IllegalStateException("The position is no longer valid");
		}*/
		return element;
	}

	public ListNode<E> getPrevious() {
		return prev;
	}

	public ListNode<E> getNext() {
		return next;
	}

	public void setPrevious(ListNode<E> prev) {
		this.prev = prev;		
	}

	public void setNext(ListNode<E> next) {
		this.next = next;		
	}

	public void setElement(E e) {
		this.element = e;

	}
	


}
