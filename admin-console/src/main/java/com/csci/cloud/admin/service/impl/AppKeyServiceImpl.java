package com.csci.cloud.admin.service.impl;

import com.csci.cloud.admin.dao.ExApplicationKeyDao;
import com.csci.cloud.admin.jooq.tables.pojos.TApplicationKey;
import com.csci.cloud.admin.service.AppKeyService;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 */
@Service
public class AppKeyServiceImpl implements AppKeyService {

  @Autowired
  ExApplicationKeyDao applicationKeyDao;

}

