
/**
 * <p>A collection of Word as they have been learnt by the application. Note
 *  that adding an element requires two String arguments as this class handles 
 * duplicate entries on its own and as such, it does not need to create an 
 * instance of Word with a PartOfSpeech attached to it. As the application 
 * has to be one and only one throughout the entire process of the application, 
 * this AVL Tree is also an implementation of Bill Pugh's Singleton pattern.
 * </p>
 * 
 * <p>As removing an element is not an operation supposed to be needed at any 
 * moment in the application, it has not been implemented at all.</p>
 * 
 * @author Antoine Lafouasse
 *
 */
public class Dictionary extends AVLTree<Word>
{
	/**
	 * Creates a new instance of Dictionary. No constructor can be called from
	 * outside of this class.
	 */
	private Dictionary()
	{
		super();
	}
	
	/**
	 * In conformity with Bill Pugh's Singleton pattern, this is the instance 
	 * holder class, which is of course private.
	 * @author Antoine Lafouasse
	 */
	private static class InstanceHolder
	{
		/**
		 * The one and only instance of Dictionary that will be lazily loaded.
		 */
		public final static Dictionary instance = new Dictionary();
	}
	
	/**
	 * The accessor to the instance of Dictionary.
	 * @return The only instance of Dictionary.
	 */
	public static Dictionary getInstance()
	{
		return InstanceHolder.instance;
	}

	/**
	 * Adds an occurrence of Word-PartOfSpeech couple while keeping the shape 
	 * of a BST and avoiding duplicate entries of Word.
	 * @param word The value of the word encountered in the corpus
	 * @param pos The value of the part of speech encountered in the corpus
	 */
	public void add(String word, String pos)
	{
		if (word.isEmpty() || pos.isEmpty())
			throw new IllegalArgumentException();
		try
		{
			Word w = new Word(word);
			w.addPartOfSpeech(pos);
			this.push(w);
		}
		catch (NodeAlreadyExistsException e)
		{
			((Word) e.getNode()).addPartOfSpeech(pos);
		}
	}
	
	/**
	 * Sorts every PartOfSpeech in each Word of the Dictionary by descending 
	 * order of occurrences.
	 */
	public void sort()
	{
		this.sortRec(this.root);
	}
	
	/**
	 * The recursive method used by sort().
	 * @param root The root of the tree we will be sorting.
	 * @see Dictionary#sort()
	 */
	private void sortRec(Word root)
	{
		root.sort();
		if (root.hasLeft())
			this.sortRec(root.left());
		if (root.hasRight())
			this.sortRec(root.right());
	}
}