package BinaryTrees;

public class BTNode<T> implements IBTPosition<T>{
	private T element;
	private IBTPosition<T> leftChild;
	private IBTPosition<T> rightChild;
	private IBTPosition<T> parent;
	
	
	public BTNode(T element,BTNode<T> parent,BTNode<T>left,BTNode<T> right)
	{
		this.element = element;
		this.leftChild = left;
		this.rightChild = right;
		this.parent = parent;
	}
	
	public void setLeft(IBTPosition<T> left)
	{
		this.leftChild = left;
	}
	
	public void setRight(IBTPosition<T> right)
	{
		this.rightChild = right;
	}
	
	public void setParent(IBTPosition<T> parent)
	{
		this.parent = parent;
	}
	
	public void setElement(T element)
	{
		this.element = element;
	}
	
	public T getElement() {
		return this.element;
	}

	public IBTPosition<T> getLeftChild() {
		return this.leftChild;
	}

	public IBTPosition<T> getRightChild() {
	
		return this.rightChild;
	}

	public IBTPosition<T> getParent() {
		return this.parent;
	}
	
}
