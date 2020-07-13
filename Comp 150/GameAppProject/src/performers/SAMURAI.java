package performers;

import logic.Random;

public class SAMURAI extends Player{
	public SAMURAI() { // SAMURAI are overpowered class, so they one shot anything
		super(
				Random.rand(70) + 20,
				Random.rand(80) + 10,
				Random.rand(80) + 80 
				);
	}

	@Override
	public String[] attack(Monster... monsters) {

		for(Monster myMonst: monsters) myMonst.damageHitPoints(1000);

		return getFoeDia(monsters.length, new String[]{"The samurai warns to get back as he unleashes a powerful slash, the foes lay dead"},

				new String[] {"In the blink of an eye, the samurai has sheathed his blade once again, the foe is defeated"});
		
	}

	@Override
	public String[] castSpell(Monster... monsters) {

		for(Monster myMonst: monsters) myMonst.damageHitPoints(1000);

		return getFoeDia(monsters.length, new String[] {"the samurai calls upon lighting to strike down our enemies before us"},

				new String[] {"the samurai calls upon lighting to strike our foe"});

	}

	@Override
	public String[] defend(){
		hitPoints +=20;
		return new String[] {"The samurai takes a defensive stance and blocks all incoming damage"};

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
