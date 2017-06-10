package DoublyLinkedList;

public interface IDoublyNode<T> {
	DoublyNode<T> getPrevious();
	DoublyNode<T> getNext();
	void setPrevious(DoublyNode<T> prev);
	void setNext(DoublyNode<T> next);
	T getElement();
}
