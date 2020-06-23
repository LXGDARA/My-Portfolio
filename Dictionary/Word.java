import java.util.Scanner;

public class Word implements Comparable<Word> {
	private String word;
	private String meaning;
	//add other fields
	
	public int compareTo(Word other){
		return word.compareTo(other.word);
	}
	
	// add other methods you need, like constructor,..

	public Word(String x){
		this.word = x;
	}

	public Word(String x, String y){
		this.word = x;

		if(y == null){
			System.out.println("What is the meaning of this word: ");
			Scanner sc = new Scanner(System.in);
			String input = sc.nextLine();
			this.meaning = input;
		}
		else{
			this.meaning = y;
		}
		
	}

	public String toString(){
		return word + " " + meaning;
	}

	public String getWord(){
		return word;
	}

	public String getMeaning(){
		return meaning;
	}
}
