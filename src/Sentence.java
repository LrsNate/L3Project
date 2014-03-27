/**
 * The tokenised representation of a corpus. It is fundamentally a queue 
 * containing instances of Entry.
 * @author Antoine Lafouasse
 *
 */
public class Sentence extends Queue<Entry>
{	
	/**
	 * Adds an element to the queue.
	 * @param word The value of the word we need to add
	 * @param pos The part of speech encountered in the corpus
	 */
	public void push(String word, String pos)
	{
		if (word.isEmpty() || pos.isEmpty())
			throw new IllegalArgumentException();
		Entry e = new Entry(word, pos);
		this.push(e);
	}
}
