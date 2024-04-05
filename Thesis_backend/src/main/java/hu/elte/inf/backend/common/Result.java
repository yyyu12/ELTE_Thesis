package hu.elte.inf.backend.common;

import org.apache.http.HttpStatus;
import java.util.HashMap;
import java.util.Objects;

public class Result extends HashMap<String, Object>  {
    public Result() {
        put("code", HttpStatus.SC_OK);
        put("msg", "success");
    }

    public Result put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public static Result ok(String msg) {
        Result r = new Result();
        r.put("msg", msg);
        return r;
    }

    public static Result error(int code, String msg){
        Result r = new Result();
        r.put("code",code);
        r.put("msg",msg);
        return r;
    }

    public static Result error(String msg) {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
    }
}
