/**
 * @author jchen824
 * @version 1.0
 */

public class Pokemon {
    private String name;
    private int level;
    private double maxHP;
    private double currentHP;
    private int atk;
    private Move[] moves;
    private String type;
    private boolean fainted;
    private String image;

    /**
    *@param n fat
    *@param l fat
    *@param h fat
    *@param a fat
    *@param t fat
    *@param i fat
    *@param move fat
    */

    public Pokemon(String n, int l, double h, int a, String t, String i, Move... move) {
        name = n;
        level = l;
        maxHP = h;
        atk = a;
        type = t;
        currentHP = maxHP;
        moves = move;
        image = i;
    }

    /**
    *@return fat
    */

    public String getImage() {
        return image;
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

    public int getLevel() {
        return level;
    }

    /**
    *@return fat
    */

    public int getAtk() {
        return atk;
    }

    /**
    *@return fat
    */

    public double getMaxHP() {
        return maxHP;
    }

    /**
    *@return fat
    */

    public double getCurrentHP() {
        return currentHP;
    }

    /**
    *@return fat
    */

    public Move[] getMoves() {
        return moves;
    }

    /**
    *@param currentHP fat
    */

    public void setCurrentHP(double currentHP) {
        this.currentHP = currentHP;
        if (this.currentHP <= 0) {
            fainted = true;
        }
    }

    /**
    *@param move fat
    *@return fat
    */

    public double compareType(Move move) {
        if (move.getType().equals(type)) {
            return 0.5;
        } else if (move.getType().equals("WATER")) {
            if (type.equals("FIRE")) {
                return 2.0;
            } else if (type.equals("GRASS")) {
                return 0.5;
            }
        } else if (move.getType().equals("GRASS")) {
            if (type.equals("WATER")) {
                return 2.0;
            } else if (type.equals("FIRE")) {
                return 0.5;
            } else if (type.equals("FLYING")) {
                return 0.5;
            }
        } else if (move.getType().equals("FIRE")) {
            if (type.equals("GRASS")) {
                return 2.0;
            } else if (type.equals("WATER")) {
                return 0.5;
            }
        } else if (move.getType().equals("FLYING")) {
            if (type.equals("GRASS")) {
                return 2.0;
            }
        }
        return 1;
    }

    /**
    *@return fat
    */

    public boolean isFainted() {
        return fainted;
    }

    /**
    *@param fainted fat
    */

    public void setFainted(boolean fainted) {
        this.fainted = fainted;
    }
}
