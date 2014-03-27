/**
 * <p>A simple object storing the name of a Part of Speech and the number of
 * times it has appeared in the learning corpus. As such, instances of this 
 * class have no meaning if they do not belong to a Word instance since their 
 * sole purpose is to act as a node of the list contained in Word - hence their 
 * extending ListNode.</p>
 * 
 * <p>For the sake of simplifying code in Word class, nodes are directly 
 * comparable with one another, which makes for a shorter and simpler 
 * alternative to calling accessors for the occurrences count.</p>
 * 
 * @author Antoine Lafouasse
 * 
 */
public class PartOfSpeech extends ListNode<PartOfSpeech>
	implements Comparable<PartOfSpeech>
{
	/**
	 * Creates a new instance of PartOfSpeech. Note that all new elements
	 * need to be integrated into the list (i.e. linked to other nodes) right 
	 * away, as this constructor was not designed to achieve this task. The 
	 * number of occurrences is by default set to 1, as stated in a static 
	 * attribute.
	 * 
	 * @param name The name of the part of speech found in the corpus.
	 * @throws IllegalArgumentException If name is empty.
	 * 
	 * @see PartOfSpeech#defaultOccurrencesCount
	 * @see PartOfSpeech#setPrevious(PartOfSpeech)
	 * @see PartOfSpeech#setNext(PartOfSpeech)
	 */
	public PartOfSpeech(String name)
	{
		super();
		if (name == "")
			throw new IllegalArgumentException("POS name cannot be empty");
		this.name = name;
		this.occurrences = PartOfSpeech.defaultOccurrencesCount;
	}
	
	/** 
	 * Increments the number of times the part of speech has appeared by one.
	 */
	public void addOccurrence()
	{
		this.occurrences++;
	}
	
	/**
	 * Compares the current PartOfSpeech to the target using their number of
	 * occurrences.
	 * @param target the PartOfSpeech used for comparison
	 * @return 1 if the current PartOfSpeech has more occurrences than target, 
	 * 0 if the number of occurrences is equal to that of target's, -1 
	 * otherwise
	 */
	@Override
	public int compareTo(PartOfSpeech target)
	{
		if (target == null)
			throw new NullPointerException();
		else if (this.occurrences() == target.occurrences())
			return 0;
		else if (this.occurrences() > target.occurrences())
			return 1;
		else
			return -1;
	}
	
	/**
	 * Returns the number of occurrences
	 * @return the number of times the part of speech has appeared in the 
	 * corpus.
	 */
	public int occurrences()
	{
		return this.occurrences;
	}
	
	/**
	 * Returns the value of the PartOfSpeech
	 * @return the value of the PartOfSpeech
	 */
	public String toString()
	{
		return this.name;
	}
	
	/**
	 * The value of the PartOfSpeech. It cannot be modified after the 
	 * constructor has been called. It can be accessed through the toString() 
	 * method.
	 * @see PartOfSpeech#toString()
	 */
	private final String name;

	/**
	 * The number of times the part of speech has appeared in the corpus, 
	 * associated with a particular word. It can be accessed through the 
	 * occurrences() method and incremented with addOccurrence()
	 * @see PartOfSpeech#addOccurrence()
	 * @see PartOfSpeech#occurrences()
	 */
	private int occurrences;
	
	/**
	 * The default initialisation value for occurrences. It cannot be 
	 * overridden.
	 * @see PartOfSpeech#occurrences
	 * @see PartOfSpeech#PartOfSpeech(String)
	 */
	private final static int defaultOccurrencesCount = 1;
}
