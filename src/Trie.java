import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Trie {

    private static class Node {
        //private Map<Character, Node> child = new TreeMap<>();
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

    public int find(String s) {
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
                return 0;
            }
        }
        return v.value;
    }
    public void add(String s){
//        Node v = root;
//        for (char ch : s.toLowerCase().toCharArray()) {
//            for (Node n : v.children) {
//                if (n.key != ch) {
////                    Node node = new Node();
////                    node.key = ch;
////                    v.children.add(node);
//                    v = n;
//                } else {
//                    //n.value++;
//                    v = n;
//                }
//            }
//        }
//        v.value++;
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
            //boolean toDel = false;
            //Node find = null;
            for (Node n : v.children) {
                if(n.key == ch){
                    //find = n;
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

    @Override
    public String toString() {
        //System.out.println(v.children);
        return root.toString();
    }
}
