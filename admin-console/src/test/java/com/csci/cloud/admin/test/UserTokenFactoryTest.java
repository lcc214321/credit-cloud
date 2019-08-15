package com.csci.cloud.admin.test;

import static org.junit.Assert.assertTrue;

import com.csci.cloud.admin.AccountBootstrap;
import com.csci.cloud.admin.auth.UserTokenFactory;
import java.util.Date;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AccountBootstrap.class)
@WebAppConfiguration
public class UserTokenFactoryTest {

  @Autowired
  UserTokenFactory userTokenFactory;

  @Test
  public void testAccessToken() throws InterruptedException {
    for (int i = 0; i < 10; i++) {
      Thread.sleep(100);
      Pair<String, Date> pair = userTokenFactory.createAccessToken(999999);
      System.out.println(pair.getKey());
      assertTrue(pair.getKey().length() == 169);
    }
  }

  @Test
  public void testRefreshToken() throws InterruptedException {
    for (int i = 0; i < 10; i++) {
      Thread.sleep(100);
      Pair<String, Date> pair = userTokenFactory.createRefreshToken(999999);
      System.out.println(pair.getKey());
      assertTrue(pair.getKey().length() == 43);
    }
  }
}
