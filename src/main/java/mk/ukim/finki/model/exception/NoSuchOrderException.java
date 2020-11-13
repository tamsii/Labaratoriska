package mk.ukim.finki.model.exception;

public class NoSuchOrderException extends RuntimeException {

    public NoSuchOrderException(){
        super("No Such Order Exception");
    }
}
