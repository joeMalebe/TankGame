package BinaryTrees;

public interface IBTPosition<T> {
	T getElement();
	IBTPosition<T> getLeftChild();
	IBTPosition<T> getRightChild();
	IBTPosition<T> getParent();
	
}
