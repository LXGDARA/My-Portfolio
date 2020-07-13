package performers;

import graphics.Point2D;

import java.util.ArrayList;

/**
 * A superclass for organizing the common code of all kinds of player subclasses.
 * An abstract class cannot be initialized, so initialize subclasses of it instead.
 * For subclasses, we are forced to write the code of the abstract methods.
 * For regular methods in the superclass, we get to use these in any subclass,
 * without having to write them in the subclasses again and again.
 */
public abstract class Player {
	protected final static int MAX_HIT_POINTS = 70;
	// Package-protected means it can be used by other classes in the same package.
	protected int hitPoints;
	protected int strength;
	protected int wisdom;

	public ArrayList<Items> Invenory = new ArrayList<Items>();
	/**
	 * This superclass constructor should take care of common setup for 
	 * creating instances of *any subclass* of person the player chooses. 
	 * So calling super() for any subclass constructors saves you from 
	 * writing this code again.
	 *   
	 * @param hitPoints
	 *   The number of points a player has to stay alive.
	 *   
	 * @param strength
	 *   A basic measure of the amount of strength a player has. This could
	 *   be used to help calculate attack damage to hit points of monsters.
	 *   
	 * @param wisdom
	 *   A basic measure of the amount of wisdom a player has. This could be
	 *   used to help calculate the amount of spell effects.
	 */
	public Player(int hitPoints, int strength, int wisdom) {
		this.hitPoints = hitPoints;
		this.strength = strength;
		this.wisdom = wisdom;
	}
	
	/**
	 * A method to get the maximum possible hit points for the player. 
	 * You could use this value to help you calculate results for 
	 * situations you design.
	 * 
	 * @return
	 *   An integer for the largest possible hit points of the player.
	 */
	public int getMaxHitPoints() {
		return MAX_HIT_POINTS;
	}
	
	/**
	 * To find out the current hit points status of the player.
	 * 
	 * @return
	 *   An integer for the current hit point value.
	 */
	public int getHitPoints() {
		return hitPoints;
	}
	
	/**
	 * Decreases hit points of this player.
	 * 
	 * @param damage
	 *   The amount of damage this player is receiving.
	 */
	public void damageHitPoints(int damage) {
		if (hitPoints > damage)
			hitPoints -= damage;
		else
			hitPoints = 0;
	}
	
	/**
	 * To find out the strength of the player.
	 * 
	 * @return
	 *   An integer for the strength of the player.
	 */
	public int getStrength() {
		return strength;
	}
	
	/**
	 * To find out the wisdom of the player.
	 * 
	 * @return
	 *   An integer for the wisdom of the player.
	 */
	public int getWisdom() {
		return wisdom;
	}
	
	/**
	 * To change the value of the player's current hit points.
	 * 
	 * @param hitPoints
	 *   The value to save as the current hit points.
	 */
	public void setHitPoints(int hitPoints) {
		this.hitPoints = hitPoints;
	}
	
	/**
	 * To change the value of the player's strength.
	 * 
	 * @param strength
	 *   The value to save as the player's strength.
	 */
	public void setStrength(int strength) {
		this.strength = strength;
	}
	
	public String[] getFoeDia(int a, String[] b, String[] c) { // saves from having to repetitively write if statements to choose the appropriate dialogue based on # of Foes 
		String[] myString = {};
		if(a ==1) {
			myString = b;
		}
		else if(a > 1) {
			myString = c;
		}
		return myString;
	}
	
	/**
	 * To change the value of the player's wisdom.
	 * 
	 * @param wisdom
	 *   The value to save as the player's wisdom.
	 */
	public void setWisdom(int wisdom) {
		this.wisdom = wisdom;
	}
	
	/**
	 * Checks whether this monster is dead or still alive.
	 * 
	 * @return
	 *   A value of <code>true</code> if this player has no hit points, 
	 *   and <code>false</code> otherwise.
	 */
	public boolean isDead() {
		return hitPoints == 0;
	}
	
	/**
	 * Every kind of player should implement their own version of an attack.
	 * 
	 * @param monsters
	 *   The attack should affect one, some, or all of the monsters. 
	 *   You decide how this should be implemented in subclasses.
	 * 
	 * @return
	 *   An array of strings, describing the results of this player attack on the monsters.
	 */
	public abstract String[] attack(Monster... monsters);
	
	/**
	 * Every kind of player should implement their own version of a spell to cast.
	 * 
	 * @param monsters
	 *   The spell should affect one, some, or all of the monsters. 
	 *   You decide how this should be implemented in subclasses.
	 * 
	 * @return
	 *   An array of strings, describing the results of this player spell on the monsters.
	 */
	public abstract String[] castSpell(Monster...monsters);
	public abstract String[] defend();
	public abstract String[] showMercy();
	public abstract String[] kill();
}