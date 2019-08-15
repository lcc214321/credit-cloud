package com.csci.cloud.admin.service.impl;

import com.csci.cloud.admin.auth.UserTokenFactory;
import com.csci.cloud.admin.config.JwtProps;
import com.csci.cloud.admin.dao.ExUserRefreshTokenDao;
import com.csci.cloud.admin.data.vo.RefreshTokenReqVo;
import com.csci.cloud.admin.data.vo.RefreshTokenRespVo;
import com.csci.cloud.admin.exception.AppBizException;
import com.csci.cloud.admin.exception.UserBizException;
import com.csci.cloud.admin.jooq.Tables;
import com.csci.cloud.admin.jooq.tables.daos.TUserRefreshTokenDao;
import com.csci.cloud.admin.jooq.tables.pojos.TUserRefreshToken;
import com.csci.cloud.admin.jooq.tables.records.TUserRefreshTokenRecord;
import com.csci.cloud.admin.service.TokenService;
import com.csci.cloud.admin.utils.ErrorCode;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.InsertResultStep;
import org.jooq.TransactionalCallable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 刷新token服务.
 * @author ben
 */
@Service
public class UserSessionTokenServiceImpl implements TokenService {

  @Autowired
  TUserRefreshTokenDao userRefreshTokenDao;
  @Autowired
  UserTokenFactory tokenFactory;

  @Autowired
  private ExUserRefreshTokenDao refreshTokenDao;

  @Autowired
  private DSLContext dslContext;

  @Autowired
  private JwtProps settings;


  @Override
  public TUserRefreshToken create(Integer tenantId, Integer userId, String token, Date expiredAt) {
      dslContext
        .insertInto(Tables.T_USER_REFRESH_TOKEN)
        .set(Tables.T_USER_REFRESH_TOKEN.TENANT_ID, tenantId)
        .set(Tables.T_USER_REFRESH_TOKEN.EXPIRED_AT, Timestamp.from(expiredAt.toInstant()))
        .set(Tables.T_USER_REFRESH_TOKEN.USER_ID, userId)
        .set(Tables.T_USER_REFRESH_TOKEN.TOKEN, token)
        .onDuplicateKeyUpdate()
        .set(Tables.T_USER_REFRESH_TOKEN.TOKEN, token)
        .set(Tables.T_USER_REFRESH_TOKEN.EXPIRED_AT, Timestamp.from(expiredAt.toInstant()))
        .execute();

    TUserRefreshToken refreshToken = userRefreshTokenDao.fetchOneByToken(token);
    return refreshToken;
  }

  @Override
  public RefreshTokenRespVo refreshToken(Integer userId, RefreshTokenReqVo refreshTokenReqVo) {
    TUserRefreshTokenRecord tokenRecord = dslContext
        .selectFrom(Tables.T_USER_REFRESH_TOKEN)
        .where(Tables.T_USER_REFRESH_TOKEN.USER_ID.eq(userId)
            .and(Tables.T_USER_REFRESH_TOKEN.TOKEN.eq(refreshTokenReqVo.getRefreshToken())))
        .fetchOptional()
        .orElseThrow(() -> new UserBizException(ErrorCode.USER_REFRESH_TOKEN_ERROR));

    if (tokenRecord.getExpiredAt().before(new Date())) {
      throw new UserBizException(ErrorCode.USER_REFRESH_TOKEN_EXPIRED);
    }
    Pair<String, Date> pairOfAccessToken = tokenFactory.createAccessToken(userId);
    tokenRecord.setExpiredAt(Timestamp.from(DateUtils.addHours(new Date(),settings.getRefreshTokenExpHours()).toInstant()));
    tokenRecord.update();

    RefreshTokenRespVo respVo = RefreshTokenRespVo.builder()
        .refreshToken(tokenRecord.getToken())
        .refreshTokenExpireAt(tokenRecord.getExpiredAt())
        .accessToken(pairOfAccessToken.getLeft())
        .accessTokenExpireAt(pairOfAccessToken.getRight())
        .build();
    return respVo;
  }


}
