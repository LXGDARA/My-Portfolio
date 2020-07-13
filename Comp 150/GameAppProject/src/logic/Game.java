package logic;

import javax.swing.SwingUtilities;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import graphics.*;
import gui.AnimatedGUI;
import gui.Panel2D;
import performers.*;
import story.Narrator;

/**
 * This is the class that will manage all parts of the game, bringing them together.
 * Its execution sets up the application window, begins other threads for animation,
 * and loops through the story of the game until the user either wins or loses.
 * 
 * @author Dr Russell Campbell
 *
 */
public class Game {
	
	// Only need one instance of each piece of the game, so they can all be static.
	
	// For the main application window for our program.
	private static AnimatedGUI window;
	// For the separate execution of our game logic.
	private static Thread gameThread;
	// For controlling the sharing of information between parts of our game.
	private static InputManager in = new InputManager();
	private static OutputManager out = new OutputManager();
	private static DataManager data = new DataManager();
	
	// For performers within our game that can do various interesting things.
	private static Player plyr;
	private static ArrayList<Monster> monsters;
	// How many monsters the player must defeat.
	private static final int N_MONSTERS = 5;
	public static boolean timeForBoss = false;
	public static boolean showMercy;
	/**
	 * This is where the game begins.
	 */
	public void play() {
		
		setupApplicationWindow();
		setupAnimations();
		
		GameThread gameThread = new GameThread();
		in.setGameThread(gameThread);
		window.getPanelIO().setGameThread(gameThread);
		gameThread.start(); // begins execution of gameLoop method
		
		// Take special note of the small size of each method.
		// Small methods are easier to understand and debug.
	}
	public void changeScene(){
		setupAnimations();
	}
	private void setupApplicationWindow() {
		
		// our main application
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					window = new AnimatedGUI();
				}
			});
		} catch (InterruptedException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		// Connect GUI to the rest of our program.
		out.setPanelIO(window.getPanelIO());
		out.setPanelStats(window.getPanelStats());
		in.setPanelIO(window.getPanelIO());
	}

	private static void setupAnimations() {
		// Now we have interfaces to let us collect objects that are NOT related.
		// This allows us to call the methods on each object in the collection described in Drawable.
		ArrayList<Drawable> actors = new ArrayList<Drawable>();
		switch (Narrator.getChapter()){
			case 2:
				actors.clear();
			actors.add(new CharSprite(2, 10, 1, 4, Panel2D.BLUE));
			actors.get(0).setParent(window.getPanel2D());


			actors.add(new CharSprite(2, 16, 1, 4, Panel2D.CYAN));
			actors.get(1).setParent(window.getPanel2D());
//----------------------------------------------------------------------------------------------------------
			actors.add(new CharSprite(30, 10, 1, 5, Panel2D.LIGHT_BLUE));
			actors.get(2).setParent(window.getPanel2D());

			actors.add(new CharSprite(30, 15, 1, 5, Panel2D.LIGHT_BLUE));
			actors.get(3).setParent(window.getPanel2D());

			actors.add(new CharSprite(30, 20, 1, 5, Panel2D.LIGHT_BLUE));
			actors.get(4).setParent(window.getPanel2D());

			actors.add(new CharSprite(30, 25, 1, 5, Panel2D.LIGHT_BLUE));
			actors.get(5).setParent(window.getPanel2D());

			actors.add(new CharSprite(30, 30, 1, 5, Panel2D.LIGHT_BLUE));
			actors.get(6).setParent(window.getPanel2D());
				window.getPanel2D().setActors(actors);
			case 4:
				actors.clear();
				actors.add(new CharSprite(2, 10, 1, 1, Panel2D.BLUE));
				actors.get(0).setParent(window.getPanel2D());

//----------------------------------------------------------------------------------------------------------
				actors.add(new CharSprite(30, 10, 1, 5, Panel2D.LIGHT_BLUE));
				actors.get(1).setParent(window.getPanel2D());

				actors.add(new CharSprite(30, 15, 1, 5, Panel2D.LIGHT_BLUE));
				actors.get(2).setParent(window.getPanel2D());

				actors.add(new CharSprite(30, 20, 1, 5, Panel2D.LIGHT_BLUE));
				actors.get(3).setParent(window.getPanel2D());

				actors.add(new CharSprite(30, 25, 1, 5, Panel2D.LIGHT_BLUE));
				actors.get(4).setParent(window.getPanel2D());

				actors.add(new CharSprite(30, 30, 1, 5, Panel2D.LIGHT_BLUE));
				actors.get(5).setParent(window.getPanel2D());
				window.getPanel2D().setActors(actors);
			case 6:
				actors.clear();
				actors.add(new CharSprite(2, 10, 1, 1, Panel2D.BLUE));
				actors.get(0).setParent(window.getPanel2D());
				actors.add(new Label(2, 10, 0, 1, 8));
				actors.get(1).setParent(window.getPanel2D());

				actors.add(new CharSprite(2, 16, 1, 2, Panel2D.CYAN));
				actors.get(2).setParent(window.getPanel2D());
				actors.add(new Label(2, 16, 0, 2, 8));
				actors.get(3).setParent(window.getPanel2D());


				actors.add(new CharSprite(2, 22, 1, 3, Panel2D.LIGHT_BLUE));
				actors.get(4).setParent(window.getPanel2D());
				actors.add(new Label(2, 22, 0, 3, 8));
				actors.get(5).setParent(window.getPanel2D());

				actors.add(new CharSprite(30, 10, 1, 4, Panel2D.LIGHT_BLUE));
				actors.get(6).setParent(window.getPanel2D());
				actors.add(new Label(30, 10, 0, 4, 8));
				actors.get(7).setParent(window.getPanel2D());
				window.getPanel2D().setActors(actors);
			case 8:
				actors.clear();
				actors.add(new CharSprite(2, 10, 1, 1, Panel2D.BLUE));
				actors.get(0).setParent(window.getPanel2D());
				actors.add(new Label(2, 10, 0, 1, 8));
				actors.get(1).setParent(window.getPanel2D());

				actors.add(new CharSprite(2, 16, 1, 2, Panel2D.CYAN));
				actors.get(2).setParent(window.getPanel2D());
				actors.add(new Label(2, 16, 0, 2, 8));
				actors.get(3).setParent(window.getPanel2D());


				actors.add(new CharSprite(2, 22, 1, 3, Panel2D.LIGHT_BLUE));
				actors.get(4).setParent(window.getPanel2D());
				actors.add(new Label(2, 22, 0, 3, 8));
				actors.get(5).setParent(window.getPanel2D());

				actors.add(new CharSprite(30, 10, 1, 4, Panel2D.LIGHT_BLUE));
				actors.get(6).setParent(window.getPanel2D());
				actors.add(new Label(30, 10, 0, 4, 8));
				actors.get(7).setParent(window.getPanel2D());
				window.getPanel2D().setActors(actors);
			default:
				window.getPanel2D().setActors(actors);
		}

		/*actors.add(
			new Box(3, 25, 28, 6, 6, Panel2D.ORANGE)
		);
		actors.get(0).setParent(window.getPanel2D());
		window.getPanel2D().setActors(actors);
		
		// Can still change actors after connecting with Panel2D.
		actors.add(
			new Circle(10, 10, 3, 4, Panel2D.BLUE)
		);
		
		actors.add(
			new Fuzzy(20, 10)
		);*/
		// Fuzzy doesn't share static parentPanel with the other Shapes,
		// because it is not a subclass of Shape, so we have to set it separately.
		//actors.get(2).setParent(window.getPanel2D());
		
		window.beginAnimation(); // same concepts as in Assignment 2
	}
	
	/**
	 * The basic program flow-of-control for our game.
	 * You could design your own flow-of-control if you want to make
	 * 	 * a completely different game. The basic flow here is for every iteration
	 * to interact with the user, and then process their choices until the user
	 * either achieves their goal, or fails.
	 */
	static void gameLoop() {
		start();
		
		while (!Narrator.isAtFinalChapter()) {
			setupAnimations();
			iteration(); // designed for Narrator to advance one chapter each iteration

		}
		
		finish();
	}
	
	/*
	 * The start of the game. Initialize and connect any part of the game that should 
	 * be set up before the iteration loop.
	 */
	private static void start() {
		Narrator.setData(data);
		data.setUserName(in.getInputString("Please enter your name, HERO! (enter below)"));
		// Needed to store the user's name in data, because there is no Player intsance created yet.
		// This is because the user still has to choose which subclass of Player they want to be.

		out.printFancyIO(Narrator.tell());

		in.pause();
		int choice = in.getPlayerChoice();
		plyr = data.createPlayer(choice);
		data.setPlayer(plyr);

		out.printInfoIO(in.getHistory(0));

		monsters = new ArrayList<Monster>();
		//data.createMonsters(monsters, N_MONSTERS);


	}
	
	/*
	 * This method is the part of the game that can be repeated. The basic
	 * idea is to give the player a choice, and then process that choice
	 * towards some kind of goal. Attacking monsters is a <b>very</b> common 
	 * theme in many games.
	 */
	private static void iteration() {
		
		// Describe some interesting events.

		in.pause();
		if(Narrator.getChapter()%2 == 0){ // We are going to fight every other chapter
			if(Narrator.getChapter() == 6 || Narrator.getChapter() == 8){
				timeForBoss = true;
				data.createMonsters(monsters, 1);
			}else{
				data.createMonsters(monsters, N_MONSTERS);
			}

			out.printFancyIO(Narrator.tell());
			printPlayerInv();
			// Player and monsters take turns attacking each other until either has no hit points.


			while (!monsters.isEmpty()) {
				monsterPrintStats();
				playerPrintStats();

				playerAction();
				// You could design attacks on a random number of randomly chosen monsters.
				monsterActions();

				playerPrintStats();

				out.printInfoIO("You have " + plyr.getHitPoints() + " hit points left.");

				out.clearStats();

				in.pause(); // give user time to absorb reading output of actions

				// No need to loop if player has no hit points remaining.
				if (plyr.isDead()) {
					// Narrator will give details of losing the game elsewhere.
					return;
				}
			}
		}
		else {

			out.printFancyIO(Narrator.tell());
			if(Narrator.getChapter()-1 == 3){
				invtPromt();

			}
			printPlayerInv();
			in.pause();
			out.clearStats();
		}
		//out.printInfoIO("----------\\\\ YOU DEFEATED ALL THE MONSTERS! //----------");
		//in.pause();
	}
	
	// Let the player decide what they want to do in a monster battle.
	private static void playerAction() {
		
		int choice;
		
		String[] results = new String[] { "no actions processed yet" };

			choice = in.getActionChoice();
			switch (Actions.values()[choice]) {
				case DEFEND:
					results = plyr.defend();
					break;
				case ATTACK:
					// These are arbitrary actions, and you need to change them.
					if (monsters.size() > 1)
						for(Monster myMonst: monsters)
							results = plyr.attack(myMonst);
					else
						results = plyr.attack(monsters.get(0));
					break;
				case CAST:
					if(monsters.size() >1)
						for(Monster myMonst: monsters)
							results = plyr.castSpell(myMonst);
					else
						results = plyr.castSpell(monsters.get(0));
			}
		
		out.printInfoIO(results);
	}

	private static void invtPromt(){
		int choice;

		String[] results = new String[] {"no actions passed yet"};

		choice =  in.getInvntChoice();
		switch (MyItems.values()[choice]){
			case GREATSWORD:
				plyr.Invenory.add(new GreatSword());
				break;
			case POTION:
				plyr.Invenory.add(new Potions());
				break;
			case GAUNTLET:
				plyr.Invenory.add(new Gauntlet());
				break;
		}
	}
	private static void printPlayerInv(){
		out.printInfoStats(DataManager.getUserName()+"'s Inventory:");
		if(plyr.Invenory.size()>0) {
			String[] inventory = new String[plyr.Invenory.size()];
			int k = 0;
			for (Items myItem : plyr.Invenory) {

				inventory[k++] = myItem.condition();

			}
			out.printInfoStats(inventory);
		}
		else{
			out.printInfoStats("Inventory is Empty");
		}
	}
	// Clean up the monsters collection, and let remaining monsters do some actions.
	private static void monsterActions() {

		removeDefeatedMonsters();

		for (Monster mnstr : monsters) {
			String[] results;
			int mnstr_choice = Random.rand(2) + 1; // +1 a way to omit DEFEND choice; redesign this
			switch (Actions.values()[mnstr_choice]) {
				case ATTACK:
					results = mnstr.attack(plyr);
					out.printInfoIO(results);
					break;
				case CAST:
					results = mnstr.castSpell(plyr);
					out.printInfoIO(results);
					break;
			}
			// No need to keep attacking if player has no hit points remaining.
			if (plyr.isDead()) {
				// Narrator will give details of losing the game elsewhere.
				return;
			}
		}
	}


	// Monsters with zero hit points should be removed from the monsters collection.
	private static void removeDefeatedMonsters() {
		ArrayList<Monster> temp = new ArrayList<Monster>();
		for (Monster mnstr : monsters)
			if (!mnstr.isDead()) temp.add(mnstr); // this avoids needing iterators
		monsters = temp;
		data.setMonsters(monsters); // update references to other parts of the game
	}
	
	// You could add code to print other statistics about the player.
	private static void playerPrintStats() {
		out.printInfoStats(
			data.getUserName() + "'s hit points: " + plyr.getHitPoints()
		);
		out.printBlankLineStats();
	}
	
	// Prints the stats of remaining monsters---again, you could design more statistics.
	private static void monsterPrintStats() {
		if (!monsters.isEmpty()) {
			String[] results = new String[monsters.size()];
			int k = 0;
			for (Monster mnstr : monsters) {
				results[k++] =
					"There is " +
					mnstr.getDescription() +
					" remaining with " +
					mnstr.getHitPoints() +
					" hit points left.";
			}
			out.printInfoStats(results);
		}
	}
	
	// You should put any code here you want to be the last thing to execute.
	// Maybe a climactic animation?
	private static void finish() {
		// You could have different endings described in the Narrator.
		out.printFancyIO(Narrator.tell());
	}
}