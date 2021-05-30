public class Main {

    public static void main(String[] args) {
	// write your code here
        Trie trie = new Trie();
        trie.add("apples");
//        System.out.println("apples - " + trie.find("apples"));
//        System.out.println("app - " +trie.find("app"));
        trie.add("applos");
        trie.add("app");
        trie.add("artemchik");
        trie.delete("applos");
        System.out.println(trie);
    }
}
