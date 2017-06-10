package BinaryTrees;

public class BinaryTree<T> implements ITree<T> {
	private int size;
	private BTNode<T> root;

	
	public BinaryTree()
	{
		size = 0;
		root = null;
	}
	public BinaryTree(IBTPosition<T> root)
	{
		size = 0;
		this.root = (BTNode<T>) root;
	}
	/**
	 * Create a new node and return its position
	 * @param element the element of the new node
	 * @param parent the new nodes parent
	 * @param left the nodes left child
	 * @param right the nodes right child
	 * @return
	 */
	protected BTNode<T> createNode(T element,BTNode<T> parent,BTNode<T> left, BTNode<T> right)
	{
		return new BTNode<T>(element,parent,left,right);
	}

	public int depth(IBTPosition<T> treeNode) {
		if(this.isRoot(treeNode))
			return 0;
		else
			return 1 + depth(treeNode.getParent());
	}

	

	private BTNode<T> validate(IBTPosition<T> p) throws IllegalArgumentException
	{
		if(!(p instanceof BTNode))
		{
			throw new IllegalArgumentException("Not a valid position type");
		}
		BTNode<T> b = (BTNode<T>) p;
		if(b.getParent() == b)
			throw new IllegalArgumentException("The position is no longer on the tree");
		return b;

	}

	public IBTPosition<T> parent(IBTPosition<T> p) throws IllegalArgumentException {		// TODO Auto-generated method stub
		BTNode<T> n = this.validate(p);
		return n.getParent();
	}

	public IBTPosition<T> left(IBTPosition<T> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		BTNode<T> n = this.validate(p);
		return n.getLeftChild();
	}

	public IBTPosition<T> right(IBTPosition<T> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		BTNode<T> n = this.validate(p);
		return n.getRightChild();

	}

	public IBTPosition<T> root() {

		return root;
	}

	public IBTPosition<T> sibling(IBTPosition<T> p) throws IllegalArgumentException {
		BTNode<T> parent = validate(p);
		if(isRoot(p))
			return null;
		else if(p == left(parent))
		{
			return right(parent);
		}
		else
		{
			return left(parent);
		}

	}

	public boolean isRoot(IBTPosition<T> p) {
		BTNode<T> n = validate(p);
		if(n == root)
			return true;
		else
			return false;
	}

	public boolean isInternal(IBTPosition<T> p) {
		if( this.numChildren(p) > 0)
			return true;
		else;
		return false;
	}

	public boolean isExternal(IBTPosition<T> p) {
		if(this.numChildren(p) == 0)
			return true;
		else;
		return false;
	}

	public IBTPosition<T> addRoot(T element) throws IllegalStateException {
		if(!isEmpty())throw new IllegalStateException("tree is not empty");
		root = createNode(element,null,null,null);
		size = 1;
		return root;

	}

	public IBTPosition<T> addLeft(IBTPosition<T> p, T element)
			throws IllegalArgumentException {
		BTNode<T> parent = validate(p);
		if(parent.getLeftChild() != null) throw new IllegalArgumentException("p already has a left child");
		BTNode<T> child = createNode(element,parent,null,null);
		parent.setLeft(child);
		size++;
		return child;

	}

	public IBTPosition<T> addRight(IBTPosition<T> p, T element)
			throws IllegalArgumentException {
		BTNode<T> parent = validate(p);
		if(parent.getRightChild() != null) throw new IllegalArgumentException("p already has a right child");
		BTNode<T> child = createNode(element,parent,null,null);
		parent.setRight(child);
		size++;
		return child;
	}

	public T remove(IBTPosition<T> p) throws IllegalArgumentException {
		BTNode<T> n = validate(p);
		if(this.numChildren(p) == 2)throw new IllegalArgumentException("p has two children");
		BTNode<T> nChild = (BTNode<T>) (n.getLeftChild() != null ? n.getLeftChild() : n.getRightChild());
		if(nChild != null)
		{
			nChild.setParent(n.getParent());
		}
		if(n == root)
		{
			root = nChild;
		}
		else
		{
			BTNode<T> parent = (BTNode<T>) n.getParent();
			if(n == parent.getLeftChild())
				parent.setLeft(nChild);
			else
				parent.setRight(nChild);
		}
		size--;
		T temp = n.getElement();
		n.setElement(null);
		n.setLeft(null);
		n.setRight(null);
		n.setParent(n);
		
		return temp;
	}

	public T set(IBTPosition<T> p, T element)
			throws IllegalArgumentException {
		BTNode<T> n = validate(p);
		T temp = n.getElement();
		n.setElement(element);
		return temp;
	}

	public void attachSubtree(IBTPosition<T> p, ITree<T> t1, ITree<T> t2)
			throws IllegalArgumentException {
		//		BTNode<T> n = validate(p);
		//		if(isInternal(p)) throw new IllegalArgumentException("p has to be a leaf node");
		//		size += t1.size() + t2.size();
		//		if(!t1.isEmpty())
		//		{
		//			BTNode<T> root = (BTNode<T>) t1.root();
		//			root.setParent(n);
		//			n.setLeft(root);
		//			root = null;
		//	
		//		}
	}

	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	public boolean isEmpty() {
		if(size == 0) 
			return true;
		else;
		return false;
	}

	public int height(IBTPosition<T> treeNode) {
		return 0;
	}

	public int numChildren(IBTPosition<T> p) {
		int count = 0;
		if(left(p) != null)
			count++;
		if(right(p) != null)
			count++;
		
		return count;
	}

	public void attachSubtree(IBTPosition<T> p, BinaryTree<T> t1,
			BinaryTree<T> t2) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}


}
