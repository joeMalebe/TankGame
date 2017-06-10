package BinaryTrees;

public interface ITree<T> {
	int depth(IBTPosition<T> treeNode);
	int height(IBTPosition<T> treeNode);
	int numChildren(IBTPosition<T> p);
	
	IBTPosition<T> parent(IBTPosition<T> p)throws IllegalArgumentException;
	IBTPosition<T> left(IBTPosition<T> p)throws IllegalArgumentException;
	IBTPosition<T> right(IBTPosition<T> p)throws IllegalArgumentException;
	IBTPosition<T> root();
	IBTPosition<T> sibling(IBTPosition<T> p)throws IllegalArgumentException;
	
	boolean isRoot(IBTPosition<T> p);
	boolean isInternal(IBTPosition<T> p);
	boolean isExternal(IBTPosition<T> p);
	
	IBTPosition<T> addRoot(T element)throws IllegalStateException;
	IBTPosition<T> addLeft(IBTPosition<T> p, T element)throws IllegalArgumentException;
	IBTPosition<T> addRight(IBTPosition<T> p, T element)throws IllegalArgumentException;
	T remove(IBTPosition<T> p)throws IllegalArgumentException;
	T set(IBTPosition<T> p ,T element)throws IllegalArgumentException;
	void attachSubtree(IBTPosition<T> p,BinaryTree<T> t1,BinaryTree<T> t2)throws IllegalArgumentException;
	
	
	
	
	//void preOrderTraversal();
	int size();
	boolean isEmpty();
	

}
