public class BST<K extends Comparable<K>, V> {
    private Node root;
    private int size;

    private class Node {
        private K key;
        private V val;
        private Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }
    public void put(K key, V val) {
        root = putRec(root, key, val);
    }

    private Node putRec(Node currentNode, K key, V val){
        if(currentNode == null){
            size++;
            return new Node(key, val);
        } else if (currentNode.key.compareTo(key) > 0) {
            currentNode.right = putRec(currentNode.right, key, val);
        } else if (currentNode.key.compareTo(key) < 0) {
            currentNode.left = putRec(currentNode.left, key, val);
        } else if (currentNode.key.compareTo(key) == 0) {
            currentNode.val = val;
        }
        return currentNode;
    }

    public V get(K key) {
        return null;
    }

    public void delete(K key) {
    }

    public Iterable<K> iterator() {
        return null;
    }
}
