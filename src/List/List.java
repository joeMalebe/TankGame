package List;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class List<E> implements IList<E>{
	int size;
	//E[] data;
	//int arrayLength;
	ListNode<E> header;
	ListNode<E> trailer;
	
	public List()
	{
		size = 0;
		//data = (E[])new Object[10];
		header = new ListNode<E>(null,null,null);
		trailer = new ListNode<E>(null,header,null);
		header.setNext(trailer);
	}
	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		if(this.size <= 0)
			return true;
		return false;
	}
	
	/**
	 * Validates a position and returns it as a node 
	 * @param p The position to check
	 * @return
	 */
	private ListNode<E> validate(IPosition<E> p) throws IllegalArgumentException
	{
		if(!(p instanceof ListNode<?>))
			throw new IllegalArgumentException("Invalid position type");
		ListNode<E> n = (ListNode<E>)p;
		if(n.getNext() == null)
			throw new IllegalArgumentException("p is no longer in the list");
		return n;
			
	}
	/**
	 * Add a new node in between two specified nodes and returns its position
	 * @param a The node that will be its predecessor
	 * @param b The node that will be its successor
	 * @param e The element to insert 
	 * @return The position of tthe new node
	 */
	private IPosition<E> addBetween(ListNode<E> a, ListNode<E> b, E e)
	{
		ListNode<E> newNode = new ListNode<E>(b,a,e);
		a.setNext(newNode);
		b.setPrevious(newNode);
		size++;
		return newNode;
	}
	public IPosition<E> addFirst(E element) {
	
		return this.addBetween(header, header.getNext(), element);
	}

	public IPosition<E> addLast(E element) {
		return this.addBetween(trailer.getPrevious(),trailer,  element);
	}

	public IPosition<E> addBefore(IPosition<E> p, E element) throws IllegalArgumentException{
		ListNode<E> node = this.validate(p);
		return this.addBetween(node.getPrevious(), node, element);
	}

	public IPosition<E> addAfter(IPosition<E> p, E element) throws IllegalArgumentException{
		ListNode<E> node = this.validate(p);
		return this.addBetween(node, node.getNext(), element);
	}
	
	private IPosition<E> position(ListNode<E> n)
	{
		if(n == header || n == trailer)
			return null;
		return n;
	}
	
	public IPosition<E> first() {
		
		return this.position((ListNode<E>) this.header.getNext());
	}

	public IPosition<E> last() {
		
		return this.position((ListNode<E>)this.trailer.getPrevious());
	}

	public IPosition<E> before(IPosition<E> p) throws IllegalArgumentException {
		ListNode<E> node = this.validate(p);
		return node.getPrevious();
	}

	public IPosition<E> after(IPosition<E> p) throws IllegalArgumentException {
		ListNode<E> node = this.validate(p);
		return node.getNext();
	}

	
	
	public E set(IPosition<E> p, E element) throws IllegalArgumentException {
		ListNode<E> n = this.validate(p);
		E temp = p.getElement();
		n.setElement(element);
		return temp;
	}

	public E remove(IPosition<E> p) throws IllegalArgumentException {
		ListNode<E> n = validate(p);
		ListNode<E> prev = n.getPrevious();
		ListNode<E> next = n.getNext();
		prev.setNext(next);
		next.setPrevious(prev);
		size--;
		E temp = p.getElement();
		n.setElement(null);
		n.setNext(null);
		n.setPrevious(null);
		return temp;
		
	}
	
	public Iterable<IPosition<E>> positions()
	{
		return this;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Iterator<IPosition<E>> iterator() {
		// TODO Auto-generated method stub
		return (Iterator<IPosition<E>>) new ListIterator<E>(this);
	}
	@Override
	public void forEach(Consumer<? super IPosition<E>> action) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Spliterator<IPosition<E>> spliterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
