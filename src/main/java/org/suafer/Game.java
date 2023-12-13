package org.suafer;

import java.util.ArrayList;
import java.util.List;

public class Game {


    private boolean status;
    private final List<Player> playerList;
    private final int maxTurn;
    private int turn;
    private final int maxScore;
    private final int flag;
    private Field[][] map;
    private int playerNow;
    private int chosenI;
    private int chosenJ;

    public Game(List<Player> playerList, Field[][] map,  int maxTurn, int maxScore, int flag) {
        this.status = true;
        this.playerList = playerList;
        this.map = map;
        this.turn = 1;
        this.maxTurn = maxTurn;
        this.maxScore = maxScore;
        this.flag = flag;
        this.chosenI = -1;
        this.chosenJ = -1;
        this.playerNow = 0;
    }

    public Game(List<Player> playerList,  int maxTurn, int maxScore, int flag) {
        this.status = true;
        this.playerList = playerList;
        this.map = null;
        this.turn = 1;
        this.maxTurn = maxTurn;
        this.maxScore = maxScore;
        this.flag = flag;
        this.chosenI = -1;
        this.chosenJ = -1;
        this.playerNow = 0;
    }

    public boolean isStatus() {return status;}
    public void addPlayer(Player player) {playerList.add(player);}
    public void clearPlayer() {playerList.clear();}
    public Field[][] getMap() {return map;}
    public int getChosenI() {return chosenI;}
    public int getChosenJ() {return chosenJ;}
    public String getPlayerNowName(){return playerList.get(playerNow).getName();}


    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setMap(Field[][] map) {this.map = map;}

    public int getTurn() {return turn;}
    public int getMaxTurn() {return maxTurn;}
    public int getMaxScore() {return maxScore;}
    public int getFlag() {return flag;}

    public Field getField(int i, int j){
        if(map.length>i && map[0].length>j){
            return map[i][j];
        }
        return null;
    }

    /** Считаем урон наносимый такном*/
    public double calculateDamage(Tank tankShooting, Tank tankTarget, List<Structure> structures){
        double value = 0;
        for(Structure structure : structures){
            value+=structure.getStrength();
        }
        return Math.max(0, tankShooting.shoot()-tankTarget.getArmor()-value);
    }

    /** Проверяем список танков игрока*/
    private boolean checkTankList(Tank tank){
        for(Tank tank1 : playerList.get(playerNow).getTanks()){
            if(tank1 == tank){
                return true;
            }
        }
        return false;
    }

    /** Устанавливаем новое выбранное поле*/
    public void setChosenField(Field field){
        if(status){
            if(chosenJ==-1 && chosenI==-1 && field.getTank()!=null && field.getTank().getStatus()==TankStatus.ShootMove){
                if(checkTankList(field.getTank())){
                    for(int i=0; i< map.length;i++){
                        for(int j=0; j<map.length; j++){
                            if(map[i][j]==field){
                                this.chosenI = i;
                                this.chosenJ = j;
                            }
                        }
                    }
                }
            }
        }
    }

    /** Устанавливаем новое выбранное поле*/
    public void setChosenField(int i, int j){
        if(status){
            if(chosenJ==-1 && chosenI==-1 && map[i][j].getTank()!=null && map[i][j].getTank().getStatus()==TankStatus.ShootMove){
                if(checkTankList(map[i][j].getTank())){
                    this.chosenI = i;
                    this.chosenJ = j;
                }
            }
        }
    }
    public void clearChosenField(){
        this.chosenI = -1;
        this.chosenJ = -1;
    }

    /** Проверяем являются ли поля соседними*/
    public boolean checkField(Field secondField){
        if(map==null || map.length==0){return false;}
        int i2=-1; int j2=-1;
        for(int i=0; i< map.length;i++){
            for(int j=0; j<map[0].length;j++){
                if(map[i][j]==secondField){
                    i2 = i; j2 = j;
                }
                if(chosenI!=-1 && chosenJ!=-1 && i2!=-1 && j2!=-1){
                    return (Math.abs(chosenI-i2)<=1 && Math.abs(chosenJ-j2)<=1);
                }
            }
        }
        return false;
    }
    public boolean checkField(int i, int j){
        if(map==null || map.length==0){return false;}
        return (Math.abs(chosenI-i)<=1 && Math.abs(chosenJ-j)<=1);
    }

    public void checkMap(){
        for(Field[] fields : map){
            for(Field field : fields){
                if(field.getTank()!=null && field.getTank().getHealth()==0){field.setTank(null);}
                if(field.getStructure()!=null && field.getStructure().getState()==0){field.setStructure(null);}
            }
        }
    }

    /** Выполняем ход*/
    public void makeTurn(int i, int j){
        if(status && chosenI!=-1 && chosenJ!=-1){
            if(map[i][j].getTank()!=null && map[chosenI][chosenJ].getTank().getAmmoCount()>0){
                List<Structure> structures = new ArrayList<>();
                FindStructure.findStructure(map,chosenI,chosenJ,i,j,structures);
                map[i][j].getTank().doDamage(calculateDamage(map[chosenI][chosenJ].getTank(),map[i][j].getTank(),structures));
                map[chosenI][chosenJ].getTank().setStatus(TankStatus.None);
                if(map[i][j].getTank().getStatus()==TankStatus.Dead){
                    map[i][j].setTank(null);
                    playerList.get(playerNow).addScore(200);
                }
                clearChosenField();
            }else{
                if(checkField(i,j) && (map[i][j].getStructure()==null || map[i][j].getStructure().getStructureStatus()==StructureStatus.MaybeTank)){
                    map[chosenI][chosenJ].getTank().setStatus(TankStatus.None);
                    map[i][j].setTank(map[chosenI][chosenJ].moveTank());
                    clearChosenField();
                }
            }
        }else{
            setChosenField(i,j);
        }
    }

    /** Смена хода*/
    //Кольцевой список
    public void nextTurn() {
        if (turn <= maxTurn) {
            turn++;
            /*
            turn++;
            playerNow++;
            if (playerNow >= playerList.size()) {
                playerNow = 0;
            }

             */
            playerNow = (playerNow + 1) % playerList.size();
            for (Tank tank : playerList.get(playerNow).getTanks()) {
                tank.setStatus(TankStatus.ShootMove);
            }
            for (Player player : playerList) {
                for(Tank tank : player.getTanks()){
                    if(tank.getStatus()==TankStatus.Dead){
                        player.removeTank(tank);
                    }
                }
            }
        } else {
            status = false;
        }
    }

}
