/* MyBST is an extension of BST
 *		-- to support storing/reading BST as strings in file
 *		-- to support string I/O
 *
 *	Some Methods MUST be implemented
 *	Others Methods MAY be implemented
 *	As usual, you may implement your own helpers
 *	The main method should be kept intact except for your own testing
 *
 *
 *****************************************/
import java.io.*;
import java.util.Scanner;
class MyBST
		extends BST {
// MEMBERS:==============================
// CONSTRUCTOR:==========================
// METHODS:==============================
	static void debug(String msg){
		System.out.println(msg);
	}
	//
	// myString() returns the Parenthesis Representation of BST
	//	-- we put a space between key and "(" or "[" for easier parse
	//	-- since we have 2 types of paren, we can omit a null child!
	String myString(){ return myString(root);}
	String myString(Bnode2 u){ 
		if (u == null) //base case is when we pass a null node
			return "";
		String answer = u.key + "";	//add the key and a space to our answer string
		if (u.left != null)				//make sure left subtree exists
			answer += " (" + myString(u.left) + ")";	//if so, then add open parentheses, and run it on the rest of the subtree and close it
		if (u.right != null)			//makes sure right subtree exists
			answer += " [" + myString(u.right) + "]";	//if so, then add open brackets, and run it on the rest of the subtree and close it
		return answer;		//return our answer string
	}
	// Helper for findLeftRight.
	// findSubstring( str, "()" , start)
	//		returns the pair of indices [i,j] of the substring
	//		of str that enclosed strictly between "(" ... ")",
	//		starting from start.
	// e.g., findSubstring( "1 (2[3]) [45]", "()", 0)
	//			returns the pair of ints: (3,6) representing "2[3]".
	int[] findSubstring(String str, String paren, int start){
		//DO NOT HAVE TO IMPLEMENT!
		int[] pair = {0,0};
		return pair; 
	}// findSubstring
	
	// E.g., findLeftRight("1 (2[3]) [45]")
	//         returns the pair "2[3]" and "45".
	String[] findLeftRight(String str){
		//DO NOT HAVE TO IMPLEMENT!
		String[] answer = {"", ""};
		return answer;
	}//findLeftRight

	// fromString("3 (1)[5]") will return the BST rooted at 3:
	Bnode2 fromString(String str){
		if (str == "")	//in case the string is empty
			return null;
		//create the new BST and add the root, which is always the first character
		BST initial = new BST();
		String strCopy = str;	//create a copy of the string as to not modify the parameter
		initial.add(Integer.parseInt(strCopy.substring(0,1)));
		//loop through the string, as long as it still has something in it 
		while(strCopy.length() > 0) {
			if(strCopy.substring(0,1).matches("\\d+")) {	//if it matches any digit from [0-9]
				String tmp = "";		//create tmp string to store digits
				while(strCopy.substring(0,1).matches("\\d+")) {	//keep looping thru until we reach a non-digit character
					tmp += strCopy.substring(0,1);	//add any digits we find
					strCopy = strCopy.substring(1);	//keep cutting the string off by removing the first character
				}
				initial.add(Integer.parseInt(tmp));	//Convert the tmp string to an integer and add it to the tree
			} else {
				strCopy = strCopy.substring(1);	//otherwise, it's a character we don't care about and we can just ignore it
			}
		}
		return initial.root;	//return the root of the tree
	}
	// equals(u) is true iff
	//	the BST at u and the BST root have the same shape and keys
	boolean equal(Bnode2 u){ return equal(root, u);}
	boolean equal(Bnode2 u, Bnode2 v){
		// IMPLEMENTED FOR YOU!!
		if (u==null || v==null){
			if (u!=null || v!=null) return false;
			return true;
		}
		if (u.key != v.key) return false;
		return equal(u.left, v.left) && equal(u.right, v.right);
	}
	// storeBST("bst.txt") will write the parenthesized BST rep
	//		of root into the file bst.txt.
	void storeBST(String ofilename) throws Exception{
		FileWriter write = new FileWriter(ofilename, false);	//make a new FileWriter object
		PrintWriter print_line = new PrintWriter(write);		//make a new PrintWriter object
		print_line.printf(myString(root));						//Write to the file the output of myString(root)
		print_line.close();										//close the file
	}
	// readBST("bst.txt") will read the parenthesized BST rep
	//		that is stored in the file bst.txt, and return the BST.
	Bnode2 readBST(String ifilename) throws Exception{
		File file = new File(ifilename);		//make a new File object
		Scanner input = new Scanner(file);		//make a new Scanner object
		String nextLine = "";					//make nextLine variable to hold contents
		while(input.hasNext()) {				//as long as the file has a newline
			nextLine = input.nextLine();		//set nextLine to the line
		}
		//System.out.println(nextLine);			//print out the line for debugging
		BST read = new BST();					//make a new BST
		read.root = fromString(nextLine);		//set the root to the output of fromString(nextLine)
		return read.root;						//return the root
	}
	// returns a random BST of size n
	Bnode2 randBST(int n){
		//MAY IMPLEMENT IF YOU WANT!!
		return null;
	}
	//HELPER METHOD TO GET THE HEIGHT (from A.4)
	int ht(Bnode2 u) {
		if (u == null) {
			return -1;
		} else {
			int leftHeight = ht(u.left);
			int rightHeight = ht(u.right);

			if (leftHeight > rightHeight)
				return leftHeight + 1;
			else
				return rightHeight + 1;
		}
	}
	// showLevels() will the levels of a root BST
	void showLevels() {
		int h = ht(root);		//variable to store the height
		//System.out.println(h);
		for(int i = 0; i <= h; i++) {	//loop thru the height
			System.out.print(i + ": ");	//print out the current level and a colon with a space
			showLevels(root, i);		//call the helper recursive method
			System.out.println();		//print a new line
		}
	}//showLevels

	//helper mmethod that utilizes recursion to print out each level
	void showLevels(Bnode2 node, int level) {
		if (node == null)	//base case is when the node is null
			return;
		if (level == 0)		//the level int tells us when its okay to print the key
			System.out.print(node.key + " ");
		else if (level > 0) {	//otherwise, we traverse the tree, decrementing level by one each time
			showLevels(node.left, level - 1);
			showLevels(node.right, level - 1);
		}
	}
// MAIN:=================================
    public static void main(String[] args) throws Exception{
		int nn= (args.length > 1)? Integer.valueOf(args[1]) : 9;
		int seed= (args.length > 0)? Integer.valueOf(args[0]) : 111;
		java.util.Random ran = (seed==0)?
			new java.util.Random() : new java.util.Random(seed);
	
		MyBST bst = new MyBST();
		int x;
		Bnode2 u;
		System.out.println("\n========================================");
		System.out.println("Attempt to insert " + nn + " random ints:");
		for (int i=0; i<nn; i++){
		   x = ran.nextInt(nn);
		   System.out.printf("i=%d, x=%d\n", i, x);
		   if (bst.add(x) == null)
		       System.out.print("-" + x + ", ");
		   else
		       System.out.print("+" + x + ", ");
		}
		System.out.printf("\n Final BST size is %d\n", bst.size);
		// TESTS:================================
		test(bst);				// you can put your own tests here
		testShowLevels(bst);	// see below
		testConvert2String(bst);	// see below
		testFileIO(bst);		// see below
		testFindSubstring(bst); // see below
		//testTraversal(bst); 	// see below
		System.out.println("\n========================================\n");
    }//main
// TESTS:================================
// THESE HAVE BEEN IMPLEMENTED
	static void test(MyBST bst) throws Exception{
		// DO YOUR OWN TESTING HERE...
	}
	static void testShowLevels(MyBST bst) throws Exception{
		bst.showLevels();
	}
	static void testFileIO(MyBST bst) throws Exception{
		System.out.println("\n=====Testing File I/O==================\n");
		bst.storeBST("bst.txt");
		String str=bst.myString();
		Bnode2 u = bst.readBST("bst.txt");
		debug("read from file: "+ bst.myString(u));
		if (str.equals(bst.myString(u)))
			System.out.printf("str and str2 are equal (good)!\n");
		else
			System.out.printf("str and str2 are NOT equal (error)!\n");
	}
	static void testConvert2String(MyBST bst){
		System.out.println("\n=====Convert2String=====================\n");
			String str = bst.myString();
			System.out.printf("myString::str= %s\n", str);
			Bnode2 u = bst.fromString(str);
			String str2 = bst.myString(u);
			System.out.printf("fromString::str2= %s\n", str2);
			if (str.equals(str2)) 
				System.out.printf("str and str2 are equal (good)!\n");
			else
				System.out.printf("str and str2 are NOT equal (error)!\n");
	}
	static void testTraversal(MyBST bst){
		System.out.println("\n=======Test Traversal===================\n");
		   System.out.println("Post, In, Pre order of BST:");
		   bst.post(); 	System.out.println();
		   bst.inorder();	System.out.println();
		   bst.pre();
	}
	static void testFindSubstring(MyBST bst){
		System.out.println("\n=======Test FindSubstring===============\n");
		String str1="123 ( 4 [ 5 ] ) [ 6 ( 7 ) [ 8 ] ]";
		String str2="123 [ 6 ( 7 ) [ 8 ] ]";
		int[] pair = bst.findSubstring(str1, "()", 0);
		debug("str1="+str1);
		System.out.printf("str1 pair= %d, %d\n", pair[0], pair[1]);
		debug("str2="+str2);
		pair = bst.findSubstring(str2, "[]", 0);
		System.out.printf("str2 pair= %d, %d\n", pair[0], pair[1]);
		String[] leftRight = bst.findLeftRight(str1);
		debug("left="+ leftRight[0]);
		debug("right="+ leftRight[1]);
	}
}//class
