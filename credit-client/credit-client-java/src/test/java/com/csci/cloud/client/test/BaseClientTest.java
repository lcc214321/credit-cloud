package com.csci.cloud.client.test;

import com.csci.cloud.client.CreditClient;

public abstract class BaseClientTest {

    /*public static final String API_KEY = "ntjhb0v6thrwaujqttytbzayow5ozw";
    public static final String SECRET = "m2i5oddjmgzhmgi0ndk2m2jhytjkmznjmzdhymfkmwq";*/

  //protected static String HOST = "http://paas-api-cc.prod.chinacsci.com";

  //prod
    protected static String HOST = "http://paas-api-cc.chinacsci.com";
    public static final String API_KEY = "b0r2vld1nlzzekjxd3dsq29ou2hwzw";
    public static final String SECRET = "mji5owjmnwi4ntbkngixztgwy2rjowq1yzjimgfiotc";

  //demo
  //protected static String HOST = "http://paas-api-cc.demo.chinacsci.com";
  //public static final String API_KEY = "dhlbslztvklkzvpmbhnot1g3slfizw";
  //public static final String SECRET = "zjmwogjlztrlyzblnddmywiwzgi4mtc1ywm1ymy2ota";


  //dev
  //protected static String HOST = "http://paas-api-cc.devn.chinacsci.com";
//  protected static String HOST = "http://localhost:8020";
//  public static final String API_KEY = "x3m1mnfslvptajvkbxh0btrfvvhtqq";
//  public static final String SECRET = "m2q0mtfhogfkn2qxndzjmwiwm2rkmtqwymu5zjg0otu";


  protected CreditClient creditClient = new CreditClient.Builder()
      .apiKey(API_KEY)
      .secret(SECRET)
      .basePath(HOST)
      .build();


}
