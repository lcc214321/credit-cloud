package com.csci.cloud.auth.server.exception.handler;

import com.csci.cloud.auth.server.data.vo.ResponseVo;
import com.csci.cloud.auth.server.exception.ArgumentInvalidResult;
import com.csci.cloud.auth.server.exception.AuthBizException;
import com.csci.cloud.core.common.ErrorEnums.CommonErrorEnum;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    @Resource
    protected Environment environment;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseVo> MethodArgumentNotValidHandler(HttpServletRequest request,
        MethodArgumentNotValidException ex) throws Exception {

        log.warn("handleException请求ex:{}", ex);
        HttpStatus status = HttpStatus.BAD_REQUEST;
        List<String> errorFields = ex.getBindingResult().getFieldErrors().stream()
            .map(fieldError -> String
                .format("%s should %s %s", fieldError.getField(), fieldError.getCode(),
                    fieldError.getDefaultMessage()))
            .collect(Collectors.toList());

        List<String> errorTypes = ex.getBindingResult().getGlobalErrors().stream()
            .map(fieldError -> fieldError.getDefaultMessage())
            .collect(Collectors.toList());

        ResponseVo rv = ResponseVo
            .error("参数校验不通过: " + String.join("\n", CollectionUtils.union(errorFields, errorTypes)),CommonErrorEnum.INVALID_REQ_PARAMETER.getCode());
        return new ResponseEntity(rv, status);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler({AuthBizException.class})
    protected ResponseVo handlerAuthorException(AuthBizException e) {
        log.warn("handle auth exception 请求e:{}", e.getMessage(),e);
        return ResponseVo.error(e.getErrorMessage(),e.getErrorCode());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    @ExceptionHandler({Exception.class})
    protected ResponseVo handlerException(Exception e) {
        log.warn("handle exception 请求e:{}", e.getMessage(),e);
        return ResponseVo.error(CommonErrorEnum.INTERNAL_SERVER_ERROR.getDesc(),CommonErrorEnum.INTERNAL_SERVER_ERROR.getCode());
    }
}
