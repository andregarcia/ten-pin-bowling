package domain;

import java.util.List;

public class PlayerGame {

    private String player;

    private List<Frame> frames;

    public PlayerGame(String player, List<Frame> frames) {
        this.player = player;
        this.frames = frames;
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public Integer getScore(){
        return frames.get(frames.size()-1).getScore();
    }

    public String getPlayer() {
        return player;
    }

    public void prettyPrint(){
        System.out.println(String.format("========== PLAYER: %s", player));
        System.out.print("Frame\t\t");
        for(Frame frame : frames){
            frame.printFormattedNumber();
        }
        System.out.print("\nPinfalls\t");
        for(Frame frame : frames){
            frame.printFormattedPinfalls();
        }
        System.out.print("\nScore\t\t");
        for(Frame frame : frames){
            frame.printFormattedScore();
        }
        System.out.println();
    }

}
