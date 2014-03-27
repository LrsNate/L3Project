import java.io.IOException;

public class Main
{
	public static void main(String[] args)
	{
		Timer timer = new Timer();
		try
		{
			Loader l = new Loader(args);
			FileHandler result = l.getResult();
			Corpus learn = l.getLearn().read();
			Corpus test = l.getTest().read();
			System.out.println("Corpora loaded in "+timer.lap()+"\n");
			
			Learner.getDictionary(learn);
			System.out.println("Learning finished in "+timer.lap()+"\n");
			
			Tester tester = new Tester(test);
			System.out.println("Testing finished in "+timer.lap());
			System.out.println(tester.getAccuracy());
			result.save(tester.getCorpus());
			System.out.println();
			
			System.out.println("Output saved in "+timer.lap()+"\n");
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
			System.exit(-1);
		}
	}
}
