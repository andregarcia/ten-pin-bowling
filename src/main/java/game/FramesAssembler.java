package game;

import domain.Frame;
import domain.LastFrame;
import domain.PlayerGame;
import domain.Roll;
import exception.InputParseException;
import exception.InvalidArgumentException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class FramesAssembler {

    private static final int MAX_FRAMES = 10;

    public static List<PlayerGame> multiplePlayerRollsToPlayersGames(List<Roll> multiplePlayerRolls) throws InputParseException {
        List<PlayerGame> games = multiplePlayerRolls
                .stream()
                .collect(Collectors.groupingBy(Roll::getPlayer, Collectors.toList()))
                .values()
                .stream()
                .map((List<Roll> rolls) -> {
                        return playerRollsToPlayerGame(rolls);
                })
                .sorted(Comparator.comparing(PlayerGame::getPlayer))
                .collect(Collectors.toList());
        return games;
    }

    public static PlayerGame playerRollsToPlayerGame(List<Roll> playerRolls) throws RuntimeException {
        try{
            return _playerRollsToPlayerGame(playerRolls);
        } catch(Exception e){
            throw new InputParseException("Error parsing input", e);
        }
    }


    private static PlayerGame _playerRollsToPlayerGame(List<Roll> playerRolls) throws RuntimeException {
        List<Frame> frames = new ArrayList<Frame>();
        Roll previousRoll = null;
        Frame previousFrame = null;
        String player = null;
        for(Roll roll : playerRolls){
            Frame frame = null;
            if(player == null){
                player = roll.getPlayer();
            }
            else if(!player.equals(roll.getPlayer())){
                throw new InputParseException("Player rolls must contain only a single player rolls, found different user.");
            }
            if(frames.size() == MAX_FRAMES){
                Frame lastFrameTemp = frames.remove(MAX_FRAMES - 1);

                LastFrame lastFrame = null;
                if(playerRolls.indexOf(roll) == playerRolls.size() - 2){
                    lastFrame = new LastFrame(lastFrameTemp);
                    lastFrame.setRoll2(roll);
                }
                else if(playerRolls.indexOf(roll) == playerRolls.size() - 1 ){
                    lastFrame = new LastFrame(lastFrameTemp, roll);
                }
                else if(playerRolls.indexOf(roll) < playerRolls.size() - 2){
                    throw new InputParseException("Input has more rolls than expectec");
                }
                frames.add(lastFrame);
                Frame frameBeforeLastFrame = frames.get(MAX_FRAMES -2 );
                lastFrame.setPrevious(frameBeforeLastFrame);
                frameBeforeLastFrame.setNext(lastFrame);
            }
            else {
                if (roll.getScore() == 10) {
                    frame = new Frame(frames.size() + 1, roll);
                } else {
                    if (previousRoll != null) {
                        frame = new Frame(frames.size() + 1, previousRoll, roll);
                    } else {
                        previousRoll = roll;
                    }
                }
                if (frame != null) {
                    frames.add(frame);
                    previousRoll = null;
                    if (previousFrame != null) {
                        try {
                            previousFrame.setNext(frame);
                        } catch (InvalidArgumentException e) {
                            e.printStackTrace();
                        }
                        frame.setPrevious(previousFrame);
                    }
                    previousFrame = frame;
                }
            }
        }
        PlayerGame playerGame = new PlayerGame(player, frames);
        validatePlayerGame(playerGame);
        return playerGame;
    }

    private static void validatePlayerGame(PlayerGame playerGame) throws InputParseException {
        if(playerGame.getFrames().size() != 10) throw new InputParseException(String.format("Error parsing player [%s] game. Wrong number of frames", playerGame.getPlayer()));
        for(Frame frame : playerGame.getFrames()){
            if(!frame.getClass().equals(LastFrame.class) && frame.isStrike() && frame.getRoll2() != null){
                throw new InputParseException(String.format("Error parsing player [%s] game.", playerGame.getPlayer()));
            }
            if(frame.getClass().equals(LastFrame.class) && (frame.isStrike() || frame.isSpare()) && ((LastFrame) frame).getRoll3() == null){
                throw new InputParseException(String.format("Error parsing player [%s] game.", playerGame.getPlayer()));
            }
        }
    }

}
