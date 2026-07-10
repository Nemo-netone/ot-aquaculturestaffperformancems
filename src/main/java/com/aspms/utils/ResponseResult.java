package com.aspms.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 响应结果包装类
 */
@Data
@AllArgsConstructor
public class ResponseResult {

    // 操作结果
    private boolean status;
    // 返回消息
    private String message;
    // 返回数据
    private Object data;

    /**
     * 构造响应结果对象
     * @param data
     * @return
     */
    public static ResponseResult build(boolean status, String message){
        return new ResponseResult(status, message, null);
    }

    /**
     * 构造响应结果对象
     * @param data
     * @return
     */
    public static ResponseResult build(boolean status, String message, Object data){
        return new ResponseResult(status, message, data);
    }

}
