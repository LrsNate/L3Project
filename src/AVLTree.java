import java.util.NoSuchElementException;

/**
 * The base class of an AVL Tree. It is, so to speak, a container that only 
 * contains the root of the tree and browsing functions such as searching and 
 * adding. As it is not needed, removing an element has not been implemented. 
 * In the same way that the toString method is compulsory for every 
 * specialization of AVLNode, comparisons with String objects are used while 
 * browsing the tree.
 * @author Antoine Lafouasse
 *
 * @param <T> The type of nodes contained in the tree.
 */
public abstract class AVLTree<T extends AVLNode<T>>
{
	/**
	 * Creates a new empty Tree. Note that no node will be created.
	 */
	public AVLTree()
	{
		this.root = null;
	}

	/**
	 * Finds and returns a word in the dictionary.
	 * @param target The String representation of the AVLNode that should be 
	 * returned
	 * @return A specialized instance of AVLNode, the String representation of 
	 * which is identical to the target argument -- i.e. to which the equals() 
	 * method should return true.
	 * @throws NoSuchElementException If there is no such node.
	 */
	public T find(String target) throws NoSuchElementException
	{
		if (this.root == null)
			throw new NoSuchElementException();
		return this.findRec(target, this.root);
	}
	
	/**
	 * Adds an specialized instance of AVLNode to the Tree by linking it to the 
	 * rest of the BST, then rebalances the tree so as to reduce its depth as 
	 * much as possible.
	 * @param target the node we should be inserting.
	 * @throws NodeAlreadyExistsException If there already is an identical node
	 * (i.e. to which equals() returns true).
	 */
	public void push(T target) throws NodeAlreadyExistsException
	{
		if (this.root == null)
			this.root = target;
		else
			this.root = this.pushRec(target, this.root);
	}
	
	/**
	 * The recursive method used by find(). 
	 * @param target The string representation of the node that should be 
	 * returned.
	 * @param root The root of the tree we will consider.
	 * @return A specialized instance of AVLNode.
	 * @throws NoSuchElementException If no such node could be found.
	 * 
	 * @see Dictionary#find(String)
	 */
	private T findRec(String target, T root)
		throws NoSuchElementException
	{
		if (root.equals(target))
			return root;
		else if (root.isLeaf())
			throw new NoSuchElementException();
		else if (root.toString().compareTo(target) > 0)
			return this.findRec(target, (T) root.left());
		else
			return this.findRec(target, (T) root.right());
	}
	
	/**
	 * The recursive method used by push().
	 * @param target the Node we need to insert.
	 * @param pin the root of the BST we will consider.
	 * @return the new root of the BST after balancing.
	 * @throws NodeAlreadyExistsException If there is already a node identical 
	 * to the one we are trying to insert.
	 * 
	 * @see Dictionary#push(Word)
	 */
	private synchronized T pushRec(T target, T pin) 
			throws NodeAlreadyExistsException
	{
		if (pin.equals(target))
			throw new NodeAlreadyExistsException(pin);
		else if (pin.compareTo(target) > 0)
		{
			if (pin.hasLeft())
				pin.setLeft(this.pushRec(target, pin.left()));
			else
				pin.setLeft(target);
		}
		else
		{
			if (pin.hasRight())
				pin.setRight(this.pushRec(target, pin.right()));
			else
				pin.setRight(target);
		}
		return pin.balance();
	}
	
	/**
	 * The root of the BST.
	 */
	protected volatile T root;
}
