/*
 * This file is generated by jOOQ.
 */
package com.csci.cloud.auth.server.jooq;


import com.csci.cloud.auth.server.jooq.tables.TApplication;
import com.csci.cloud.auth.server.jooq.tables.TApplicationKey;
import com.csci.cloud.auth.server.jooq.tables.TConfigServer;
import com.csci.cloud.auth.server.jooq.tables.TPermission;
import com.csci.cloud.auth.server.jooq.tables.TTenant;
import com.csci.cloud.auth.server.jooq.tables.TUser;
import com.csci.cloud.auth.server.jooq.tables.TUserCaptcha;
import com.csci.cloud.auth.server.jooq.tables.TUserGroup;
import com.csci.cloud.auth.server.jooq.tables.TUserRefreshToken;

import javax.annotation.Generated;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.Internal;


/**
 * A class modelling indexes of tables of the <code>credit_cloud_console</code> 
 * schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.4"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index T_APPLICATION_PRIMARY = Indexes0.T_APPLICATION_PRIMARY;
    public static final Index T_APPLICATION_KEY_APP_KEY_UNIQUE = Indexes0.T_APPLICATION_KEY_APP_KEY_UNIQUE;
    public static final Index T_APPLICATION_KEY_APP_SECRET_UNIQUE = Indexes0.T_APPLICATION_KEY_APP_SECRET_UNIQUE;
    public static final Index T_APPLICATION_KEY_PRIMARY = Indexes0.T_APPLICATION_KEY_PRIMARY;
    public static final Index T_CONFIG_SERVER_PRIMARY = Indexes0.T_CONFIG_SERVER_PRIMARY;
    public static final Index T_PERMISSION_PRIMARY = Indexes0.T_PERMISSION_PRIMARY;
    public static final Index T_TENANT_CODE_UNIQUE = Indexes0.T_TENANT_CODE_UNIQUE;
    public static final Index T_TENANT_NAME_UNIQUE = Indexes0.T_TENANT_NAME_UNIQUE;
    public static final Index T_TENANT_PRIMARY = Indexes0.T_TENANT_PRIMARY;
    public static final Index T_USER_EMAIL_UNIQUE = Indexes0.T_USER_EMAIL_UNIQUE;
    public static final Index T_USER_LOGIN_NAME_UNIQUE = Indexes0.T_USER_LOGIN_NAME_UNIQUE;
    public static final Index T_USER_MOBILE_UNIQUE = Indexes0.T_USER_MOBILE_UNIQUE;
    public static final Index T_USER_PRIMARY = Indexes0.T_USER_PRIMARY;
    public static final Index T_USER_CAPTCHA_PRIMARY = Indexes0.T_USER_CAPTCHA_PRIMARY;
    public static final Index T_USER_CAPTCHA_UNIQ_LOGIN_NAME = Indexes0.T_USER_CAPTCHA_UNIQ_LOGIN_NAME;
    public static final Index T_USER_GROUP_PRIMARY = Indexes0.T_USER_GROUP_PRIMARY;
    public static final Index T_USER_REFRESH_TOKEN_PRIMARY = Indexes0.T_USER_REFRESH_TOKEN_PRIMARY;
    public static final Index T_USER_REFRESH_TOKEN_USER_ID = Indexes0.T_USER_REFRESH_TOKEN_USER_ID;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Indexes0 {
        public static Index T_APPLICATION_PRIMARY = Internal.createIndex("PRIMARY", TApplication.T_APPLICATION, new OrderField[] { TApplication.T_APPLICATION.ID }, true);
        public static Index T_APPLICATION_KEY_APP_KEY_UNIQUE = Internal.createIndex("app_key_UNIQUE", TApplicationKey.T_APPLICATION_KEY, new OrderField[] { TApplicationKey.T_APPLICATION_KEY.APP_KEY }, true);
        public static Index T_APPLICATION_KEY_APP_SECRET_UNIQUE = Internal.createIndex("app_secret_UNIQUE", TApplicationKey.T_APPLICATION_KEY, new OrderField[] { TApplicationKey.T_APPLICATION_KEY.APP_SECRET }, true);
        public static Index T_APPLICATION_KEY_PRIMARY = Internal.createIndex("PRIMARY", TApplicationKey.T_APPLICATION_KEY, new OrderField[] { TApplicationKey.T_APPLICATION_KEY.APP_KEY }, true);
        public static Index T_CONFIG_SERVER_PRIMARY = Internal.createIndex("PRIMARY", TConfigServer.T_CONFIG_SERVER, new OrderField[] { TConfigServer.T_CONFIG_SERVER.ID }, true);
        public static Index T_PERMISSION_PRIMARY = Internal.createIndex("PRIMARY", TPermission.T_PERMISSION, new OrderField[] { TPermission.T_PERMISSION.GROUP_ID }, true);
        public static Index T_TENANT_CODE_UNIQUE = Internal.createIndex("code_UNIQUE", TTenant.T_TENANT, new OrderField[] { TTenant.T_TENANT.CODE }, true);
        public static Index T_TENANT_NAME_UNIQUE = Internal.createIndex("name_UNIQUE", TTenant.T_TENANT, new OrderField[] { TTenant.T_TENANT.NAME }, true);
        public static Index T_TENANT_PRIMARY = Internal.createIndex("PRIMARY", TTenant.T_TENANT, new OrderField[] { TTenant.T_TENANT.ID }, true);
        public static Index T_USER_EMAIL_UNIQUE = Internal.createIndex("email_UNIQUE", TUser.T_USER, new OrderField[] { TUser.T_USER.EMAIL }, true);
        public static Index T_USER_LOGIN_NAME_UNIQUE = Internal.createIndex("login_name_UNIQUE", TUser.T_USER, new OrderField[] { TUser.T_USER.LOGIN_NAME }, true);
        public static Index T_USER_MOBILE_UNIQUE = Internal.createIndex("mobile_UNIQUE", TUser.T_USER, new OrderField[] { TUser.T_USER.MOBILE }, true);
        public static Index T_USER_PRIMARY = Internal.createIndex("PRIMARY", TUser.T_USER, new OrderField[] { TUser.T_USER.ID }, true);
        public static Index T_USER_CAPTCHA_PRIMARY = Internal.createIndex("PRIMARY", TUserCaptcha.T_USER_CAPTCHA, new OrderField[] { TUserCaptcha.T_USER_CAPTCHA.ID }, true);
        public static Index T_USER_CAPTCHA_UNIQ_LOGIN_NAME = Internal.createIndex("uniq_login_name", TUserCaptcha.T_USER_CAPTCHA, new OrderField[] { TUserCaptcha.T_USER_CAPTCHA.LOGIN_NAME }, true);
        public static Index T_USER_GROUP_PRIMARY = Internal.createIndex("PRIMARY", TUserGroup.T_USER_GROUP, new OrderField[] { TUserGroup.T_USER_GROUP.ID }, true);
        public static Index T_USER_REFRESH_TOKEN_PRIMARY = Internal.createIndex("PRIMARY", TUserRefreshToken.T_USER_REFRESH_TOKEN, new OrderField[] { TUserRefreshToken.T_USER_REFRESH_TOKEN.TOKEN }, true);
        public static Index T_USER_REFRESH_TOKEN_USER_ID = Internal.createIndex("user_id", TUserRefreshToken.T_USER_REFRESH_TOKEN, new OrderField[] { TUserRefreshToken.T_USER_REFRESH_TOKEN.USER_ID }, true);
    }
}
