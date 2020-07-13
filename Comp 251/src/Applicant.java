public class Applicant implements Comparable<Applicant>{
    private String name;
    private int loanAmnt;
    private int yrsEducation;
    private int yrsExperience;
    private int score;

    public int compareTo(Applicant o) {
        // TODO Auto-generated method stub
        int  i = 1;
        if(this.score > o.score){
            i = 1;
        }
        else if(this.score == o.score){
            i = 0;
        }
        else if(this.score <o.score){
            i = -1;
        }

        return i;
    }


    public Applicant(Applicant x){
        this.name = x.name;
        this.loanAmnt = x.loanAmnt;
        this.yrsEducation = x.yrsEducation;
        this.yrsExperience = x.yrsExperience;
        this.score = x.score;
    }
    public Applicant(String name, int loanAmnt, int yrsEducation, int yrsExperience, int score){
        this.name = name;
        this.loanAmnt = loanAmnt;
        this.yrsEducation = yrsEducation;
        this.yrsExperience = yrsExperience;
        this.score = score;
    }

    public int getLoanAmnt(){
        return loanAmnt;
    }

    public String getName(){
        return name;
    }


    public int getScore(){
        return  score;
    }
}