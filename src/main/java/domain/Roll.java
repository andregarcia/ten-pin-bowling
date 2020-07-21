package domain;

import exception.InvalidArgumentException;

public class Roll {

    private String player;

    private Integer score;

    private Boolean isFault;

    public Roll(String player, Integer score, Boolean isFault) throws Exception {
        if(isFault && score != 0){
            throw new InvalidArgumentException("isFault=true is not compatible with score != 0");
        }
        if(score > 10){
            throw new InvalidArgumentException("score cannot be greater than 10");
        }
        if(score < 0){
            throw new InvalidArgumentException("score cannot be less than 0");
        }
        this.player = player;
        this.score = score;
        this.isFault = isFault;
    }

    public String getPlayer() {
        return player;
    }

    public Integer getScore() {
        return score;
    }

    public String getPrintableScore(){
        if(isFault){
            return "F";
        }
        return score.toString();
    }

    public Boolean getFault() {
        return isFault;
    }

}
