import java.io.*;
import java.util.Scanner;
//==============================================================================================
/**
 * Class Name: AhamedShoumikA3Q1
 *
 * DESCRIPTION: gets input from the user on while file to execute on tries to
 * solve the maze using stacks first displayes the result resets the maze and
 * tries it again with queue displays the result COMP2140 Section D01
 * Assighnment Assightnment #3, Question #1
 * 
 * @author Rubait Ul Ahamed, 007876180
 * @version (25th June 2020)
 */
public class AhamedRubaitA3Q1 {
    
    private static final int STACK = 1;
    private static final int QUEUE = 2;

    public static void main(String[] args) {
        String path = getInput(System.in); //gettin path location from user
        Maze ratMaze = new Maze(path);  //creating new maze obj from the file
        System.out.println("The initial maze is:"); 
        displayMaze(ratMaze); 
        solveMaze_Stack(ratMaze);  //solves maze using stackADT(using array)
        ratMaze.reset();  //resets the maze
        solveMaze_Queue(ratMaze);  //solves maze using QueueADT(using LinkesList)
    }

    /**
     * gets input from the user on which file to process file must be present in the
     * same folder as the .java file
     * 
     * @param in inputstream to get data from
     * @return (String) name of the file to be worked on
     */
    private static String getInput(InputStream in) {
        System.out.println("Please enter the input file name (.txt only):");
        String toReturn = "";
        Scanner scan = new Scanner(in);
        toReturn = scan.next();
        scan.close();
        System.out.println("Processing " + toReturn + "...");
        return toReturn;
    }

    /**
     * prints out the maze using the maze class's tostring function
     * 
     * @param toPrint maze object to print
     */
    private static void displayMaze(Maze toPrint) {
        String maze = toPrint.toString();
        System.out.println(maze);

    }

    /**
     * solves the maze using stacks
     * calls the internal method in the maze class to solve
     * displays appropiate msg after solving or lack there of
     * @param ratMaze maze to solve
     * @see solve in Maze class
     * @see solvedToString
     */
    private static void solveMaze_Stack(Maze ratMaze) {
        if (ratMaze.solve(STACK)) {
            System.out.println("The path found using a stack is:");
            System.out.println(ratMaze.solvedToString());
            String path = "path from start to finish: ";
            int[][] pathArray = ratMaze.getPath();
            for (int i = 0; i < pathArray.length; i++) {
                path += pathArray[i].toString() + " ";
            }

        } else {
            System.out.println("No path could be found.");

        }
        
    }

}

// ==============================================================================================
/**
 * Class Name: Maze
 *
 * DESCRIPTION: **** 
 * COMP2140 Section D01 Assighnment Assightnment #3, Question
 * #1
 * 
 * @author Rubait Ul Ahamed, 007876180
 * @version (25th June 2020)
 */
class Maze {

    private static final boolean DEBUG = true;

    private Position[][] arr2D;
    private int row, col;
    private int[][] pathToFredom;

    /**
     * Constructor:
     * loads maze and prints out error msg if path is incorrect
     * @param path location to the maze file(.txt only) given by the user
     * @see laodMaze
     */
    Maze(String path) {
        try {
            loadMaze(useTemp(path));

        } catch (Exception e) {
            System.out.println("File not found");
            e.printStackTrace();

        }
    }
   

    /**
     * usesa a String[][] to load eash tyle into a position[][]
     * 
     * @param temp the string 2d array to use as reference
     * @see constructor
     */
    private void loadMaze(String[][] temp) {
        for (int row = 0; row < temp.length; row++) {
            for (int col = 0; col < temp.length; col++) {
                arr2D[row][col] = new Position(row, col, temp[row][col]);
            }
        }
    }

    /**
     * loads the maze from the input file into the 2D array of Strings
     * 
     * @param path to location of the maze file (.txt only)
     * @return (String) the 2D Strig array
     * @throws FileNotFoundException if maze file mentioned by user not found
     * @see constructor
     */
    private String[][] useTemp(String path) throws FileNotFoundException {
        String[][] temp;
        File mazeFile = new File(path);
        Scanner scan;
        scan = new Scanner(mazeFile);
        this.row = scan.nextInt();
        this.col = scan.nextInt();
        temp = new String[row][col];
        scan.nextLine();
        int counter = 0;
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            temp[counter++] = line.split("");
        }
        scan.close();
        return temp;
    }


    /**
     * if param value is 1, solves the maze using stacks
     * if param value is 2, solves the maze using queues
     * @param methodToUse int value to determine which ADT to use to solve
     * @return true is possible to solve, flase if not
     */
    public boolean solve(int methodToUse) {
        if (methodToUse == 1)
            return solveUsingStacks();
        else if (methodToUse == 2)
            return solveUsingQueue();
        else
            if(DEBUG)
                System.out.println("Error choosing ADT to solve with.");
        return false;
    }


    private boolean solveUsingStacks() {
        
    }

    @Override
    public String toString () {
        String toReturn = "";
        for (int row = 0; row < arr2D.length; row++) {
            for (int col = 0; col < arr2D[row].length; col++) {
                toReturn += arr2D[row][col];
            }
            toReturn += "\n";
        }

        return toReturn;
    }
    
    
}

// ==============================================================================================
/**
 * Class Name: position
 *
 * DESCRIPTION: **** 
 * COMP2140 Section D01 Assighnment Assightnment #3, Question #1
 * 
 * @author Rubait Ul Ahamed, 007876180
 * @version (25th June 2020)
 */
class Position {

    private static final boolean DEBUG = true;

    private int x;
    private int y;
    private Tyle tile = null;
    private boolean visited;

    /**
     * constructor:
     * creates a maze tyle and sets it type
     * sets visited to false initially
     * @param x x coordinate in the array
     * @param y y coordinate in the array
     * @param type String to represent the type od tyle
     */
    Position (int x, int y, String type){
        this.x = x;
        this.y = y;
        if(type == Tyle.walkable.value) {
            tile = Tyle.walkable;
        } else if (type == Tyle.wall.value) {
            tile = Tyle.wall;
        } else if (type == Tyle.start.value) {
            tile = Tyle.start;
        } else if (type == Tyle.finish.value) {
            tile = Tyle.finish;
        } else {
            if(DEBUG)
                System.out.println("Map data corrupted");
        }
        visited = false;
    }

    
}

// ==============================================================================================
/**
 * Enum Name: Tyle
 *
 * DESCRIPTION: **** 
 * COMP2140 Section D01 Assighnment Assightnment #3, Question
 * #1
 * 
 * @author Rubait Ul Ahamed, 007876180
 * @version (25th June 2020)
 */
enum Tyle {
    walkable("."),
    wall("#"),
    start("S"),
    finish("F");

    String value;
    
    /**
     * constructor:
     * assighns the parameter as the valure of the obj
     * @param value the actual string value of the tyle
     */
    Tyle(String value) {
        this.value = value;
    }
}


// ==============================================================================================
/**
 * Class Name: StackADT
 *
 * DESCRIPTION: Stack abstract data structure using array
 * COMP2140 Section D01 Assighnment Assightnment #3, Question
 * #1
 * 
 * @author Rubait Ul Ahamed, 007876180
 * @version (25th June 2020)
 */
class StackADT {

    private static final int MAX_SIZE = 999999;
    private static final boolean DEBUG = true;
    private int top;
    private int[][] stackArr;

    /**
     * constructor:
     * creats an empty stack list
     */
    StackADT () {
        stackArr = new int[MAX_SIZE][2];
        top = -1;  //empty stack
    }

    /**
     * adds item at the bottom of the stack
     * @param x x coordinate of the maze
     * @param y y coordinate of the maze
     */
    public void push (int x, int y) {
        int[] coordinate = new int[2];
        coordinate[0] = x;
        coordinate[1] = y;
        if (top+1 < MAX_SIZE) {
            stackArr[++top] = coordinate;
        } else {
            if (DEBUG)
                System.out.println("Cant push. No space left in stack");
        }
    }


    /**
     * removes item from the bottom of the stack
     * @param x x coordinate of the maze
     * @param y y coordinate of the maze
     */
    public int[] pop (int x, int y) {
        int[] coordinate = new int[2];
        coordinate[0] = x;
        coordinate[1] = y;
        if (top+1 >= 0) {
            return stackArr[top--];
        } else {
            if (DEBUG)
                System.out.println("Can't pop. No item left in stack");
            return null;

        }
    }

    /**
     * return the next item to be removed without actally removing it
     * @param x x coordinate of the maze
     * @param y y coordinate of the maze
     */
    public int[] peak (int x, int y) {
        return stackArr[top];
    }

    @Override
    public String toString () {
        String toReturn = "[ ";
        for (int i = 0; i < stackArr.length; i++) {
            toReturn += "(" + stackArr[i][0] + ", " + stackArr[i][1] + ") ";
        }
        return toReturn;
    }
}


// ==============================================================================================
/**
 * Class Name: QueueADT
 *
 * DESCRIPTION: Queue abstract data structure using linkedlist 
 * COMP2140 Section D01 Assighnment Assightnment #3, Question
 * #1
 * 
 * @author Rubait Ul Ahamed, 007876180
 * @version (25th June 2020)
 */
class QueueADT {
    private LinkedList list;

    /**
     * constructor:
     * creates an empty queue
     */
    QueueADT () {
        list = new LinkedList();
    }

    /**
     * adds the coordinate at the bottom of the queue
     * @param x x coordinate in the maze arr
     * @param y y coordinate in the maze arr
     */
    public void enqueue(int x, int y) {
        list.addlast(x, y);
    }

    /**
     * removes an item from the front of the list
     * @return (int[]) the item that was removed
     */
    public int[] dequeue() {
        return list.removeFirst();
    }

    /**
     * return the item that is on the top, without removing it
     * @return (int[]) the first  coordinate
     */
    public int[] peak () {
        return list.getHead().getPosition();
    }

    @Override
    public String toString () {
        String toReturn = "< ";
        Node temp = list.getHead();
        while (temp != null) {
            toReturn += temp.toString() + " ";
        }
        toReturn += " <";
        return toReturn;
    }

}


//==============================================================================================
/**
 * Class Name: LinkedList
 *
 * DESCRIPTION: Stack abstract data structure using linkedlist 
 * COMP2140 Section D01 Assighnment Assightnment #3, Question
 * #1
 * 
 * @author Rubait Ul Ahamed, 007876180
 * @version (25th June 2020)
 */
class LinkedList {

    private Node head;
    private Node tail;
    private int currsize;

    /**
     * constructor:
     * creates an empty linek list
     */
    LinkedList () {
        head =null;
        tail = null;
        currsize = 0;
    }

    /**
     * adds an item to the front of the linked list
     * @param x x coordinate of the maze
     * @param y y coordinate of the maze
     */
    public void addFirst (int x, int y) {
        Node toAdd = new Node(x, y);
        if (isEmpty()) {
            head = tail = toAdd;
            currsize++;
        } else {
            toAdd.setNext(head);
            head.setPriv(toAdd);
            head = toAdd;
            currsize++;
        }
    }

    /**
     * adds an item to the end of the linked list
     * @param x x coordinate of the maze
     * @param y y coordinate of the maze
     */
    public void addlast (int x, int y) {
        Node toAdd = new Node(x, y);
        if (isEmpty()) {
            addFirst(x, y);
        } else {
            tail.setNext(toAdd);
            toAdd.setPriv(tail);
            tail = toAdd;
            currsize++;
        }
    }

    /**
     * return true is head is null. Return false otherwise
     * @return (boolean)
     */
    private boolean isEmpty () {
        return head == null;
    }

    /**
     * removes the first item in the linked list and decrements the currentSize
     * @return (int[]) coordinate that was just removed
     */
    public int[] removeFirst() {
        Node toReturn = head;
        if (currsize == 1) {
            this.head = null;
            this.tail = null;
            currsize--;
        } else if ((currsize > 1) && !isEmpty()) {
            Node temp = head.getNext();
            this.head = temp;
            currsize--;
        }
        return toReturn.getPosition();
    }

    /**
     * removes the last item in the linked list
     * and decrements the currentSize
     */
    public void removeLast () {
        if (!isEmpty()) {
            if (currsize == 1)
                removeFirst();
            else {
                Node newLast = tail.getPriv();
                newLast.setNext(null);
                tail = newLast;
                currsize--;
            }
        }
    }


    /** GETTERS & SETTERS */

    public Node getHead() {
        return this.head;
    }
}



//==============================================================================================
/**
 * Class Name: Node
 *
 * DESCRIPTION: node class for linked list
 * COMP2140 Section D01 Assighnment Assightnment #3, Question
 * #1
 * 
 * @author Rubait Ul Ahamed, 007876180
 * @version (25th June 2020)
 */
class Node {
    
    //data
    private int[] location;

    //pointers
    private Node next;
    private Node priv;

    /**
     * constructor:
     * creates a node to hold coordinate data
     * @param x x coordinate
     * @param y y coordinate
     * @see LinkedList
     */
    Node(int x, int y){
        location = new int[2];
        location[0] = x;
        location[1] = y;
        next = null;
        priv = null;
    }


    @Override
    public String toString () {
        return "[" + location[0] + ", " + location[1] + "]";
    }

    /** GETTERS & SETTERS */
    int[] getPosition () {
        return location;
    }

    Node getNext () {
        return this.next;
    }

    Node getPriv () {
        return this.priv;
    }

    void setNext (Node next) {
        this.next = next;
    }

    void setPriv (Node priv) {
        this.priv = priv;
    }
}
