import java.util.*;

public class VertexNetwork {

	private Vertex[] myNetwork;
	private int k, timeSteps, L, Hthr;
	private Random r;
	private BitSet iv;
	private double mu;
	private ArrayList<Integer> update1;
	private ArrayList<BitSet> update2;
	private ArrayList<Integer> results;

// constructor
	public VertexNetwork(int N, int k, BitSet iv, int timeSteps, double mu, int L, int Hthr, int id) {
		this.k = k;
		myNetwork = new Vertex[N];

		for (int i = 0; i < N; i++) {
			Vertex v = new Vertex(k, L, id);
			v.initializeEdges(i,N);
			myNetwork[i] = v;
		}
		r = new Random();
		this.iv = (BitSet)iv.clone();
		this.timeSteps = timeSteps;
		this.mu = mu;
		this.L = L;
		this.Hthr = Hthr;
		results = new ArrayList<Integer>();
	}

// this one disconnects and reconnects based on randomness
	public void buildEdges() {

	}

// helps us see network information
	public void networkInfo() {
		for (int i = 0; i < myNetwork.length; i++) {
			System.out.print("I'm vertex number " + i + " and my edges are ");
			myNetwork[i].getEdges();
			System.out.println();
		}
	}

// this is going to randomize all edges
	public void randomizeNetwork(double p) {
		boolean b = true;
		int x;

		for (int i = 0; i < myNetwork.length; i++) {
			for (int j = 0; j < k; j++) {
				int m = myNetwork[i].getEdge();
				if (r.nextDouble() < p) {
					while (b) {
						x = r.nextInt(myNetwork.length);
						if (myNetwork[i].containsEdge(x) || x == i) { }
						else if (x == m) {
							myNetwork[i].removeEdge(m);
							myNetwork[i].addEdge(m);
							b = false;
						}
						else {
							myNetwork[i].addEdge(x);
							myNetwork[i].removeEdge(m);
							myNetwork[m].removeEdge(i);
							myNetwork[x].addEdge(i);
							b = false;
						}
					}
				}
				else {
					myNetwork[i].removeEdge(m);
					myNetwork[i].addEdge(m);
				}
				b = true;
			}
		}
	}

// this helps us know how many edges are in the network
	public void countEdges() {
		int count = 0;
		for (int i = 0; i < myNetwork.length; i++) {
			count += myNetwork[i].edgeCount();
		}
		System.out.println("I detect " + count + " number of edges in the network");
	}

	public void runSimulation() {

		// infect initial victim
		myNetwork[r.nextInt(myNetwork.length)].infect(iv);

		for (int i = 0; i < timeSteps; i++) {

			if (i%50 == 0) { System.out.println("working on time = " + i); }
//			System.out.println("The time is " + i);

			// refresh our update list
			update1 = new ArrayList<Integer>();
			update2 = new ArrayList<BitSet>();

			// check every person infected to see if the virus mutated
			for (int j = 0; j < myNetwork.length; j++) {
				if (myNetwork[j].getStatus() == 'I') { myNetwork[j].mutation(mu);
//				System.out.println("Checking for mutation on person " + j);
				}
			}

			// now we go through infected indiviuals and have them interact
			for (int j = 0; j < myNetwork.length; j++) {
				if (myNetwork[j].getStatus() == 'I') {
//					System.out.println("I'm person " + j + " and I'm attempting to infect ");
					interact(myNetwork[j].getEdgeList(), j);
				}
			}

			// now we clear those infected and give them immunity
			for (int j = 0; j < myNetwork.length; j++) {
				if (myNetwork[j].getStatus() == 'I') {
					myNetwork[j].immunize();
				}
			}

			// this is going to report how many ppl are infected
			results.add(new Integer(update1.size()));

			// now we update the graph with newly infected individuals
			for (int j = 0; j < update1.size(); j++) {
//				System.out.println(update1.size() + " is the size of our update list");
				myNetwork[update1.get(j).intValue()].infect(update2.get(j));
			}

		}
	}


// this method helps our simulation by having interactions for each vertex at each step
// any action to be made is put in the update list
	private void interact(ArrayList<Integer> e, int j) {
		int t, h;
		for (int i = 0; i < e.size(); i++) {
			t = e.get(i).intValue();
//			System.out.print(t);
			if (myNetwork[t].getStatus() == 'S' && !update1.contains(new Integer(t))) {
				h = myNetwork[t].challenge(myNetwork[j].getVirus());
				if (h > Hthr) {
//					System.out.print(" and I'm getting infected! ");
//					System.out.print(t + " is getting added to update list");
					update1.add(new Integer(t));
					update2.add(myNetwork[j].getVirus().viralSeq());
				}
			}
//			System.out.println();
		}
	}

	public ArrayList<Integer> getResults() { return results; }
}