package logic;

import gui.PanelIO;
import performers.MyItems;
import performers.PlayerType;
import story.Narrator;

import java.awt.*;

/**
 * This is the class that will manage all the input from the user as your game
 * interacts with the story you design.
 * It was created so that you would not have to set up as many parameters when
 * creating your own methods in the project code.
 * 
 * @author Dr Russell Campbell
 *
 */
public class InputManager {
	
	private static PanelIO in;
	private static OutputManager out = new OutputManager();
	private static GameThread gameThread; // so InputManager can wait when it needs user input
	private String[] choiceHistory = new String[5]; // place to save the last 5 user input choices
	private int currentChoice = choiceHistory.length - 1; // index for choiceHistory
	
	/**
	 * Sets a reference to a PanelIO instance that the user needs to interact with later.
	 * 
	 * @param in
	 *   The PanelIO instance that the user needs to interact with.
	 */
	public void setPanelIO(PanelIO in) {
		this.in = in;
	}
	
	/**
	 * Sets a GameThread instance that this InputManager needs to be able to signal
	 * in order to both pause and resume the game later.
	 * 
	 * @param gameThread 
	 *   The GameThread instance that this InputManager needs to be able to signal.
	 */
	public void setGameThread(GameThread gameThread) {
		this.gameThread = gameThread;
	}
	
	/**
	 * Prints a prompt to the user for some String input, 
	 * and then waits for the user to enter one line of text.
	 * 
	 * @param msg 
	 *   The prompt for the user to read, so the player
	 *   knows what kind of input to type.
	 *   
	 * @return 
	 *   The line of text the user entered.
	 */
	public String getInputString(String msg) {
		out.printPromptIO(msg);
		gameThread.pause();
		setHistory(in.getInputString());
		return getHistory(0);
	}
	
	/**
	 * Prints a prompt to the user for an integer input, 
	 * and then waits for the user to enter an integer.
	 * 
	 * @param msg 
	 *   The prompt for the user to read, so the player
	 *   knows what integer values they can choose.
	 *   
	 * @return 
	 *   The integer the user entered.
	 */
	public int getInputInt(String msg) {
		boolean isInputValid = false;
		int choice =0;
		out.printPromptIO(msg);
		while(!isInputValid){
			gameThread.pause();
			// The user *could* type something that is not a number; debug and fix this!
			String input = in.getInputString();
			isInputValid = validateUserChoice(input);
			if(isInputValid){
				choice= Integer.parseInt(input);
				out.clearIO();
			}

		}
		return choice;
	}
	
	/**
	 * A prompt for the player so they have some time to read output. You should be using
	 * this method in Game quite often, so the user is not overwhelmed with output.
	 */
	public void pause() {
		// Use this when you want to pause output so the 
		// player can read it before printing more output.
		getInputString("(press \"submit\" button to continue...)");
		out.clearIO(); // you might not want output to be erased every time after pausing...
	}

	/**
	 * We test whether or not we're working with actual numbers
	 * @param choice
	 * @return null or an integer
	 */
	private Integer willItParse(String choice){
		try{
			return Integer.parseInt(choice);
		}catch (NumberFormatException e){
			return null;
		}
	}

	/**
	 * test if we have the correct numbers or not
	 * @param testValue
	 * @return true or false
	 */
	private boolean is012(Integer testValue){
		return  testValue.equals(0) || testValue.equals(1) || testValue.equals(2);

	}
	/**
	 * This validates the player's input choice, given a maximum number of choices.
	 * Note that it does NOT handle input data with mismatched types. Fix that.
	 * 
	 * @param choice
	 *   The input integer the user entered.
	 *   
	 * //@param n
	 *   The maximum number of choices the user has.
	 *   
	 * @return
	 *   Will return <code>true</code> if the user's choice is valid, and
	 *   <code>false</code> otherwise.
	 */

	boolean inInputValid= false;
	public boolean validateUserChoice(String choice) {

			Integer testValue = willItParse(choice);
			if(testValue == null){
				out.printInfoIO("You need to choose amongst the given options");
			}else{

				if(is012(testValue)){
					inInputValid = true;

				}else{
					out.printInfoIO("please enter the numbers 0, 1 or 2");
					inInputValid = false;
				}
			}

		return inInputValid;
	}
	
	/*s
	 * This prints the prompt for the choices the player has to choose.
	 */
	public void promptForChoice(String prompt, String[] choices) {
		int n = choices.length;
		String[] choicesMessage = new String[n+1];
		choicesMessage[0] = prompt;
		for (int i = 0; i < n; i++) {
			choicesMessage[i+1] = 
				" - "
				+ choices[i]
				+ " (enter " + i + ")";
		}
		
		out.printFancyIO(choicesMessage);
	}
	
	/**
	 * This asks the user for their first choice: 
	 * which type of player they want to be.
	 * 
	 * @return
	 *   A valid integer number chosen by the user within the range of possible choices.
	 */
	public int getPlayerChoice() {
		int choice = -1;
					promptForChoice(
				"The possible classes you can play:",
				PlayerType.getValues()
			);
			
			choice = getInputInt("Choose a class. (type choice above!)");
			
			int n = PlayerType.size();
		
		// Record their choice.
		switch (PlayerType.values()[choice]) {
			case SAMURAI:
				setHistory("You chose a Samurai!");
				break;
			case SHAMAN:
				setHistory("You chose a Shaman!");
				break;
			case TANK:
				setHistory("You chose a Tank!");
		}
		return choice;
	}

	/**
	 * Get out choice of inventory item
	 * @return <>GREATSWORD</> , <>POTION</> , <>GAUNTLET</>
	 */
	public int getInvntChoice(){
		int choice = -1;
		boolean validChoice = false;
			promptForChoice(
					"Which Item will you choose?",
					MyItems.getValues()
			);

			choice = getInputInt("Choose an item. (type choice above!)");

			int n = MyItems.size();


		// Record their choice.
		switch (MyItems.values()[choice]) {
			case GREATSWORD:
				setHistory("You chose the GreatSword!");
				break;
			case POTION:
				setHistory("You chose the potion!");
				break;
			case GAUNTLET:
				setHistory("You chose the gauntlet!");
		}
		return choice;
	}
	/**
	 * This asks the user for their action choice during a brawl with monsters.
	 * 
	 * @return
	 *   A valid integer number chosen by the user within the range of possible choices.
	 */
	public int getActionChoice() {
		int choice = -1;
		boolean validChoice = false;
		while (!validChoice ) {

				promptForChoice(
						"The possible things you can do:",
						Actions.getValues()
				);
			choice = getInputInt("Choose an action. (type choice below)");
			validChoice = inInputValid;
			int n = Actions.size();

		}
			switch (Actions.values()[choice]) {
				case DEFEND:
					setHistory("You chose to defend.");
					break;
				case ATTACK:
					setHistory("You chose to attack.");
					break;
				case CAST:
					setHistory("You chose to cast a spell.");
					break;
			}

		
		// Code for processing their choice is more involved and should be in Game.java.
		
		return choice;
	}

	
	// Only the InputManager needs to record what the user chooses.
	// You could design a different way of keeping track of what the user chooses.
	private void setHistory(String choice) {
		currentChoice++; // incrementing first makes it a bit easier for writing code of getHistory
		currentChoice %= choiceHistory.length; // modulo wrap index back to 0 if needed
		choiceHistory[currentChoice] = choice;
	}
	
	/**
	 * This gets a description of a choice made by the user at <code>back</code> number of inputs
	 * ago. If you are reading this, then congratulations, you have found extra code you can use
	 * that is not currently called anywhere else in the game. Feel free to modify it to do
	 * something more interesting, or use it the way it is in an interesting way.
	 * 
	 * @param back
	 *   The number of elements backward to read one choice from the choiceHistory array.
	 *   Need to add some validation code here to make sure the integer passed in is less than
	 *   the number of elements in the choiceHistory array.
	 * 
	 * @return
	 *   A string describing the choice made <i>back</i> number of choices ago.
	 */
	public String getHistory(int back) {
		int prevIndex = currentChoice;
		prevIndex += (choiceHistory.length - back);
		prevIndex %= choiceHistory.length;
		// May seem weird, but modulo does not have standard behaviour with negative numbers
		// across programming languages, so you always want to avoid the issue by using only
		// positive numbers involved; done here by adding choiceHistory.length in the calculations.
		return choiceHistory[currentChoice];
	}
}