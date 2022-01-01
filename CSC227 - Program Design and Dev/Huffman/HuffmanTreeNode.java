public class HuffmanTreeNode implements Comparable {
    public int myValue, myCount;
    public HuffmanTreeNode left;
	public HuffmanTreeNode right;

    // Constructs a HuffmanTreeNode, value is the ascii value of the char
    // and count is the number of times it appears in the input
    // 2 parameter constructor
    public HuffmanTreeNode(int value, int count) {
    	myValue = value;
    	myCount = count;
    	this.left = null;
    	this.right = null;
    }

	// 3-parameter constructor
    public HuffmanTreeNode(int count, HuffmanTreeNode left, HuffmanTreeNode right) {
		myCount = count;
		this.left = left;
		this.right = right;
	}

	// empty constructor
	public HuffmanTreeNode() {
		myValue = 0;
		myCount = 0;
		this.left = null;
		this.right = null;
	}

	// always useful
	public int getCount() {return myCount;}
    public int getValue() {return myValue;}

    // returns < 0 if Object o is bigger, returns > 0 if
    // Object o is smaller
    //
    // if node1 had count = 1 and node2 had count = 3 then
    // node1.compareTo(node2) returns < 0
    // node2.compareTo(node1) returns > 0
    //
    // if the counts are equal, then based on the char value
    // if node1 and node2 had count = 2, node1 char = 'a' and node2 char = 'b'
    // node1.compareTo(node2) returns < 0
    // node2.compareTo(node1) returns > 0
    //
    // node.compareTo(node) should always return 0
    public int compareTo(Object o) {
		HuffmanTreeNode node = (HuffmanTreeNode)o;
        if (myCount < node.getCount())
        	return -1;
        else if (myCount > node.getCount())
        	return 1;
        else {
			if (myValue < node.getValue())
				return -1;
			else
				return 1;
		}
    }

    // returns a string in this format:
    // ASCII_Value=ASCIIVALUEOFCHAR Count=COUNT
    //
    // for example 5 'e's would return:
    // ASCII_Value=101 Count=5
    public String toString() {
        String s = "ASCII_Value= "+myValue+" Count= "+myCount;
        return s;
    }
}