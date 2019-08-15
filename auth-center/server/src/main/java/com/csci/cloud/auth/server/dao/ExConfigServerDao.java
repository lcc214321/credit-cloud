package com.csci.cloud.auth.server.dao;

import com.csci.cloud.auth.server.jooq.tables.daos.TConfigServerDao;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by ben on 2018/9/18. benkris1@126.com
 */
@Repository
public class ExConfigServerDao extends TConfigServerDao {

  private DSLContext dslContext;

  @Autowired
  public ExConfigServerDao(DSLContext dslContext) {
    super(dslContext.configuration());
    this.dslContext = dslContext;
  }

}
