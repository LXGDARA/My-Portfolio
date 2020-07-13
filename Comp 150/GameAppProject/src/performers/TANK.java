package performers;

import logic.DataManager;
import logic.Random;
import story.Narrator;

public class TANK extends Player{
	
	public TANK() {
		super(
				Random.rand(50) + 200, 
				Random.rand(60) + 20,
				10
				
		); // your dumb meatshield that tanks all the damage
	}
	
	@Override
	public String[] attack(Monster...monsters) {
		for(Monster myMonst: monsters) myMonst.damageHitPoints(6);
		return getFoeDia(monsters.length, new String[] {"This tank attacked the monsters for : \" + 6 + \"Damage!"}, new String[] {"This tank attacked the monster for : \" + 6 + \" Damage!"});
	}

	@Override
	public String[] castSpell(Monster... monsters) {
		for(Monster myMonst: monsters) myMonst.damageHitPoints(4);

		return getFoeDia(monsters.length, new String[]{"This tank casted a blind spell for " + 4 + " Damage!"}, new String[]{"This tank casted an area of effect blind spell" + 4  + "Damage!"});
	}
	@Override
	public String[] defend(){
		hitPoints += 40;
		return new String[] {DataManager.getUserName() + "Raises their shield to block all incoming damage"};
	}
	@Override
	public String[] showMercy(){
		return  new String[] {"You've decided to show mercy"};
	}
	@Override
	public String[] kill(){
		return new String[] {"You Show no mercy"};
	}
}
