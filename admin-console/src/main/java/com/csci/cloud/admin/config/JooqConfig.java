package com.csci.cloud.admin.config;

import com.alibaba.druid.pool.DruidDataSource;
import javax.sql.DataSource;
import org.jooq.ConnectionProvider;
import org.jooq.DSLContext;
import org.jooq.ExecuteListenerProvider;
import org.jooq.RecordListenerProvider;
import org.jooq.RecordMapperProvider;
import org.jooq.SQLDialect;
import org.jooq.TransactionProvider;
import org.jooq.VisitListenerProvider;
import org.jooq.conf.Settings;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.jooq.impl.DefaultExecuteListener;
import org.jooq.impl.DefaultExecuteListenerProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jooq.SpringTransactionProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Created by ben on 2018/9/13. benkris1@126.com
 */
@Configuration
public class JooqConfig {

  @Autowired
  MysqlConfig mysqlConfig;


  @Bean
  public DataSourceConnectionProvider connectionProvider(DataSource dataSource) {
    return new DataSourceConnectionProvider
        (new TransactionAwareDataSourceProxy(dataSource));
  }

  @Bean
  public DSLContext dsl(DataSource dataSource,PlatformTransactionManager transactionManager) {
    return new DefaultDSLContext(configuration(dataSource,transactionManager));
  }



  public DefaultConfiguration configuration(DataSource dataSource,PlatformTransactionManager transactionManager) {
    DefaultConfiguration jooqConfiguration = new DefaultConfiguration();
    jooqConfiguration.set(connectionProvider(dataSource));
    jooqConfiguration.setSQLDialect(SQLDialect.MYSQL_5_7);
    jooqConfiguration.setTransactionProvider(new SpringTransactionProvider(transactionManager));
    jooqConfiguration
        .set(new DefaultExecuteListenerProvider(new DefaultExecuteListener()));

    return jooqConfiguration;
  }


  @Bean
  @Primary
  public DataSource dataSource() {
    DruidDataSource dataSource = new DruidDataSource();
    dataSource.setUrl(mysqlConfig.getUrl());
    dataSource.setUsername(mysqlConfig.getUsername());
    dataSource.setPassword(mysqlConfig.getPassword());
    dataSource.setDriverClassName(mysqlConfig.getDriver());
    dataSource.setMaxActive(400);
    dataSource.setMaxWait(10_000);
    dataSource.setMinIdle(0);
    dataSource.setTestOnBorrow(true);
    dataSource.setTestWhileIdle(false);
    dataSource.setTestOnReturn(false);
    dataSource.setInitialSize(1);
    dataSource.setMinEvictableIdleTimeMillis(1000 * 60 * 10);
    dataSource.setTimeBetweenEvictionRunsMillis(60 * 1000);
    dataSource.setPoolPreparedStatements(true);
    dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
    dataSource.setValidationQuery("select 'x'");
    return dataSource;
  }

  @Bean(name = "txManager")
  @Primary
  public PlatformTransactionManager txManager(DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }

}

