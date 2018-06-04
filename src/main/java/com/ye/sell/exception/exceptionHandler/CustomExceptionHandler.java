package com.ye.sell.exception.exceptionHandler;

import com.ye.sell.exception.SellException;
import com.ye.sell.utils.ResultVoUtils;
import com.ye.sell.vo.ResultVo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RestController
public class CustomExceptionHandler {

    @ExceptionHandler(SellException.class)
    public ResultVo sellExceptionHandler(SellException e) {

        return ResultVoUtils.error(e.getCode(), e.getMessage());
    }
}
