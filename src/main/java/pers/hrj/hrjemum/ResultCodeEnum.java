package pers.hrj.hrjemum;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.util.stream.Stream;

/**
 * @Author: HuangRenjie
 * @Date: 2023/1/23 22:49
 * @Description:
 */
@Getter
public enum ResultCodeEnum {
    SUCCESS("0000", "操作成功"),
    FAILED("0001", "操作失败"),
    ;

    private String code;
    private String msg;

    ResultCodeEnum(String code, String msg) {
        this.msg = msg;
        this.code = code;
    }
    public String msg() {
        return this.msg;
    }

    public String code() {
        return this.code;
    }


    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static ResultCodeEnum fromCode(Integer code) {
        return Stream.of(ResultCodeEnum.values()).filter(e -> e.code.equals(code)).findFirst().orElse(null);
    }
}
