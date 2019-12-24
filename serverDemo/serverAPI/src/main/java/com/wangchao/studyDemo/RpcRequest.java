package com.wangchao.studyDemo;

import java.io.Serializable;

/**
 * 2019/12/24
 * by Wang Chao
 */
public class RpcRequest implements Serializable {
    private String clazz;

    private String method;

    private Object args[];

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}
