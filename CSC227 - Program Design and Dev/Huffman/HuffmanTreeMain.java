// Dominic Martinez
// 12/4/02

import java.io.*;
import java.util.LinkedList;
import java.util.Collections;

public class HuffmanTreeMain {
    public static final String FILENAME = "test.txt";

// this is what turns the Linked list into the Huffman Tree
// makes the left and right leaf then adds them to the root
// then adds the root back into the linked list and sorts it
/***************************************************************************************/
    public static HuffmanTreeNode gatherHuffmanTree(LinkedList ll) {
    	HuffmanTreeNode root = new HuffmanTreeNode();
    	while (ll.size() >= 2) {
		    HuffmanTreeNode left = new HuffmanTreeNode();
		    HuffmanTreeNode right = new HuffmanTreeNode();
			left = (HuffmanTreeNode)ll.removeFirst();
			right = (HuffmanTreeNode)ll.removeFirst();
			root = new HuffmanTreeNode(left.getCount() + right.getCount(), left, right);
			linkInsert(ll, root);
		}
		return (HuffmanTreeNode)root;
    }

	// helper method
    public static void linkInsert(LinkedList ll, HuffmanTreeNode root) {
		ll.addFirst(root);
		Collections.sort(ll);
	}
/***************************************************************************************/

// this will print out the tree as necessary for the assignment
/***************************************************************************************/
    public static void printHuffmanTree(HuffmanTreeNode root) {
       String c = "";
       assistPrint(root, c);
    }

    public static void assistPrint(HuffmanTreeNode root, String compressed) {
    	if (root.left == null && root.right == null)
        	System.out.println(root.toString() + " Compressed= " + compressed);
		else {
			assistPrint(root.left, compressed.concat("0"));
			assistPrint(root.right, compressed.concat("1"));
		}
	}
/**************************************************************************************/

// this will print out the series of 1's and 0's by getting the character from input
// and then searching the tree for the same value, all the time keeping record of where
// the search has gone in order to tell us something
/**************************************************************************************/
    public static void printCompressed(HuffmanTreeNode root, String input) {
        String comp = "";
        String found;
        // set up the input file
        TextReader tr = null;
        try {
            tr = new TextReader(new FileInputStream(input));
        } catch(Exception e) {
            throw new RuntimeException(e.toString());
        }

		while (tr.ready()) {
			found = compressedHelp(root, comp, tr.readChar());
			System.out.print(found);
		}
    }

    public static String compressedHelp(HuffmanTreeNode root, String comp, char value) {
        String temp;

        if (root == null)
            return null;

        if (root.getValue() == ((int)value)) {return comp;}
		else {
			temp = compressedHelp(root.left, comp + "0", value);
			if (temp == null) {
				temp = compressedHelp(root.right, comp + "1", value);
			}
			return temp;
		}
    }
/****************************************************************************************/

    public static void main(String[] args) {
        // set up the input file
        TextReader tr = null;
        try {
            tr = new TextReader(new FileInputStream(FILENAME));
            tr.eolIsSignificant(false);
        } catch(Exception e) {
            throw new RuntimeException(e.toString());
        }

        // set up an array the length of the ASCII chars and determine how
        // many of each of them are in the file
        int[] arr = new int[128];
        while (tr.ready()) {
            arr[tr.readChar()] += 1;
        }

        // create tree nodes for each of the characters in the file with the
        // character and the count of that char.
        LinkedList ll = new LinkedList();
        for (int i = 0; i < 128; i++) {
            if (arr[i] != 0 && arr[i] != 10)
                ll.add(new HuffmanTreeNode(i, arr[i]));
        }
        // Sort the linked list in ascending order
        Collections.sort(ll);

        // constructs the huffman tree from all of the little trees
        HuffmanTreeNode root = gatherHuffmanTree(ll);

        // prints out the huffman tree in the format layed out in the assignment
        printHuffmanTree(root);

        System.out.println();

        // prints out to the file the verson of input using the compression
        printCompressed(root, FILENAME);
    }
}