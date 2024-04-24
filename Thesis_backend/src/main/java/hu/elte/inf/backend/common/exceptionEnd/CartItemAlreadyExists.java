package hu.elte.inf.backend.common.exceptionEnd;

public class CartItemAlreadyExists extends ServiceException{

    public CartItemAlreadyExists() {
        super();
    }

    public CartItemAlreadyExists(String message) {
        super(message);
    }

    public CartItemAlreadyExists(String message, Throwable cause) {
        super(message, cause);
    }

    public CartItemAlreadyExists(Throwable cause) {
        super(cause);
    }

    public CartItemAlreadyExists(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
