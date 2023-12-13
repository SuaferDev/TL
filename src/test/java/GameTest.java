import org.suafer.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class GameTest {

    private Game game;

    @Before
    public void setDefault(){
        Ammo defaultAmmo = new Ammo("Классический снаряд",1);
        List<Tank> playersTanks1= new ArrayList<>();
        playersTanks1.add(new Tank("T-26",10,6,3, 2, defaultAmmo,20));
        playersTanks1.add(new Tank("T-34",15,8,2, 4, defaultAmmo,20));
        playersTanks1.add(new Tank("КВ-2",20,9,1, 6, defaultAmmo,20));
        List<Tank> playersTanks2 = new ArrayList<>();
        playersTanks2.add(new Tank("PZ.KPFW. II AUSF. G",10,6,3, 2, defaultAmmo,20));
        playersTanks2.add(new Tank("PANTHER",15,8,2, 4, defaultAmmo,20));
        playersTanks2.add(new Tank("TIGER 1",20,9,1, 6, defaultAmmo,20));

        Player player1 = new Player("Player 1", playersTanks1);
        Player player2 = new Player("Player 2", playersTanks2);

        List<Player> playerList = new ArrayList<>(); playerList.add(player1); playerList.add(player2);

        game = new Game(playerList,30, 3000, Integer.MAX_VALUE);
        game.setMap(GameCreator.createClassic1vs1map(game));
    }

    @Test
    public void testFirstPlayer(){
        //Проверяем определила ли игра первого игрока
        assertEquals("Player 1", game.getPlayerNowName());
    }

    @Test
    public void gameTest(){
        //Проверяем сможем ли мы выбрать поле без танка
        game.makeTurn(0,3);
        assertEquals(-1, game.getChosenI());
        assertEquals(-1, game.getChosenJ());

        //Проверяем сможем ли мы выбрать поле с танком другого игрока
        game.makeTurn(1,8);
        assertEquals(-1, game.getChosenI());
        assertEquals(-1, game.getChosenJ());

        //Проверяем сможем ли мы выбрать поле со своим танком
        game.makeTurn(1,0);
        assertEquals(1, game.getChosenI());
        assertEquals(0, game.getChosenJ());

        //Передвигаем танк
        game.makeTurn(1,1);
        assertNotEquals(null, game.getMap()[1][1].getTank());

        //Проверяем сменился ли ход
        game.nextTurn();
        assertEquals("Player 2", game.getPlayerNowName());
        assertEquals(2, game.getTurn());

        //Проверяем сможем ли мы выбрать поле с танком другого игрока
        game.makeTurn(1,1);
        assertEquals(-1, game.getChosenI());
        assertEquals(-1, game.getChosenJ());

        //Проверяем сможем ли мы выбрать поле со своим танком
        game.makeTurn(1,8);
        assertEquals(1, game.getChosenI());
        assertEquals(8, game.getChosenJ());

        //Стреляем по другому танку
        game.makeTurn(1,1);
        assertEquals(-1, game.getChosenI());
        assertEquals(-1, game.getChosenJ());
        assertEquals(TankStatus.None, game.getMap()[1][8].getTank().getStatus());

        assertEquals(8, game.getMap()[1][1].getTank().getHealth(),0.1);
        assertEquals(19, game.getMap()[1][8].getTank().getAmmoCount());

        //Проверяем, что снова нельзя использовать тот же танк
        game.makeTurn(1,8);
        assertEquals(-1, game.getChosenI());
        assertEquals(-1, game.getChosenJ());

        game.makeTurn(3,8);
        assertEquals(3, game.getChosenI());
        assertEquals(8, game.getChosenJ());

        //game.makeTurn();
    }

}
