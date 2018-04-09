package com.ye.sell.utils;

import com.ye.sell.enums.ResultEnum;
import com.ye.sell.vo.ResultVo;

public class ResultVoUtils {

    public static ResultVo success(Object object) {
        ResultVo<Object> resultVo = new ResultVo<>();
        resultVo.setCode(ResultEnum.SUCCESS.getCode());
        resultVo.setMsg(ResultEnum.SUCCESS.getMsg());
        resultVo.setData(object);

        return resultVo;
    }

    public static ResultVo success() {
        return success(null);
    }

    public static ResultVo error() {
        ResultVo<Object> resultVo = new ResultVo<>();
        resultVo.setCode(ResultEnum.ERROR.getCode());
        resultVo.setMsg(ResultEnum.ERROR.getMsg());

        return resultVo;
    }
}
