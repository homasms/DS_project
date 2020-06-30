class Node {
    private char data;
    Node[] children;
    String isEndOfWord;

    Node() {
        children = new Node[63];
    }

    Node(char Data) {
        data = Data;
        children = new Node[63];
    }
}
