package hu.elte.inf.backend.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Exception extends RuntimeException {
    private String msg;
    private int code = 500;

    public Exception(String msg){
        super(msg);
        this.msg = msg;
    }
}
