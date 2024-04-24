package hu.elte.inf.backend.common.exceptionEnd;

public class CartItemNotFound  extends ServiceException{
    public CartItemNotFound() {
        super();
    }

    public CartItemNotFound(String message) {
        super(message);
    }

    public CartItemNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public CartItemNotFound(Throwable cause) {
        super(cause);
    }

    public CartItemNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
