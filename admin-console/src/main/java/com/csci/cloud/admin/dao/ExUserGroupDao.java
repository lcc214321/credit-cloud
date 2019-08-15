package com.csci.cloud.admin.dao;

import com.csci.cloud.admin.jooq.Tables;
import com.csci.cloud.admin.jooq.tables.daos.TUserDao;
import com.csci.cloud.admin.jooq.tables.daos.TUserGroupDao;
import com.csci.cloud.admin.jooq.tables.pojos.TUser;
import com.csci.cloud.admin.jooq.tables.records.TUserRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by ben on 2018/9/14. benkris1@126.com
 */
@Repository
public class ExUserGroupDao extends TUserGroupDao {

  private DSLContext dslContext ;

  @Autowired
  public ExUserGroupDao(DSLContext dslContext) {
    super(dslContext.configuration());
    this.dslContext =dslContext;
  }



}
