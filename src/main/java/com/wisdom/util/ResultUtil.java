package com.wisdom.util;


import com.wisdom.vo.ReturnResult;

import java.util.Objects;

/**
 * @author zcw
 * @date 2019-09-12
 */
public class ResultUtil {

    public static final String SUCCESS = "1";
    public static final String FAIL = "0";

    public static <T> ReturnResult<T> GenSuccessResultDto(T t) {
        ReturnResult<T> result = new ReturnResult<>();
        if (Objects.nonNull(t)) {
            result.setObj(t);
        }
        result.setMessage("成功");
        result.setStatus(ResultUtil.SUCCESS);
        return result;
    }

    public static <T> ReturnResult<T> GenSuccessMessageDto(T t, String message) {
        ReturnResult<T> result = new ReturnResult<>();
        if (Objects.nonNull(t)) {
            result.setObj(t);
        }
        result.setMessage(message);
        result.setStatus(ResultUtil.SUCCESS);
        return result;
    }

    public static <T> ReturnResult<T> GenSuccessMessageDto(String message) {
        return GenSuccessMessageDto(null, message);
    }

    public static <T> ReturnResult<T> GenFailResultDto(T t, String message){
        ReturnResult<T> result = new ReturnResult<>();
        if (Objects.nonNull(t)) {
            result.setObj(t);
        }
        result.setMessage(message);
        result.setStatus(ResultUtil.FAIL);
        return result;
    }

    public static <T> ReturnResult<T> GenFailMessageDto(String message){
        return GenFailResultDto(null, message);
    }

    public static <T> ReturnResult<T> GenFailStatusMessageDto(String fail,String message){
        ReturnResult<T> result = new ReturnResult<>();
        result.setMessage(message);
        result.setStatus(fail);
        return result;
    }



}
