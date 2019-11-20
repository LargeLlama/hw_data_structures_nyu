/*
 * file: expr/ExprX.java
 *
 *	You are to implement this class.
 *
 *	It implements balanced/ExprXAbstr class, so read that file
 *		for details first.
 *
 *	We import Queue and Stack classes
 *		-- Stack comes from Java.util,
 *			so it has push and pop as expected.
 *	
 *		-- There is no direct Queue implementation in Java,
 *			and so I "roll my own" to have
 *			enqueue and dequeue operations.
 *
 *	DO NOT REIMPLEMENT THE MAIN METHOD HERE!
 *
 *  Chee Yap
 *  Data Structures
 *  Fall 2016
 *****************************************/
//package expr;

import java.lang.Integer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.*;
import java.util.Random;
//import java.util.Stack;
// import util.MyQueue; // our own implementation!

public class ExprX extends Expr {
//========================================
// MEMBERS:
//========================================
       static int verbosity = 0;  // larger value means more verbose
       MyQueue<String> inQ = new MyQueue<String>();
       MyQueue<String> outQ = new MyQueue<String>();
       MyStack<String> stk = new MyStack<String>();
	
       enum TokenEnum 
	   		{OPERATOR, OPERAND, LEFTPAREN, RIGHTPAREN, ERROR};
	   
	   static String[] good = {
			"123 - 456 / 789",
			"1.1 - 2 * 3",
			"1 / 2 - 3",
			"1 - 2 + 3 - 4 + 5 - 6",
			"1 - ( 2 + 3 - 4 ) + 5 - 6",
			"1 - ( 2 + 3 - ( 4 + 5 ) - 6 )",
			"1 - 2 + ( 3 - ( 4 + 5 ) - 6 )",
			"1 - ( 2 + 3 ) - 4 + ( 5 - 6 )",
	   };

	   static String[] bad = {
			"12 + x",
			"1 - ( 2 +", "1 - 2 )",
			"1 - )", "1 - *",
			"1 (", "1 2",
			"1 - ( )", "1 - ( *",
			"( 1 ) (", "( 1 ) 2"	
	   };

	   static double[] answers = {
			122.422053,-4.9, -2.5, -3, -1, 11, -13, -9,
			-1, 1, 2,
			3, 3, 4, 4, 5, 5, 6
	   };

//========================================
// CONSTRUCTORS:
//========================================
	ExprX(int seed){
		super(seed); }
//========================================
// METHODS:
//========================================
	/***************************************************
	 * method bal(ss) checks if ss is balanced with respect to the
	 *	parenthesis "( )" in ss.   It returns
	 *	-1	if ss does not match "[0-9 ()+-*]+"
	 *	0	else if ss is balanced with respect to "()"
	 *	1	else if ss could be balanced by adding one or more ")".
	 *	2	else
	 ***************************************************/
	@Override
	public int bal(String ss){
		int count=0;
		for (int i=0; i< ss.length(); i++){
			if (ss.charAt(i)=='(') count++;
			if (ss.charAt(i)==')') count--;
			if(count<0) return count;
		}
	    return count;
	}//bal
	
	@Override
	public int valid(String ss){
		// We just do the minimal work, checking for balanced parenthesis.
		// WE DO NOT DIRECTLY CHECK FOR FULL VALIDITY!
		// 		If it is not valid, it will be detected in
		//		parsing phase or in the conversion phase
	    return bal(ss);
	}//valid

	/***************************************************
	 * method expr(n, rg) generates a random expr with n operands.
	 *	USE THIS RECURSIVE RULE:
	 *	  if n=1 then
	 *		expr(n) =  x
	 *	  else
	 *		expr(n) =
	 *		     expr(m) op expr(n-m)
	 *		     | ( expr(m) ) op expr(n-m) 
	 *		     | expr(m) op ( expr(n-m) )
	 ***************************************************/
	@Override
	public String expr(int n, Random rg){
		return generateExprX(n,rg).substring(1);
	}//expr

	public String generateExprX (int n, Random rg){
		if (n==1) {
			return " " + Integer.toString(rg.nextInt(10)) + " ";
		} else {
			int m = rg.nextInt(n - 1) + 1;
			switch (rg.nextInt(3)) {
				case 0:
					return generateExprX(m, rg)
						+ generateOperator(rg) + generateExprX(n - m, rg);
				case 1:
					return " ( " + generateExprX(m, rg) + " ) "
						+ generateOperator(rg) + generateExprX(n - m, rg);
				case 2:
					return generateExprX(m, rg) + generateOperator(rg)
						+ " ( " + generateExprX(n - m, rg) + " ) ";
			}
		}
		return "";
	}
	String generateOperator(Random rg){
		switch (rg.nextInt(3)){
			case 0:
				return " + ";
			case 1:
				return " - ";
			case 2:
				return " * ";
		}
		return null;
	}

	/***************************************************
	 * evalX(ss)
	 *	evalXuates and returns the value of
	 *	the expression ss.
	 ***************************************************/
	public double evalX(String ss) {// throws Exception {
		if (parse(ss)>0)
				return Integer.MIN_VALUE;
	    if (convert()>0)	// first convert it into postfix form 
	    					// storing the result in outQ.
				return Integer.MAX_VALUE;
	    return postfixEvalX();
	}

	/***************************************************
	 * DO NOT HAVE TO IMPLEMENT THIS!!! 
	 * ================================
	 * method badExprX(n, rg)
	 * 	This will initially get an expression using expr(n, rg).
	 *	Then it randomly removes a token to "unbalance it".
	 *	We will  implement this to test your bal(..) method.
	 ***************************************************/
	public String unbalGen(int n, Random rg) {
		ArrayList<String> tokens
			= new ArrayList<String>(
				Arrays.asList(
					generateExprX(n, rg).substring(1).split(" +")));
		tokens.remove(rg.nextInt(tokens.size()));
		String result = "";
		for (String op : tokens) {
			result = result + op + " ";
		}
		return result;
	}
//========================================
// MAIN METHOD:
//========================================
   public static void main(String[] args) {
	    int seed =  (args.length > 0) ? Integer.valueOf(args[0]) : 0;
	    int nn =  (args.length > 1) ? Integer.valueOf(args[1]) : 6;//nn=0..14
	    int mm =  (args.length > 2) ? Integer.valueOf(args[2]) : 0;//mm=0..2
	    verbosity =  (args.length > 3) ? Integer.valueOf(args[3]) : 0;
	
	    ExprX ex = new ExprX(seed);
	    // The test depends on the mode: 
		if (mm>2) { System.out.printf(
				"mode mm=%d not implemented, set to 0\n", mm);
				mm=0; }
		// Note: if mm=1, then we use nn to select an expression
		//		to evalXuate.  If mm=0, we do not look at value of nn.
	    System.out.printf("========> Testing Mode %d:\n", mm);
		switch (mm){
			case 0:
				int i=0;
	    		System.out.printf("Testing inbuilt ExprXessions \n");
		     	for (String ss : good){
	    			System.out.printf("%d-th ExprXession is \"%s\"\n",
							i++, ss);
					if (ex.valid(ss)==0) {
						double v = ex.evalX(ss);				
	    				System.out.printf("   It evalXuates to %f, ",v);
						if (v == ex.answers[i-1] || v <  ex.answers[i-1] + .001 || v > ex.answers[i-1] - .001)
	    					System.out.printf(" correct!\n");
						else
	    					System.out.printf(" ERROR!\n");
					}
					else
	    				System.out.printf("   It is not valid\n");
				}
				break;
			case 1:
	    		System.out.printf("   Mode 1 not yet implemented\n");
				break;
			case 2:
	    		System.out.printf("   Mode %d not yet implemented\n", mm);
		}//switch
	}//main

//========================================
// HELPER METHODS:
//========================================
   // DETERMINE the type of a token:
   TokenEnum theTypeX(String tok){
       if (tok.matches("[0-9\\.]+"))
	       return TokenEnum.OPERAND;
       if (tok.matches("[+-/]|\\*"))
	       return TokenEnum.OPERATOR;
       if (tok.matches("\\("))
	       return TokenEnum.LEFTPAREN;
       if (tok.matches("\\)"))
	       return TokenEnum.RIGHTPAREN;
       return TokenEnum.ERROR;
   }//theTypeX

   // LOAD the expression ss as a sequence of tokens into the input Queue:
   int parse(String ss) { //throws Exception {
       String[] toks = ss.split(" +");
       for (int i = 0; i< toks.length; i++){
	   	inQ.enqueue(toks[i]);
		if (theTypeX(toks[i])==TokenEnum.ERROR)
			return (i+1);
		    //throw new Exception("load: TokenEnum.ERROR, " + toks[i]);
	   	if (verbosity>9)
	   	    System.out.printf("Token = %s of type %s\n",
			    toks[i], theTypeX(toks[i]));
       }//for
	   return 0;
   }//parse

   // COMPARE TWO OPERATORS and return true if op1 <= op2 :
   boolean lessOrEqual(String op1, String op2){
       if (verbosity>9)
	   System.out.printf("%s <= %s: %s\n", op1, op2,
		   (op2.matches("\\*") || op1.matches("[+-]") ));
       return
	   (op2.matches("\\*") || op1.matches("[+-]") );
   }//

   // THIS converts the tokens in inQ
   // 	into a postfix form, storing result in outQ.
   //	Return 1 if error; else return 0.
   int convert () {// 
	while (!inQ.isEmpty()){
	    String tok = inQ.dequeue();
	    switch (theTypeX(tok)){
			case OPERAND:
			    	outQ.enqueue(tok);
			    	break;
			case LEFTPAREN:
			    	stk.push(tok);
			    	break;
			case RIGHTPAREN:
				while (!stk.isEmpty()
						&&
					theTypeX(stk.peek())!=TokenEnum.LEFTPAREN)
					    outQ.enqueue(stk.pop());
				// precondition: there is a LEFTPAREN
				stk.pop();
			    	break;
			case OPERATOR:
				while ((!stk.isEmpty())
					    	&&
					theTypeX(stk.peek())!=TokenEnum.LEFTPAREN
					    	&&
					lessOrEqual(tok,stk.peek()))
				   outQ.enqueue(stk.pop()); //endwhile
				stk.push(tok);
			    	break;
			default:
				// ERROR
				// System.out.println("ERROR in parsing expression!");
				return 1;
	    }//switch
	}//while
	while (!stk.isEmpty())
		outQ.enqueue(stk.pop()); //endwhile
//showQs("End of convert");
	return 0;
   }//convert

   // This evalXuates the postfix expression in outQ:
   // precond: stk is empty
   double postfixEvalX(){
       int val;
       String tok;
       double op1, op2;
       while (!outQ.isEmpty()){
	   tok = outQ.dequeue();
	   switch (theTypeX(tok)){
	       case OPERAND:
		   stk.push(tok);
		   break;
	       case OPERATOR:
		   op2 = Double.valueOf(stk.pop());
		   op1 = Double.valueOf(stk.pop());
		   if (tok.matches("\\+"))
		       stk.push(String.valueOf(op1 + op2));
		   else if (tok.matches("-"))
		       stk.push(String.valueOf(op1 - op2));
		   else if (tok.matches("\\*")) 
		       stk.push(String.valueOf(op1 * op2));
		   else if (tok.matches("\\/"))
			   stk.push(String.valueOf(op1 / op2));
		   break;
	   }//switch
       }//postfixEvalX
       return Double.valueOf(stk.pop());
   }// postfixEvalX

   // Show both Queues:
   void showQs(String msg){
	if (verbosity>0) {
   	    System.out.printf("::: %s\n", msg);
   	    System.out.printf("   Stack: \n");
            for (String s : stk)
   	          System.out.printf(" %s ", s);
   	    System.out.printf("\n");
   	    System.out.printf("   Input Queue: \n");
            for (String s : inQ)
   	          System.out.printf(" %s ", s);
   	    System.out.printf("\n");
   	    System.out.printf("   Output Queue: \n");
            for (String s : outQ)
   	          System.out.printf(" %s ", s);
   	    System.out.printf("\n");
	}
   }
}//class
