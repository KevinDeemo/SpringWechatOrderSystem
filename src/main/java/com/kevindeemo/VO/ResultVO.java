package com.kevindeemo.VO;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/*
* Http请求返回的最外层对象
* */
@Data
public class ResultVO<T> implements Serializable {

//    序列化id保持唯一
    private static final long serialVersionUID = -2048906403473982942L;

    // 错误码
    private Integer code;

    // 提示信息
    private String msg;

    // 返回的具体内容
    private T data;
}
