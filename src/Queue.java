import java.util.NoSuchElementException;

/**
 * A FIFO (First In, First Out) structure. This queue relies on ListNode for 
 * its implementation.
 * @author Antoine Lafouasse
 *
 * @param <T>
 */
public class Queue<T extends ListNode<T>>
{
	/**
	 * Creates a new empty queue.
	 */
	public Queue()
	{
		this.head = null;
		this.tail = null;
	}
	
	/**
	 * Checks whether the queue is empty.
	 * @return True if the queue holds no element, false otherwise.
	 */
	public boolean isEmpty()
	{
		return this.head == null;
	}
	
	/**
	 * Checks whether the queue contains one and only one element.
	 * @return True if the queue holds one and only one element, false 
	 * otherwise.
	 */
	public boolean isSingleton()
	{
		return !this.isEmpty() && this.head == this.tail;
	}
	
	/**
	 * Returns the first element in the queue. Note that as a consequence, what 
	 * this method returns actually is a linked list.
	 * @return The Queue's first element.
	 * @throws NoSuchElementException If the queue is empty.
	 */
	public T peek()
	{
		return this.head;
	}
	
	/**
	 * Removes and returns the first element of the queue.
	 * @return An instance of T (i.e. a specialized instance of ListNode), 
	 * completely unlinked.
	 * @throws NoSuchElementException If the queue is empty.
	 */
	public T pop()
	{
		if (this.isEmpty())
			throw new NoSuchElementException();
		T t = this.head;
		if (this.head == this.tail)
		{
			this.head = null;
			this.tail = null;
		}
		else
		{
			this.head = this.head.next();
			t.unlinkNext();
		}
		return t;
	}
	
	/**
	 * Adds an element to the queue.
	 * @param target the object to be inserted
	 */
	public void push(T target)
	{
		if (this.isEmpty())
		{
			this.head = target;
			this.tail = target;
		}
		else
		{
			this.tail.setNext(target);
			this.tail = target;
		}
	}
	
	/**
	 * The first element of the queue, which is likely to be returned at the 
	 * next pop().
	 */
	private T head;
	/**
	 * The last element of the queue, behind which the push() method will add
	 * new entries.
	 */
	private T tail;
}
