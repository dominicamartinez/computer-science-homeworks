// Dominic Martinez
// Grader: Mark Miles
// 4/23/03

// This is the graph from pg 582 of the Rosen book

// I believe my Prim's algorithm to run in O ( |V|^2 )


import java.util.*;

class Graph {

	private class Vertex {

		LinkedList edge;
		String name;

		public Vertex(String name) {
			edge = new LinkedList();
			this.name = name;
		}

		// adds an edge to the vertex
		public void add(Edge e) {
			edge.add(e);
		}

		// removes the edge to the vertex
		public void remove(Edge e) {
			edge.remove(e);
		}

		// returns the name of the vertex
		public String getName() { return name; }

		// returns the minimum weighted edge
		public Edge findMin(Hashtable h) {
			Edge min = (Edge)edge.getFirst();
			Edge temp;
			for (int i = 0; i < edge.size(); i++) {
				temp = (Edge)edge.get(i);
				if (temp.getWeight() < min.getWeight() && (!h.containsKey(temp.getV1().getName()) || !h.containsKey(temp.getV2().getName()))) {
					min = temp;
				}
			}
			return min;
		}
	}

	private class Edge {
		int weight;
		Vertex v1, v2;

		public Edge(int weight, Vertex v1, Vertex v2) {
			this.weight = weight;
			this.v1 = v1;
			this.v2 = v2;
		}

		// returns the first vertex
		public Vertex getV1() { return v1; }
		// returns the second vertex
		public Vertex getV2() { return v2; }

		// returns the weight of the edge
		public int getWeight() { return weight; }
	}

	LinkedList list, tree;
	Hashtable hash;
	Vertex sanfran, chicago, denver, ny, atlanta;
	Edge a, b, c, d, e, f, g, h, i, j;

	public Graph() {
		tree = new LinkedList();
		list = new LinkedList();
		hash = new Hashtable();

		sanfran = new Vertex("sanfran");
		chicago = new Vertex("chicago");
		denver = new Vertex("denver");
		ny = new Vertex("ny");
		atlanta = new Vertex("atlanta");

		a = new Edge(2000, sanfran, ny);
		b = new Edge(1200, sanfran, chicago);
		c = new Edge(1000, chicago, ny);
		d = new Edge(900, sanfran, denver);
		e = new Edge(1300, denver, chicago);
		f = new Edge(2200, sanfran, atlanta);
		g = new Edge(1600, denver, ny);
		h = new Edge(1400, denver, atlanta);
		i = new Edge(700, chicago, atlanta);
		j = new Edge(800, atlanta, ny);

		sanfran.add(a);
		sanfran.add(b);
		sanfran.add(d);
		sanfran.add(f);
		chicago.add(b);
		chicago.add(c);
		chicago.add(e);
		chicago.add(i);
		denver.add(d);
		denver.add(e);
		denver.add(g);
		denver.add(h);
		ny.add(a);
		ny.add(c);
		ny.add(g);
		ny.add(j);
		atlanta.add(f);
		atlanta.add(h);
		atlanta.add(i);
		atlanta.add(j);

		// making a list of the vertices
		list.add(chicago);
		list.add(ny);
		list.add(atlanta);
		list.add(sanfran);
		list.add(denver);
	}

	public void solve() {

		// for output purposes
		String temp = "";

		// also for output
		int i = 1;

		// let's start with chicago
		Vertex v = (Vertex)list.getFirst();
		tree.add(v);
		hash.put(v.getName(), v);

		// make sure we haven't touched all the vertices
		while (tree.size() != list.size()) {

			// this prints out all the information
			temp += i + "\t" + v.findMin(hash).getV1().getName();
			temp += ", " + v.findMin(hash).getV2().getName() + "\t";
			temp += v.findMin(hash).getWeight();
			System.out.println(temp);
			temp = "";
			i++;

			// find the newly touched vertex we want to check for min
			// including all the previous ones touched
			// and remove the edge that was touched
			Edge mini = (Edge)v.findMin(hash);

			if (!hash.containsKey(mini.getV1().getName())) {
				int j = list.indexOf(mini.getV2());
				v = (Vertex)list.get(j);
				v.remove(mini);

				j = list.indexOf(mini.getV1());
				v = (Vertex)list.get(j);
				v.remove(mini);
				tree.add(v);
				hash.put(v.getName(), v);
			}
			else {
				int j = list.indexOf(mini.getV1());
				v = (Vertex)list.get(j);
				v.remove(mini);

				j = list.indexOf(mini.getV2());
				v = (Vertex)list.get(j);
				v.remove(mini);
				tree.add(v);
				hash.put(v.getName(), v);
			}


			// find the next vertex with the smallest weighted
			// edge to an untouched vertex
			Vertex min = (Vertex)tree.getFirst();

			for (int k = 0; k < tree.size(); k++) { // scans the tree
				v = (Vertex)tree.get(k);
				// if it's got the smallest weight over all the tree
				// and if one of it's vertex hasn't been touched
				if (v.findMin(hash).getWeight() < min.findMin(hash).getWeight() && (!hash.contains(v.findMin(hash).getV2()) || !hash.contains(v.findMin(hash).getV1()))) {
					min = v;
				}
			}

			// set it up to go over again
			v = min;

		} // end of the while
	} // end of the method
} // end of the class

// TestDriver
public class Prim {
	public static void main(String[] args) {

		// creating the object
		Graph g = new Graph();

		// use the algorithm to solve it
		g.solve();

	}
}

