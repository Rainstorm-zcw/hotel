package com.wisdom.vo;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReturnResult<T> {

    private String status;

    private String message;

    private T obj;

}
