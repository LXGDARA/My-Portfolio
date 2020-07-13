package performers;

public enum MyItems {
    GREATSWORD,
    POTION,
    GAUNTLET;

    public static String[] getValues() {
        int n = MyItems.values().length;
        String[] vals = new String[n];
        int i = 0;
        for (MyItems type : MyItems.values()) {
            vals[i] = type.toString();
            i++;
        }
        return vals;
    }

    public static int size() {
        return PlayerType.values().length;
    }
}
