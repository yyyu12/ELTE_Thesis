package hu.elte.inf.backend.common.exceptionEnd;

public class ArtworkNotFoundException extends ServiceException{
    public ArtworkNotFoundException() {
        super();
    }

    public ArtworkNotFoundException(String message) {
        super(message);
    }

    public ArtworkNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArtworkNotFoundException(Throwable cause) {
        super(cause);
    }

    public ArtworkNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
