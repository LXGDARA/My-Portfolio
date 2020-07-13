package performers;

import logic.Random;

/**
 * A Blob is just like a mushy ball of glue. Described in your story whatever way you want.
 * The basic version here is designed to cast a spell to hurt the player with a wave of energy. 
 */
public class Blob extends Monster {
	
	/**
	 * To create a Blob, the superclass constructor is called.
	 * You could make more variables to design more interesting things if you wanted.
	 */
	public Blob() {
		// *If* you call the superclass constructor, make sure you call it first.
		super(
			Random.rand(7) + 1,  // hitPoints
			Random.rand(2) + 1 // strength
		);
		// Then initialize other variables after...
	}
	
	/**
	 * A basic attack for the Blob type of monster.
	 * 
	 * @return
	 *   An array of strings, describing the results of a Blob attack on the player.
	 */
	@Override
	public String[] attack(Player plyr) {
		plyr.damageHitPoints(2);
		return new String[] { "Blob attacked you for " + 2 + " damage!" };
	}
	
	/**
	 * A more descriptive result to go along with a Blob's unique spell to cast.
	 * 
	 * @return
	 *   An array of strings, describing the results of a Blob spell on the player.
	 */
	@Override
	public String[] castSpell(Player plyr) {
		plyr.damageHitPoints(4);
		return new String[] {
			"A blob pulses with what seems like excitement and unleashes a blast of energy!",
			"The blast of energy waves over you, and you take " + 4 + " damage!"
		};
	}
	
	/**
	 * Get a Blob description.
	 * 
	 * @return
	 *   A short phrase describing a Blob. You could randomize descriptions
	 *   to be unique for different instances in different ways.
	 */
	@Override
	public String getDescription() {
		return "a blob";
	}
}