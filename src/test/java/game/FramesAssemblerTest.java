package game;

import domain.LastFrame;
import domain.PlayerGame;
import domain.Roll;
import exception.InputParseException;
import input.TenPinBowlingInputReader;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class FramesAssemblerTest {

    @Test
    void multiplePlayerRollsToPlayersGames() {
        //given
        String filePath = getClass().getClassLoader().getResource("test_input.txt").getPath();
        final List<Roll> rollList = TenPinBowlingInputReader.readFile(filePath);

        //when
        List<PlayerGame> playerGames = FramesAssembler.multiplePlayerRollsToPlayersGames(rollList);

        assertEquals(10, playerGames.get(0).getFrames().size());
        assertEquals("Jeff", playerGames.get(0).getPlayer());
        assertEquals(167, playerGames.get(0).getScore());

        assertEquals(10, playerGames.get(1).getFrames().size());
        assertEquals("John", playerGames.get(1).getPlayer());
        assertEquals(151, playerGames.get(1).getScore());
    }

    @Test
    void playerRollsToPlayerGame() throws Exception {
        //given
        String filePath = getClass().getClassLoader().getResource("test_input.txt").getPath();
        List<Roll> rollList = TenPinBowlingInputReader.readFile(filePath);
        assert rollList.size() == 35;
        rollList = rollList.stream().filter((Roll roll) -> roll.getPlayer().equals("Jeff")).collect(Collectors.toList());
        assert rollList.size() == 17;

        //when
        PlayerGame playerGame = FramesAssembler.playerRollsToPlayerGame(rollList);

        //then
        assertEquals(10, playerGame.getFrames().size());
        assertEquals("Jeff", playerGame.getPlayer());
        assertEquals(167, playerGame.getScore());

        //first frame
        assertEquals(rollList.get(0), playerGame.getFrames().get(0).getRoll1());
        assertNull(playerGame.getFrames().get(0).getRoll2());
        assertEquals(1, playerGame.getFrames().get(0).getNumber());
        assertEquals(playerGame.getFrames().get(1), playerGame.getFrames().get(0).getNext());
        assertNull(playerGame.getFrames().get(0).getPrevious());

        //second frame
        assertEquals(rollList.get(1), playerGame.getFrames().get(1).getRoll1());
        assertEquals(rollList.get(2), playerGame.getFrames().get(1).getRoll2());
        assertEquals(2, playerGame.getFrames().get(1).getNumber());
        assertEquals(playerGame.getFrames().get(2), playerGame.getFrames().get(1).getNext());
        assertEquals(playerGame.getFrames().get(0), playerGame.getFrames().get(1).getPrevious());

        //third frame
        assertEquals(rollList.get(3), playerGame.getFrames().get(2).getRoll1());
        assertEquals(rollList.get(4), playerGame.getFrames().get(2).getRoll2());
        assertEquals(3, playerGame.getFrames().get(2).getNumber());
        assertEquals(playerGame.getFrames().get(3), playerGame.getFrames().get(2).getNext());
        assertEquals(playerGame.getFrames().get(1), playerGame.getFrames().get(2).getPrevious());

        //nineth frame
        assertEquals(rollList.get(13), playerGame.getFrames().get(8).getRoll1());
        assertNull(playerGame.getFrames().get(8).getRoll2());
        assertEquals(9, playerGame.getFrames().get(8).getNumber());
        assertEquals(playerGame.getFrames().get(9), playerGame.getFrames().get(8).getNext());
        assertEquals(playerGame.getFrames().get(7), playerGame.getFrames().get(8).getPrevious());

        //last frame
        assertEquals(LastFrame.class, playerGame.getFrames().get(9).getClass());
        LastFrame lastFrame = (LastFrame) playerGame.getFrames().get(9);
        assertEquals(rollList.get(14), lastFrame.getRoll1());
        assertEquals(rollList.get(15), lastFrame.getRoll2());
        assertEquals(rollList.get(16), lastFrame.getRoll3());
        assertEquals(10, lastFrame.getNumber());
        assertNull(lastFrame.getNext());
        assertEquals(playerGame.getFrames().get(8), lastFrame.getPrevious());

    }

    @Test
    void testAssembleGameWithMoreRollsThanAcceptable() {
        //given
        String filePath = getClass().getClassLoader().getResource("test_input_has_more_rolls_than_acceptable.txt").getPath();

        //when
        final List<Roll> rollList = TenPinBowlingInputReader.readFile(filePath);
        assertThrows(InputParseException.class, () -> FramesAssembler.multiplePlayerRollsToPlayersGames(rollList));
    }

    @Test
    void testAssembleGameWithLessRollsThanAcceptable() {
        //given
        String filePath = getClass().getClassLoader().getResource("test_input_partial.txt").getPath();

        //when
        final List<Roll> rollList = TenPinBowlingInputReader.readFile(filePath);
        assertThrows(InputParseException.class, () -> FramesAssembler.multiplePlayerRollsToPlayersGames(rollList));
    }
}