package DoublyLinkedList;

public class DoublyList<T> implements IDoublyList<T> {

	private DoublyNode<T> header;
	private DoublyNode<T> trailer;
	private int size;

	public DoublyList()
	{
		this.header = new DoublyNode<T>(null,null,null);
		this.trailer = new DoublyNode<T>(header, null, null);
		this.header.setNext(trailer); 
		size = 0;
	}

	public void insertBefore(DoublyNode<T> node, T element) {
		DoublyNode<T> newNode = new DoublyNode<T>(node.getPrevious(),node,element);
		node.getPrevious().setNext(newNode);
		node.setPrevious(newNode);
		size++;
	}

	public void insertAfter(DoublyNode<T> node, T element) {
		// TODO Auto-generated method stub
		DoublyNode<T> newNode = new DoublyNode<T>(node,node.getNext(),element);
		node.getNext().setPrevious(newNode);
		node.setNext(newNode);
		size++;

	}

	public T removeFirst() {
		// TODO Auto-generated method stub
		//		T temp = this.header.getNext().getElement();
		//		this.header.getNext().getNext().setPrevious(header);
		//		this.header.setNext(header.getNext().getNext());
		if(this.isEmpty())
		{
			return null;
		}
		return this.remove(header.getNext());
	}

	public T removeLast() {
		// TODO Auto-generated method stub
		if(this.isEmpty())
		{
			return null;
		}
		return this.remove(trailer.getPrevious());
	}

	public T remove(DoublyNode<T> node) {
		// TODO Auto-generated method stub
		T temp = node.getElement();
		node.getPrevious().setNext(node.getNext());
		node.getNext().setPrevious(node.getPrevious());
		node = null;
		size--;
		return temp;
	}

	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	public boolean isEmpty() {

		// TODO Auto-generated method stub
		if(this.size == 0)
		{
			return true;
		}
		else
			return false;
	}

	
	public void addFirst(T element) {
		// TODO Auto-generated method stub
		DoublyNode<T> newNode = new DoublyNode<T>(header,header.getNext(),element);
		header.getNext().setPrevious(newNode);
		header.setNext(newNode);
		size++;
	}

	public void addLast(T element) {
		// TODO Auto-generated method stub
		DoublyNode<T> newNode = new DoublyNode<T>(trailer.getPrevious(),trailer,element);
		trailer.getPrevious().setNext(newNode);
		trailer.setPrevious(newNode);
		size++;
	}
	
	public String toString()
	{
		String list = "";
		IDoublyNode<T> temp = header.getNext();
		while(temp != trailer)
		{
			list += temp.toString();
			temp = temp.getNext();
		}
		return list;
	}

	public DoublyNode<T> getLast() {
		return trailer.getPrevious();
	}

	public DoublyNode<T> getFirst() {
		return header.getNext();
	}

}
