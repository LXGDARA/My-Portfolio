package performers;

import logic.Random;

public class Ghoul extends Monster{
	
	public Ghoul() {
		super(
		Random.rand(5) + 2,
		Random.rand(5) + 2
		);
	
	}

	@Override
	public String[] attack(Player plyr) {
		plyr.damageHitPoints(1);
		return new String[] {"The Ghoul slashes for : " + 1 + " damage but we feel nothing"};
	}

	@Override
	public String[] castSpell(Player plyr) {
		plyr.damageHitPoints(3);
		return new String[] {"The Ghoul lets our a mortifying screach for " + 4 + " Damage"};
	}

	@Override
	public String getDescription() {
		String myDesc;
		return myDesc =  "A Ghoul dealing medium damage";
	}
}
