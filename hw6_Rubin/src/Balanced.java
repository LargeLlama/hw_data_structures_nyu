//package balanced;

/*
 *  This is for a pure parenthesis expression involving
 *	several types of parenthesis.
 */

import java.util.Random;

public class Balanced {
    	static char[] left = {'(', '[', '{', '<'};
    	static char[] right = {'(', ']', '}', '>'};
	static Random rg;
	static int seed;
	// Constructor:
	Balanced(int s){
	    rg = (s==0)? new Random() : new Random(seed);
	}
		
	public static boolean bal(String s){
	        if (s.length()==0) return true;
	        if (s.length()==1) return false;
		int type = -1;
		// find the type of the first paren at charAt(0):
		for (int i=0; i< left.length; i++)
		    if (left[i] == s.charAt(0)) type=i;
		if (type<0) return false;  // charAt(0) is not a left paren.
		
		//Invariant: type = 0,1,2 or 3 (i.e., "(, [, {, <" ).
		
		for (int i=1; i< s.length(); i++){
		   if (s.charAt(i) == right[type]) {
			if (bal(s.substring(1,i))
			        && bal(s.substring(i+1,s.length()))  ) 
			       return true;
		   }//if
		}//for
		return false;
	}//bal

	// Generate a balanced parenthesis string of length n (or n-1)
	public static String balGen(int n, Random rg){
	    if (n<=1) return "";
	    int t = rg.nextInt(3);
	    String ss = ""+ left[t] + right[t]; 
	    if (n<=3) return ss;
	    String s = balGen(n-2, rg);
	    t = rg.nextInt(s.length());
	    return (s.substring(0,t) + ss + s.substring(t,s.length()));
	}//balGen
	
	//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	public static void main(String[] args) {
	//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	    seed =  (args.length>0) ? Integer.parseInt(args[0]) : 0;
	    int n = (args.length>1)? Integer.parseInt(args[1]) : 10;

	    rg = (seed > 0)? new Random(seed) : new Random();
	    String ss = (n>0) ? balGen(n,rg) : "(([[]]()[])[]){}";

	    // remove a random character:
	    int r = rg.nextInt(ss.length());
	    if (r==0) r=1;
	    String ss1 = ss.substring(0,r-1)
			 + ss.substring(r, ss.length()-1);

	    if (bal(ss))
	       System.out.printf("String \"%s\" is balanced\n",ss);
	    else
	       System.out.printf("String \"%s\" is unbalanced\n",ss);

	    if (bal(ss1))
	       System.out.printf("String \"%s\" is balanced\n",ss1);
	    else
	       System.out.printf("String \"%s\" is unbalanced\n",ss1);

	}//main

}//class
