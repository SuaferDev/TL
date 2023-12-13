package org.suafer;

import java.util.List;

public class GameCreator {

    public static Game create1vs1Score(List<Player> playerList, int maxTurn, int maxScore){return new Game(playerList, maxTurn, maxScore, Integer.MAX_VALUE);}
    public static Game create1vs1vs1Score(List<Player> playerList, int maxTurn, int maxScore){return new Game(playerList, maxTurn, maxScore, Integer.MAX_VALUE);}
    public static Game create1vs1vs1vs1Score(List<Player> playerList, int maxTurn, int maxScore){return new Game(playerList, maxTurn, maxScore, Integer.MAX_VALUE);}


    public static Field[][] createClassic1vs1map(Game game){
        //Паттерн Builder
        /*
        ☐ ☐ ⛰ ☐ ☐ ☐ ⛰ ☐ ☐
        ⛑ ☐ ☐ ☐ ⛰ ☐ ☐ ☐ ⛑
        ⛑ ☐ ☐ ☐ ⛰ ☐ ☐ ☐ ⛑
        ⛑ ☐ ⛰ ☐ ⛰ ☐ ⛰ ☐ ⛑
        ☐ ☐ ☐ ☐ ☐ ☐ ☐ ☐ ☐
         */


        Field[][] map = createClearMap(5,9);

        map[1][0].setTank(game.getPlayerList().get(0).getTanks().get(0));
        map[2][0].setTank(game.getPlayerList().get(0).getTanks().get(1));
        map[3][0].setTank(game.getPlayerList().get(0).getTanks().get(2));

        map[1][8].setTank(game.getPlayerList().get(1).getTanks().get(0));
        map[2][8].setTank(game.getPlayerList().get(1).getTanks().get(1));
        map[3][8].setTank(game.getPlayerList().get(1).getTanks().get(2));

        map[1][4].setStructure(new Structure("Стена",true, 2, Integer.MAX_VALUE));
        map[2][4].setStructure(new Structure("Стена",true, 2, Integer.MAX_VALUE));
        map[3][4].setStructure(new Structure("Стена",true, 2, Integer.MAX_VALUE));

        map[0][2].setStructure(new Structure("Стена",true, 2, Integer.MAX_VALUE));
        map[0][6].setStructure(new Structure("Стена",true, 2, Integer.MAX_VALUE));

        map[3][2].setStructure(new Structure("Стена",true, 2, Integer.MAX_VALUE));
        map[3][6].setStructure(new Structure("Стена",true, 2, Integer.MAX_VALUE));

        return map;
    }

    public static Field[][] createClearMap(int a, int b){
        Field[][] map = new Field[a][b];
        for(int i=0; i< map.length; i++){
            for(int j=0; j< map[0].length; j++){
                map[i][j] = new Field();
            }
        }
        return map;
    }
}
