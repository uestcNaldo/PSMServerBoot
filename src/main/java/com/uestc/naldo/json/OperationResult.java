package com.uestc.naldo.json;


import com.alibaba.fastjson.annotation.JSONType;

@JSONType(orders = {"opt","code","message"})
public class OperationResult {

    private String opt;

    private Integer code;

    private String message;

    public OperationResult(){}

    public String getOpt() {
        return opt;
    }

    public void setOpt(String opt) {
        this.opt = opt;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
