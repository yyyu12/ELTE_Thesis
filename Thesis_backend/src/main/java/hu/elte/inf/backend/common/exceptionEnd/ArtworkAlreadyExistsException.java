package hu.elte.inf.backend.common.exceptionEnd;

public class ArtworkAlreadyExistsException extends ServiceException{
    public ArtworkAlreadyExistsException() {
        super();
    }

    public ArtworkAlreadyExistsException(String message) {
        super(message);
    }

    public ArtworkAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArtworkAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    public ArtworkAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
