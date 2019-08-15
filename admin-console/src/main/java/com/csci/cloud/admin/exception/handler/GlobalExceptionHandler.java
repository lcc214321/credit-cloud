package com.csci.cloud.admin.exception.handler;

import com.csci.cloud.admin.captcha.exception.CaptchaClientException;
import com.csci.cloud.admin.data.vo.ResponseVo;
import com.csci.cloud.admin.exception.AppBizException;
import com.csci.cloud.admin.exception.ArgumentInvalidResult;
import com.csci.cloud.admin.exception.AuthenticationException;
import com.csci.cloud.admin.exception.InternalBizException;
import com.csci.cloud.admin.exception.UserBizException;
import com.csci.cloud.admin.utils.ErrorCode;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

  @Resource
  protected Environment environment;

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  public ResponseEntity<ResponseVo> MethodArgumentNotValidHandler(HttpServletRequest request,
      MethodArgumentNotValidException exception) throws Exception {

    List<ArgumentInvalidResult> invalidArguments = new ArrayList<>();
    for (FieldError error : exception.getBindingResult().getFieldErrors()) {
      ArgumentInvalidResult invalidArgument = new ArgumentInvalidResult();
      invalidArgument.setDefaultMessage(error.getDefaultMessage());
      invalidArgument.setField(error.getField());
      invalidArgument.setRejectedValue(error.getRejectedValue());
      invalidArguments.add(invalidArgument);
    }

   //TODO

    ResponseVo response = ResponseVo.error(invalidArguments.toString(), ErrorCode.INVALID_REQ_PARAMETER.getCode());

    return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  @ExceptionHandler({UserBizException.class})
  protected ResponseEntity<ResponseVo> handlerUserException(UserBizException e) {
    log.warn("handle biz exception 请求e:{}", e.getMessage(),e);
    ResponseVo response = ResponseVo.error(e.getReason(),e.getErrorCode());
    return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  @ExceptionHandler({CaptchaClientException.class})
  protected ResponseEntity<ResponseVo> handlerCaptchaClientException(CaptchaClientException e) {
    log.warn("handle captcha client exception 请求e:{}", e.getMessage(),e);
    ResponseVo response = ResponseVo.error(ErrorCode.INVALID_CAPTCHA_CHALLENGE.getDesc(),ErrorCode.INVALID_CAPTCHA_CHALLENGE.getCode());
    return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
  }


  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  @ExceptionHandler({AppBizException.class})
  protected ResponseEntity<ResponseVo> handlerApiException(AppBizException e) {
    log.warn("handle api exception 请求e:{}", e.getMessage(),e);
    ResponseVo response = ResponseVo.error(e.getReason(),e.getErrorCode());
    return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
  }


  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ResponseBody
  @ExceptionHandler({InternalBizException.class})
  protected ResponseEntity<ResponseVo> handlerUserException(InternalBizException e) {
    log.warn("handle biz exception 请求e:{}", e.getMessage(),e);
    ResponseVo response = ResponseVo.error(e.getReason(),e.getErrorCode());
    return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
