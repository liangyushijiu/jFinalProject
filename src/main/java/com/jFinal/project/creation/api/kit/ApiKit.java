package com.jFinal.project.creation.api.kit;

import com.jFinal.project.creation.api.constant.ApiConstant;
import com.jFinal.project.creation.api.model.ApiResp;

/**
 * Api接口调用类
 * @author 凉雨时旧
 */
public class ApiKit {
    private ApiKit () {}

    /**
     * 方法不存在
     * @return apiResp
     */
    public static ApiResp getMethodError() {
        return new ApiResp().setSuccess(false).setCode(ApiConstant.CODE_METHOD_ERROR).setMsg(ApiConstant.MSG_METHOD_ERROR);
    }

    public static ApiResp getMethodHandlerError() {
        return new ApiResp().setSuccess(false).setCode(ApiConstant.CODE_METHOD_HANDLER_ERROR).setMsg(ApiConstant.MSG_METHOD_HANDLER_ERROR);
    }



}
