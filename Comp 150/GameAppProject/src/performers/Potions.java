package performers;

public class Potions extends Items {
    public Potions(){
        super(
                "Elixir Of Iron",
                "Restores health",
                "Your attacks will do Damage Over time!"
        );

    }

    @Override
    public String condition() {
        StringBuilder condition = new StringBuilder();
        condition.append(this.NAME + "\n" +this.Function+ "\n"+this.echantment);
        return condition.toString();
    }
}
