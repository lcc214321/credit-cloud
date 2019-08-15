package com.csci.cloud.admin.dao;

import com.csci.cloud.admin.jooq.tables.daos.TPermissionDao;
import com.csci.cloud.admin.jooq.tables.daos.TUserDao;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by ben on 2018/9/14. benkris1@126.com
 */
@Repository
public class ExPermissionDao extends TPermissionDao {

  private DSLContext dslContext ;

  @Autowired
  public ExPermissionDao(DSLContext dslContext) {
    super(dslContext.configuration());
    this.dslContext =dslContext;
  }


}
