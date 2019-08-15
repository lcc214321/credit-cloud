package com.csci.cloud.admin.dao;

import com.csci.cloud.admin.jooq.Tables;
import com.csci.cloud.admin.jooq.tables.daos.TUserDao;
import com.csci.cloud.admin.jooq.tables.daos.TUserRefreshTokenDao;
import com.csci.cloud.admin.jooq.tables.pojos.TUser;
import com.csci.cloud.admin.jooq.tables.pojos.TUserRefreshToken;
import com.csci.cloud.admin.jooq.tables.records.TUserRecord;
import com.csci.cloud.admin.jooq.tables.records.TUserRefreshTokenRecord;
import java.sql.Timestamp;
import java.time.Instant;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by ben on 2018/9/14. benkris1@126.com
 */
@Repository
public class ExUserRefreshTokenDao extends TUserRefreshTokenDao {

  private DSLContext dslContext ;

  @Autowired
  public ExUserRefreshTokenDao(DSLContext dslContext) {
    super(dslContext.configuration());
    this.dslContext =dslContext;
  }

  public TUserRefreshToken create(TUserRefreshToken refreshToken) {
    TUserRefreshTokenRecord record = dslContext.newRecord(Tables.T_USER_REFRESH_TOKEN, refreshToken);
    int result = record.insert();
    return record.into(TUserRefreshToken.class);
  }

  /**
   * 把用户token过期.
   * @param userId 用户Id.
   * @return >0 成功,<=0 失败.
   */
  public int expireToken(int userId) {

    return dslContext.update(Tables.T_USER_REFRESH_TOKEN)
        .set(Tables.T_USER_REFRESH_TOKEN.EXPIRED_AT, Timestamp.from(Instant.now()))
        .where(Tables.T_USER_REFRESH_TOKEN.USER_ID.eq(userId))
        .execute();
  }

}
