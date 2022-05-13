package com.springbootdemo.exception;

public class BusinessException extends Exception {
    private final static String defaultMsg = "this is a Business Exception";
    public BusinessException(){
        super(defaultMsg);
    }
    public BusinessException(String msg){
        super(msg);
    }
    public BusinessException(String msg,Throwable throwable){
        super(msg,throwable);
    }
}
