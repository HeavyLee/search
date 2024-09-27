package com.assess.search.vo;

import com.assess.search.enums.RspCode;
import lombok.Data;

@Data
public class Result<T> {

    public Result(RspCode rspCode, T data) {
        this.code = rspCode.getCode();
        this.msg = rspCode.getMsg();
        this.data = data;
    }

    public Result(RspCode rspCode) {
        this.code = rspCode.getCode();
        this.msg = rspCode.getMsg();
    }

    private String code;

    private String msg;

    private T data;
}
