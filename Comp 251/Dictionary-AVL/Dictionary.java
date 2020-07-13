import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.management.InstanceNotFoundException;

public class Dictionary {
	private static AVLTree<Word> tree;

	public Dictionary() {
		tree = new AVLTree<Word>();
	}
	
	public void run(String myString) throws InstanceNotFoundException, FileNotFoundException {
			switch (myString) {
				case "f":
					System.out.println("Enter a word: ");
					Scanner myChoice1 = new Scanner(System.in);
					String myString1 = myChoice1.nextLine();
					find(myString1);
					break;
				case "i":
					System.out.println("Enter a word: ");
					Scanner myChoice2 = new Scanner(System.in);
					String myString2 = myChoice2.nextLine();
					insert(myString2);
					break;
				case "l":
					System.out.println("Enter file name: ");
					Scanner myChoice3 = new Scanner(System.in);
					String myString3 = myChoice3.nextLine();
					load(myString3);
					break;
				case "p":
					System.out.println("Dictionary: ");
					print();
					break;
				case "r":
					System.out.println("Enter a word: ");
					Scanner myChoice4 = new Scanner(System.in);
					String myString4 = myChoice4.nextLine();
					remove(myString4);
					break;
				case "s":
					System.out.println("Enter file address: ");
					Scanner myChoice5 = new Scanner(System.in);
					String myString5 = myChoice5.nextLine();
					save(myString5);
					break;
				default:
				System.exit(-1);
			}
		}
		
		/*

		/*
		 * print the options for the dictionary app and execute them
		 * 
		 * Possible operations: f) find the meaning of a word. i) insert and entry. l)
		 * load the dictionary from an input file. p) print all the entries. r) remove
		 * an entry. s) save the contents of the dictionary in an output file. x) exit
		 * Please choose an option (f, i, l, p, r, s, or x):
		 */
	

	// add other methods
	public void find(String myString) throws InstanceNotFoundException, FileNotFoundException {
		tree.find(myString);
	}

	public void insert(String myString) throws InstanceNotFoundException, FileNotFoundException {
		tree.insert(myString);
	}

	public void load(String myString) throws InstanceNotFoundException, FileNotFoundException {
		tree.load(myString);
	}

	 public void print() throws InstanceNotFoundException, FileNotFoundException {
		tree.print(tree.getRoot());
	}

	public void remove(String myString) throws InstanceNotFoundException, FileNotFoundException {
		tree.remove(myString);
	}

	public void save(String myString) throws InstanceNotFoundException, FileNotFoundException {
		tree.save(myString);
	}

	public void exit(){
		System.exit(-1);
	}
}
