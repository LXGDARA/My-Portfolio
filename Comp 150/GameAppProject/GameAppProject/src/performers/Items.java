/**
 * Our Items super class
 * Any items we give the player will be subclasses of this class
 */
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

    /**
     * The condition of our item
     * @return <>NAME</> , <>Function</> and <>echantment</>
     */
    public abstract String condition();
}
