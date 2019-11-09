package com.chat.tools;

import com.chat.model.Result;

public class ResultUtil {
    public static Result success(Object object, String msg) {
        Result result = new Result();
        result.setCode(0);
        result.setMsg(msg);
        result.setData(object);
        return result;
    }

    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(0);
        result.setMsg("Thành công");
        result.setData(object);
        return result;
    }

    public static Result success() {
        return success(null);
    }

    public static Result success(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
