/**
 * <p>The type of element stocked in Sentence. They are fundamentally two
 * strings representing a word, and by heritage they are part of a linked list. 
 * In effect they are part of a queue and directly using linking methods and 
 * such accessors is deprecated.</p>
 * 
 * @author Antoine Lafouasse
 *
 */
public class Entry extends ListNode<Entry>
{
	/**
	 * Creates a new instance of Entry, completely unlinked.
	 * @param v the value of the word encountered in the corpus
	 * @param p the value of the part of speech linked to the word
	 */
	public Entry(String value, String pos)
	{
		super();
		if (value.isEmpty() || pos.isEmpty())
			throw new IllegalArgumentException();	
		this.value = value;
		this.partOfSpeech = pos;
	}
	

	/**
	 * Returns the value of the part of speech encountered in the corpus
	 * @return the value of the part of speech encountered in the corpus
	 */
	public String partOfSpeech()
	{
		return this.partOfSpeech;
	}

	/**
	 * Returns the value of the word encountered in the corpus.
	 * @return the value of the word encountered in the corpus
	 */
	public String value()
	{
		return this.value;
	}
	
	/**
	 * The value of the part of speech encountered in the corpus.
	 * @see Entry#Entry(String, String)
	 * @see Entry#partOfSpeech()
	 */
	private final String partOfSpeech;
	
	/**
	 * The value of the word encountered in the corpus.
	 * @see Entry#Entry(String, String)
	 * @see Entry#value()
	 */
	private final String value;

}
