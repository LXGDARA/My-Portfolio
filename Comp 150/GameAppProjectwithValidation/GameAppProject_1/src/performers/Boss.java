package performers;

public class Boss extends Monster{
    public Boss(){
        super(500, 25);
    }

    @Override
    public String[] attack(Player plyr) {
        String[] myAttac;
        plyr.damageHitPoints(25);
        return myAttac = new String[] {"Illidan swings his great sword for :" + 25 + " damage"};
    }

    @Override
    public String[] castSpell(Player plyr) {
        String[] mySpell;
        plyr.damageHitPoints(25);
        return mySpell = new String[] {"Illidan curses the ground near you for :" + 25 + " damage"};
    }
    @Override
    public String getDescription() {
        String myDesc;
        return myDesc = "Illidan, the ender of worlds";

    }
}
