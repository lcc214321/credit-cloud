package com.csci.cloud.admin.dao;

import com.csci.cloud.admin.jooq.Tables;
import com.csci.cloud.admin.jooq.tables.daos.TUserDao;
import com.csci.cloud.admin.jooq.tables.pojos.TUser;
import com.csci.cloud.admin.jooq.tables.records.TUserRecord;
import java.util.Optional;
import javax.annotation.PostConstruct;
import org.apache.commons.lang3.StringUtils;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Created by ben on 2018/9/14. benkris1@126.com
 */
@Repository
public class ExUserDao extends TUserDao {

  @Autowired
  private DSLContext dslContext ;

  @Autowired
  public ExUserDao (DSLContext dslContext) {
    super(dslContext.configuration());
    this.dslContext =dslContext;
  }

  public TUser create(TUser tUser) {
    TUserRecord record = dslContext.newRecord(Tables.T_USER, tUser);
    int result = record.insert();
    return record.into(TUser.class);
  }

  /**
   * 判断用户登录名or电话or邮箱是否有重复
   * @param loginName 登录名
   * @param email 邮箱
   * @param mobile 电话.
   * @return
   */
  public boolean isExistsByLoginNameOrEmailOrMobile(String loginName,String email,String mobile) {

    Condition condition = DSL.noCondition();
    if(StringUtils.isNotEmpty(loginName)) {
      condition = condition.or(Tables.T_USER.LOGIN_NAME.eq(loginName));
    }

    if(StringUtils.isNotEmpty(email)) {
      condition = condition.or(Tables.T_USER.EMAIL.eq(email));
    }

    if(StringUtils.isNotEmpty(mobile)) {
      condition = condition.or(Tables.T_USER.MOBILE.eq(mobile));
    }
    return dslContext.fetchExists(Tables.T_USER,condition);
  }

  /**
   * 根据tenantId和userId 判断用户是否存在.
   * @param tenantId
   * @param userId
   * @return
   */
  public boolean existUser(int tenantId, int userId) {
    return dslContext.fetchExists(Tables.T_USER,
        Tables.T_USER.TENANT_ID.eq(tenantId).and(Tables.T_USER.ID.eq(userId)));

  }

}
