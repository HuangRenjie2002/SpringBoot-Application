package pers.hrj.config;

import lombok.extern.slf4j.Slf4j;
import pers.hrj.hrjemum.ResultCodeEnum;

@Slf4j
public class HrjException  extends RuntimeException{
    protected String code;
    protected String message;

    public HrjException(){

    }

    public HrjException(ResultCodeEnum codeEnum){
        super(codeEnum.msg());
        this.code = codeEnum.code();
        this.message = codeEnum.msg();
        log.error(this.code  + ":" + this.message);
    }

    public HrjException(ResultCodeEnum codeEnum, String msg){
        super(msg);
        this.code = codeEnum.code();
        this.message = msg;
        log.error(this.code  + ":" + msg);
    }
}
