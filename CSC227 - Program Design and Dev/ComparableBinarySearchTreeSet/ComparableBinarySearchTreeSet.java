// Dominic Martinez
// 4/20/03

// Data Structure: BST
// with certain common methods

public class ComparableBinarySearchTreeSet implements ComparableTreeSet {

	// this is the private inner node class
	private class TreeNode {

		public Comparable data;
	    public TreeNode left;
		public TreeNode right;

	    // Constructs a TreeNode
	 	public TreeNode() {
			data = null;
			this.left = null;
			this.right = null;
		}

	    public TreeNode(Comparable data) {
	    	this.data = data;
	    	this.left = null;
	    	this.right = null;
	    }

	    public TreeNode(Comparable data, TreeNode left, TreeNode right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}


	// this is the root node
	private TreeNode root = null;
	private boolean flag = false;
	private String s = "";

	/* adds comp to the tree, if a matching element
       already exists in the set, then the element should
       not be added, the method should return without
       editing the tree at all
    */

    /* this method runs in O (log n) time */
    public void add(Comparable comp) {
		if (contains(comp)) return;

		if (root == null) {
			root = new TreeNode(comp);
		}
		else {
			addHelp(comp, root);
		}
	}

	private void addHelp(Comparable comp, TreeNode root) {
		if (comp.compareTo(root.data) < 0) {
			if (root.left != null) {
				addHelp(comp, root.left);
			}
			else {
				root.left = new TreeNode(comp);
			}
		}
		else {
			if (root.right != null) {
				addHelp(comp, root.right);
			}
			else {
				root.right = new TreeNode(comp);
			}
		}
	}
/*************************************************************/

    /* returns true if comp exists in the set
       and false if it does not
    */

    /* this also runs in O (log n) time */
    public boolean contains(Comparable comp) {
		if (root == null) { return false; }
		else {
			containsHelp(comp, root);
			boolean temp = flag;
			flag = false;
			return temp;
		}
	}

	private void containsHelp(Comparable comp, TreeNode root) {
		if (root.left != null) containsHelp(comp, root.left);
		if (comp.compareTo(root.data) == 0) flag = true;
		if (root.right != null) containsHelp(comp, root.right);
	}
/**************************************************************/

    /* removes the element from the set, if the element
       does not exist, then the method should simply
       return without editing the tree at all
    */

    /* this algorithm is almost verbatim from Mark Allan Weiss,(pg.
       612 from Data Structures and Problem Solving using Java and
       from his online code) his remove method is the kind
       of one that uses the min from the right which also
       runs in O (log n)
    */
    public void remove(Comparable comp) {
		root = removeHelp(comp, root);
	}

	private TreeNode removeHelp(Comparable comp, TreeNode root) {
        if( root == null )
        	return root;
	    if( comp.compareTo( root.data ) < 0 ) {
		    root.left = removeHelp( comp, root.left );
		}
	    else if( comp.compareTo( root.data ) > 0 ) {
		    root.right = removeHelp( comp, root.right );
		}
	    else if( root.left != null && root.right != null ) {
    		root.data = findMin( root.right ).data;
    		root.right = removeMin( root.right );
    	}
    	else {
    		root = ( root.left != null ) ? root.left : root.right;
		}
    	return root;
	}

    private TreeNode findMin( TreeNode root ) {
    	if (root == null) {
    		return null;
		}
    	else if (root.left == null) {
    		return root;
		}
    	return findMin(root.left);
    }

    private TreeNode removeMin(TreeNode root) {
		if (root == null) {
			System.out.println("Item not found");
		}
		else if (root.left != null) {
			root.left = removeMin(root.left);
			return root;
		}

		return root.right;
	}
/**************************************************************/

    /* returns another ComparableTree object which
       contains all the elements of this object that
       fall within the range of the two parameters
    */

    /* runs in O (log n) */
    public ComparableTreeSet getAllInRange(Comparable lowRange, Comparable highRange) {
		ComparableBinarySearchTreeSet c = new ComparableBinarySearchTreeSet();
		getHelper(root, lowRange, highRange, c);
		return c;
	}

	private void getHelper(TreeNode root, Comparable low, Comparable hi,
	ComparableBinarySearchTreeSet c) {
		if (root != null) {
			if ((root.data.compareTo(low) == 0 || root.data.compareTo(low) > 0)
			&&  (root.data.compareTo(hi) == 0 || root.data.compareTo(hi) < 0)) {
				c.add(root.data);
				System.out.print(root.data);
			}
			getHelper(root.left, low, hi, c);
			getHelper(root.right, low, hi, c);
		}
		return;
	}
/************************************************************/

    /* returns a string representing a path in the
       tree down to the comp
       If the element is at the root, the string returned
       should be: "Element is at the root."
       If the element does not exist, the string returned
       should be: "No such element exists."
       Otherwise, the string returned should be a path
       telling what order to go down the path to get to the element
       For example, "root->llrrl" says that from the root, to get
       to the element, one would have to go left, left, right, right,
       and then left to arrive at the element
    */

    /* this runs in O (log n) */
    public String pathTo(Comparable comp) {
		if (contains(comp)) {
			if (comp.compareTo(root.data) == 0) {
				return "Element is at the root.";
			}
			else {
				s += "root->";
				pathToHelp(root, comp);
				String temp = s;
				s = "";
				return temp;
			}
		}
		else {
			return "No such element exists";
		}
	}

	private void pathToHelp(TreeNode root, Comparable comp) {
		if (comp.compareTo(root.data) == 0) {
			return;
		}

		if (comp.compareTo(root.data) < 0) {
			s += "l";
			pathToHelp(root.left, comp);
		}
		else if (comp.compareTo(root.data) > 0) {
			s += "r";
			pathToHelp(root. right, comp);
		}
	}

/***************************************************************/
    /* returns a reverse inorder traversal of the tree, with
       tabs set appropriately for each level
    */

    /* runs in O (log n) time */
    public String toString() {
		toStringHelp(root, 0);
		String temp = s;
		s = "";
		return temp;
	}

	private void toStringHelp(TreeNode root, int depth) {
		if (root != null) {
			depth += 1;
			toStringHelp(root.right, depth);
			for (int i = depth; i >= 0; i--) {
				s += "\t";
			}
			s = s + root.data + "\n";
			toStringHelp(root.left, depth);
    	}
    	return;
    }
/***************************************************************/

	/*
	addAll - (This method is not in the interface.) This method
	takes a ComparableBinarySearchTreeSet object, and adds all
	of the elements of it into the current object.
	*/

	/* runs in O (log n) */
	public void addAll(ComparableBinarySearchTreeSet t) {
		addHelper(t.root);
	}

	private void addHelper(TreeNode root) {
		if (root != null)  {
			addHelper(root.left);
			add(root.data);
			addHelper(root.right);
		}
		return;
	}
}