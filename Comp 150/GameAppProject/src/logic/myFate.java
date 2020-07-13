package logic;

public enum myFate {
    SPARE_HIM,
    KILL_HIM;

    public static String[] getValues() {
        int n = myFate.values().length;
        String[] vals = new String[n];
        int i = 0;
        for (myFate type : myFate.values()) {
            vals[i] = type.toString();
            i++;
        }
        return vals;
    }

    /**
     * A method to get the number of constants in this Enum.
     *
     * @return
     *   An integer for the number of constants in this Enum.
     */
    public static int size() {
        return myFate.values().length;
    }
}
