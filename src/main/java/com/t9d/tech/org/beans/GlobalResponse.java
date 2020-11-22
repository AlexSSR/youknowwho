package com.t9d.tech.org.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * ArcResponse : 通用响应对象, 封装数据如下
 * <p>
 * {"meta":{"code":200,"message":"操作成功"},"data":[{....},{....}]}
 *
 * @author chenxi
 * @since 2020-11-10
 */

@Getter
@Setter
public class GlobalResponse<T> {
    private Meta meta;

    private T data;

    public GlobalResponse(T data) {
        this.meta = new Meta(HttpStatus.OK.value(), "操作成功");
        this.data = data;
    }

    public GlobalResponse(Integer code, String message) {
        this.meta = new Meta(code, message);
    }

    public GlobalResponse(Integer code, String message, T data) {
        this.meta = new Meta(code, message);
        this.data = data;
    }

    @Data
    @AllArgsConstructor
    public static class Meta {

        public static final Integer SUCCESS_CODE = 200;

        private Integer code;

        private String message;
    }
}
