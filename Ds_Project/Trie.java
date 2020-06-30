public class Trie {
    private Node root;
    private boolean searchResult = false;

    Trie() {
        root = new Node();
    }

    void insert(String varOrFunc, String type, String name, String inputType) {
        Node node = root;
        for (int i = 0; i < name.length(); i++) {
            char d = name.charAt(i);
            int index = findIndex(d);
            node = insert(d, node, index);
            if (i == name.length() - 1) {
                if (varOrFunc.equals("variable"))
                    node.isEndOfWord = "variable" + " " + type + " " + name;
                else
                    node.isEndOfWord = "function" + " " + type + " " + name + " " + "(" + inputType + ")";
            }
        }

    }

    private Node insert(char d, Node n, int index) {
        if (n.children[index] == null)
            n.children[index] = new Node(d);
        return n.children[index];
    }

    protected int findIndex(char d) {
        int index = 0;
        if ((int) d > 64 && (int) d < 91)
            index = (int) d % 63 - 2;
        if ((int) d > 96 && (int) d < 123)
            index = (int) d % 63 - 7;
        if ((int) d == 95)
            index = 26;
        if ((int) d > 47 && (int) d < 58)
            index = (int) d % 63 + 5;
        return index;
    }

    boolean remove(String name) {
        Node n = root;
        for (int i = 0; i < name.length(); i++) {
            char d = name.charAt(i);
            if (n.children[findIndex(d)] == null)
                return false;
            else {
                if (i == name.length() - 1) {
                    if (n.children[findIndex(d)].isEndOfWord != null) {
                        n.children[findIndex(d)].isEndOfWord = null;
                        return true;
                    } else
                        return false;
                }
                if (n.children != null)
                    n = n.children[findIndex(d)];
            }
        }
        return false;
    }

    boolean search(String name) {
        searchResult = false;
        if (name == null || name.equals(""))
            throw new java.lang.IllegalArgumentException();

        Node node = root;
        for (int i = 0; i < name.length(); i++) {
            char d = name.charAt(i);
            node = node.children[findIndex(d)];
            if (node == null)
                return false;
        }
        if (node.isEndOfWord != null) {
            System.out.println(node.isEndOfWord);
            searchResult = true;
        }
        return search(node);
    }

    private boolean search(Node n) {
        for (int i = 0; i < 63; i++) {
            if (n.children[i] != null) {
                if (n.children[i].isEndOfWord != null) {
                    System.out.println(n.children[i].isEndOfWord);
                    searchResult = true;
                }
                search(n.children[i]);
            }
        }
        return searchResult;
    }
}
