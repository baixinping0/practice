package com.baixinping.cache.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {

    public static final int STATUS_SUCCEED = 200;
    public static final int STATUS_FAIL_NOT_RESOURCES = 404;
    public static final int STATUS_ERROR = 504;

    public static final String MESSAGE_OK = "ok";
    private int status;
    private String message;
    private List<T> data;

    public static Response getSucceesResponse(){
        return new Response(STATUS_SUCCEED, MESSAGE_OK, null);
    }

}
