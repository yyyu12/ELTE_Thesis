package hu.elte.inf.backend.common.exceptionEnd;

public class WishlistItemAlreadyExists extends ServiceException{

    public WishlistItemAlreadyExists() {
        super();
    }

    public WishlistItemAlreadyExists(String message) {
        super(message);
    }

    public WishlistItemAlreadyExists(String message, Throwable cause) {
        super(message, cause);
    }

    public WishlistItemAlreadyExists(Throwable cause) {
        super(cause);
    }

    public WishlistItemAlreadyExists(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
