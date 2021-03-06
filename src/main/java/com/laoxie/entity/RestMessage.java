package com.laoxie.entity;

/**
 * 返回信息
 *
 * @author xiedongting
 */
public class RestMessage {

    private int code;

    private String message;

    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public  RestMessage setMessage(String message) {
        this.message = message;
        return null;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public RestMessage(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public RestMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

}