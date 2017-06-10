package Queue;

import DoublyLinkedList.DoublyList;

public class Queue<T> implements IQueue<T> {

	DoublyList<T> data;
	int size;
	
	public Queue()
	{
		size = 0;
		data = new DoublyList<T>();
	}
	
	@Override
	public void enqueue(T element) {
		// TODO Auto-generated method stub
		data.addLast(element);
	}

	@Override
	public T dequeue() {
		// TODO Auto-generated method stub
		return data.removeFirst();
				
	}

	@Override
	public T first() {
		// TODO Auto-generated method stub
		return data.getFirst().getElement();
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return data.isEmpty();
	}

}
