import java.io.FileReader;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;



public class Loan {
    private int budget = 0;
    ArrayList<Applicant> approved = new ArrayList<>();
    ArrayList<Applicant> rejected = new ArrayList<>();
    PriorityQueue<Applicant> active = new PriorityQueue<Applicant>( );

    public void run() throws IOException {
        
        load();
        setBudget(300000);
        decide();
        print();
        update();
        decide();
        print();
    }

    public void load() throws IOException {
        
		FileWriter fr = null;
        Scanner sc = new Scanner(new FileReader("application.txt"));

        File file = new File("rejected.txt");
            File file2 = new File("active.txt");

            String rej = "";
            String act = "";

        while(sc.hasNextLine()){

            String input = sc.nextLine();
            String [] arr = input.split("\t");
            
            

            if((Integer.parseInt(arr[2]) + Integer.parseInt(arr[3])) < 10){
                
                rej += input +"\n";
                

                int i = 4;
                int j = 1;
                int score = 0;
                while(i < arr.length){
                    score += ( Integer.parseInt(arr[i]) / j++);
                    i++;
                }
                Applicant myApp = new Applicant(arr[0], Integer.parseInt(arr[1]), Integer.parseInt(arr[2]), Integer.parseInt(arr[3]), score);
                rejected.add(myApp);
            }

            
            else{
                
                act += input + "\n";

                int i = 4;
                int j = 1;
                int score = 0;
                while(i < arr.length){
                    score += ( Integer.parseInt(arr[i]) / j++);
                    i++;
                }
                Applicant myApp = new Applicant(arr[0], Integer.parseInt(arr[1]), Integer.parseInt(arr[2]), Integer.parseInt(arr[3]), score);
                active.push(myApp);
            }
        }
        fr = new FileWriter(file);
        fr.write(rej);
        fr.close();

        fr = new FileWriter(file2);
        fr.write(act);
        fr.close();

        sc.close();
    }

    public void setBudget(int budget){
        this.budget += budget;
    }

    public void decide() throws IOException {
        System.out.println("trace");
            FileWriter fr = null;
            File file = new File("approved.txt");
            File file2 = new File("active.txt");

            String app  =  "";
            String rej  =  "";
            while(active.size() > 0){
                if(active.top().getLoanAmnt()<= budget){
                    setBudget(-active.top().getLoanAmnt());
                    Applicant  x = new Applicant(active.pop());
                    app += x.getName() + " " +x.getLoanAmnt()  + "\n";

                    approved.add(x);
                }
                
                else{
                    //pushed back to the active list?   
                    rej += active.top().getName() + " " + active.top().getLoanAmnt() + " " + active.top().getScore() + "\n";
                    active.pop();
                   }
            }
            fr = new FileWriter(file);
            fr.write(app);
            fr.close();

            fr = new FileWriter(file2);
            fr.write(rej);
            fr.close();
    }

    public void print() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("active.txt"));
        String  input;
        System.out.println("Active.txt: ");
        while(sc.hasNextLine()){
            input = sc.nextLine();
            System.out.println(input);
        }
        sc.close();

        System.out.println("\n"+"Approved.txt: ");
        sc = new Scanner(new FileReader("approved.txt"));
        while(sc.hasNextLine()){
            input = sc.nextLine();
            System.out.println(input);
        }

        sc.close();

        System.out.println("\n"+"rejected.txt: ");
        sc = new Scanner(new FileReader("rejected.txt"));
        while(sc.hasNextLine()){
            input = sc.nextLine();
            System.out.println(input);
        }
        sc.close();
    }

    public void update() throws IOException {

        FileWriter fr = null;
        Scanner sc = new Scanner(new FileReader("update.txt"));
        while(sc.hasNextLine()){
            String input = sc.nextLine();
            String  [] arr = input.split("\t");
            int sumYrs = Integer.parseInt(arr[2]+ Integer.parseInt(arr[2]));
            ArrayList<Applicant> toRemove = new ArrayList<>();
            if(sumYrs >= 10){
                for(Applicant myApp : rejected){
                    if(arr[0].equals(myApp.getName())){
                        toRemove.add(myApp);                        
                    }
                }
                rejected.removeAll(toRemove);
            }
            else{
                toRemove.clear();
                for(Applicant myApp: approved){
                    if(arr[0].equals(myApp.getName())){
                        toRemove.add(myApp);
                    }
                }
            }
            approved.removeAll(toRemove);
            
        }
            

            

            String rej = "";
            String app = "";
            
            while(sc.hasNextLine()){

                String input = sc.nextLine();
                String [] arr = input.split("\t");

                if((Integer.parseInt(arr[2]) + Integer.parseInt(arr[3])) < 10){
                    int i = 4;
                    int j = 1;
                    int score = 0;
                    while(i < arr.length){
                        score += ( Integer.parseInt(arr[i]) / j++);
                        i++;
                    }
                    Applicant myApp = new Applicant(arr[0], Integer.parseInt(arr[1]), Integer.parseInt(arr[2]), Integer.parseInt(arr[3]), score);
                    rej +=  myApp.getName() + " " + myApp.getLoanAmnt() + " " + myApp.getScore() + "\n";
                    
                }
                else{
                    int i = 4;
                    int j = 1;
                    int score = 0;
                    while(i < arr.length){
                        score += ( Integer.parseInt(arr[i]) / j++);
                        i++;
                    }
                    Applicant myApp = new Applicant(arr[0], Integer.parseInt(arr[1]), Integer.parseInt(arr[2]), Integer.parseInt(arr[3]), score);
                    app +=  myApp.getName() + " " + myApp.getLoanAmnt() + " " + myApp.getScore() + "\n";
                }
            }
            File file = new File("rejected.txt");
            fr = new FileWriter(file);
            fr.write(rej);
            fr.close();

             File file2 = new File("active.txt");
            fr = new FileWriter(file2);
            fr.write(app);
            fr.close();
            
        }
    

  
}