//Author: Rocky Xia
public class BinarySearchTreeTest {

    public static void main(String[] args){
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(20);
        tree.insert(17);
        tree.insert(35);
        tree.insert(7);
        tree.insert(19);
        tree.insert(26);
        tree.insert(40);
        tree.insert(5);
        tree.insert(13);
        tree.insert(3);
        tree.printPreOrder();
    }
}
