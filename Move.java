/**
 * @author jchen824
 * @version 1.0
 */

public class Move {
    private String name;
    private int power;
    private String type;

    /**
    *@param n fat
    *@param p fat
    *@param t fat
    */

    public Move(String n, int p, String t) {
        name = n;
        power = p;
        type = t;

    }

    /**
    *@return fat
    */

    public String getName() {
        return name;
    }

    /**
    *@return fat
    */

    public int getPower() {
        return power;
    }

    /**
    *@return fat
    */

    public String getType() {
        return type;
    }
}
