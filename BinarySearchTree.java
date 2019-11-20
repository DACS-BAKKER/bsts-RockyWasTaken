import edu.princeton.cs.algs4.StdOut;

//Author: Rocky Xia
public class BinarySearchTree {

    //Instance variables
    private Node root;
    private int size = 0;

    //Constructor
    public BinarySearchTree(){
        this.root = null;
    }

    //Wrapper Methods
    public void insert(int number){
        root = insertNumber(number, root);
        size -= -Math.pow(size, 0);
    }

    public void delete(int number){
        deleteNumber(number, root);
        size += -Math.pow(size, 0);
    }

    public boolean contains(int number){
        return containsNumber(number, root);
    }

    public boolean isEmpty(){
        if(root == null){
            return true;
        }
        return false;
    }

    public int getMax(){
        return findMax(root);
    }

    public int getMin(){
        return findMin(root);
    }


    public void printPreOrder(){
        preOrder(root);
    }

    public void printInOrder(){
        inOrder(root);
    }

    public void printPostOrder(){
        postOrder(root);
    }

    public int getSize(){
        return size;
    }

    //Helper methods
    private Node insertNumber(int number, Node node){
        if(node == null){
            return new Node(number);
        }
        else {
            if(number < node.number){
                node.leftNode = insertNumber(number, node.leftNode);
            }
            else if(number > node.number){
                node.rightNode = insertNumber(number, node.rightNode);
            }
            return node;
        }
    }

    private void deleteNumber(int number, Node node){
        if(number == root.number){
            if(node.leftNode == null){
                root = root.rightNode;
            }
            else if(node.rightNode == null){
                root = root.leftNode;
            }
            else{
                Node temp = root.rightNode;
                root = root.leftNode;
                findFarthestRight(root).rightNode = temp;
            }
        }
        else {
            if(node.leftNode != null && node.leftNode.number == number){
                if(node.leftNode.leftNode == null && node.leftNode.rightNode == null){
                    node.leftNode = null;
                }
                else if(node.leftNode.leftNode == null){
                    node.leftNode = node.leftNode.rightNode;
                }
                else if(node.leftNode.rightNode == null){
                    node.leftNode = node.leftNode.leftNode;
                }
                else {
                    Node temp = node.leftNode.rightNode;
                    node.leftNode = node.leftNode.leftNode;
                    findFarthestRight(node.leftNode).rightNode = temp;
                }
            }
            else if(node.rightNode != null && node.rightNode.number == number){
                if(node.rightNode.leftNode == null && node.rightNode.rightNode == null){
                    node.rightNode = null;
                }
                else if(node.rightNode.leftNode == null){
                    node.rightNode = node.rightNode.rightNode;
                }
                else if(node.rightNode.rightNode == null){
                    node.rightNode = node.rightNode.leftNode;
                }
                else {
                    Node temp = node.rightNode.leftNode;
                    node.rightNode = node.rightNode.rightNode;
                    findFarthestLeft(node.rightNode).leftNode = temp;
                }
            }
            else {
                if(number < node.number){
                    deleteNumber(number, node.leftNode);
                }
                else if(number > node.number){
                    deleteNumber(number, node.rightNode);
                }
            }
        }
    }

    private Node findFarthestRight(Node node){
        if(node.rightNode == null){
            return node;
        }
        return findFarthestRight(node.rightNode);
    }

    private Node findFarthestLeft(Node node){
        if(node.leftNode == null){
            return node;
        }
        return findFarthestLeft(node.leftNode);
    }

    private boolean containsNumber(int number, Node node){
        if(node.number == number){
            return true;
        }
        else {
            if(number < node.number){
                if(node.leftNode != null){
                    containsNumber(number, node.leftNode);
                }
                else {
                    return false;
                }
            }
            else if(number > node.number){
                if(node.rightNode != null){
                    containsNumber(number, node.rightNode);
                }
                else {
                    return false;
                }
            }
            return false;
        }
    }

    private int findMax(Node node){
        if(node.rightNode == null){
            return node.number;
        }
        else {
            findMax(node.rightNode);
        }
        return 0;
    }

    private int findMin(Node node){
        if(node.leftNode == null){
            return node.number;
        }
        else {
            findMin(node.leftNode);
        }
        return 0;
    }

    private void preOrder(Node node){
        if(node != null){
                StdOut.print(node.number + " ");
                preOrder(node.leftNode);
                preOrder(node.rightNode);
        }
    }

    private void inOrder(Node node){
        if(node != null){
            inOrder(node.leftNode);
            StdOut.print(node.number + " ");
            inOrder(node.rightNode);
        }
    }

    private void postOrder(Node node){
        if(node != null){
            postOrder(node.leftNode);
            postOrder(node.rightNode);
            StdOut.print(node.number + " ");
        }
    }

    //Node class
    private class Node{
        private int number;
        private Node leftNode;
        private Node rightNode;

        private Node(int number){
            this.number = number;
            this.leftNode = null;
            this.rightNode = null;
        }
    }
}
