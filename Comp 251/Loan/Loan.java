import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import jdk.jfr.events.FileWriteEvent;
import oracle.net.aso.a;
import oracle.net.aso.i;

public class Loan {
	private PriorityQueue<Applicant> activeList;
	private ArrayList<Applicant> approvedList;
	private ArrayList<Applicant> rejectedList;
	private int budget;

	public Loan() {
		activeList = new PriorityQueue<Applicant>();
		approvedList = new ArrayList<Applicant>();
		rejectedList = new ArrayList<Applicant>();
		budget = 0;
	}

	public void run() throws IOException {
		/*
		 * print the options for the Loan app and execute them
		 * 
		 * Possible operations: Load applications Set the budget Make a decision Print
		 * Update an application (there is a method find() in PriorityQueue that you can
		 * use to find the applicant you would like to update)
		 */
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter your operation to perform:(l, s, d, p, u) ");
		String input = sc.nextLine();
		
		while(!input.equals(" ")){
			
			if(input.equals("l")){
				load();
			}
			else if(input.equals("s")){
				System.out.println("Please set the budget: ");
				input = sc.nextLine();
				setBudget(Integer.parseInt(input));
			}
			else if(input.equals("d")){
				decide();
			}
			else if(input.equals("p")){
				print();
			}
			else if(input.equals("u")){
				update();
			}
			System.out.println("Enter your operation to perform:(l, s, d, p, u) ");
			input = sc.nextLine();
		}
		
		sc.close();
	}

	public void load() throws FileNotFoundException {
		Scanner sc = new Scanner(new FileReader("application.txt"));

		while (sc.hasNextLine()) {
			String input = sc.nextLine();
			String[] arr = input.split("\t");

			
			Applicant x = new Applicant(arr);

			if (x.getSumYrs() >= 10) {
				activeList.push(x);
			} else {
				rejectedList.add(x);
			}

		}
		sc.close();
	}

	public void setBudget(int budget) {
		this.budget += budget;
	}

	public void decide() {
		//activeList.buildHeap();
		ArrayList<Applicant> pushBack = new ArrayList<>();
		while(activeList.top()!=null){
			Applicant x = new Applicant(activeList.pop());

			if(x.getloanAmnt() <= budget){
				setBudget(-x.getloanAmnt());
				approvedList.add(x);
			}
			else{
				pushBack.add(x);
			}
		}

		// I've done this because I wasn't sure where to place instances like larry
		// page;
		// his score is high enough but we dont have enough budget for him. I will just
		// store him and others like him in a temporary array
		// I will then push them back into the active list once those can be approved
		// have been approved.
		while(!pushBack.isEmpty()){
			activeList.push(pushBack.remove(pushBack.size()-1));
		}
	}

	public void print() throws IOException {
		File file = new File("active.txt");
		FileWriter fr = new FileWriter(file);
		String rep = "";
		fr.write(activeList.toString());
		fr.close();

		file = new File("approved.txt");
		for(Applicant x : approvedList){
			rep += x +"\n";
		}
		fr = new FileWriter(file);

		fr.write(rep);
		fr.close();

		file = new File("rejected.txt");
		rep = "";
		for(Applicant x : rejectedList){
			rep += x + "\n";
		}
		fr = new FileWriter(file);

		fr.write(rep);
		fr.close();
	}

	public void update() throws FileNotFoundException {
		Scanner sc = new Scanner(new FileReader("application.txt"));
		ArrayList<Applicant> toRemove  = new ArrayList<>();
		while(sc.hasNextLine()){
			String input = sc.nextLine();
			String [] arr = input.split("\t");

			Applicant x = new Applicant(arr);
			
			
			for(Applicant myApp: rejectedList){
				if(x.equals(myApp)){
					if(x.getSumYrs() >= 10){
						toRemove.add(myApp);
						activeList.push(x);
					}
				}
			}
			
		}
		sc.close();
		rejectedList.removeAll(toRemove);
	}
	
}
