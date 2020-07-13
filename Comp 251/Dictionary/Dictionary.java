import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.management.InstanceNotFoundException;

public class Dictionary {
	private AVLTree<Word> tree;
	
	public static void main(String[] args) throws InstanceNotFoundException, IOException {
        Dictionary dict = new Dictionary();
        dict.run();
    }

	public Dictionary() {
		tree = new AVLTree<Word>();
	}

	public void run() throws FileNotFoundException, InstanceNotFoundException {

		System.out.println("Please choose an option (f, i, l, p, r, s, or x):");

		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		while (!input.equals("x")) {
			switch (input) {
				case "f":
					find();
					break;
				case "i":
					insert();
					break;
				case "l":
					load();
					break;
				case "p":
					print();
					break;
				case "r":
					remove();
					break;
				case "s":
					save();
					break;
				default:
					break;
			}
			System.out.println("Please choose an option (f, i, l, p, r, s, or x):");
			input = sc.nextLine();
		}
		/*
		 * print the options for the dictionary app and execute them
		 * 
		 * Possible operations: f) find the meaning of a word. i) insert and entry. l)
		 * load the dictionary from an input file. p) print all the entries. r) remove
		 * an entry. s) save the contents of the dictionary in an output file. x) exit
		 * Please choose an option (f, i, l, p, r, s, or x):
		 */
	}

	// add other methods
	public void find() {
		System.out.println("Enter a word: ");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();

		String[] arr = input.split(" ");

		Word myWord = new Word(arr[0]);

		System.out.println(tree.findWord(myWord));

	}

	public void insert() {
		System.out.println("Enter a word: ");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		String[] arr = input.split(" ");
		Word myWord = new Word(arr[0], null);

		tree.insert(myWord);
	}

	public void load() throws FileNotFoundException {
		System.out.println("Enter the address of the file : ");

		Scanner myScan = new Scanner(System.in);
		String input = myScan.nextLine();

		Scanner sc = new Scanner(new FileReader(input));

		while (sc.hasNextLine()) {
			String str = sc.nextLine();
			String[] arr = str.split(" ");
			Word myWord = new Word(arr[0], arr[1]);
			tree.insert(myWord);
		}
	}

	public void print() {
		System.out.println(tree.preorder());
	}

	public void remove() throws InstanceNotFoundException {
		System.out.println("Enter the word you would like to delete: ");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		Word myWord = new Word(input);
		tree.remove(tree.findWord(myWord));
	}
	
	public void save(){
		System.out.println("enter the name of the file: ");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		File file = new File(input);
		FileWriter fr = null;

		String output = tree.preorder();
		try {
			fr = new FileWriter(file);
			fr.write(output);
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
}
