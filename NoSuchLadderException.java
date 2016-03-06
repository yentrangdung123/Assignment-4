/*
    Project   :  Assignment 4
    Written by:  Bharat Kulkarni - bsk524
    			 Dung Le - dkl524
    Completed :	 03/06/16
 */

package assignment4;

public class NoSuchLadderException extends Exception
{
    private static final long serialVersionUID = 1L;

    public NoSuchLadderException(String message)
    {
        super(message);
    }

    public NoSuchLadderException(String message, Throwable throwable)
    {
        super(message, throwable);
    }
}
