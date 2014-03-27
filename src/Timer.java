/**
 * A simple timer counting in milliseconds.
 * @author Antoine Lafouasse
 *
 */
public class Timer
{
	/**
	 * Creates a new timer and starts it.
	 */
	public Timer()
	{
		this.start = System.currentTimeMillis();
		this.lastLap = this.start;
	}
	
	/**
	 * Creates an intermediate time, and return the time between the last two 
	 * intermediates (or laps) and the time elapsed since the timer's start.
	 * @return A ready-to-print string containing the time between the last two 
	 * intermediates (or laps) and the time elapsed since the timer's start.
	 */
	public String lap()
	{
		long current = System.currentTimeMillis();
		long lap = current - this.lastLap;
		this.lastLap = current;
		long total = current - this.start;
		return lap+"ms (total: "+total+"ms)";
	}
	
	/**
	 * The time at which the lap() method was last called.
	 * @see Timer#lap()
	 */
	private long lastLap;
	
	/**
	 * The time at which the timer started.
	 */
	private final long start;
}
