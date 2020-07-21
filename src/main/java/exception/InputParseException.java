package exception;

public class InputParseException extends RuntimeException {

    public InputParseException(String msg){
        super(msg);
    }

    public InputParseException(String msg, Exception e){
        super(msg, e);
    }

}
