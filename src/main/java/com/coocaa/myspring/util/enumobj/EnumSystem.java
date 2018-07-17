package com.coocaa.myspring.util.enumobj;

public enum EnumSystem {

    SYSTEM_SUCCESS("success", "1000", "操作成功"),

    SYSTEM_FAILED("fail", "2000", "操作失败"),

    ILLEGAL("fail", "2001", "参数缺失或不合法"),

    SYSTEM_EXCEPTION("fail", "2002", "系统繁忙,请稍后再试"),

    ILLEGAL_PLATFORM("fail", "2003", "平台不存在或已停用"),

    SESSION_OUT("sessionOut", "9999", "您还未登录,请先登录"),;


    private String result;
    private String code;
    private String msg;

    EnumSystem(String result, String code, String msg) {
        this.result = result;
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public static boolean isSuccess(String code) {
        if (SYSTEM_SUCCESS.code.equals(code)) {
            return true;
        }
        return false;
    }
}
