public class RTGTester {
    public static void main(String[] args) {
        RandomTextGenerator rtg = new RandomTextGenerator("bible.txt");
        rtg.generate(10, 100, "gui.txt");
    }
}