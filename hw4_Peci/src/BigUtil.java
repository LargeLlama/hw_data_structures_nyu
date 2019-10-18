/* BigUtil.java
 * We test the routines on very BigIntegers using the
 *	following "Fibonacci-like" function
 *		H(n) =	0				   if n<2
 *				1			   	   if n=2
 *				2*H(n-1)+ H(n-3)   if n>2
 *	E.g.,
 *				n 	= 0 1 2 3 4 5  6
 *				H(n)= 0 0 1 2 4 9 20
 *  Useful utility functions:
 *	We want to print the BigInteger in ellipsis, showing
 *		only the first few digits, and the last few digits, and 
 *		the total number of digits:  
 *	show35(N):	shows the first 3 and last 5 digits only,
 *					and the total number of digits (in parenthesis):
 *				N=12345678		=> 12345678
 *				N=123456789		=> 123...56789 (9)
 *				N=1234567890	=> 123...67890 (10)
 ***************************************************/
import java.math.BigInteger;
class BigUtil {
	static void debug(String msg){
		System.out.println(msg);
	}
	static BigInteger hh(int n) {
		BigInteger[] base =
				{BigInteger.ZERO, BigInteger.ZERO, BigInteger.ONE};
		return hh(n, base);
	}
	static BigInteger hh(int n, BigInteger[] base) {
		if (n<=1) return base[0]; // in case n is negative, this works.
		if (n==2) return base[2];
		BigInteger tmp = base[0].add(base[2]).add(base[2]);
		base[0] = base[1];
		base[1] = base[2];
		base[2] = tmp;
		return hh(n-1, base);
	}
	static String show35(BigInteger N) {// show first 3 and last 5 digits
		BigInteger M = new BigInteger("10000000");
		if (N.compareTo(M)<=0) return N.toString();
		String ss = N.toString();
		int len = ss.length();
		return (ss.substring(0,2) 
				+ "..." + ss.substring(len-5,len)
				+ "(" + String.valueOf(len) + ")");
	}
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	public static void main(String[] args){
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
		// int ss is args[0], not used
		int nn=(args.length>1)? Integer.valueOf(args[1]) : 21;

		for (int i=-1; i<4; i++){
			BigInteger N = hh(nn+i);
			System.out.printf("H(%d) = %s\n", nn+i, show35(N));
		}

	}//main
}
