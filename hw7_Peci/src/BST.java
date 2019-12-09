/* BST.java
 *
 *  CHEAT: we add "balance" info into Node2 class so that AVL 
 *			trees can extend this class.
 *****************************************	
 * 		PURPOSE: a minimal BST implementation 
 * 			WHAT we mean by "minimal":
 * 				-- no information hiding whatsoever
 * 				-- avoiding access specifiers if possible
 * 				-- non-generic
 * 		Methods covered here:
 *
 * 	If you call this program with two integers, s and n
 *		(default s=111, n=10), it will use s as seed for random numbers,
 * 		randomly generate n integers, insert them into a BST
 * 		and do various output to confirm that things are correct.
 * 	USAGE:
 * 		> javac BST.java
 * 		> java BST [s=111][n=9]
 *	WHAT IS IN HERE?
 *	-- We have an inner class called Bnode2 (the "2" here means that we
 *			(have parent pointer, like Node2 for doubly-linked Node)
 *	-- Keys are int's
 *	-- BST members:
 *				root, size
 *	-- BST methods: 
 *	             max(), min()
 *	             add(k), remove(k)
 *	             post(), inorder(), pre()
 *	             check()
 *	             rank(k), ith(i)
 *	-- Helper methods:
 *		     replace(k,k')
 *
 *	-- In the main method:
 *		we randomly generate n keys and add them to a BST.   
 *	   	Then we post, in, pre-order list the keys.
 *	   	Finally, we start from the max key,
 *	   		and keep computing their predecessor until the min key.
 *	   		This confirms that the predecessor code is correct.
 *
 * 	Data Structure Class, Spring 2012
 * 	Chee Yap (Mar7 2012; Nov 2019)
 *
 * **************************************************/

public class BST {
/***************************************************
* Members and Constructors
***************************************************/
    Bnode2 root;
    int size;
    BST(){ root=null; size=0; }
/***************************************************
* Bnode2 (inner) Class
***************************************************/
    public class Bnode2 {
		int key;
		Bnode2 left, right, parent;
		int balance; // =-1,0,+1 (for AVL use)
        Bnode2(int k) {
			key = k; left = null; right = null; parent = null;
			balance=0;}
    }//Bnode2
/***************************************************
* Methods
***************************************************/
    // LOOKUP: returns node containing given key
	Bnode2 lookup (int k){
		return lookup(k, root);
	}
	Bnode2 lookup (int k, Bnode2 u){//if u non-null, return last seen node
		if (u == null) return null;
		if (k == u.key) return u;
		Bnode2 v = (k<u.key) ? u.left : u.right;
		return (v == null) ? u: lookup(k, v);
	}//lookup

	// MAX: returns node containing maximum key
	Bnode2 max () { return max(root); }
		Bnode2 max (Bnode2 u) {
		if (u == null) return null;
		if (u.right != null) return max(u.right);
		return u;
	}//max

    // MIN: returns node containing minimum key
	Bnode2 min () { return min(root); }
		Bnode2 min (Bnode2 u) {
		if (u == null) return null;
		if (u.left != null) return min(u.left);
		return u;
	}//min

    //CHECK: returns true if subtree at node is a BST
	boolean check() {
		return check(root); }
	boolean check(Bnode2 u) { //	relies on the correctness of succ(u)
		if (u==null) return true;
		u=min(u);
		Bnode2 next = succ(u);
		while (next != null)
		    if (u.key > next.key) return false;
		    else { u = next; next=succ(u); }
		return true;
    }//check

    // POSTORDER: prints postorder list of keys
	void post (){
		Bnode2 m = max();
		System.out.print("(");
		post(root);
		System.out.print(")");
    }//post
    void post (Bnode2 u){
		if (u == null) return;
		post(u.left);
		post(u.right);
		System.out.print(u.key);
		if (u != root) System.out.print(", ");
    }//post

    // PREORDER: prints preorder list of keys
    void pre (){
		// Must find the last node to be printed:
		System.out.print("(");
		pre(root, preorder_end(root));
		System.out.print(")");
    }//pre
    void pre (Bnode2 u, Bnode2 end){
		if (u == null) return;
		System.out.print(u.key);
		if (u != end) System.out.print(", ");
		pre(u.left, end);
		pre(u.right, end);
    }//pre
    Bnode2 preorder_end( Bnode2 u) {	//Helper for pre()
		u = max(u);
		if (u.left != null) return(preorder_end(u.left));
		return u;
    }//preorder_end

    // INORDER: prints inorder list of keys
    void inorder () {
		Bnode2 m = max();
		System.out.print("(");
		inorder(root, m);
		System.out.print(")");
    }//inorder
    void inorder(Bnode2 u){ inorder(u, null); }
    void inorder(Bnode2 u, Bnode2 m){
		if (u == null) return;
		inorder(u.left);
		System.out.print(u.key);
		if (u != m) System.out.print(", ");
		inorder(u.right, m);
    }//inorder

    // ADD: inserts a new node with key k (if k is not already in bst)
    Bnode2 add (int k){
		if (root == null) {
		    root = new Bnode2(k);
		    size++;
		    return root;
		}
		Bnode2 u = lookup (k, root);
		if (u.key == k) return null; //k already in bst (failure)
		Bnode2 v = new Bnode2(k);
		size++;
		if (k < u.key) u.left = v;
		else u.right = v;
		v.parent = u;
		return v;
    }//add

    // REMOVE: removes the node with key k (if k is in bst)
    // @return the node that was cut.
    Bnode2 remove (int k){
		Bnode2 u = lookup(k, root);
		if (u == null || u.key != k) return null;	// remove failure
		if ((u.left != null) && (u.right != null)) {
			u.key = max(u.left).key;
			return cut(max(u.left));
		}
		return cut(u);
    }//remove

    Bnode2 cut (Bnode2 u){//helper for remove, eliminate node u & returns u
		// ASSUME: u non-null with at most one child.
		// We always decrement size (is this the only place?)
		size--;
		Bnode2 v;
		if (u.left == null) 
		    v = u.right;
		else
		    v = u.left;
		if (v != null) v.parent = u.parent;
		if (u.parent == null) {
		    root = v;
		} else {
		    if (u.parent.left == u) 
		    	u.parent.left = v;
		    else 
		    	u.parent.right = v;
		}
		return u;
    }//cut

    // RANK: return the rank of given key k (rank=1 for smallest key)
    int rank (int k) {
		// The rank of key k is the number of keys less-than or equal-to k
		Bnode2 u = lookup(k);
		int i = 0;
		if (u.key == k) i++;
		while (u != null) {
		    u= pred(u); i++;
		}
		return i;
    }//rank

    // ITH (index):
	//	--returns the i-th smallest node (when i=0, return the min node)
    Bnode2 ith (int i) { 
		Bnode2 u = min();  // ASSUMING i>0
		for (int j=1; j<i; j++)
			u = succ(u);
		return u;
    }//ith
   	/***************************************************
    * pred and succ methods
    ***************************************************/
    Bnode2 pred (Bnode2 u) {
	if (u.left != null) {
	    u = u.left;
	    while (u.right != null)
		u = u.right;
	    return (u);
	} else {
	    Bnode2 p = u.parent;
	    while (p != null && p.left == u) {
		u=p; p = u.parent;
	    }
	    return p;
	}
    }//pred

    Bnode2 succ (Bnode2 u) {
	if (u.right != null) {
	    u = u.right;
	    while (u.left != null)
		u = u.left;
	    return (u);
	} else {
	    Bnode2 p = u.parent;
	    while (p != null && p.right == u) {
		u=p; p = u.parent;
	    }
	    return p;
	}
    }//succ
   	/***************************************************
    * helper method
    ***************************************************/
   	Bnode2 probe (int k) {
	   // return the node with key k; also print parent and children
		return probe(lookup(k));
   	}
   	Bnode2 probe (Bnode2 u) {// return node u; print u, parent and children
       System.out.print("   >> node =" + u.key );
       if (u == root) 
       	   System.out.println(" (root)");
       else if (u.parent.left == u)
       	   System.out.println(" (left child of " + u.parent.key + ")");
       else
       	   System.out.println(" (right child of " + u.parent.key + ")");

       if (u.left == null)
       	   System.out.print(" (no left child), ");
       else
       	   System.out.print(" (left child =" + u.left.key + "), ");
       if (u.right == null)
       	   System.out.println(" (no right child)");
       else
       	   System.out.println(" (right child =" + u.right.key + ")");
       return u;
   	}//probe
/***************************************************
* Main method
***************************************************/
    public static void main(String[] args) throws Exception{
		int nn= (args.length > 1)? Integer.valueOf(args[1]) : 9;
		int seed= (args.length > 0)? Integer.valueOf(args[0]) : 111;
		java.util.Random ran = (seed==0)?
			new java.util.Random() : new java.util.Random(seed);
	
		BST bst = new BST();
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
		System.out.println("\n========================================\n");
		   if (bst.check())
			   System.out.println("Tree passes the BST check!");
		   else System.out.println("Tree fails the BST check!");
		System.out.println("\n========================================\n");
		   System.out.println("Post, In, Pre order of BST:");
		   bst.post(); 	System.out.println();
		   bst.inorder();	System.out.println();
		   bst.pre();
		/* Nov 2019: I WILL NOT ASK YOU TO DO PREDECESSOR/SUCCESSOR!
		System.out.println("\n========================================\n");
		   System.out.println("CHECKING predecessor:");
		   u = bst.max();
		   while (u != null) {
		       System.out.print(u.key);
		       u = bst.pred(u); 
		       if (u != null) System.out.print(", ");
		   }
		System.out.println("\n========================================\n");
		System.out.println("CHECKING succ:");
		   u = bst.min();
		   while (u != null) {
		       System.out.print(u.key);
		       u = bst.succ(u); 
		       if (u != null) System.out.print(", ");
		   }
		*/
		System.out.println("\nPROBE: root, min and max ===============");
			bst.probe(bst.root);
			bst.probe(bst.min());
			bst.probe(bst.max());
		System.out.println("\n========================================");
		System.out.println("CHECKING remove: first pick a random index");
		while (bst.size > 0) {
		   u = bst.ith(ran.nextInt(bst.size));// pick random node to delete
		   System.out.println("Key to be deleted: "
				   + u.key + "\nInorder Result:");
		   bst.remove(u.key);
		   bst.inorder();	System.out.println();
		   System.out.println("Postorder Result:");
		   bst.post(); 		System.out.println();
		}
		System.out.println("\n========================================\n");
    }//main
}//BST
