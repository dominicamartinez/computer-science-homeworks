public class TestDriver {


	public static void main(String[] args) {
	    DoubleList sdm = new SortedDoubleMultiset();

	    System.out.println("Add 3.0");
	    sdm.add(3.0);
	    System.out.println("Add 5.0");
	    sdm.add(5.0);
	    System.out.println("Add 8.0");
	    sdm.add(8.0);
	    System.out.println("Add 3.5");
	    sdm.add(3.5);
	    System.out.println("Add 3.0");
	    sdm.add(3.0);
	    System.out.println("Add 7.0");
	    sdm.add(7.0);
	    System.out.println(sdm.toString() + " " + sdm.size());

	    System.out.println();

	    System.out.println("Remove Single 3.5: " + sdm.removeSingle(3.5));
	    System.out.println(sdm.toString() + " " + sdm.size());
	    System.out.println("Remove Single 8.0: " + sdm.removeSingle(8.0));
	    System.out.println(sdm.toString() + " " + sdm.size());

	    System.out.println();

	    System.out.println("Remove All 9.0: " + sdm.removeAll(9.0));
	    System.out.println(sdm.toString() + " " + sdm.size());
	    System.out.println("Remove All 3.0: " + sdm.removeAll(3.0));
	    System.out.println(sdm.toString() + " " + sdm.size());
	    System.out.println("Remove All 5.0: " + sdm.removeAll(5.0));
	    System.out.println(sdm.toString() + " " + sdm.size());

	    System.out.println();

	    System.out.println("Building another list back up");
	    sdm = new SortedDoubleMultiset();
	    sdm.add(5.0);
	    sdm.add(8.0);
	    sdm.add(3.0);
	    sdm.add(3.5);
	    sdm.add(7.0);
	    sdm.add(3.0);
	    sdm.add(2.0);
	    sdm.add(7.0);
	    System.out.println(sdm.toString() + " " + sdm.size());

	    System.out.println("Get 7: " + sdm.get(7));
	    System.out.println("Get -1: " + sdm.get(-1));
	    System.out.println("Get 122: " + sdm.get(122));
	    System.out.println("Get 5: " + sdm.get(5));
	    System.out.println(sdm.toString() + " " + sdm.size());

	    System.out.println();

	    System.out.println("Sublist 0, 2: " + sdm.sublist(0, 2));
	    System.out.println("Sublist -1, 8: " + sdm.sublist(-1, 8));
	    System.out.println("Sublist 5, 6: " + sdm.sublist(5, 6));
	    System.out.println("Sublist 3, 2: " + sdm.sublist(3, 2));
	    System.out.println("Sublist 0, 0: " + sdm.sublist(0, 0));
	    System.out.println(sdm.toString() + " " + sdm.size());

	    System.out.println();

	    System.out.println("Remove Range 0, 2: " + sdm.removeRange(0, 2));
	    System.out.println(sdm.toString() + " " + sdm.size());
	    System.out.println("Remove Range -1, 20: " + sdm.removeRange(-1, 20));
	    System.out.println(sdm.toString() + " " + sdm.size());
	    System.out.println("Remove Range 2, 4: " + sdm.removeRange(2, 4));
	    System.out.println(sdm.toString() + " " + sdm.size());
	    System.out.println("Remove Range 1, 5: " + sdm.removeRange(1, 5));
	    System.out.println(sdm.toString() + " " + sdm.size());
	    System.out.println("Remove Range 1, 1: " + sdm.removeRange(1, 1));
	    System.out.println(sdm.toString() + " " + sdm.size());
	}

}