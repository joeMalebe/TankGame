package DoublyLinkedList;

public interface IDoublyList<T> {
	void insertBefore(DoublyNode<T> node, T element);
	void insertAfter(DoublyNode<T> node,T element);
	void addFirst(T element);
	void addLast(T element);
	T removeFirst();
	T removeLast();
	T remove(DoublyNode<T> node);
	DoublyNode<T> getLast();
	DoublyNode<T> getFirst();
	int size();
	boolean isEmpty();
	
}
