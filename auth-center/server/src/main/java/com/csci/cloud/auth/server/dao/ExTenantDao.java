package com.csci.cloud.auth.server.dao;


import com.csci.cloud.auth.server.jooq.tables.daos.TTenantDao;
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

}
