import java.util.NoSuchElementException;

/**
 * A class whose role is to confront a corpus to a dictionary. The result of 
 * the confrontation can then be fetched through the accessors.
 * @author Antoine Lafouasse
 *
 */
public class Tester
{
	/**
	 * Builds a new instance of Tester and confronts the corpus and the 
	 * dictionary
	 * @param d The dictionary to be tested
	 * @param c The corpus to be used as reference
	 */
	public Tester(Corpus c)
	{
		this.guess = new Corpus();
		this.totalGuesses = Tester.defaultTotalStart;
		this.firstGuess = new Result();
		for (int i : c.keys())
		{
			Sentence s = new Sentence();
			while (!c.get(i).isEmpty())
			{
				Entry e = c.get(i).pop();
				s.push(e.value(), this.test(e));
			}
			this.guess.put(i, s);
		}
	}
	
	/**
	 * Returns the accuracy of the dictionary on the corpus
	 * @return A ready-to-print message containing each loop's accuracy rounded 
	 * to 10^-2%
	 */
	public String getAccuracy()
	{
		StringBuffer s = new StringBuffer();
		int rank = 1;
		int acc = 0;
		boolean first = true;
		Result r = this.firstGuess;
		while (true)
		{
			if (first)
				first = false;
			else if (r.hasNext())
			{
				r = r.next();
				rank++;
			}
			else
				break;
			acc += r.value();
			double res = (double) acc / (double) this.totalGuesses;
			res = (double) Math.round(res * 10000) / 100.0d;
			s.append(rank+"-best : "+res+"% ");
			s.append("("+acc+"/"+this.totalGuesses+")\n");
		}
		return s.toString();
	}
	
	/**
	 * Return the corpus with the PartOfSpeech guessed by the dictionary.
	 * @return An instance of Corpus
	 */
	public Corpus getCorpus()
	{
		return this.guess;
	}
	
	/**
	 * Confronts one entry from a Corpus to the Dictionary.
	 * @param e The entry from the Corpus to serve as comparison reference.
	 * @return The part of speech guessed by the Dictionary.
	 */
	private String test(Entry e)
	{
		this.totalGuesses++;
		Result r = this.firstGuess;
		try
		{
			PartOfSpeech p = Dictionary.getInstance().find(e.value())
					.getFirst();
			while (true)
			{
				if (p.toString().equals(e.partOfSpeech()))
				{
					r.increment();
					return p.toString();
				}
				else if (p.hasNext())
				{
					p = p.next();
					if (!r.hasNext())
						r.setNext(new Result());
					r = r.next();
				}
				else
				{
					return "UKN";
				}
			}
		}
		catch (NoSuchElementException ex)
		{
			return "UKN";
		}
	}
	
	/**
	 * The number of words that have been correctly guessed.
	 */
	private Result firstGuess;
	
	/**
	 * The most accurate corpus possibly generated by the dictionary.
	 */
	private Corpus guess;
	
	/**
	 * The length of the test corpus (i.e. the number of words in total).
	 */
	private int totalGuesses;
	
	/**
	 * The default initialisation value for the totalGuesses attribute.
	 * @see Tester#totalGuesses
	 * @see Tester#Tester(Corpus)
	 */
	private static final int defaultTotalStart = 0;
}