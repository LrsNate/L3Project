import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A node from a single-linked list. This object does not contain any data but 
 * only provides references for simple use and reading of specializations. 
 * This Node can be iterated. The reason why it is a template type is to 
 * prevent different specializations from being linked with one another.
 * @author Antoine Lafouasse
 *
 * @param <T> A particular specialization of ListNode.
 */
public abstract class ListNode<T extends ListNode<T>> 
implements Iterator<ListNode<T>>
{
	/**
	 * Creates a new instance of ListNode, completely unlinked.
	 */
	public ListNode()
	{
		this.next = null;
	}
	
	/**
	 * Checks whether this Node has a next element linked to it.
	 * @return False if there is no next element, true otherwise.
	 */
	@Override
	public boolean hasNext()
	{
		return this.next != null;
	}
	
	/**
	 * Returns the next element linked to this node.
	 * @return An instance of T (i.e. a specialized node).
	 * @throws NoSuchElementException If there is no next element.
	 */
	public T next()
	{
		if (this.next == null)
			throw new NoSuchElementException();
		return this.next;
	}
	
	/**
	 * This operation is not supported.
	 * @throws UnsupportedOperationException
	 */
	@Override
	public void remove()
	{
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Links this node to the argument by making it a pointer to the current 
	 * object's next element in the linked list.
	 * @param target the specialized Node we need to link to the current object.
	 */
	public void setNext(T target)
	{
		if (target == null)
			throw new IllegalArgumentException("Use unlink method");
		this.next = target;
	}
	
	/**
	 * Unlinks this node from its next element.
	 */
	public void unlinkNext()
	{
		this.next = null;
	}
	
	/**
	 * A reference to the next element in the linked list.
	 * @see ListNode#next()
	 * @see ListNode#setNext(T)
	 * @see ListNode#unlinkNext()
	 */
	private volatile T next;
}
