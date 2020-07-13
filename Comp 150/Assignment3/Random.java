
/**
 * This class helps you generate random integers. It is a version that will
 * generate values distributed around the middle of the range with higher probability.
 * 
 * @author Dr Russell Campbell
 */
public class Random {
	private static int count = 0;
	/**
	 * This method generates a pseudorandom integer in the range [0, max).
	 * 
	 * @param max
	 *   This is the strict upper bound for the possible integers that will be generated.
	 * 
	 * @return
	 *   The generated pseudorandom integer.
	 */
	public static int rand(int max) {

		final int PRIME_1 = 4079; // different primes will change the random behaviour
		final int PRIME_2 = 9767; // its generally better to use larger primes
		long time = System.currentTimeMillis() % 1000000;
		long seed = time + count;
		double trig = Math.sin(PRIME_1 * seed + PRIME_2); // value between -1 and 1
		double func = Math.abs(PRIME_1 * trig); // make it positive and larger
		double frac = func - Math.floor(func); // digits past the decimal seem random
		double tran = mystery(frac);
    	count++;
		return (int) (tran * max); // get an integer in our range [0, max)
	}
	
	public static double mystery(double x) {
	  double p = Math.pow(x, 0.5);
	  return p / (p + Math.pow(1 - x, 0.5));
	}
}
