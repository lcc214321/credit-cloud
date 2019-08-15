package com.csci.cloud.admin.service;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by ben on 2018/9/18. benkris1@126.com
 */
public abstract class AbstractService {

  @Autowired
  protected DSLContext dslContext;
}
