import java.util.NoSuchElementException;

/**
 * The implementation of an AVL Tree. As such, this is a self-balancing binary 
 * search tree which allows for consistent adding and searching in O(log(n)). 
 * The attributes that should be used for the Tree to store any data are not 
 * implemented, which means any specialization is free to do so -- though the 
 * toString() method will need to be overridden for comparison purposes. The 
 * reason why it is a template type is to prevent different specializations 
 * from being linked with one another.
 * @author Antoine Lafouasse
 *
 *@param <T> A particular specialization of AVLNode.
 */
public abstract class AVLNode<T extends AVLNode<T>> implements Comparable<T>
{
	/**
	 * Creates a new instance of AVLNode, completely unlinked.
	 */
	public AVLNode()
	{
		this.left = null;
		this.right = null;
	}
	
	/**
	 * Balances the tree so as to make it take the shape of an AVL again.
	 * @param target The tree to be balanced.
	 * @return The root of the resulting tree.
	 */
	@SuppressWarnings("unchecked")
	public T balance()
	{
		if (this.leftHeight() - this.rightHeight() == 2)
		{
			T l = this.left();
			if (l.rightHeight() > l.leftHeight())
				this.setLeft(l.rotateLeft());
			return this.rotateRight();
		}
		else if (this.rightHeight() - this.leftHeight() == 2)
		{
			T r = this.right();
			if (r.leftHeight() > r.rightHeight())
				this.setRight(r.rotateRight());
			return this.rotateLeft();
		}
		else
			return (T) this;
	}
	
	/**
	 * Checks whether this node is identical to this one.
	 * @param o the object with which we will compare the tree.
	 * @return True if o is an instance of the same specialization of AVLNode 
	 * as the current object, in which the stored data is deemed equal. False 
	 * otherwise.
	 */
	public abstract boolean equals(Object o);

	/**
	 * Checks whether this tree has a left daughter.
	 * @return True if there is a left daughter, false otherwise.
	 */
	public boolean hasLeft()
	{
		return this.left != null;
	}
	
	/**
	 * Checks whether this tree has a right daughter.
	 * @return True if there is a right daughter, false otherwise.
	 */
	public boolean hasRight()
	{
		return this.right != null;
	}
	
	/**
	 * Checks whether this Tree is a leaf.
	 * @return False if there is one daughter at least, true otherwise.
	 */
	public boolean isLeaf()
	{
		return !this.hasLeft() && !this.hasRight();
	}
	
	/**
	 * Returns this tree's left daughter.
	 * @return This tree's left daughter.
	 * @throws NoSuchElementException If this tree does not have a left 
	 * daughter.
	 */
	public T left() throws NoSuchElementException
	{
		if (this.hasLeft())
			return this.left;
		else
			throw new NoSuchElementException("No left daughter");
	}

	/**
	 * Returns This tree's right daughter.
	 * @return This tree's right daughter.
	 * @throws NoSuchElementException If this tree does not have a right
	 * daughter.
	 */
	public T right() throws NoSuchElementException
	{
		if (this.hasRight())
			return this.right;
		else
			throw new NoSuchElementException("No right daughter");
	}
	
	/**
	 * Links this tree to the target by making it its left daughter.
	 * @param target The object we need to link.
	 */
	public void setLeft(T target)
	{
		if (target == null)
			throw new IllegalArgumentException("Daughter cannot be set" +
					" to null. Use unlink method");
		this.left = target;
	}
	
	/**
	 * Links this tree to the target by making it its right daughter.
	 * @param target The object we need to link.
	 */
	public void setRight(T target)
	{
		if (target == null)
			throw new IllegalArgumentException("Daughter cannot be set" +
					" to null. Use unlink method");
		this.right = target;
	}
	
	/**
	 * The representation of a Node as a String. As it is used for comparison, 
	 * it needs to be overridden.
	 * @return The String representation of the Node's value.
	 */
	public abstract String toString();

	/**
	 * Nullifies the link from this tree to its left daughter.
	 */
	public void unlinkLeft()
	{
		this.left = null;
	}
	
	/**
	 * Nullifies the link from this tree to its right daughter.
	 */
	public void unlinkRight()
	{
		this.right = null;
	}
	
	
	/**
	 * Returns the height of the tree
	 * @return The length of the longest branch in the tree, starting from 
	 * the current tree.
	 */
	private int height()
	{
		if (this.isLeaf())
			return 0;		
		else
		{
			int max = 0;
			if (this.hasLeft())
				max = this.left().height();
			if (this.hasRight())
			{
				int r = this.right().height();
				max = (r > max ? r : max);
			}
			return max + 1;
		}
	}
	
	/**
	 * Returns the height of the tree's left daughter.
	 * @return The length of the longest branch starting from this tree's left 
	 * daughter, or -1 if there isn't one.
	 */
	private int leftHeight()
	{
		if (!this.hasLeft())
			return -1;
		else
			return this.left().height();
	}
	
	/**
	 * Returns the height of the Tree's right daughter.
	 * @return The length of the longest branch starting from this tree's right 
	 * daughter, or -1 if there isn't one.
	 */
	private int rightHeight()
	{
		if (!this.hasRight())
			return -1;
		else
			return this.right().height();
	}
	
	/**
	 * Rotates a tree to the left so as to balance it.
	 * @param target The tree to be rotated.
	 * @return The resulting tree.
	 */
	@SuppressWarnings("unchecked")
	private T rotateLeft()
	{
		T pivot = this.right();
		if (pivot.hasLeft())
			this.setRight(pivot.left());
		else
			this.unlinkRight();
		pivot.setLeft((T) this);
		return pivot;
	}
	
	/**
	 * Rotates a tree to the right so as to balance it.
	 * @param target The tree to be rotated.
	 * @return The resulting tree.
	 */
	@SuppressWarnings("unchecked")
	private T rotateRight()
	{
		T pivot = this.left();
		if (pivot.hasRight())
			this.setLeft(pivot.right());
		else
			this.unlinkLeft();
		pivot.setRight((T) this);
		return pivot;
	}
	
	/**
	 * This node's left daughter in the BST. It must be lesser than the current 
	 * object in terms of stored data.
	 */
	private volatile T left;
	
	/**
	 * This node's right daughter in the BST. It must be greater than the 
	 * current object in terms of stored data.
	 */
	private volatile T right;
}
