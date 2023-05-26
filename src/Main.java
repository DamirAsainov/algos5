import org.w3c.dom.Node;

public class Main {
    public static void main(String[] args) {
        BST<Integer,String> bst = new BST<>();
        bst.put(4, "Da");
        bst.put(2, "dasda");
        bst.put(6, "600023123d");
        bst.put(8, "8---243423423;sd");
        bst.put(7, "79003012030123Dmaie");
        bst.put(5, "5124990023123Dasdasd");
        System.out.println(bst.getSize());
        System.out.println(bst.get(4));
        bst.delete(4);
        System.out.println(bst.get(4));
        for(var elem: bst){
            System.out.println("key is " + elem.getKey() + " and value is " + elem.getVal());
        }
        System.out.println();
        System.out.println("BST contains 8 - " + bst.contains(8));
        System.out.println("BST contains 9 - " + bst.contains(9));
        System.out.println("BST contains 4 - " + bst.contains(4));
        System.out.println("BST contains 6 - " + bst.contains(6));
    }
}