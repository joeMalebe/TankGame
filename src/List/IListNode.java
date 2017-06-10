package List;

public interface IListNode<E> {
	ListNode<E> getPrevious();
	ListNode<E> getNext();
	E getElement();
	void setPrevious(ListNode<E> prev);
	void setNext(ListNode<E> next);
	void setElement(E e);
}
