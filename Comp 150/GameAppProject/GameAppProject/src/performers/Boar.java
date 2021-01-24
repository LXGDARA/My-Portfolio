package performers;
import logic.Random;
public class Boar extends Monster{

	public Boar() {
		super(
				Random.rand(5) + 2,
				Random.rand(5) + 2
		);

	}

	@Override
	public String[] attack(Player plyr) {
		String[] myAttac;
		plyr.damageHitPoints(5);
		myAttac = new String[]{"This Boar attacks player for " + 5 + "damage"};
		return null;
	}

	@Override
	public String[] castSpell(Player plyr) {
		 plyr.damageHitPoints(3);
		return new String[]{"This Boar casts a Taunt on player for :  " + 3 + " damage"};
	}

	@Override
	public String getDescription() {
		String myDesc;
		myDesc = "A Wild Boar, a low level creep";
		return myDesc;
	}

}
