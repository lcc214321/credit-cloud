package com.csci.cloud.admin.test;

import static org.junit.Assert.assertTrue;

import com.csci.cloud.admin.auth.AppKeyFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 */
@RunWith(SpringRunner.class)
public class AppKeyFactoryTest {

  AppKeyFactory appKeyFactory = new AppKeyFactory();

  @Test
  public void testAppKey() {
    for (int i = 0; i < 10; i++) {
      String appKey = appKeyFactory.generateAppSecret();
      System.out.println(appKey);
      assertTrue(appKey.length() == 43);
    }
  }

  @Test
  public void testAppId() {
    for (int i = 0; i < 10; i++) {
      String appId = appKeyFactory.generateAppKey();
      System.out.println(appId);
      assertTrue(appId.length() == 30);
    }
  }
}
