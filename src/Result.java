/**
 * A node from a single-linked list simply containing an integer value that can 
 * only be incremented and read. Its main usage in the application is to store 
 * a score, more precisely the number of words that have been correctly guessed 
 * after each loop -- a loop being an attempt from the Guesser at determining 
 * the correct part of speech for a given word in the test corpus.
 * @author Antoine Lafouasse
 *
 */
public class Result extends ListNode<Result>
{
	/**
	 * Creates a new instance of Result and initialises its value to the 
	 * default setting stated in a static attribute.
	 * @see Result#defaultValue
	 */
	public Result()
	{
		super();
		this.value = Result.defaultValue;
	}
	
	/**
	 * Increments the value of the Result
	 */
	public synchronized void increment()
	{
		this.value += 1;
	}
	
	/**
	 * Reads and returns the value of the Result
	 * @return the value of the Result
	 */
	public int value()
	{
		return this.value;
	}
	
	/**
	 * The number of words correctly guessed.
	 */
	private volatile int value;
	/**
	 * The default initialisation setting for the value attribute.
	 * @see Result#Result()
	 */
	private static final int defaultValue = 0;
}
