public class OpenAddressingHashTable {

	// Implements openAddressing with linear probing,
	// quadratic probing, or double hashing.
	// The default collision resolving strategy is "linear probing"

	public static final int DEFAULT_TABLESIZE = 101;
	private int choice;
	private int strategy;
	private int tablesize;
	private int collision;
	private Object[] hash;
	Object tombstone;

  	// set tablesize to a default
  	public OpenAddressingHashTable() {
		this(DEFAULT_TABLESIZE);
	}

  	// specify how big the tablesize is supposed to be
  	public OpenAddressingHashTable(int tablesize) {
		collision = 0;
		tombstone = "T";
		strategy = 0;
		choice = 1;
		this.tablesize = tablesize;
		hash = new Object[tablesize];
	}

  	public void insert(String key, Object value) {
		if (isFull()) { return; }
		collisionDetection(key);
		HashElement he = new HashElement(key, value);

		if (strategy == 0) { // linear
			if (choice == 1) {
				int j = 0;
				int i = h1(key);
				int k = i+j;
				while (hash[i+j] != null) {
					if ( i+j == tablesize-1) {
						i = 0; j = 0;
					}
					j++;
					if (i+j == k) { return; }
				}
				hash[i+j] = he;
			}
			else if (choice == 2) {
			if (choice == 1) {
				int j = 0;
				int i = h2(key);
				int k = i+j;
				while (hash[i+j] != null) {
					if ( i+j == tablesize-1) {
						i = 0; j = 0;
					}
					j++;
					if (i+j == k) { return; }
				}
				hash[i+j] = he;
			}
			else if (choice == 3) {
			if (choice == 1) {
				int j = 0;
				int i = h3(key);
				int k = i+j;
				while (hash[i+j] != null) {
					if ( i+j == tablesize-1) {
						i = 0; j = 0;
					}
					j++;
					if (i+j == k) { return; }
				}
				hash[i+j] = he;
		}
		else if (strategy == 1) { // quadratic
			if (choice == 1) {
				int k = 0;
				int j = k ^ 2;
				int i = h1(key);
				while (hash[i+j] != null || hash[i+j] == tombstone) { k++; j = k*k; }
				hash[i+j] = he;
			}
			else if (choice == 2) {
				int k = 0;
				int j = k ^ 2;
				int i = h2(key);
				while (hash[i+j] != null || hash[i+j] == tombstone) { k++; j = k*k; }
				hash[i+j] = he;
			}
			else if (choice == 3) {
				int k = 0;
				int j = k ^ 2;
				int i = h3(key);
				while (hash[i+j] != null || hash[i+j] == tombstone) { k++; j = k*k; }
				hash[i+j] = he;
			}
		}
		else if (strategy >= 2) { // double hashing
			int i = 1;
			int k = hash2(key);
			int j = i * k;
			if (j > tablesize) { j = 0;	}

			if (hash[j] == null || hash[j] == tombstone) {
				hash[j] = he;
			}
			else {
				while (hash[j] != null) { j++; }
				hash[j] = he;
			}

		}
		System.out.println("Success in adding " + key);
	}

	private int hash2(String key) {
  		return strategy-(sum(key) % strategy);
	}

	private int sum(String key) {
		int sum = 0;
		for(int i = 0; i < key.length(); i++) {
			sum += key.charAt(i);
		}
		return sum;
	}

  	public void remove(String key) {
		//tombstone 1st element that matches key
		if (strategy >= 2) {
			int i = 1;
			int k = hash2(key);
			int j = i * k;

			if (hash[j] == null || hash[j] == tombstone) { return; }
			HashElement temp = (HashElement)hash[j];
			while (j < tablesize) {
				if (hash[j] == null || hash[j] == tombstone) { return; }
				else { temp = (HashElement)hash[j];
					if (key == temp.getKey()) {
						hash[j] = tombstone;
					}
					i += 1;
					j = i * k;
				}
			}
		}
		else {
			int i = 0;
			if (choice == 1) {
				i = h1(key);
			}
			else if (choice == 2) {
				i = h2(key);
			}
			else { i = h3(key); }

			if (hash[i] == null || hash[i] == tombstone) {return;}
			else {
				HashElement temp = (HashElement)hash[i];
				if (key == temp.getKey()) {
					hash[i] = tombstone;
				}
				else {
					if (strategy == 0) {
						int j = 1;
						while (i+j < tablesize) {
							int k = j+i;
							temp = (HashElement)hash[k];
							if (key == temp.getKey()) {
								hash[k] = tombstone;
							}
							j++;
						}
					}
					else {
						int j = 1;
						int m = 1;
						while (m+j < tablesize) {
							int k = m+i;
							temp = (HashElement)hash[k];
								if (key == temp.getKey()) {
								hash[k] = tombstone;
							}
							j++;
						m = j*j;
						}
					}
				}
			}
		}
		System.out.println("Success in removing " + key);
	}

  	public Object find(String key) {
		if (strategy >= 2) {
			for(int i = 0; i < tablesize; i++) {
				if (hash[i] == null || hash[i] == tombstone) { }
				else {
					HashElement h = (HashElement)hash[i];
					if (key == h.getKey()) {
						return h.getValue();
					}
				}
			}
		}
		else {
			int i = 0;
			if (choice == 1) {
				i = h1(key);
			}
			else if (choice == 2) {
				i = h2(key);
			}
			else { i = h3(key); }

			if (hash[i] == null || hash[i] == tombstone) {
				return null;
			}
			else {
				HashElement temp = (HashElement)hash[i];
				if (key == temp.getKey()) {
					return temp.getValue();
				}
				else {
					if (strategy == 0) {
						int j = 1;
						while (i+j < tablesize) {
							int k = j+i;
							temp = (HashElement)hash[k];
							if (key == temp.getKey()) {
								return temp.getValue();
							}
							j++;
						}
						return null;
					}
					else {
						int j = 1;
						int m = 1;
						while (m+j < tablesize) {
							int k = m+i;
							temp = (HashElement)hash[k];
							if (key == temp.getKey()) {
								return temp.getValue();
							}
							j++;
							m = j*j;
						}
						return null;
					}
				}
			}
		}
		return null;
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

  // Changes the collision resolving strategy:
  //   strategy == 0  means linear probing
  //   strategy == 1  means quadratic probing
  //   strategy >= 2  means double hashing where "strategy" is the
  //                  parameter for the 2nd hash function, so:
  //                      hash2(k) = strategy - ((sum of ASCII values
  //                                  of characters in k) % strategy);
  //                  and f(i) = i*hash2(k);
  //
  // The default collision resolving strategy is linear probing.

  	// allows someone to change strategy
  	public void changeStrategy(int strat) {
		if (strat < 0) {
			strategy = 0;
		}
		else {
			strategy = strat;
		}
	}

	// this method will detect collisions that are of the first
	// order...and by this i mean that once they are inserted into
	// the hash, if they probe linear and go to the next index
	// in the hash and collide there..this won't detect it
	private void collisionDetection(String key) {
		if (choice == 1) {
			int i = h1(key);
			if (hash[i] == null || hash[i] == tombstone) { return; }
			else { collision++; return; }
		}
		else if (choice == 2) {
			int i = h2(key);
			if (hash[i] == null || hash[i] == tombstone) { return; }
			else { collision++; return; }
		}
		else if (choice == 3) {
			int i = h3(key);
			if (hash[i] == null || hash[i] == tombstone) { return; }
			else { collision++; return; }
		}
	}

	// this will return the amount of collisions detected
	public int getCollisions() { return collision; }

	// find out if the hash is full
	public boolean isFull() {
		for(int i = 0; i < tablesize-1; i++) {
			if (hash[i] == null) { return false; }
		}
		return true;
	}
}