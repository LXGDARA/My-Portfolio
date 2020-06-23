import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.management.InstanceNotFoundException;

public class AVLTree<AnyType extends Comparable<? super AnyType>> {
    private AVLNode root;

    public AVLTree() {
        root = null;
    }

    /**
     * Returns the height (max root-to-leaf depth) of the tree. Uses a recursive
     * helper that recurs down to find the height (max depth).
     */
    public int height() {
        return AVLNode.height(root);
    }

    protected int height(AVLNode t) {
        return AVLNode.height(t);
    }

    public void insert(String myString){
        insert(myString, null);
    }

    public void insert(String x , String meaning) {
        root = insert(x, meaning, root);
    }

    private AVLNode insert(String x, String meaning, AVLNode t) {

        if (t == null)
            t = new AVLNode(x , meaning);
        else if (x.compareTo(t.getWord()) < 0) {
            t.left = insert(x, meaning, t.left);
            if (height(t.left) - height(t.right) == 2)
                if (x.compareTo(t.left.getWord()) < 0)
                    t = rotateWithLeftChild(t);
                else
                    t = doubleWithLeftChild(t);
        } else if (x.compareTo(t.getWord()) > 0) {
            t.right = insert(x, meaning, t.right);
            if (height(t.right) - height(t.left) == 2)
                if (x.compareTo(t.right.getWord()) > 0)
                    t = rotateWithRightChild(t);
                else
                    t = doubleWithRightChild(t);
        } else
            throw new IllegalArgumentException("The word you entered already exists in the dictionary"); // Duplicate;
                                                                                                         // do nothing
                                                                                                         // or you can
                                                                                                         // throw an
                                                                                                         // exception

        t.height = Math.max(height(t.left), height(t.right)) + 1;
        return t;

    }

    public void remove(String x) throws InstanceNotFoundException {
        root = remove(x, root);
    }

    private AVLNode remove(String x, AVLNode t) throws InstanceNotFoundException {

        if (t == null)
            throw new InstanceNotFoundException(x.toString() + "not found");
        if (x.compareTo(t.element) < 0)
            t.left = remove(x, t.left);
            if (height(t.left) - height(t.right) == 2)
                if (x.compareTo(t.left.element) < 0)
                    t = rotateWithLeftChild(t);
                else
                    t = doubleWithLeftChild(t);
        else if (x.compareTo(t.element) > 0)
            t.right = remove(x, t.right);
        //t.right = insert(x, t.right);
            if (height(t.right) - height(t.left) == 2)
            if (x.compareTo(t.right.element) > 0)
                t = rotateWithRightChild(t);
                else
                t = doubleWithRightChild(t);
        else if (t.left != null && t.right != null) // Two children
        {
            t.setWord(findMin(t.right).getWord());
            t.right = removeMin(t.right);
        } else
            t = (t.left != null) ? t.left : t.right;
        return t;
    }

    public boolean find(String myString) throws InstanceNotFoundException {
        return find(myString, (AVLNode) root);
    }

    public boolean find(String myString, AVLNode t) throws InstanceNotFoundException {
        boolean myBool = false;
        if (t == null) {
            throw new InstanceNotFoundException(myString);
        }
        if (myString.compareTo(t.getWord()) < 0) {
            myBool = find(myString, t.left);
        }

        else if (myString.compareTo(t.getWord()) > 0) {
            myBool = find(myString, t.right);
        } else { // word was found
            System.out.println("Word: " + myString + " Meaning: " + t.getMeaning());
            myBool = true;
        }
        return myBool;
    }

    protected AVLNode removeMin(AVLNode t) throws InstanceNotFoundException {
        if (t == null)
            throw new InstanceNotFoundException();
        else if (t.left != null) {
            t.left = removeMin(t.left);
            return t;
        } else
            return t.right;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * 
     * @param t the node that roots the tree.
     * @return node containing the smallest item.
     */
    protected AVLNode findMin(AVLNode t) {
        if (t != null)
            while (t.left != null)
                t = t.left;

        return t;
    }

    private AVLNode rotateWithLeftChild(AVLNode k2) {
        AVLNode k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), k2.height) + 1;
        return k1;
    }

    /**
     * Rotate binary tree node with right child. For AVL trees, this is a single
     * rotation for case 4. Update heights, then return new root.
     */
    private AVLNode rotateWithRightChild(AVLNode k1) {
        AVLNode k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
        k2.height = Math.max(height(k2.right), k1.height) + 1;
        return k2;
    }

    /**
     * Double rotate binary tree node: first left child with its right child; then
     * node k3 with new left child. For AVL trees, this is a double rotation for
     * case 2. Update heights and return new root.
     * 
     * @param k3 current AVLNode we want to double with left child.
     * @return new root of AVL Tree
     */
    private AVLNode doubleWithLeftChild(AVLNode k3) {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    /**
     * Double rotate binary tree node: first right child with its left child; then
     * node k1 with new right child. For AVL trees, this is a double rotation for
     * case 3. Update heights, then return new root.
     */
    private AVLNode doubleWithRightChild(AVLNode k1) {
        k1.right = rotateWithLeftChild(k1.right);
        return rotateWithRightChild(k1);
    }

    public void load(String myString) throws FileNotFoundException {
          Scanner fin =  new Scanner(new FileReader(myString));

          while(fin.hasNext()){
            String myLine = fin.nextLine();
            String [] strArr = myLine.split(" ");
            insert(strArr[0], strArr[1]);
        }
          fin.close();
      }

    public void print(AVLNode t){
        printLeftSide(t);
        System.out.println(t.getWord() + " " + t.getMeaning());
        printRightSide(t);
    }


    /**
     * Internal method to print the smallest item in a subtree.
     * @param t the node that roots the tree.
     * @return node containing the smallest item.
     */
    public void printLeftSide( AVLNode t )
    {
        if( t != null )
           if( t.left != null )
                printLeftSide(t.left);

        System.out.println(t.getWord() + " " + t.getMeaning());
            printRightSide(t);
    }

    /**
     * Internal method to print the largest item in a subtree.
     * @param t the node that roots the tree.
     * @return node containing the largest item.
     */
    public void printRightSide( AVLNode t )
    {
        if( t != null )
            if( t.right != null )
                print(t.left);

        System.out.println(t.getWord() + " " + t.getMeaning());
    }
    
    public void saveLeft(AVLNode t  , String myString){
        String result;
        if( t != null )
           if( t.left != null ){
            saveLeft(t.left, myString);
           }
           result = t.getWord() + " " + t.getMeaning();
                File file = new File(myString);
                FileWriter fr = null;
                try {
                    fr = new FileWriter(file);
                    fr.write(result);
                } catch (IOException e) {
                    e.printStackTrace();
                }finally{
                    //close resources
                    try {
                        fr.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            saveRight(t , myString);
    }
    public void saveRight(AVLNode t , String myString){
        String result;
        if( t != null )
            if( t.right != null ){
               saveRight(t.left, myString);
            }
               result = t.getWord() + " " + t.getMeaning();
                File file = new File(myString);
                FileWriter fr = null;
                try {
                    fr = new FileWriter(file);
                    fr.write(result);
                } catch (IOException e) {
                    e.printStackTrace();
                }finally{
                    //close resources
                    try {
                        fr.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
    }
    public void save(String myString){
        saveLeft(root, myString);
       // saveRight(root, myString);
    }

    public AVLNode getRoot(){
        return root;
    }

    private String preorder(AVLNode<AnyType> t) {
    	String output = "";
    	if (t!=null){
            output += t.element.toString();
    		output += "\n"+preorder(t.left);
    		output += "\n"+preorder(t.right);
    	}
    	return output;
    }
    public String preorder(){
    	return preorder(root);
    }

      public static void main( String [ ] args )
      {
          /*
          AVLTree<Integer> t = new AVLTree<Integer>( );
          final int NUMS = 4000;
          final int GAP  =   37;

          System.out.println( "Checking... (no more output means success)" );

          for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
              t.insert( i );
              */
      }
}





















