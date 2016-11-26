package com.hickey.tool.base;

/**
 * Created by jb on 2016/11/25.
 */

import rx.functions.Func1;

/**
 * 返回值公共字段的处理，包括业务异常的抛出
 *
 * @param <T>
 */
public class BaseHttpFunc<T> implements Func1<BaseResponse<T>, T> {

    @Override
    public T call(BaseResponse<T> baseResponse) {
        int statue = baseResponse.getCode();
        String msg = baseResponse.getMsg();
        T data = baseResponse.getData();
        if (statue != 1) {
            throw new HttpServiceException(statue, msg);
        } else if (statue == 1000) {
            throw new HttpServiceException(statue, "身份信息过期,请重新登录");
        }
        return data;
    }
}