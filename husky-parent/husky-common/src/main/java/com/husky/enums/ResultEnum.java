package com.husky.enums;
public enum ResultEnum {

    SUCCESS("000", "成功"),
    SYS_ERROR("001", "系统错误"),
    LOGIN_ERROR("002", "登录失败"),
    WAIT("403", "无权限访问");

    private String code;

    private String msg;

    ResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
