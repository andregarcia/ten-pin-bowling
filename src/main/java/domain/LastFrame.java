package domain;

import exception.InvalidArgumentException;

public class LastFrame extends Frame{

    private Roll roll3;

    public LastFrame(Frame frame){
        this(frame, null);
    }

    public LastFrame(Frame frame, Roll roll3){
        this(frame.number, frame.roll1, frame.roll2, roll3);
        this.previous = frame.previous;
    }

    public LastFrame(Integer number, Roll roll1, Roll roll2, Roll roll3) {
        super(number, roll1, roll2);
        this.roll3 = roll3;
    }

    public Roll getRoll3() {
        return roll3;
    }

    @Override
    public void setNext(Frame next) throws InvalidArgumentException {
        throw new InvalidArgumentException("Last frame cannot have next frame.");
    }

    @Override
    protected Integer calculateScore() {
        Integer score = super.calculateScore();
        if (roll3 != null) {
            score += roll3.getScore();
        }
        return score;
    }

    @Override
    public void printFormattedNumber(){
        System.out.print(String.format("%d\t\t\t", getNumber()));
    }

    @Override
    public void printFormattedPinfalls(){
        if(roll1.getScore() == 10){
            System.out.print("X\t");
        }
        else{
            System.out.print(String.format("%s\t", roll1.getPrintableScore()));
        }
        if(roll2.getScore() == 10){
            System.out.print("X\t");
        }
        else if(isSpare()){
            System.out.print("/\t");
        }
        else{
            System.out.print(String.format("%s\t", roll2.getPrintableScore()));
        }
        if(roll3 != null){
            if(roll3.getScore() == 10){
                System.out.print("X\t");
            }
            else{
                System.out.print(String.format("%s\t", roll3.getPrintableScore()));
            }
        }
    }

    @Override
    public void printFormattedScore(){
        System.out.print(String.format("%d\t\t\t", getScore()));
    }
}
