package assignment4;

public class TooFewInputException extends Exception{

	private static final long serialVersionUID = 1L;

	public TooFewInputException(String message)
    {
        super(message);
    }
    public TooFewInputException(String message, Throwable throwable)
    {
        super(message, throwable);
    }

}
