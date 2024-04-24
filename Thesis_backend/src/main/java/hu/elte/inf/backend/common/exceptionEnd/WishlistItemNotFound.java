package hu.elte.inf.backend.common.exceptionEnd;

public class WishlistItemNotFound extends ServiceException{

    public WishlistItemNotFound() {
        super();
    }

    public WishlistItemNotFound(String message) {
        super(message);
    }

    public WishlistItemNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public WishlistItemNotFound(Throwable cause) {
        super(cause);
    }

    public WishlistItemNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
