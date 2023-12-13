package org.suafer;

public class Ammo {
    private final String name;
    private final double bonus;

    public Ammo(String name, int bonus) {
        this.name = name;
        this.bonus = bonus;
    }

    public String getName() {
        return name;
    }

    public double getBonus() {
        return bonus;
    }
}
