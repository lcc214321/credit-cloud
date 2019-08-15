package com.csci.cloud.core.client.mirror.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by ben on 2018/9/26. benkris1@126.com 反欺诈业务.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@Builder
public class MirrorFraud001ReqVo {

  /**
   * 客户身份证号.
   */
  private String idNo;

  /**
   * 客户姓名.
   */
  private String name;

  /**
   * 客户手机号码.
   */
  private String mobile;

  /**
   * 客户单位名称.
   */
  @JsonAlias(value = {"org_name", "orgName"})
  @JsonProperty("org_name")
  private String orgName;

  /**
   * 客户单位电话.
   */
  @JsonAlias(value = {"org_phone", "orgPhone"})
  @JsonProperty("org_phone")
  private String orgPhone;

  /**
   * 客户单位GPS位置 格式为：精度,维度.
   */
  @JsonAlias(value = {"orgLocGps", "org_loc_gps"})
  @JsonProperty("org_loc_gps")
  private String orgLocGps;

  /**
   * 外部数据（如滴滴）获取单位地址GPS，格式：精度,纬度如114.06128224887138,22.54775263769751.
   */
  @JsonAlias(value = {"org_loc_3rd_gps", "orgLoc3rdGps"})
  @JsonProperty("org_loc_3rd_gps")
  private String orgLoc3rdGps;

  /**
   * 外部数据（如滴滴）获取家庭地址GPS，格式：精度,纬度如114.06128224887138,22.54775263769751.
   */
  @JsonProperty("home_loc_3rd_gps")
  @JsonAlias(value = {"homeLoc3rdGps", "home_loc_3rd_gps"})
  private String homeLoc3rdGps;

  /**
   * 工作城市 如 “深圳市”.
   */
  @JsonAlias(value = {"work_city", "workCity"})
  @JsonProperty("work_city")
  private String workCity;

  /**
   * 授信额度.
   */
  @JsonAlias(value = {"credit_limit", "creditLimit"})
  @JsonProperty("credit_limit")
  private String creditLimit;

  /**
   * 客户学历：博士/硕士/本科/大专/中专/高中/初中/小学/其他.
   */
  @JsonAlias(value = {"edu_level", "eduLevel"})
  @JsonProperty("edu_level")
  private String eduLevel;

  /**
   * 婚姻状态：未婚/已婚/离异/其他.
   */
  @JsonAlias(value = {"marriage_status", "marriageStatus"})
  @JsonProperty("marriage_status")
  private String marriageStatus;

  /**
   * 手机安装app数量.
   */
  @JsonAlias(value = {"mobile_install_app", "mobileInstallApp"})
  @JsonProperty("mobile_install_app")
  private Integer mobileInstallApp;

  /**
   * 客户登录时打开搜索引擎APP，查询高风险类字词.
   */
  @JsonAlias(value = {"app_search_words", "appSearchWords"})
  @JsonProperty("app_search_words")
  private String appSearchWords;

  /**
   * 客户同时打开贷款申请APP数量.
   */
  @JsonAlias(value = {"mobileRunApp", "mobile_run_app"})
  @JsonProperty("mobile_run_app")
  private String mobileRunApp;

  /**
   * 客户手机语言 如中文”zh-CN”.
   */
  @JsonAlias(value = {"mobile_language", "mobileLanguage"})
  @JsonProperty("mobile_language")
  private String mobileLanguage;

  /**
   * 客户手机是否root。 是:”Y” 否:”N”.
   */
  @JsonAlias(value = {"mobile_is_root", "mobileIsRoot"})
  @JsonProperty("mobile_is_root")
  private String mobileIsRoot;

  /**
   * 是否为模拟器登录。是:”Y” 否:”N”.
   */
  @JsonAlias(value = {"mobile_is_simulator", "mobileIsSimulator"})
  @JsonProperty("mobile_is_simulator")
  private String mobileIsSimulator;


  /**
   * 客户是否使用代理IP登录。是:”Y” 否:”N”.
   */
  @JsonAlias(value = {"mobile_is_proxyip", "mobileIsProxyIp"})
  @JsonProperty("mobile_is_proxyip")
  private String mobileIsProxyIp;

  /**
   * 客户手机sim卡个数.
   */
  @JsonAlias(value = {"mobile_sim_count", "mobileSimCount"})
  @JsonProperty("mobile_sim_count")
  private Integer mobileSimCount;

  /**
   * 客户手机Line1手机号.
   */
  @JsonAlias(value = {"mobile_line1_num", "mobileLine1Num"})
  @JsonProperty("mobile_line1_num")
  private String mobileLine1Num;


  /**
   * 客户手机GPS，格式：精度,纬度 如114.06128224887138,22.54775263769751.
   */
  @JsonAlias(value = {"mobile_loc_gps", "mobileLocGps"})
  @JsonProperty("mobile_loc_gps")
  private String mobileLocGps;

  /**
   * 常用GPS，格式：精度,纬度 如114.06128224887138,22.54775263769751.
   */
  @JsonAlias(value = {"common_used_loc_gps", "commonUsedLocGps"})
  @JsonProperty("common_used_loc_gps")
  private String commonUsedLocGps;

  /**
   * 客户手机接入的wifi ip地址。如172.168.10.1.
   */
  @JsonAlias(value = {"wifi_ip", "wifiIp"})
  @JsonProperty("wifi_ip")
  private String wifiIp;

  /**
   * 客户手机接入的wifi的网关地址。如172.168.10.1.
   */
  @JsonAlias(value = {"wifi_gateway", "wifiGateway"})
  @JsonProperty("wifi_gateway")
  private String wifiGateway;

  /**
   * 附近wifi的名称。多个用逗号“,”隔开。如“tplink001,tplink002”.
   */
  @JsonAlias(value = {"near_wifis", "nearWifis"})
  @JsonProperty("near_wifis")
  private String nearWifis;

  /**
   * 客户连接wifi的ssid.
   */
  @JsonAlias(value = {"wifi_ssid", "wifiSsid"})
  @JsonProperty("wifi_ssid")
  private String wifiSsid;

  /**
   * 客户连接wifi的bssid.
   */
  @JsonAlias(value = {"wifi_bssid", "wifiBssid"})
  @JsonProperty("wifi_bssid")
  private String wifiBssid;
}
