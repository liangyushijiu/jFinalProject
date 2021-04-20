package com.jFinal.project.creation.api.model;

import com.jFinal.project.creation.api.constant.ApiConstant;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回报文
 * @author 凉雨时旧
 */
public class ApiResp {

    private boolean success = true;
    private int code = ApiConstant.CODE_SUCCESS;
    private String msg = ApiConstant.MSG_SUCCESS;
    private Map<String, Map<String,Object>> data = new HashMap<>();

    public ApiResp() { }

    public ApiResp( Map<String, Object> data) {
        this.data.put("data",data);
    }

    public ApiResp setCode(int code) {
        this.code = code;
        return this;
    }

    public ApiResp setMsg(String msg){
        this.msg = msg;
        return this;
    }

    public ApiResp setSuccess(boolean success) {
        this.success = success;
        return this;
    }
}
