/* MatchAB.java
 *
 *    String[]
 *			AA = {"pit","spot", "spate", "slaptwo", "respite"},
 *    		BB = {"pt","Pot", "peat", "part"};
 *
 *	automatic testing of patterns for 
 *		-- matching every string in array AA,
 *		-- matching no string in array BB.
 *
 *	We give 3 possible solutions below:
 *
 *    	  pat0 = "pit|spot|spate|slaptwo|respite"
 *    	  pat1 = "[^pP].*|.*[^t]|pit"
 *    	  pat2 = "pit|s.*|^r.*"
 *
 *	Also, some non-solutions:
 *    	  pat3 = "pit|spot|spate|slaptwo|respite|pt"
 *    	  pat4 = "pit|spot|spate|slaptwo"
 *
 *	Exercise: Produce this behavior: if nn>0, it has
 *		the current behavior.  But if nn=0,
 *		you randomly generate some 
 *		instances for AA and BB (using the seed ss)
 *		and ask the user to input a pattern, PAT.
 *		Then print whether PAT is a solution, has false positive only,
 *		or false negative only, or both kinds of false.
 *
 * Prof Chee Yap
 * Data Structures (CS102)
 * Fall 2016
 */
// package match;

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

class MatchAB {
    ////////////////////////////////////////////////////
    // METHODS:
    ////////////////////////////////////////////////////

    // show the contents of array list:
    ////////////////////////////////////////////////////
    static void show(ArrayList<String> A, String msg){
	System.out.println(msg);
	for (String aa : A)
		System.out.printf("   %s ", aa);
	System.out.println("\n===========");
    }//show

    // Load the from file to an array list:
    //		-- ignore empty lines and #-lines
    ////////////////////////////////////////////////////
    static void load(String infile, ArrayList<String> out)
    		throws Exception {
      Scanner ifile = new Scanner(new File(infile));
      while (ifile.hasNext()){
	  String ll = ifile.nextLine();
	  if ((ll.length()>0) && !ll.matches(".*#.*"))
		out.add(ll);
      }//while
      ifile.close();
    }//load

    // Test if
	//		(1) all in alist matches pat, and
    //		(2) none in blist matches pat
	// Return	0 is no errors,
	//			1 if errors in only one list,
	//			2 if errors in both lists.
    ////////////////////////////////////////////////////
    static int test(String pat,
	    	ArrayList<String> alist, ArrayList<String> blist){
         int err=0;
         System.out.printf("\nPattern: \"%s\"\n", pat);
         for (String ss : alist){
            if (!ss.matches(pat)) {// if a mismatch in Alist
                  err++;
                  break;
            }
         }
         for (String ss : blist){
            if (ss.matches(pat)) {// if a match in Blist
                if (err>0){
                   System.out.println("  False Positive and False Negative!");
                    return 2;
                } else {
                    System.out.println("  False Positive only!");
                    return 1;
                }
            }
         }//for
         if (err>0) {
            System.out.println("  False Negative only!");
			return 1;
		 }
         else {
            System.out.println("  Pattern is a solution!");
			return 0;
		 }
    }//test


  // %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
  public static void main(String[] args)
      				throws Exception {
  // %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
     // Please keep these default arguments:
	  // args[0] is seed, but it is not used for this hw!
      int nn = (args.length>1) ? Integer.valueOf(args[1]) : 1 ;
      		// if nn=0, then read pattern from command line
      		// if nn=1, then read patterns from pattern file
      String pfile = (args.length>2) ? args[2] : "src/ppp.txt";
      String afile = (args.length>3) ? args[3] : "src/aaa.txt";
      String bfile = (args.length>4) ? args[4] : "src/bbb.txt";

       ArrayList<String>
	    alist= new ArrayList<String>(),
	    blist= new ArrayList<String>(),
	    plist= new ArrayList<String>();
       String pat;

      load(afile, alist); //load A strings (ignore blank lines and #-lines)
      load(bfile, blist); //load B strings

       // SHOW the data:
	 System.out.println("\n\n Welcome to MatchAB!");
         show(alist, "   This is list A:");
         show(blist, "   This is list B:");
	 System.out.println("   We want a pattern that matches all strings");
	 System.out.println("   in list A, and matches no strings in list B");

      // The interactive mode:
      if (nn==0) {
          Scanner stdin = new Scanner(System.in);
	  System.out.println("   Please type a pattern. Try one of these:");
	  System.out.println("      \"ha\" (list A error), or");
	  System.out.println("      \"\\S*\" (list B error), or");
	  System.out.println("      \"pit|.*s.*\" (no error)");
	  System.out.printf("   Type your pattern here (omit quotes): ");
	  pat = stdin.nextLine();
	  test(pat, alist, blist);  
	  return;
      }//if

      // The non-interactive version:
      load(pfile, plist); // load the list of patterns

	System.out.printf("\n");
        for (String pp : plist)
	    	test(pp, alist, blist);
 }//main
}//class


