/* BST1.java
 * 		PURPOSE: a minimal BST1 implementation 
 * 			WHAT we mean by "minimal":
 * 				-- no information hiding whatsoever
 * 				-- avoiding access specifiers if possible
 * 				-- non-generic
 * 		Methods covered here:
 *
 * 	If you call this program with two integers, s and n
 *		(default s=111, n=10), it will use s as seed for random numbers,
 * 		randomly generate n integers, insert them into a BST1
 * 		and do various output to confirm that things are correct.
 * 	USAGE:
 * 		> javac BST1.java
 * 		> java BST1 [s=111][n=9]
 *	WHAT IS IN HERE?
 *	-- We have an inner class called Bnode (the "2" here means that we
 *			(have parent pointer, like Node2 for doubly-linked Node)
 *	-- Keys are int's
 *	-- BST1 members:
 *				root, size
 *	-- BST1 methods: 
 *	             max(), min()
 *	             add(k), remove(k)
 *	             post(), inorder(), pre()
 *	             check()
 *	             rank(k), ith(i)
 *	-- Helper methods:
 *		     replace(k,k')
 *
 *	-- In the main method:
 *		we randomly generate n keys and add them to a BST1.   
 *	   	Then we post, in, pre-order list the keys.
 *	   	Finally, we start from the max key,
 *	   		and keep computing their predecessor until the min key.
 *	   		This confirms that the predecessor code is correct.
 *
 * 	Data Structure Class, Spring 2012
 * 	Chee Yap (Mar7 2012; Nov 2019)
 *
 * **************************************************/

public class BST1 {
/***************************************************
* Members and Constructors
***************************************************/
    Bnode root;
    int size;
    BST1(){ root=null; size=0; }
/***************************************************
* Bnode (inner) Class
***************************************************/
    public class Bnode {
		int key;
		Bnode left, right;
        Bnode(int k) {
			key = k; left = null; right = null; }
    }//Bnode
/***************************************************
* Methods
***************************************************/
    // LOOKUP: returns node containing given key
	Bnode lookup (int k){
		return lookup(k, root);
	}
	Bnode lookup (int k, Bnode u){//if u non-null, return last seen node
		if (u == null) return null;
		if (k == u.key) return u;
		Bnode v = (k<u.key) ? u.left : u.right;
		return (v == null) ? u: lookup(k, v);
	}//lookup

	// MAX: returns node containing maximum key
	Bnode max () { return max(root); }
		Bnode max (Bnode u) {
		if (u == null) return null;
		if (u.right != null) return max(u.right);
		return u;
	}//max

    // MIN: returns node containing minimum key
	Bnode min () { return min(root); }
		Bnode min (Bnode u) {
		if (u == null) return null;
		if (u.left != null) return min(u.left);
		return u;
	}//min

    //CHECK: returns true if subtree at node is a BST1
	boolean check() {
		return check(root); }
	boolean check(Bnode u) { //	relies on the correctness of succ(u)
		if (u==null) return true;
		u=min(u);
		Bnode next = succ(u);
		while (next != null)
		    if (u.key > next.key) return false;
		    else { u = next; next=succ(u); }
		return true;
    }//check

    // POSTORDER: prints postorder list of keys
	void post (){
		Bnode m = max();
		System.out.print("(");
		post(root);
		System.out.print(")");
    }//post
    void post (Bnode u){
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
    void pre (Bnode u, Bnode end){
		if (u == null) return;
		System.out.print(u.key);
		if (u != end) System.out.print(", ");
		pre(u.left, end);
		pre(u.right, end);
    }//pre
    Bnode preorder_end( Bnode u) {	//Helper for pre()
		u = max(u);
		if (u.left != null) return(preorder_end(u.left));
		return u;
    }//preorder_end

    // INORDER: prints inorder list of keys
    void inorder () {
		Bnode m = max();
		System.out.print("(");
		inorder(root, m);
		System.out.print(")");
    }//inorder
    void inorder(Bnode u){ inorder(u, null); }
    void inorder(Bnode u, Bnode m){
		if (u == null) return;
		inorder(u.left);
		System.out.print(u.key);
		if (u != m) System.out.print(", ");
		inorder(u.right, m);
    }//inorder

    // ADD: inserts a new node with key k (if k is not already in bst)
    Bnode add (int k){
		if (root == null) {
		    root = new Bnode(k);
		    size++;
		    return root;
		}
		Bnode u = lookup (k, root);
		if (u.key == k) return null; //k already in bst (failure)
		Bnode v = new Bnode(k);
		size++;
		if (k < u.key) u.left = v;
		else u.right = v;
		return v;
    }//add

    // REMOVE: removes the node with key k (if k is in bst)
    // @return the node that was cut.
    Bnode remove (int k){
		Bnode u = lookup(k, root);
		if (u == null || u.key != k) return null;	// remove failure
		if ((u.left != null) && (u.right != null)) {
			u.key = max(u.left).key;
			return cut(max(u.left));
		}
		return cut(u);
    }//remove

    Bnode cut (Bnode u){//helper for remove, eliminate node u (and returns u)
		// ASSUME: u non-null with at most one child.
		// We always decrement size (is this the only place?)
		size--;
		Bnode v;
		if (u.left == null) 
		    v = u.right;
		else
		    v = u.left;
		if (u == root) {
		    root = v;
		} else {
		    if (parent(u).left == u) 
		    	parent(u).left = v;
		    else 
		    	parent(u).right = v;
		}
		return u;
    }//cut

    // RANK: return the rank of given key k (rank=1 for smallest key)
    int rank (int k) {
		// The rank of key k is the number of keys less-than or equal-to k
		Bnode u = lookup(k);
		int i = 0;
		if (u.key == k) i++;
		while (u != null) {
		    u= parent(u); i++;
		}
		return i;
    }//rank

    // ITH (index):
	//	--returns the i-th smallest node (when i=0, return the min node)
    Bnode ith (int i) { 
		Bnode u = min();  // ASSUMING i>0
		for (int j=1; j<i; j++)
			u = succ(u);
		return u;
    }//ith
   	/***************************************************
    * pred and succ methods
    ***************************************************/
    Bnode pred1 (Bnode u) {
	// 		ASSERT(u != null)
	// 		@return p = predecessor of u;
	//			if u has no predecessor, p=null.
		Bnode p; 
		if (u.left !=null) {//easy case
			p = u.left;
			while (p.right !=null)
				p = p.right;
		}else{ //hard case
			p = root;
			if (min(p) == u) return null;
			// Now, we know p is ancestor of u!
			while (p.right ==null || u != succ1(p))
				p = (p.key<u.key) ? p.right : p.left;
		}
		return p;
	}//pred1
    Bnode succ1 (Bnode u) {
	// 		ASSERT(u != null)
	// 		@return s = success of u;
	//			if u has no successor, s=null.
		Bnode s; 
		if (u.right !=null) {//easy case
			s = u.right;
			while (s.left !=null)
				s = s.left;
		}else{ //hard case
			s = root;
			if (max(s) == u) return null;
			// Now, we know s is ancestor of u!
			while (s.left ==null || u != pred1(s))
				s = (s.key<u.key) ? s.right : s.left;
		}
		return s;
	}//succ1

    Bnode pred (Bnode u) {
		return pred1(u);
    }//pred

    Bnode succ (Bnode u) {
		return succ1(u);
    }//succ
   	/***************************************************
    * helper method
    ***************************************************/
   	Bnode probe (int k) {
	   // return the node with key k; also print parent and children
		return probe(lookup(k));
   	}
   	Bnode probe (Bnode u) {// return node u; print u, parent and children
       System.out.print("   >> node =" + u.key );
       if (u == root) 
       	   System.out.println(" (root)");
       else if (parent(u) != null && parent(u).left == u)
       	   System.out.println(" (left child of " + parent(u).key + ")");
       else if(parent(u) != null)
       	   System.out.println(" (right child of " + parent(u).key + ")");

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

	Bnode parent(Bnode u) {
		return parentHelper(root, u);	//need a helper function because we must update currentRoot w/out modifying the actual root of the tree
	}//parent

	Bnode parentHelper(Bnode currentRoot, Bnode u) {
		if (currentRoot == null || u == root) {	//check to prevent any null pointer exceptions - the root has no parent, and if it's null we can't run these fxns
			return null;
		} else {		//recursion!
			if (currentRoot.left == u || currentRoot.right == u)	//base case - if the right/left child of the currentRoot is the node we are looking for, then we know currentRoot is the parent
				return currentRoot;
			else {
				if (currentRoot.key < u.key)	//if the key we're looking for is greater than the key we are at, go to the right
					return parentHelper(currentRoot.right, u);
				else							//otherwise, the key we're looking for is less than the key we are at, so we go to the left
					return parentHelper(currentRoot.left, u);
			}
		}
	}//parentHelper
/***************************************************
* Main method
***************************************************/
    public static void main(String[] args){
		int nn= (args.length > 1)? Integer.valueOf(args[1]) : 9;
		int seed= (args.length > 0)? Integer.valueOf(args[0]) : 111;
		java.util.Random ran = (seed==0)?
			new java.util.Random() : new java.util.Random(seed);
		BST1 bst = new BST1();
		int x;
		Bnode u;
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
			   System.out.println("Tree passes the BST1 check!");
		   else System.out.println("Tree fails the BST1 check!");
		System.out.println("\n========================================\n");
		   System.out.println("Post, In, Pre order of BST1:");
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
}//BST1
