package exceptions;

public class TransactionTypeNotFoundException extends RuntimeException{
    public TransactionTypeNotFoundException(String message){
        super(message);
    }
}
