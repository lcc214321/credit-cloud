package com.csci.cloud.admin.utils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {
  SUCCESS("ACC00000", "请求成功"),
  INVALID_REQ_PARAMETER("ACC00011", "请求参数无效"),
  OPERATOR_ERROR("ACC00012", "操作失败."),
  DATE_FORMAT_ERROR("ACC00013", "日期格式错误.yyyy-MM-dd"),
  INTERNAL_SERVER_ERROR("ACC00014", "服务器异常,请稍后再试，如有疑问请联系客服"),


  AUTH_EXPIRE_ERROR("ACC00001","会话超时,请重新登录"),
  AUTH_ERROR("ACC00002","当前会话信息错误,请重新登录"),
  AUTH_SIGNATURE_ERROR("ACC00003","当前会话Authorization信息非法."),
  AUTH_HEADER_NOT_BLANK("ACC00004","Authorization header不能为空"),

  USER_PASSWORD_NOT_CORRECT("ACC01201", "用户名或者密码不正确"),
  USER_ALREADY_EXIST("ACC01202","用户已存在"),
  USER_NOT_EXIST("ACC01203","用户不存在"),
  USER_INFORMATION_NOT_CONCURRENCY("ACC01204","用户信息不完整,请联系客服"),
  USER_REFRESH_TOKEN_ERROR("ACC01205","refresh token 不正确"),
  USER_REFRESH_TOKEN_EXPIRED("ACC01206","refresh token 已经过期, 请重新登录."),
  USER_LOGOUT_ERROR("ACC01207","用户注销失败"),
  USER_NOT_MAPPING("ACC01208","当前用户信息不匹配"),
  ILLEGAL_LOGIN_WITH_OUT_CAPTCHA("ACC01209","请输入验证码"),
  INVALID_CAPTCHA_CHALLENGE("ACC01210","无效的验证信息"),

  TENANT_EXIST("ACC013101","公司名称已经存在"),
  TENANT_SERVER_STATUS_ERROR("ACC013102","请求服务状态错误.参考值为 0:开启 1：关闭");



  private String code;
  private String desc;

}
