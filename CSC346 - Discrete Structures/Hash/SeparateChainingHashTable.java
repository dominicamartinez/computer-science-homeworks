import java.util.*;

public class SeparateChainingHashTable{

	public static final int DEFAULT_TABLESIZE = 101;
	private int tablesize;
	private int choice;
	private int collision;
	private LinkedList[] hash;

	public SeparateChainingHashTable() { this(DEFAULT_TABLESIZE); }
	public SeparateChainingHashTable(int tablesize) {
		collision = 0;
		choice = 1;
		this.tablesize = tablesize;
		hash = new LinkedList[tablesize];
		for (int i = 0; i < hash.length; i++) {
			hash[i] = new LinkedList();
		}
	}

	// adds to the hashtable depending on the hash function
	public void insert(String key, Object value) {
		collisionDetection(key);
		HashElement he = new HashElement(key, value);
		if (choice == 1) {
			int i = h1(key);
			hash[i].add(he);
		}
		else if (choice == 2) {
			int i = h2(key);
			hash[i].add(he);
		}
		else if (choice == 3) {
			int i = h3(key);
			hash[i].add(he);
		}
		System.out.println("Success in adding " + key);
	}

	public void remove(String key) {
	//actually remove 1st element that matches key
		if (choice == 1) {
			int i = h1(key);
			if (hash[i].size() != 0)
				hash[i].removeFirst();
		}
		else if (choice == 2) {
			int i = h2(key);
			if (hash[i].size() != 0)
				hash[i].removeFirst();
		}
		else if (choice == 3) {
			int i = h3(key);
			if (hash[i].size() != 0)
				hash[i].removeFirst();
		}
		System.out.println("Success in removing " + key);
	}

	// find method
	public Object find(String key) {
		if (choice == 1) {
			int i = h1(key);
			Object[] temp = hash[i].toArray();
			for (int j = 0; j < temp.length; j++) {
				HashElement poo = (HashElement)temp[j];
				if (key == poo.getKey()) {
					return poo.getValue();
				}
			}
			return null;
		}
		else if (choice == 2) {
			int i = h2(key);
			Object[] temp = hash[i].toArray();
			for (int j = 0; j < temp.length; j++) {
				HashElement poo = (HashElement)temp[j];
				if (key == poo.getKey()) {
					return poo.getValue();
				}
			}
			return null;
		}
		else {
			int i = h3(key);
			Object[] temp = hash[i].toArray();
			for (int j = 0; j < temp.length; j++) {
				HashElement poo = (HashElement)temp[j];
				if (key == poo.getKey()) {
					return poo.getValue();
				}
			}
			return null;
		}
	}

	// implements function from Fig. 5.2
	private int h1(String key) {
		int hashVal = 0;

		for(int i = 0; i < key.length(); i++) {
			hashVal += key.charAt(i);
		}

		return hashVal % tablesize;
	}

	// implements function from Fig. 5.3
	private int h2(String key) {
		return (key.charAt(0) + 27 * key.charAt(1) + 729 *
		key.charAt(2)) % tablesize;
	}

	// implements function from Fig. 5.4
	private int h3(String key) {
		int hashVal = 0;

		for(int i = 0; i < key.length(); i++) {
			hashVal = 37 * hashVal + key.charAt(i);
		}

		hashVal %= tablesize;
		if ( hashVal < 0) { hashVal += tablesize; }

		return hashVal;
	}

	// Changes the hash function to h<choice>
	// The default hash function is h1
	public void changeHash(int c) {
		if (c < 1 || c > 3) {
			choice = 1;
		}
		else {
			choice = c;
		}
	}

	// this method will detect collisions
	private void collisionDetection(String key) {
		if (choice == 1) {
			int i = h1(key);
			if (hash[i].size() == 0) { return; }
			else { collision++; return; }
		}
		else if (choice == 2) {
			int i = h2(key);
			if (hash[i].size() == 0) { return; }
			else { collision++; return; }
		}
		else if (choice == 3) {
			int i = h3(key);
			if (hash[i].size() == 0) { return; }
			else { collision++; return; }
		}
	}

	// this will return the amount of collisions detected
	public int getCollisions() { return collision; }
}

