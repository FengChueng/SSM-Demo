package com.zyl.ssm.exception;

public class CustomException extends RuntimeException{
    private static final long serialVersionUID = 7578567483018388777L;
    /**
     * 错误码
     */
    private int errorCode;
    public CustomException(int errorCode,String msg){
        super(msg);
        this.errorCode = errorCode;
    }
    public int getErrorCode() {
        return errorCode;
    }

}
