public class Main {

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.add("apples");
        trie.add("applos");
        trie.add("app");
        trie.add("cat");
        trie.delete("cat");
        Trie.Node node = trie.find("applo");
        System.out.println(trie.makeWord(node));
    }
}
