import java.util.NoSuchElementException;

/**
 * <p>The representation of a word. It contains a double-linked list containing 
 * every Part of Speech it has been associated to. This list can (and is 
 * supposed to) be sorted.</p>
 * 
 * @author Antoine Lafouasse
 *
 */
public class Word extends AVLNode<Word>
{
	/**
	 * Creates a new instance of Word, with an empty list of PartOfSpeech.
	 * 
	 * @param name The value of the word.
	 * @throws IllegalArgumentException If name is empty.
	 */
	public Word(String name)
	{
		super();
		if (name == "")
			throw new IllegalArgumentException("Word name cannot be empty.");
		this.name = name;
		this.pos = new Queue<PartOfSpeech>();
	}
	
	/**
	 * Adds a part of speech to the list. All that is needed is a String 
	 * containing the value that needs to be inserted. The method will then 
	 * add either an occurrence or a new part of speech altogether, and mind 
	 * not to create any duplicate objects.
	 * 
	 * @param name The part of speech associated to the word found in the 
	 * corpus.
	 */
	public void addPartOfSpeech(String name)
	{
		if (name.isEmpty())
			throw new IllegalArgumentException();
		if (this.pos.isEmpty())
			this.pos.push(new PartOfSpeech(name));
		else
		{
			PartOfSpeech current = this.getFirst();
			boolean found = false;
			boolean proceed = true;
			while (proceed)
			{
				if (current.toString().equals(name))
				{
					current.addOccurrence();
					found = true;
					break;
				}
				else if (current.hasNext())
					current = current.next();
				else
					proceed = false;
			}
			if (!found)
				this.pos.push(new PartOfSpeech(name));
		}
	}
	
	/**
	 * Compares the value of the word with the given string.
	 * @param name The string this word needs to be compared to.
	 * @return 1 if the word is greater than name, 0 if they are equal, -1 
	 * otherwise.
	 */
	public int compareTo(String name)
	{
		return this.toString().compareTo(name);
	}
	
	/**
	 * Compares the Word to another one, using their values. This is the 
	 * required method for implementation of the Comparable interface.
	 * @param target the Word used for comparison.
	 * @return 1 if the current Word is greater than target, 0 if they are 
	 * equal, -1 otherwise.
	 */
	@Override
	public int compareTo(Word target)
	{
		return this.toString().compareTo(target.toString());
	}
	
	/**
	 * Checks whether this object is identical to the argument. This function 
	 * can actually compare this Word to other Words and Strings -- it will 
	 * then check the value of the Word, as with Word-comparison.
	 * @param o The object to serve as comparison target.
	 * @return True if o is a String or Word of identical value, false
	 * otherwise.
	 */
	@Override
	public boolean equals(Object o)
	{
		if (o == null)
			return false;
		if (o instanceof String)
			return this.toString().equals((String) o);
		if (o instanceof Word)
			return this.compareTo((Word) o) == 0;
		else
			return false;
	}
	
	/**
	 * Returns the first element in the list, or throws an exception if the 
	 * list is empty.
	 * 
	 * @see Enumeration
	 * @return The PartOfSpeech at the top of the list
	 * @throws NoSuchElementException If the list is empty
	 */
	public PartOfSpeech getFirst()
	{
		return this.pos.peek();
	}
	
	/**
	 * Sorts the List of PartOfSpeech by descending order of occurrences.
	 */
	public void sort()
	{
		this.pos = this.mergeSort(this.pos);
	}

	/**
	 * Returns the word's value
	 * @return the word's value
	 */
	@Override
	public String toString()
	{
		return this.name;
	}
	
	/**
	 * Merges two Queues already sorted into one. 
	 * @param left The first Queue to merge.
	 * @param right The second Queue to merge.
	 * @return The resulting Queue. It must be sorted.
	 */
	private Queue<PartOfSpeech> merge(
			Queue<PartOfSpeech> left, Queue<PartOfSpeech> right)
	{
		Queue<PartOfSpeech> result = new Queue<PartOfSpeech>();
		while (!left.isEmpty() || !right.isEmpty())
		{
			if (right.isEmpty())
				result.push(left.pop());
			else if (left.isEmpty())
				result.push(right.pop());
			else if (left.peek().compareTo(right.peek()) > 0)
				result.push(left.pop());
			else
				result.push(right.pop());
		}
		return result;
	}
	
	/**
	 * The implementation of a merge sort on Queue. Its role is to sort it by 
	 * descending order of occurrences.
	 * @param target The Queue to be sorted.
	 * @return The resulting Queue.
	 */
	private Queue<PartOfSpeech> mergeSort(Queue<PartOfSpeech> target)
	{
		if (target.isSingleton())
			return target;
		Queue<PartOfSpeech> left = new Queue<PartOfSpeech>();
		Queue<PartOfSpeech> right = new Queue<PartOfSpeech>();
		left.push(target.pop());
		right.push(target.pop());
		boolean isLeft = true;
		while (!target.isEmpty())
		{
			if (isLeft)
			{
				left.push(target.pop());
				isLeft = false;
			}
			else
			{
				right.push(target.pop());
				isLeft = true;
			}
		}
		left = this.mergeSort(left);
		right = this.mergeSort(right);
		return this.merge(left, right);
	}
	
	/**
	 * The value of the word. It cannot be changed. Its only accessor is the 
	 * toString() method.
	 * @see Word#toString()
	 */
	private final String name;
	
	/**
	 * The list of PartOfSpeech contained in the Word. While its type is Queue, 
	 * its methods make it possible to be used as a linked list -- push() can 
	 * therefore be used as an append() method.
	 * @see Queue#push(ListNode)
	 */
	private Queue<PartOfSpeech> pos;
}
