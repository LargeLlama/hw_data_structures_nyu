/* MatrixBig.java
 *
 * Chee Yap, CS102 Data Structures
 * Fall 2019
 *****************************************/
import java.util.Random;
import java.math.BigInteger;

class MatrixBig {
	// MEMBER:========================================
		BigInteger[][] mat;  					
	// CONSTRUCTOR========================================
	MatrixBig(){ mat = new BigInteger[2][2]; }
	MatrixBig(BigInteger a, BigInteger b, BigInteger c, BigInteger d){
		mat = new BigInteger[2][2];
		mat[0][0]=a; mat[0][1]=b;
		mat[1][0]=c; mat[1][1]=d;
	}
	// METHODS:========================================
	static void debug(String msg){
		System.out.println(msg);}
	static MatrixBig IDENT(){
		return new MatrixBig(BigInteger.ONE, BigInteger.ZERO,
				BigInteger.ZERO, BigInteger.ONE);
	}
	static MatrixBig NXT(){
		return new MatrixBig(BigInteger.ONE, BigInteger.ONE,
				BigInteger.ONE, BigInteger.ZERO);
	}
	MatrixBig add(MatrixBig B){ // self addition
		for (int i=0; i<2; i++)
			for (int j=0; j<2; j++){
				mat[i][j] = mat[i][j].add( B.mat[i][j] );
			}
		return this;
	}
	MatrixBig mul(MatrixBig B){ // self multiplication
		BigInteger[][] cmat = new BigInteger[2][2];
		for (int i=0; i<2; i++)
			for (int j=0; j<2; j++){
				cmat[i][j] = new BigInteger("0");
				for (int k=0; k<2; k++)
					cmat[i][j] =
						cmat[i][j].add(mat[i][k].multiply(B.mat[k][j]));
			}
		mat = cmat;
		return this;
	}
	MatrixBig square(){
		return this.mul(this);
	}
	//METHODS ========================================
	//usage: show("n = "+ Integer.toString(n));
	void show(String msg){
		System.out.println(msg);
		System.out.printf("[%s, %s, %s] \n",
				BigUtil.show35(mat[0][0]),
				BigUtil.show35(mat[0][1]),
				BigUtil.show35(mat[1][1]));
	}
	static int[] bin(int n) {
		String tmp = Integer.toBinaryString(n);
		//System.out.println(tmp);
		int[] retArr = new int[tmp.length()];
		for(int i = 0; i < tmp.length(); i++)
			retArr[i] = Integer.parseInt(tmp.substring(i, i + 1));
		return retArr;
	}
	static MatrixBig power(int n){
		if(n == 0) {	//if you raise something to the zero power, you get the ident matrix
			return IDENT();
		} else if (n == 1) {	//if you raise something to the first power, you get itself (NXT() in this case)
			return NXT();
		} else {				//otherwise, we start multiplying
			MatrixBig fib = IDENT();	//start off with the ident matrix and the next matrix
			MatrixBig next = NXT();
			for(int i = n; i > 0; i /= 2){				//as long as N is above zero
				if (i % 2 == 1)			//if n is odd, multiply it by the next matrix
					fib = fib.mul(next);
				next = next.mul(next);	//
			}
			return fib;
		}
	}//power

	static BigInteger fibLog(int n) {
		MatrixBig start = MatrixBig.power(n);
		BigInteger fibN = start.mat[0][1];
		return fibN;
	}

	static double[] compare(int n) {
		double TT = 0, tt = 0, rr = 0;	//TT is avg of 3 times for fibBig, tt is avg of 3 times for fibLog, and rr is TT/tt
		long start, end, tmp;	//keep track of time
		//BigInteger values to stay consistent
		BigInteger fibBigNum = BigInteger.valueOf(n);
		BigInteger fibTest;

		//The tests to get the value of TT
		for(int i = 0; i < 3; i++) {
			start = java.lang.System.currentTimeMillis();
			fibTest = FibBig.fib(fibBigNum);
			end = java.lang.System.currentTimeMillis();
			tmp = end - start;
			TT += tmp / 1000.0;
			tmp = 0;
		}
		TT /= 3.0;
		//System.out.println("Value of TT after 3 tests: " + TT);

		//The tests to get the value of tt
		for(int i = 0; i < 3; i++) {
			start = java.lang.System.currentTimeMillis();
			fibTest = FibBig.fib(fibBigNum);
			end = java.lang.System.currentTimeMillis();
			tmp = end - start;
			tt += tmp / 1000.0;
			tmp = 0;
		}
		tt /= 3.0;
		//System.out.println("Value of tt after 3 tests: " + tt);

		//rr is just TT/tt
		rr = TT / tt;

		//System.out.println("Value of rr (TT/tt): " + rr);
		double[] retArray = {TT, tt, rr};
		return retArray;
	}
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
public static void main (String[] blah){
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
		int nn = (blah.length>1)? Integer.valueOf(blah[1]) : 100;
		int mm = (blah.length>2)? Integer.valueOf(blah[2]) : 3;
		
		MatrixBig M; 
		for (int i=nn; i<nn+mm; i++){
			M = MatrixBig.power(i);
			M.show("N to the power n=" + i + "is:");
		}
		
		//The tests to print out the values of TT, tt, and rr for each test
		for (int i = 100; i <= 1000000; i *= 10) {
			System.out.println("Current value of n: " + i);
			double[] times = MatrixBig.compare(i);
			System.out.println("TT, tt, and rr (respectively): ");
			for(double s : times)
				System.out.print(s + " ");
			System.out.println("");
		}
	}//main
}//MatrixBigClass

