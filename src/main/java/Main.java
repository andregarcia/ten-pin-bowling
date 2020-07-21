import domain.PlayerGame;
import domain.Roll;
import game.FramesAssembler;
import input.TenPinBowlingInputReader;

import java.util.List;

public class Main {


    public static void main(String[] args){
        String file = args[0];
        TenPinBowlingInputReader inputReader = new TenPinBowlingInputReader();
        List<Roll> rolls = inputReader.readFile(file);
        List<PlayerGame> playerGames = FramesAssembler.multiplePlayerRollsToPlayersGames(rolls);
        for(PlayerGame game : playerGames){
            game.prettyPrint();
        }
    }


}
