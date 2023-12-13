package org.suafer;


import java.util.ArrayList;
import java.util.List;

public class Player {

    private final String name;
    private final List<Tank> tanks;
    private int score;
    private int flag;


    public Player(String name, List<Tank> tanks) {
        this.name = name;
        this.tanks = tanks;
        this.score = 0;
        this.flag = 0;
    }

    public Player(String name) {
        this.name = name;
        this.tanks = new ArrayList<>();
        this.score = 0;
        this.flag = 0;
    }

    public String getName() {return name;}
    public void addScore(int plus){score+=plus;}
    public int getScore() {return score;}
    public void addTank(Tank tank){tanks.add(tank);}
    public void addFlag(){flag++;}
    public int getFlag() {return flag;}
    public void removeTank(int i){
        tanks.remove(i);
    }
    public void removeTank(Tank tank){
        tanks.remove(tank);
    }


    public  int tankCount(){return tanks.size();}

    public List<Tank> getTanks() {
        return tanks;
    }
}
