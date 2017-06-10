package List;

public interface IList<E> extends Iterable<IPosition<E>> {
	/**
	 * Return the size of the list
	 * @return
	 */
	int size();
	/**
	 * Return true if the list is empty 
	 * @return
	 */
	boolean isEmpty();
	/**
	 * Add an element to the start of the list and return it's position
	 * @param element The element to be added
	 * @return
	 */
	IPosition<E> addFirst(E element);
	
	/**
	 * Add an element to the end of the list and return it's position
	 * @param element The element to be added
	 * @return
	 */
	IPosition<E> addLast(E element);
	
	/**
	 * Add an element before the specified position and return its l
	 * @param p The position that you will add before
	 * @param element The element that will be added
	 * @return
	 */
	IPosition<E> addBefore(IPosition<E> p , E element)throws IllegalArgumentException;
	IPosition<E> addAfter(IPosition<E> p , E element)throws IllegalArgumentException;
	IPosition<E> first();
	IPosition<E> last(); 
	IPosition<E> before(IPosition<E> p) throws IllegalArgumentException;
	IPosition<E> after(IPosition<E> p) throws IllegalArgumentException;
	E set(IPosition<E> p,E element) throws IllegalArgumentException;
	E remove(IPosition<E> p) throws IllegalArgumentException;
}
