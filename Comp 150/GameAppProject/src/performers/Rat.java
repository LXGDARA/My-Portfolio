package performers;

import logic.Random;

/**
 * Everybody knows what a rat is.
 * The version here is designed to beltch fire.
 */
public class Rat extends Monster {
	
	/**
	 * To create a Rat, the superclass constructor is called first.
	 * You could make more variables to design more interesting things if you wanted.
	 */
	public Rat() {
		// *If* you call the superclass constructor, make sure you call it first.
		super(
			Random.rand(10) + 1,  // hitPoints
			Random.rand(5) + 1 // strength
		);
		// Then initialize other variables after...
	}
	
	/**
	 * A basic attack for the Rat type of monster.
	 * 
	 * @return
	 *   An array of strings, describing the results of a Rat attack on the player.
	 */
	@Override
	public String[] attack(Player plyr) {
		plyr.damageHitPoints(3);
		return new String[] { "Rat attacked you for " + 3 + " damage!" };
	}
	
	/**
	 * A more descriptive result to go along with a Rat's unique spell (or ability) to cast.
	 * 
	 * @return
	 *   An array of strings, describing the results of a Rat's spell on the player.
	 */
	@Override
	public String[] castSpell(Player plyr) {
		plyr.damageHitPoints(4);
		return new String[] {
			"A rat leaps at you and belches stinky fire out of its mouth!",
			"The stench of the fire chokes you, its flames burn you, and you take " + 6 + " damage!"
		};
	}
	
	/**
	 * Get a Rat description.
	 * 
	 * @return
	 *   A short phrase describing a Rat. You could randomize descriptions
	 *   to be unique for different instances in different ways.
	 */
	@Override
	public String getDescription() {
		return "a rat";
	}
}