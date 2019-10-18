/* file: FibBig.java
 *		(basically same as Fib3.java)
 *
 * Computing Fibonacci numbers using
 *		-- the method of Memo-ization
 *		-- and representing integers by BigInteger
 *
 *	Chee Yap
 *	Data Structures (CS102)
 *	2019 fall
 */
import java.math.BigInteger;

class FibBig {
	// METHODS:
	static BigInteger fibBig (int n) {
		return fib(BigInteger.valueOf(n));
	}
    static BigInteger fib (BigInteger n) {
		if (n.compareTo(BigInteger.ONE)<=0) return n;
		BigInteger[] pair = new BigInteger[2];
		pair[0] = BigInteger.ZERO;
		pair[1] = BigInteger.ONE;
		for (int i=1; n.compareTo(BigInteger.valueOf(i))>0; i++)
			pair = nextPair(pair);
		return pair[1];
    }
	static BigInteger[] nextPair(BigInteger[] p){
		BigInteger tmp = p[0].add(p[1]);
		p[0] = p[1];
		p[1] = tmp;
		return p;
	}
	//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    public static void main (String[] blah){
	//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
		int nn = (blah.length>1)? Integer.valueOf(blah[1]) : 100;

		BigInteger N = BigInteger.valueOf(nn);
		BigInteger F;
		long start, stop;
	
		System.out.printf( "\nFirst Test of fib(int)\n");
		start = java.lang.System.currentTimeMillis();
		for (int i=0; i<3; i++){
			F = fib(N.add(BigInteger.valueOf(i-1)));
			System.out.printf( "  fib(%d) = %s\n", 
						i+nn-1,
						BigUtil.show35(F));
		}
		stop = java.lang.System.currentTimeMillis();
		System.out.printf( "1st Time taken is %.2f seconds\n", 
							(stop-start)/1000.0);
		
		System.out.printf( "\nSecond Test of fib(int)\n");
		start = java.lang.System.currentTimeMillis();
		for (int i=0; i<3; i++){
			F = fib(N.add(BigInteger.valueOf(i-1)));
			System.out.printf( "  fib(%d) = %s\n",
						i+nn-1,
						BigUtil.show35(F));
		}
		stop = java.lang.System.currentTimeMillis();
		System.out.printf( "2nd Time taken is %.2f seconds\n", 
						(stop-start)/1000.0);
    }//main
}//class
