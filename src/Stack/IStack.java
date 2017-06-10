package Stack;

public interface IStack<T> {
	/**
	 * Removes the element at the top of the stack and returns it.
	 * @return The removed element 
	 */
	T pop();
	
	/**
	 * Add a new item to the top of the stack
	 * @param element 
	 */
	void push(T element);
	
	/**
	 * Looks at the  item placed on the top of the stack
	 * @return The element at the top of the stack
	 */
	T peek();
	
	/**
	 * Determines the size of the ztack
	 * @return The size of the stack
	 */
	int size();
	
	/**
	 * Determines whether the stack is empty of not
	 * @return The conclusion
	 */
	boolean isEmpty();
}
