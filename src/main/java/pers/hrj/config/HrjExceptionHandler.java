package pers.hrj.config;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pers.hrj.hrjemum.ResultCodeEnum;
import pers.hrj.model.vo.SimpleRestVo;

@RestControllerAdvice
public class HrjExceptionHandler {
    @ExceptionHandler(value = HrjException.class)
    public SimpleRestVo handleJobException(Exception e){
        e.printStackTrace();
        return SimpleRestVo.failedVo(e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public SimpleRestVo handleException(Exception e){
        e.printStackTrace();
        return SimpleRestVo.failedVo(ResultCodeEnum.FAILED);
    }
}
