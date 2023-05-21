import java.util.Iterator;
import java.util.Stack;

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
}
