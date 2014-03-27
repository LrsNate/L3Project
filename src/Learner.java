
/**
 * A simple class whose sole task is to read a corpus and fill the dictionary 
 * with its sorted content. This dictionary must be a balanced tree and each
 * Word contained in it must have a list of PartOfSpeech sorted by occurrences
 * in descending order.
 * @author Antoine Lafouasse
 *
 */
public class Learner
{
	/**
	 * Reads the corpus and fills the dictionary with its content.
	 * @param c An instance of Corpus
	 */
	public static void getDictionary(Corpus c)
	{	
		for (int i : c.keys())
		{
			Sentence s = c.get(i);
			while (!s.isEmpty())
			{
				Entry e = s.pop();
				Dictionary.getInstance().add(e.value(), e.partOfSpeech());
			}
		}
		Dictionary.getInstance().sort();
	}
}
