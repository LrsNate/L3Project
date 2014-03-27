import java.util.NoSuchElementException;

/**
 * The object representation of a Corpus. In formal terms this is a simplified 
 * hashtable (or associative array) of sentences, with an integer key which 
 * represents their order in the original text. Note that the key has to be an 
 * integer (int), and that it has to be given by the user as it will not be 
 * computed in any way.
 * @author Antoine Lafouasse
 *
 */
public class Corpus
{
	/**
	 * Creates a new empty instance of Corpus, with the default size stated in 
	 * a static attribute.
	 * @see Corpus#defaultSize
	 */
	public Corpus()
	{
		this(Corpus.defaultSize);
	}
	
	/**
	 * Creates a new empty instance of Corpus with the size stated in argument.
	 * @param size The size of the new Corpus.
	 */
	public Corpus(int size)
	{
		this.content = new Sentence[size];
	}
	
	/**
	 * Fetches and returns the Sentence stored with the integer in argument as 
	 * the key.
	 * @param i The key of the Sentence we will be fetching.
	 * @return An instance of Sentence.
	 * @throw NoSuchElementException If there is no such Sentence.
	 */
	public Sentence get(int key)
	{
		if (key > this.content.length || this.content[key] == null)
			throw new NoSuchElementException();
		return this.content[key];
	}
	
	/**
	 * Computes and returns a sorted array of all the keys stored in the table.
	 * @return A sorted array of all the keys.
	 */
	public int[] keys()
	{
		int count = 0;
		for (int i = 0 ; i < this.content.length ; i++)
		{
			if (this.content[i] != null)
				count++;
		}
		int[] result = new int[count];
		int cursor = 0;
		for (int i = 0 ; i < this.content.length; i++)
		{
			if (this.content[i] != null)
			{
				result[cursor] = i;
				cursor++;
			}
		}
		return result;
	}
	
	/**
	 * Puts and overrides if needed a Sentence with its associated key in the 
	 * table.
	 * @param i The key to be inserted in the table.
	 * @param s The Sentence to be inserted in the table.
	 */
	public void put(int key, Sentence value)
	{
		if (key >= this.content.length)
		{
			synchronized (this)
			{
				Sentence[] replacement = new Sentence[key+1];
				for (int i = 0 ; i < this.content.length ; i++)
				replacement[i] = this.content[i];
				this.content = replacement;
			}
		}
		this.content[key] = value;
	}
	
	/**
	 * The hashtable itself, so to speak. It can be resized by put() and its 
	 * starting size has been defined in whichever constructor was called.
	 */
	private volatile Sentence[] content;
	
	/**
	 * The default starting size for the table, which will be used by the 
	 * default constructor.
	 */
	private static final int defaultSize = 500;
}
