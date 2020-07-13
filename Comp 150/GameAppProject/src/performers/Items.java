package performers;


public abstract class Items {
     public static String NAME;
     public static String Function;
     public static String echantment;

    public Items(String name, String function, String enchantment) {
        this.NAME = name;
        this.Function = function;
        this.echantment = enchantment;
    }

    public abstract String condition();
}
