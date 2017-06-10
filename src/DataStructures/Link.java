package DataStructures;

public class Link<T> {
	private T element;
	private Link<T>  next;
	private Link<T> previous;
	
	public Link()
	{
		this.element = null;
		this.next = null;
		this.previous = null;
	}
	
	/**
	 * Initialises a Link item
	 * @param previous
	 * @param next
	 * @param element
	 */
	public Link(Link<T> previous,Link<T> next, T element)
	{
		this.previous = previous;
		this.next = next;
		this.element = element;
		
		
	}
}
