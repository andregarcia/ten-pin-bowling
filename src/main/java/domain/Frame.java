package domain;

import exception.InvalidArgumentException;

public class Frame {

    protected Integer number;

    protected Roll roll1;

    protected Roll roll2;

    protected Integer score;

    protected Frame next;

    protected Frame previous;

    public Frame(Integer number, Roll roll1, Roll roll2) {
        this.number = number;
        this.roll1 = roll1;
        this.roll2 = roll2;
    }

    public Frame(Integer number, Roll roll1) {
        this.number = number;
        this.roll1 = roll1;
    }

    public Integer getNumber() {
        return number;
    }

    public Roll getRoll1() {
        return roll1;
    }

    public Roll getRoll2() {
        return roll2;
    }

    public void setRoll2(Roll roll2) {
        this.roll2 = roll2;
    }

    public Frame getNext() {
        return next;
    }

    public void setNext(Frame next) throws InvalidArgumentException {
        this.next = next;
    }

    public Frame getPrevious() {
        return previous;
    }

    public void setPrevious(Frame previous) {
        this.previous = previous;
    }

    public boolean isStrike(){
        return roll1.getScore() == 10;
    }

    public boolean isSpare(){
        if(roll2 == null) return false;
        return roll1.getScore() + roll2.getScore() == 10;
    }

    public Integer getScore(){
        if(score == null){
            score = this.calculateScore();
        }
        return score;
    }

    protected Integer calculateScore(){
        Integer thisFrameIsolatedScore;
        if(isStrike() && next != null) {
            Roll nextRoll1 = next.roll1;
            Roll nextRoll2 = next.roll2 != null  ? next.roll2 : (next.next != null ? next.next.roll1 : null);
            thisFrameIsolatedScore = roll1.getScore() + nextRoll1.getScore() + (nextRoll2 != null ? nextRoll2.getScore() : 0);
        }
        else if(isSpare() && next != null) {
            Roll nextRoll1 = next.roll1;
            thisFrameIsolatedScore = roll1.getScore() + roll2.getScore() + nextRoll1.getScore();
        }
        else {
            thisFrameIsolatedScore = roll1.getScore() + roll2.getScore();
        }
        Integer previousFrameScore = previous != null ? previous.getScore() : 0;
        return previousFrameScore + thisFrameIsolatedScore;
    }

    public void printFormattedNumber(){
        System.out.print(String.format("%d\t\t", getNumber()));
    }

    public void printFormattedPinfalls(){
        if(isStrike()){
            System.out.print("\tX\t");
        }
        else if(isSpare()){
            System.out.print(String.format("%s\t/\t", getRoll1().getPrintableScore()));
        }
        else{
            System.out.print(String.format("%s\t%s\t", getRoll1().getPrintableScore(), getRoll2().getPrintableScore()));
        }
    }

    public void printFormattedScore(){
        System.out.print(String.format("%d\t\t", getScore()));
    }

}
