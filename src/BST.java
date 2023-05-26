import java.util.Iterator;
import java.util.Stack;

/*
 * Binary Search Tree implementation.
 * @param <K> the type of keys stored in the tree (must be comparable)
 * @param <V> the type of values associated with the keys
 */
public class BST<K extends Comparable<K>, V> implements Iterable<BST.Node> {
    private Node root;
    private int size;

    public class Node {
        private K key;
        private V val;
        private Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
        /*
         * Constructs a new node with the specified key and value.
         * @param key the key of the node
         * @param val the value associated with the key
         */

        public K getKey(){
            return key;
        }
        public V getVal(){
            return val;
        }
    }
    public void put(K key, V val) {
        root = putRec(root, key, val);
    }
    /*
     * Inserts a key-value pair into the tree.
     * @param key the key to insert
     * @param val the value associated with the key
     */

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
        Node res = getRec(root, key);
        return res == null ? null : res.val;
    }

    private Node getRec(Node currentNode, K key){
        if(currentNode == null){
            return null;
        }
        else if(currentNode.key.compareTo(key) > 0){
            return getRec(currentNode.right, key);
        }else if (currentNode.key.compareTo(key) < 0){
            return getRec(currentNode.left, key);
        } else{
            return currentNode;
        }
    }

    public void delete(K key) {
        root = delRec(root,key);
    }
    /*
     * Deletes a node with the specified key from the tree.
     * @param key the key to delete
     */

    private Node delRec(Node currentNode, K key){
        if(currentNode == null){
            return null;
        }
        else if(currentNode.key.compareTo(key) > 0){
            currentNode.right = delRec(currentNode.right, key);
        }
        else if(currentNode.key.compareTo(key) < 0){
            currentNode.left = delRec(currentNode.left, key);
        }
        else{
            if(currentNode.left == null && currentNode.right == null){
                return null;
            } else if (currentNode.left == null) {
                return currentNode.right;
            } else if (currentNode.right == null) {
                return currentNode.left;
            } else {
                Node minNode = minNode(currentNode.right);
                currentNode.key = minNode.key;
                currentNode.val = minNode.val;
                currentNode.right = delRec(currentNode.right, minNode.key);
            }
        }
        return currentNode;
    }

    private Node minNode(Node currentNode){
        return currentNode.left == null ? currentNode : minNode(currentNode.left);
    }
    public Iterator<BST.Node> iterator(){
        return new BSTIterator();
    }
    /*
     * Returns an iterator over the nodes of the tree.
     * @return an iterator over nodes
     */

    private class BSTIterator implements Iterator<BST.Node> {
        private Stack<Node> stack;

        public BSTIterator() {
            stack = new Stack<>();
            pushLeftNodes(root);
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Node next() {
            Node current = stack.pop();
            pushLeftNodes(current.right);
            return current;
        }

        private void pushLeftNodes(Node node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }
    }

    public int getSize() {
        return size;
    }
    /*
     * Returns the size of the tree.
     * @return the size
     */
    public boolean contains(K key){
        return conRec(key, root);
    }
    private boolean conRec(K key, Node currentNode){
        if(currentNode == null){
            return false;
        }
        if(currentNode.key.equals(key)){
            return true;
        }
        else if(currentNode.key.compareTo(key) < 0){
            return conRec(key, currentNode.left);
        }
        else{
            return conRec(key, currentNode.right);
        }
    }
}
