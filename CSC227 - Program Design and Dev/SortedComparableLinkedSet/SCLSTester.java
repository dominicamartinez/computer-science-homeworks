public class SCLSTester {
    public static void main(String[] args) {
        SortedComparableLinkedSet scls = new SortedComparableLinkedSet();

        System.out.println(scls.toString());

        scls.add("mike");
        scls.add("brooks");
        scls.add("loves");
        scls.add("computer");
        scls.add("science");

        System.out.println(scls.toString());

        scls.add("mike");
        scls.add("science");
        scls.add("brooks");

        System.out.println(scls.toString());

        System.out.println(scls.contains("mike"));
        System.out.println(scls.contains("brooks"));
        System.out.println(scls.contains("mikey"));
        System.out.println(scls.contains("random"));
        System.out.println(scls.contains("yuck"));

        System.out.println(scls.toString());

        System.out.println(scls.get(0));
        System.out.println(scls.get(1));
        System.out.println(scls.get(2));
        System.out.println(scls.get(3));
        System.out.println(scls.get(4));

        System.out.println(scls.size());

        System.out.println(scls.toString());

        scls.remove("brooks");
        scls.remove("science");

        System.out.println(scls.toString());

        scls.remove("mike");
        scls.remove("computer");
        scls.remove("loves");

        System.out.println(scls.toString());

        scls.remove("mike");

        System.out.println(scls.toString());

        SortedComparableLinkedSet scls2 = new SortedComparableLinkedSet();
        scls2.add("mike");
        scls2.add("brooks");
        scls2.add("loves");
        scls2.add("computer");
        scls2.add("science");

        System.out.println(scls2.toString());

        scls.addAll(scls2);

        System.out.println(scls.toString());

        scls2.removeRange("ccc", "lvl");

        System.out.println(scls2.toString());
    }
}