package com.kevindeemo.handler;

import com.kevindeemo.VO.ResultVO;
import com.kevindeemo.config.ProjectUrlConfig;
import com.kevindeemo.exception.ResponseBankException;
import com.kevindeemo.exception.SellAuthorizeException;
import com.kevindeemo.exception.SellException;
import com.kevindeemo.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class SellExceptionHandler {

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

//    拦截登录异常，并进行跳转
    @ExceptionHandler(value = SellAuthorizeException.class)
    public ModelAndView handlerAuthorizeException(){
        return new ModelAndView("redirect:"+projectUrlConfig.getFixedUrl());
    }

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handlerSellerException(SellException e){
        return ResultVOUtil.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = ResponseBankException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public void handlerResponseBankException(SellException e){
    }

}
