package performers;

public class GreatSword extends Items {
    public GreatSword(){
        super(
                "Blessed Blade of the Wind Seeker",
                "A hefty Blade for the worthy",
                "Wind Spirits Blessing"
        );}

    @Override
    public String condition() {
        StringBuilder condition = new StringBuilder();
        condition.append(this.NAME + "\n" +this.Function+ "\n"+this.echantment);
        return condition.toString();
    }
}
