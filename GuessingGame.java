import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.io.*;
import java.util.StringTokenizer;

//Author: Rocky Xia
public class GuessingGame {

    public static BinaryTree user = new BinaryTree();
    public static int win = 0;
    public static int loss = 0;
    public static String savedData = "";

    public static void main(String[] args) throws IOException {
        run();
    }

    public static void run() throws IOException {
        setUpGame();
        boolean quit = false;
        while(!quit){
            int input = promptUser();
            if(input == 1){
                playGame();
            }
            else if(input == 2){
                checkStats();
            }
            else if(input == 3){
                quit = true;
            }
        }
        StdOut.println("Save game data");
        StdOut.println("1) Yes");
        StdOut.println("2) No");
        int input = StdIn.readInt();
        if(input == 1){
            writeData();
        }
    }

    public static void setUpGame() throws IOException {
        StdOut.println("Load previous data");
        StdOut.println("1) Yes");
        StdOut.println("2) No");
        int input = StdIn.readInt();
        if(input == 1){
            readData();
        }
    }

    //I couldn't find a solution to reading the data file back into a tree
    //My idea was to backtrack a pre order traversal and build the tree according to the results, but I couldn't figure out the implementation
    public static void readData() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("Data.txt"));
        String str = reader.readLine();
        reader.close();
        StringTokenizer tokens = new StringTokenizer(str);
        Node current = user.root;
        while(tokens.hasMoreTokens()){
            current.stuff = tokens.nextToken();
            current = current.leftNode;
        }
    }

    public static void writeData() throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Data.txt"), "utf-8"));
        preOrderSave(user.root);
        writer.write(savedData);
        writer.close();
    }

    private static void preOrderSave(Node node){
        if(node != null){
            savedData += node.stuff + " ";
            preOrderSave(node.leftNode);
            preOrderSave(node.rightNode);
        }
        else {
            savedData += "null ";
        }
    }

    public static int promptUser(){
        StdOut.println("Select option: ");
        StdOut.println("1) Play game");
        StdOut.println("2) Check stats");
        StdOut.println("3) Quit");
        return StdIn.readInt();
    }

    //Wrapper method for traversal
    public static void playGame(){
        boolean game = true;
        int level = 0;
        Node current = user.root;
        while(game){
            if(current.stuff.charAt(0) == 'q'){
                StdOut.println(convertString(getContent(current.stuff)));
                //StdOut.println(current.stuff);
                StdOut.println("1) Yes");
                StdOut.println("2) No");
                int input = StdIn.readInt();
                if(input == 1){
                    current = current.leftNode;
                }
                else if(input == 2){
                    current = current.rightNode;
                }
            }
            else if(current.stuff.charAt(0) == 'a'){
                String guess = convertString(getContent(current.stuff));
                StdOut.println("Is the answer " + guess);
                //StdOut.println(current.stuff);
                StdOut.println("1) Yes");
                StdOut.println("2) No");
                int input = StdIn.readInt();
                if(input == 1){
                    win++;
                    StdOut.println("Yeah, this is big brain time");
                }
                else if(input == 2){
                    loss++;
                    StdOut.println("What is the answer (use underlines for space)");
                    String answer = StdIn.readString();
                    StdOut.println("Enter a question that tells the difference between " + guess + " and " + convertString(answer) + " (use underlines for space)");
                    String question = StdIn.readString();
                    StdOut.println("What is the answer for " + convertString(answer));
                    StdOut.println("1) Yes");
                    StdOut.println("2) No");
                    int userInput = StdIn.readInt();
                    if(userInput == 1){
                        String temp = getContent(current.stuff);
                        current.stuff = format(question, 'q');
                        current.leftNode = new Node(format(answer, 'a'));
                        current.rightNode = new Node(format(temp, 'a'));
                    }
                    else if(userInput == 2){
                        String temp = getContent(current.stuff);
                        current.stuff = format(question, 'q');
                        current.leftNode = new Node(format(temp, 'a'));
                        current.rightNode = new Node(format(answer, 'a'));
                    }
                }
                game = false;
            }
            level++;
        }
    }

    public static void checkStats(){
        StdOut.println("Games won: " + win);
        StdOut.println("Games lost: " + loss);
    }

    public static String getContent(String str){
        return str.substring(2, str.length());
    }

    public static String format(String content, char type){
        return Character.toString(type) + "/" + content;
    }

    public static String convertString(String str){
        String output = "";
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == '_'){
                output += " ";
            }
            else {
                output += str.charAt(i);
            }
        }
        return output;
    }

    //Tree class for the games
    //Data storage format: [type]/[content]
    private static class BinaryTree{

        private Node root;

        private BinaryTree(){
            root = new Node("q/Do_programmers_like_this");
            root.leftNode = new Node("a/Well_organized_code");
            root.rightNode = new Node("a/Nonexistent_documentation");
        }
    }

    //Node class for tree
    private static class Node{
        private Node leftNode;
        private Node rightNode;
        private String stuff;

        private Node(String stuff){
            this.stuff = stuff;
            this.leftNode = null;
            this.rightNode = null;
        }
    }
}
