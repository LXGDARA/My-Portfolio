package performers;

import logic.DataManager;
import logic.Random;
import story.Narrator;

public class SHAMAN extends Player{
	
	public SHAMAN() {
		
		super(
			Random.rand(70) + 20,
			Random.rand(80) + 10,
			Random.rand(80) + 80 
		
				);
	
	}

	@Override
	public String[] attack(Monster... monsters) {
		for(Monster myMonst: monsters) myMonst.damageHitPoints(5);// please forgive the unsightly-ness of this code
		return getFoeDia(monsters.length, new String[] {"Our SHAMAN, turns into a tiger and leaps from enemy to enemy!"},
				
				new String[] {"Our SHAMAN, turns into a tiger and slashes at the enemy"});
	}

	@Override
	public String[] castSpell(Monster... monsters) {
		for(Monster myMonst: monsters) myMonst.damageHitPoints(6);
		return getFoeDia(monsters.length, new String[] {"Our SHAMAN casts a spell and ice shards impale our enemies"},
				
				new String[] {"Our SHAMAN casts a spell and ice shards impale our enemy"});
	}
	@Override
	public String[] defend(){
		hitPoints += 25;
		return new String[] {DataManager.getUserName() + "Puts up a mana shield and blocks incoming damage"};
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
