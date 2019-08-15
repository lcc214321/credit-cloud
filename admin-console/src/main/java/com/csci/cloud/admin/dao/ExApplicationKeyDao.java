package com.csci.cloud.admin.dao;

import com.csci.cloud.admin.jooq.tables.TApplicationKey;
import com.csci.cloud.admin.jooq.tables.daos.TApplicationDao;
import com.csci.cloud.admin.jooq.tables.daos.TApplicationKeyDao;
import com.csci.cloud.admin.jooq.tables.daos.TUserDao;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by ben on 2018/9/14. benkris1@126.com
 */
@Repository
public class ExApplicationKeyDao extends TApplicationKeyDao {


  private DSLContext dslContext ;

  @Autowired
  public ExApplicationKeyDao(DSLContext dslContext) {
    super(dslContext.configuration());
    this.dslContext =dslContext;
  }


}
