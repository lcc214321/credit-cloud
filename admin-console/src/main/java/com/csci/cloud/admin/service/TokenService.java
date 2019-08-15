package com.csci.cloud.admin.service;


import com.csci.cloud.admin.data.vo.RefreshTokenReqVo;
import com.csci.cloud.admin.data.vo.RefreshTokenRespVo;
import com.csci.cloud.admin.jooq.tables.pojos.TUserRefreshToken;
import java.util.Date;

/**
 */
public interface TokenService {


  /**
   * 创建refreshToken.
   * @param tenantId 租户Id
   * @param userId 用户ID
   * @param token token信息
   * @param expiredAt 过期时间
   * @return
   */
  TUserRefreshToken create(Integer tenantId, Integer userId, String token, Date expiredAt);


  /**
   * 刷新token.
   * @param userId 用户ID
   * @param refreshTokenReqVo
   * @return
   */
  RefreshTokenRespVo refreshToken(Integer userId, RefreshTokenReqVo refreshTokenReqVo);


}
