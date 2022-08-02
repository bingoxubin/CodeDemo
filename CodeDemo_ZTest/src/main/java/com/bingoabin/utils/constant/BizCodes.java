package com.bingoabin.utils.constant;

import java.util.HashMap;
import java.util.Map;

public enum BizCodes {
    SUCCESS(0, "操作成功"),
    ERROR(1, "失败"),
    INFO(1000, "提示"),
    BIZ_SELECT(1001, "请选择业务分组"),
    SSO_ERROR(1002, "请重新登录"),
    LAYER_SELECT(1003, "该数仓分层没有命名规则"),
    PARAM_ERROR(2000, "参数错误"),
    ADD_ERROR(3000, "保存失败"),
    DEL_ERROR(3001, "删除失败"),
    UPD_ERROR(3002, "更新失败"),
    BA_ERROR(4001, "BA认证失败"),
    AUTH_ERROR(4002, "没有权限"),
    PACK_INVALID_ERROR(5001, "发布包内容失效"),
    ;

    private static class Inner {
        private static final Map<Integer, BizCodes> CODES = new HashMap<>();
    }

    private final int code;
    private final String name;

    BizCodes(int code, String name) {
        this.code = code;
        this.name = name;
        BizCodes exist = Inner.CODES.putIfAbsent(code, this);
        if (exist != null) {
            throw new IllegalStateException("BizCodes duplicated:" + code);
        }
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "BizCodes{" +
                "code=" + code +
                ", name='" + name + '\'' +
                "} " + super.toString();
    }
}
