import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * A class used to handle one file. Its role will be to either fetch or create 
 * a file with the path transmitted in argument, and either save a Corpus to 
 * this file or create one from it.
 * @author Antoine Lafouasse
 *
 */
public class FileHandler
{
	/**
	 * Creates a new handler (i.e. an object representation of a file to work
	 * on).
	 * @param path A path to a file, existing or not.
	 * @throws IOException If an empty file could not be created.
	 */
	public FileHandler(String path) throws IOException
	{
		if (path.isEmpty())
			throw new IllegalArgumentException();
		this.target = new File(path);
		if (!this.target.exists())
			this.target.createNewFile();
	}
	
	/**
	 * Checks whether the file is empty.
	 * @return true if the file is empty, false otherwise.
	 */
	public boolean isEmpty()
	{
		try
		{
			BufferedReader r = new BufferedReader(new FileReader(this.target));
			boolean result = (r.readLine() == null);
			r.close();
			return result;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.exit(-1);
		}
		return true; 
	}
	/**
	 * Reads the file and tokenises it into a Corpus
	 * @return An instance of Corpus
	 * @throws IOException If this file is either empty or incorrectly 
	 * formatted
	 */
	public Corpus read() throws IOException
	{
		Corpus c = new Corpus();
		BufferedReader r = new BufferedReader(new InputStreamReader(
				new FileInputStream(this.target), "utf-8"));
		int i = 0;
		String l = new String();
		while((l = r.readLine()) != null)
		{
			Sentence result = new Sentence();
			for (String s : l.split(" "))
			{
				String[] w = s.split("__");
				if (w.length != 2)
				{
					System.out.println("Invalid word in corpus");
					System.exit(-1);
				}
				result.push(w[0], w[1]);
			}
			c.put(i, result);
			i++;
		}
		r.close();
		return c;
	}
	
	/**
	 * Saves a corpus into the file
	 * @param c A non-empty instance of Corpus
	 * @throws IOException In case of any writer failure
	 */
	public void save(Corpus c) throws IOException
	{
		StringBuffer s = new StringBuffer();
		BufferedWriter w = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(this.target), "utf-8"));
		for (int i : c.keys())
		{
			Sentence se = c.get(i);
			while (!se.isEmpty())
			{
				Entry en = se.pop();
				s.append(en.value()+"__"+en.partOfSpeech()+" ");
			}
			s.append("\n");
		}
		w.write(s.toString());
		w.close();
	}
	
	/**
	 * The object representation of an object. It cannot be modified after it 
	 * has been set in the constructor.
	 * @see FileHandler#FileHandler(String)
	 */
	private final File target;
}
