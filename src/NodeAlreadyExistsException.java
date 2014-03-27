/**
 * An exception thrown by Dictionary's pushRec() if there is already a node 
 * containing identical data in the tree. This particular exception thus stores 
 * a reference to the node at stake, so that adding a PartOfSpeech occurrence 
 * does not need a second lookup in the tree.
 * @author Antoine Lafouasse
 *
 */
public class NodeAlreadyExistsException extends Exception
{
	/**
	 * Creates a new instance of NodeAlreadyExistsException.
	 * @param pin The node in the tree sent by the Dictionary.
	 */
	@SuppressWarnings("rawtypes")
	public NodeAlreadyExistsException(AVLNode pin)
	{
		this.node = pin;
	}
	
	/**
	 * Returns the node sent by the Dictionary.
	 * @return The node sent by the Dictionary.
	 */
	@SuppressWarnings("rawtypes")
	public AVLNode getNode()
	{
		return this.node;
	}
	
	/**
	 * The node sent by the Dictionary.
	 */
	@SuppressWarnings("rawtypes")
	private AVLNode node;
	
	/**
	 * Default value of a serializable class serial version UID.
	 */
	private static final long serialVersionUID = 1L;

}
