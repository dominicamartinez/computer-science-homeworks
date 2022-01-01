import java.util.ArrayList;
import java.util.BitSet;
import java.util.Random;

public class Vertex {
	private char status;
	private Virus currentVirus;
	private ArrayList<Virus> memory;
	private ArrayList<Integer> edges;
	private int k;
	private Random r;
	private int L;
	private int id;

// constructor
	public Vertex(int k, int L, int id) {
		r = new Random();
		this.k = 2*k;
		status = 'S';
		memory = new ArrayList<Virus>();
		edges = new ArrayList<Integer>(2*k);
		currentVirus = new Virus(L);
		this.L = L;
		this.id = id;
	}

// this helps in printing out the edges
	public void getEdges() {
		for (int i = 0; i < edges.size(); i++) {
			System.out.print(" " + edges.get(i).intValue() + " ");
		}
	}

// initial setup a ring type network with each vertex connected to 2k adjacent vertices
	public void initializeEdges(int index, int N) {
		for (int i = 0; i < k/2; i++) {
			int t = (index + (i-k/2))%N;
			if (t < 0) { t = N + t; }
			edges.add(i, new Integer(t));
		}

		for (int i = k/2; i < k; i++) {
			edges.add(i, new Integer((index + (i-(k/2 - 1)))%N));
		}
	}

// returns last edge
	public int getEdge() { return edges.get(edges.size()-1).intValue();	}

// removes edge
	public void removeEdge(int i) { edges.remove(new Integer(i)); }

// returns true if edge is contained
	public boolean containsEdge(int i) { return edges.contains(new Integer(i)); }

// adds to the front of the list
	public void addEdge(int i) { edges.add(0, new Integer(i)); }

// returns edgeCount
	public int edgeCount() { return edges.size(); }

// returns edges as ArrayList
	public ArrayList<Integer> getEdgeList() { return edges; }

// returns status
	public char getStatus() { return status; }

// returns currentVirus
	public Virus getVirus() { return currentVirus; }

// individual becomes infected
	public void infect(BitSet b) {
		currentVirus.setVirus(b);
		status = 'I';
	}

// if individual is infected then there is a chance for mutation
	public void mutation(double mu) {
		if (r.nextDouble() < mu) { currentVirus.mutate(); }
	}

// this is a virus attempting to infect me
	public int challenge(Virus v) {
		int Hmin = L;
		for (int i = 0; i < memory.size(); i++) {
			Hmin = Math.min(memory.get(i).hamming(v), Hmin);
		}
		return Hmin;
	}

// this puts the person's currentVirus in memory and makes them susceptible again
	public void immunize() {
		memory.add(currentVirus);
		currentVirus = new Virus(L);
		status = 'S';
	}

// we update the memory of individual and get rid of all immunities after some time
	public void updateImmunity() {
		for (int i = 0; i < memory.size(); i++) {
			memory.get(i).runCounter();
			if (memory.get(i).getHL() == id) { memory.remove(i); }
		}
	}

}