package Queue;

public interface IQueue<T> {
	void enqueue(T element);
	T dequeue();
	T first();
	int size();
	boolean isEmpty();
}
