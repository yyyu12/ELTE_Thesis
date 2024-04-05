package hu.elte.inf.backend.common;

public class Preconditions {
    public static void checkNotNull(Object object) {
        if (object == null){
            throw new Exception("Object can not be null");
        }
    }
}