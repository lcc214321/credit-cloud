package com.csci.cloud.admin.dao;

import com.csci.cloud.admin.jooq.Tables;
import com.csci.cloud.admin.jooq.tables.daos.TTenantDao;
import com.csci.cloud.admin.jooq.tables.daos.TUserDao;
import com.csci.cloud.admin.jooq.tables.pojos.TTenant;
import com.csci.cloud.admin.jooq.tables.records.TTenantRecord;
import com.csci.cloud.admin.jooq.tables.records.TUserRecord;
import org.apache.commons.lang3.StringUtils;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by ben on 2018/9/14. benkris1@126.com
 */
@Repository
public class ExTenantDao extends TTenantDao {

  private DSLContext dslContext ;

  @Autowired
  public ExTenantDao(DSLContext dslContext) {
    super(dslContext.configuration());
    this.dslContext =dslContext;
  }

  public TTenant create(TTenant tTenant) {
    TTenantRecord record = dslContext.newRecord(Tables.T_TENANT, tTenant);
    int result = record.insert();
    return record.into(TTenant.class);
  }



  /**
   * 判断租户名称或者电话是否重复
   * @param tenantName 租户名称
   * @param code 租户代码
   * @return
   */
  public boolean isExistsByTenantNameOrCode(String tenantName,String code) {

    Condition condition = DSL.noCondition();
    if(StringUtils.isNotEmpty(tenantName)) {
      condition = condition.and(Tables.T_TENANT.NAME.eq(tenantName));
    }

    if(StringUtils.isNotEmpty(code)) {
      condition = condition.and(Tables.T_TENANT.CODE.eq(code));
    }
    return dslContext.fetchExists(Tables.T_TENANT,condition);
  }

}
