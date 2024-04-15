package hu.elte.inf.backend.common.exceptionEnd;

public class ArtistExistException extends ServiceException{
    public ArtistExistException() {
        super();
    }

    public ArtistExistException(String message) {
        super(message);
    }

    public ArtistExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArtistExistException(Throwable cause) {
        super(cause);
    }

    public ArtistExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
