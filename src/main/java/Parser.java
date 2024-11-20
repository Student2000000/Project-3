//import TreePackage;
//package StackAndQueuePackage;

/*
//Ignore this (it's so I don't loose track of what I'm doing)
    Methods for this class from the instructions:
    1) Varius constructos
        the binary serach trees and lists I presume
    2) intializeReservedWords
        Read in the file of reserved words and put them into your
        reserved words data structure. Then use setBalancedBST
        to set up a balanced BST from them.
    3) SetBalancedBST
        Add a reserved word and ensure binary search tree is balanced.
    4) getIdentifiers
        Finds the identifiers in a Java program and adds them to the
        identifiers BST.
     */

import StackAndQueuePackage.LinkedQueue;
import TreePackage.BinarySearchTree;
import TreePackage.BinaryNode;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.util.Arrays;


public class Parser {

    /**
     * Constructors
     */
    //1
    private static LinkedQueue<String> reservedWordsQueue;

    private static LinkedQueue<String> identifiers;

    private static BinarySearchTree<String> reservedBST;

    private static BinarySearchTree<String> identifiersBST;


    public Parser() {

        reservedWordsQueue = new LinkedQueue<>(); //Creating a Linked Queue to store reserved words

        identifiers = new LinkedQueue<>(); //Creating a Linked Queue to store identifiers

        reservedBST = new BinarySearchTree<>(); //Creating a BST for the reserved words found

        identifiersBST = new BinarySearchTree<>(); //Creating a BST for the identifiers found

    }

    //Ignore this: Wrote this to test that my Scanner was actually working,
    //May try to incorperate in into my testing file
    /*
    public static void printQueue(LinkedQueue<String> queue) {
        LinkedQueue<String> tempQueue = new LinkedQueue<>();

        while (!queue.isEmpty()) {
            String front = queue.dequeue();
            System.out.println(front);
            tempQueue.enqueue(front);
        }

        while (!tempQueue.isEmpty()) {
            queue.enqueue(tempQueue.dequeue());
        }
    }
    */

    /**
     * Prints out the contents of a given Linked Queue
     * @param queue   The Linked Queue that the method prints
     */
    public static void printOut(LinkedQueue<String> queue) {
        while (!queue.isEmpty()) {
            String front = queue.dequeue();
            System.out.println(front);
        }
    }

    /**
     * Enters the data from a Linked Queue
     * (specifically used for reservedWordsQueue in this program)
     * and puts them into a BST
     * @param queueReserved   Linked Queue to be put into a BST
     *                        (The Linked Queue: reservedWordsQueue as used here)
     */
    //3
    public void setBalancedBST(LinkedQueue<String> queueReserved) {
        while (!queueReserved.isEmpty()) {
            String frontReserveQueue = queueReserved.dequeue();
            if (reservedBST.isEmpty()) {
                reservedBST.add(frontReserveQueue);
            } else {
                reservedBST.add(frontReserveQueue);
            }
            //queueReserved.dequeue();
        }
    }

    /**
     * Reads the contents of reservedWords.txt and enters them into
     * a Linked Queue
     */
    //2
    public void initializeReservedWords() {
        File reservedWords = new File("src/main/java/reservedWords.txt");
        try (Scanner readReserve = new Scanner(reservedWords)) {
            while (readReserve.hasNextLine()) {
                String lineProc = readReserve.nextLine();
                reservedWordsQueue.enqueue(lineProc);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        printOut(reservedWordsQueue);

        //printQueue(reservedWordsQueue);
        while (!reservedWordsQueue.isEmpty()) {
            setBalancedBST(reservedWordsQueue);
        }

        //System.out.println(reservedWordsQueue);
        //printOut(reservedWordsQueue);
    }

    /**
     * Enters the data from a Linked Queue
     * (Specifically used for idendifiers in this program)
     * and puts them into a BST
     * @param queueIden   Linked Queue to be put into a BST
     *                    (The Linked Queue: identifiers as used here)
     */
    public void setBalancedBSTIden(LinkedQueue<String> queueIden) {
        while (!queueIden.isEmpty()) {
            String frontIdenQueue = queueIden.getFront();
            if (identifiersBST.isEmpty()) {
                identifiersBST.add(frontIdenQueue);
            }
            queueIden.dequeue();
        }
    }

    /**
     * Reads the contents of Palindrome.java breaking each
     * line into individual words (marked by spaces),
     * filters out identifiers, and enters them into a Linked Queue
     */
    //4
    public void getIdentifiers() {
        File palindromeFile = new File("src/main/java/Palindrome.java");
        try (Scanner readPalindrome = new Scanner(palindromeFile)) {
            while (readPalindrome.hasNextLine()) {
                String lineByLinePalendrome = readPalindrome.nextLine();
                String[] wordsOfPal = lineByLinePalendrome.split(" "); //spliting by white space using regex
                //System.out.println(lineByLinePalendrome); // for testing
                //System.out.println(wordsOfPal); // testing
                for (int i = 0; i < wordsOfPal.length - 1; i++) {
                    String currentWord = wordsOfPal[i];
                    String nextWord = wordsOfPal[i + 1];

                    if ((currentWord.equals("(") || currentWord.equals("=") || currentWord.equals("boolean") || currentWord.equals("String") || currentWord.equals("class")) &&(!reservedBST.contains(nextWord))) {
                        identifiers.enqueue(nextWord);
                    }
                    //System.out.println(identifiers); //testing
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        printOut(identifiers);


        while (!identifiers.isEmpty()) {
            setBalancedBSTIden(identifiers);
        }
    }
}

//Scraps:
    /*

    public void printing() {
        printOut(identifiers);
        //printOut(reservedWordsQueue);
        //System.out.println("test");
    }


    public void findingWordsToPrint (BinarySearchTree<String> BST_A, BinarySearchTree<String> BST_B) {
        File palindromeFile2 = new File("src/main/java/Palindrome.java");
        try (Scanner readPalindrome2 = new Scanner(palindromeFile2)) {
            while (readPalindrome2.hasNextLine()) {
                String lineByLinePalendrome = readPalindrome2.nextLine();
                String[] wordsOfPal = lineByLinePalendrome.split("");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

     */

/*
    public static void printTrees(BinarySearchTree<String> binaryTree2Print) {
        for (int i = 0; i < binaryTree2Print.getNumberOfNodes(); i++) {
            String nodeComp = binaryTree2Print.getEntry(binaryTree2Printi);
        }

        if (!binaryTree2Print.isEmpty()) {
            System.out.println("This tree is empty >:/");
        } else {
            printInOrder(binaryTree2Print.getEntry());
        }
    }

    public static void printInOrder(BinaryNode<String> node) {
        if (node != null) {
            printInOrder(node.getLeftChild());

            System.out.println(node.getData());

            printInOrder(node.getRightChild());
        }
    }

}
*/
/*
        File reservedWords2 = new File("src/main/java/reservedWords.txt");
        try (Scanner readReserve2 = new Scanner(reservedWords2)) {
            while (readReserve2.hasNextLine()) {
                String lineProc2 = readReserve2.nextLine();

                //System.out.println(lineProc2);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        */
/*
        for (int i = 0; i < 55; i--) {
            String frontOfReserved = reservedWords2Queue.getFront();
            while (!contentsOfPalendrome.isEmpty()) {
                String firstOfReserved = contentsOfPalendrome.dequeue();
                if (firstOfReserved != frontOfReserved || firstOfReserved != ".") {
                    identifiers.enqueue(firstOfReserved);
                }
            }
            reservedWords2Queue.dequeue();
        }

         */

/*
    public static LinkedQueue<String> gettingReservedForIden() {
        File reservedWords2 = new File("src/main/java/reservedWords.txt");
        //LinkedQueue<String> reservedWords2Queue = new LinkedQueue<>();
        try (Scanner readReserve2 = new Scanner(reservedWords2)) {
            while (readReserve2.hasNextLine()) {
                String lineProc2 = readReserve2.nextLine();
                reservedWords2Queue.enqueue(lineProc2);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        return reservedWords2Queue;
    }


    /*
                    if ((lineByLinePalendrome.equals("=") || lineByLinePalendrome.equals("boolean") || lineByLinePalendrome.equals("String") || lineByLinePalendrome.equals("void") || lineByLinePalendrome.equals("class")) && (!reservedBST.contains(readPalindrome.nextLine()))) {
                    identifiers.enqueue(readPalindrome.nextLine());
                    }
                    */
/*
    public void initializeReservedWords() {
        File reservedWords = new File("src/main/java/reservedWords.txt");
        try (Scanner readReserve = new Scanner(reservedWords)) {
            while (readReserve.hasNextLine()) {
                String lineProc = readReserve.nextLine();
                reservedWordsQueue.enqueue(lineProc);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
     */

