import java.util.Scanner;

public class Word implements Comparable<Word> {
	private String word;
	private String meaning;
	//add other fields
	
	public int compareTo(Word other){
		return word.compareTo(other.word);
	}
	
	// add other methods you need, like constructor,..


	public void setWord(String word){
		this.word = word;
	}

	public void setMeaning(String meaning){
		this.meaning = meaning;
	}

	public Word(String word , String meaning){
		this.word = word;
		if(meaning  == null){
			Scanner myScan = new Scanner(System.in);
			System.out.println("Please enter the meaning of  this word ");
			String myMeaning = myScan.nextLine();
			this.meaning = myMeaning;
		}
		else{
			this.meaning = meaning;
		}
		
	}
	public String getWord(){
		return word;
	}

	public String getMeaning(){
		return meaning;
	}
}
