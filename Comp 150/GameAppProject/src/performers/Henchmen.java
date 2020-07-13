package performers;

import logic.Random;

public class Henchmen extends Monster{
	public Henchmen() {
		super(
				Random.rand(8) + 4, 
				Random.rand(8) + 4
		);
	}

	@Override
	public String[] attack(Player plyr) {
		plyr.damageHitPoints(4);
		return new String[] {"The henchmen thrusts his spear for : " + 4 + " Damage"};
	}

	@Override
	public String[] castSpell(Player plyr) {
		plyr.damageHitPoints(4);
		return new String[] {"The henchmen chants a fireball spell : " + 4 + " Damage"};
	}

	@Override
	public String getDescription() {
		String myDesc;
		return myDesc = "A Foot soldier of the spear class";
	}
}
