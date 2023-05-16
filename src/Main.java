public class Main {
    public static void main(String[] args) {
        BST<Integer,String> bst = new BST<>();
        bst.put(4, "Da");
        bst.put(2, "dasda");
        bst.put(6, "6");
        System.out.println(bst.getSize());
        System.out.println(bst.get(2));
    }
}