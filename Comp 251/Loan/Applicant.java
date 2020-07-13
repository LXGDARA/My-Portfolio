
public class Applicant implements Comparable<Applicant> {
	private String name;
	private int score;
	private int education;
	private int experience;
	private int loanAmnt;
	private int sumYrs;
	private int anProfit;
	// add the rest of fields (education, experience, annual profit)
	
	//Complete this constructor 
	// you should calculate this.score based on estimated annual profit

	public Applicant(Applicant x){
		this.name = x.name;
		this.score = x.score;
		this.education = x.education;
		this.experience = x.experience;
		this.loanAmnt = x.loanAmnt;
		this.sumYrs = x.sumYrs;

	}

	public Applicant(String [] arr) {
		//Complete this constructor 
		// you should calculate this.score based on estimated annual profit
		this.name = arr[0];
		this.loanAmnt = Integer.parseInt(arr[1]);
		this.education = Integer.parseInt(arr[2]);
		this.experience = Integer.parseInt(arr[3]);
		this.sumYrs = education + experience;
		
		int i = 4;
		int j = 1;

		while(i < arr.length){
			this.score += (Integer.parseInt(arr[i++]) / j++);
		}
	}
	
	
	public int compareTo(Applicant other){
		if (this.score > other.score)
			return 1;
		else if (this.score < other.score)
			return -1;
		else
			return 0;
		// you can just write it in one line:
		// return this.score - other.score
	}
	
	// this method will be used to find an applicant
	public boolean equals(Applicant other) {
		return this.name.equals(other.name);
	}
	
	public String toString() {
		//complete this method
		//it will be useful for print option in class Loan 
		return name + "\t" + loanAmnt + "\t" + score;
	}
	
	// add any other method you need
	public int getloanAmnt(){
		return loanAmnt;
	}
	public int getSumYrs(){
		return sumYrs;
	}

	public String getName(){
		return name;
	}
}
