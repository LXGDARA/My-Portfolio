package performers;

public class Gauntlet extends  Items{
    public Gauntlet(){
        super(
                "The Fist of Jaurim",
                "Raises defences",
                "FrostBite: Your attacks slow down enemies"
        );
    }

    @Override
    public String condition() {
        StringBuilder condition = new StringBuilder();
        condition.append(NAME + "\n" +Function+ "\n"+echantment);
        return condition.toString();
    }
}
