package com.oa.yk.ykoa.model;

/**
 * Created by 78560 on 2017/8/1.
 */

public class BaseResponse<T> {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
