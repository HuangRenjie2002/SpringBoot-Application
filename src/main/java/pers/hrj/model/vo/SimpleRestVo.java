package pers.hrj.model.vo;

import lombok.Data;
import pers.hrj.hrjemum.ResultCodeEnum;

import java.io.Serializable;

/**
 * @Author: HuangRenjie
 * @Date: 2023/1/23 22:51
 * @Description:
 */
@Data
public class SimpleRestVo<T> implements Serializable {

    private String code;

    private String message;

    private T data;

    public SimpleRestVo() {
    }

    public SimpleRestVo(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static SimpleRestVo failedVo() {
        return failedVo(ResultCodeEnum.FAILED.msg());
    }

    public static SimpleRestVo failedVo(String message) {
        return new SimpleRestVo(ResultCodeEnum.FAILED.code(), message, null);
    }

    public static <T> SimpleRestVo failedVo(String message, T data) {
        return new SimpleRestVo(ResultCodeEnum.FAILED.code(), message, data);
    }

    public static SimpleRestVo failedVo(String code, String message) {
        return new SimpleRestVo(code, message, null);
    }

    public static <T> SimpleRestVo failedVo(String code, String message, T data) {
        return new SimpleRestVo(code, message, data);
    }

    public static SimpleRestVo failedVo(ResultCodeEnum codeEnum) {
        return new SimpleRestVo(codeEnum.code(), codeEnum.msg(), null);
    }

    public static <T> SimpleRestVo failedVo(ResultCodeEnum codeEnum, T data) {
        return new SimpleRestVo(codeEnum.code(), codeEnum.msg(), data);
    }

    public static SimpleRestVo successVo() {
        return successVo(null);
    }

    public static <T> SimpleRestVo successVo(T data) {
        return new SimpleRestVo(ResultCodeEnum.SUCCESS.code(), ResultCodeEnum.SUCCESS.msg(), data);
    }

}