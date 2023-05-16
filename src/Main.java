public class Main {
    public static void main(String[] args) {
        BST<Integer,String> bst = new BST<>();
        bst.put(4, "Da");
        bst.put(2, "dasda");
        bst.put(6, "6");
        bst.put(8, "8");
        bst.put(7, "7");
        bst.put(5, "5");
        System.out.println(bst.getSize());
        System.out.println(bst.get(4));
        bst.delete(4);
        System.out.println(bst.get(4));
    }
}