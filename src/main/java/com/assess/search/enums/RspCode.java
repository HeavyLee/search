package com.assess.search.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RspCode {

    SUCCESS("000000", "success"),
    PARAMS_CHECK_ERR("000001", "参数校验异常"),


    ;

    private String code;

    private String msg;
}
