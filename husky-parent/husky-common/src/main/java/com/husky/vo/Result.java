package com.husky.vo;

import com.husky.enums.ResultEnum;

import lombok.Data;

@Data
public class Result<T> {

    /**
     * 错误码
     */
    private String code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 具体的内容
     */
    private T data;

    /**
     * 成功时候的调用
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(data);
    }

    /**
     * 根据返回的状态对象， 构建返回结果
     * @param resultEnum
     * @param <T>
     * @return
     */
    public static <T> Result<T> build(ResultEnum resultEnum) {
        return new Result<>(resultEnum);
    }

    /**
     * 根据 code， 和  msg 构建返回结果
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> Result<T> build(String code, String msg) {
        return new Result<T>(code, msg);
    }

    /**
     * 根据 code， 和  msg, 和 data 构建返回结果
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> Result<T> build(String code, String msg, T data) {
        return new Result<T>(code, msg, data);
    }

    /**
     * 失败的调用
     * @param codeMsg
     * @param <T>
     * @return
     */
//    public static <T> Result<T> error(String codeMsg) {
//        return new Result<>(codeMsg);
//    }

    /**
     * 失败的调用 将返回结果传入
     * @param data
     * @param <T>
     * @return
     */
//    public static <T> Result<T> error(T data) {
//        return new Result<>(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMsg(), data);
//    }

    private Result(T data) {
        this.code = ResultEnum.SUCCESS.getCode();
        this.msg = ResultEnum.SUCCESS.getMsg();
        this.data = data;
    }

//    private Result(String msg) {
//        this.code = ResultEnum.ERROR.getCode();
//        this.data = null;
//        this.msg = msg;
//    }

    private Result(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
    }

    private Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Result(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
