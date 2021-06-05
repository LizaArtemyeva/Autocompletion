import java.util.ArrayList;
import java.util.List;

public class Trie {

    public static class Node {
        private char key;
        List<Node> children = new ArrayList<>();
        private int value;

        @Override
        public String toString() {
            StringBuilder s = new StringBuilder(key+ " " + value + System.lineSeparator());
            for (Node node : children) {
                s.append(node).append(System.lineSeparator());
            }
            return s.toString();
        }
    }
    Node root = new Node();

    public Node find(String s) {
        Node v = root;
        for (char ch : s.toLowerCase().toCharArray()) {
            boolean find = false;
            for (Node n : v.children) {
                if (n.key == ch) {
                    v = n;
                    find = true;
                }
            }
            if(!find){
                return null;
            }
        }
        return v;
    }

    public void add(String s){
        Node v = root;
        for (char ch : s.toLowerCase().toCharArray()) {
            Node find = null;
            for (Node n : v.children) {
                if(n.key == ch){
                    find = n;
                    break;
                }
            }
            if(find == null){
                Node node = new Node();
                node.key = ch;
                v.children.add(node);
                v = node;
            } else {
                v = find;
            }
        }
        v.value++;
    }

    public void delete(String s){
        Node v = root;
        for (char ch : s.toLowerCase().toCharArray()) {
            for (Node n : v.children) {
                if(n.key == ch){
                    if(n.children.isEmpty()){
                        v.children.remove(n);
                        break;
                    }
                    v=n;
                }
            }
        }
        if(s.length()>0) {
            delete(s.substring(0, s.length() - 1));
        }
    }
    private String makeWord(String path, Node start, Node find){
        if(start==find) return path+start.key;
        for(Node n: start.children){
            String s = makeWord(path+start.key, n, find);
            if(!s.isEmpty()) return s.trim();
        }
        return "";
    }

    public String makeWord(Node find){
        return makeWord("", root, find);
    }

    public List<String> getWords(String s){
        List<String> words = new ArrayList<>();
        Node nodeStart = find(s);
        if (nodeStart == null) return words;
        List<Node> nodes = new ArrayList<>(nodeStart.children);
        while (!nodes.isEmpty()){
            List<Node> toAdd = new ArrayList<>();
            List<Node> toRemove = new ArrayList<>();
            for (Node n : nodes) {
                if (n.value > 0) {
                    words.add(makeWord(n));
                }
                toAdd.addAll(n.children);
                toRemove.add(n);
            }
            nodes.addAll(toAdd);
            nodes.removeAll(toRemove);
            toAdd.clear();
            toRemove.clear();
        }
        return words;
    }

    @Override
    public String toString() {
        return root.toString();
    }

    public void init(){
        this.add("apples");
        this.add("applos");
        this.add("app");
        this.add("cat");
        this.add("catalog");
        this.add("catfishing");
        this.add("catflix");
        this.add("aboba");
    }
}
