package com.code.common.lang;

import lombok.Data;

import java.io.Serializable;
import java.sql.ResultSet;

@Data
public class Result implements Serializable {

    private int code;
    private String msg;
    private Object data;

    public static Result success(Object data) {

        return success(200, "操作成功!", data);

    }

    public static Result success(String msg, Object data) {

        return success(200, msg, data);

    }

    public static Result success(int code, String msg, Object data) {

        Result ret = new Result();
        ret.setCode(code);
        ret.setMsg(msg);
        ret.setData(data);
        return ret;

    }

    public static Result fail(String msg) {

        return fail(400, msg, null);

    }

    public static Result fail(String msg, Object data) {

        return fail(400, msg, data);

    }

    public static Result fail(int code, String msg, Object data) {

        Result ret = new Result();
        ret.setCode(code);
        ret.setMsg(msg);
        ret.setData(data);
        return ret;

    }
}
