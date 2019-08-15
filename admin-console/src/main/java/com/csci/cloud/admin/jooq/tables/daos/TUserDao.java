/*
 * This file is generated by jOOQ.
 */
package com.csci.cloud.admin.jooq.tables.daos;


import com.csci.cloud.admin.jooq.tables.TUser;
import com.csci.cloud.admin.jooq.tables.records.TUserRecord;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.4"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TUserDao extends DAOImpl<TUserRecord, com.csci.cloud.admin.jooq.tables.pojos.TUser, Integer> {

    /**
     * Create a new TUserDao without any configuration
     */
    public TUserDao() {
        super(TUser.T_USER, com.csci.cloud.admin.jooq.tables.pojos.TUser.class);
    }

    /**
     * Create a new TUserDao with an attached configuration
     */
    public TUserDao(Configuration configuration) {
        super(TUser.T_USER, com.csci.cloud.admin.jooq.tables.pojos.TUser.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Integer getId(com.csci.cloud.admin.jooq.tables.pojos.TUser object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<com.csci.cloud.admin.jooq.tables.pojos.TUser> fetchById(Integer... values) {
        return fetch(TUser.T_USER.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public com.csci.cloud.admin.jooq.tables.pojos.TUser fetchOneById(Integer value) {
        return fetchOne(TUser.T_USER.ID, value);
    }

    /**
     * Fetch records that have <code>tenant_id IN (values)</code>
     */
    public List<com.csci.cloud.admin.jooq.tables.pojos.TUser> fetchByTenantId(Integer... values) {
        return fetch(TUser.T_USER.TENANT_ID, values);
    }

    /**
     * Fetch records that have <code>login_name IN (values)</code>
     */
    public List<com.csci.cloud.admin.jooq.tables.pojos.TUser> fetchByLoginName(String... values) {
        return fetch(TUser.T_USER.LOGIN_NAME, values);
    }

    /**
     * Fetch a unique record that has <code>login_name = value</code>
     */
    public com.csci.cloud.admin.jooq.tables.pojos.TUser fetchOneByLoginName(String value) {
        return fetchOne(TUser.T_USER.LOGIN_NAME, value);
    }

    /**
     * Fetch records that have <code>password IN (values)</code>
     */
    public List<com.csci.cloud.admin.jooq.tables.pojos.TUser> fetchByPassword(String... values) {
        return fetch(TUser.T_USER.PASSWORD, values);
    }

    /**
     * Fetch records that have <code>email IN (values)</code>
     */
    public List<com.csci.cloud.admin.jooq.tables.pojos.TUser> fetchByEmail(String... values) {
        return fetch(TUser.T_USER.EMAIL, values);
    }

    /**
     * Fetch a unique record that has <code>email = value</code>
     */
    public com.csci.cloud.admin.jooq.tables.pojos.TUser fetchOneByEmail(String value) {
        return fetchOne(TUser.T_USER.EMAIL, value);
    }

    /**
     * Fetch records that have <code>mobile IN (values)</code>
     */
    public List<com.csci.cloud.admin.jooq.tables.pojos.TUser> fetchByMobile(String... values) {
        return fetch(TUser.T_USER.MOBILE, values);
    }

    /**
     * Fetch a unique record that has <code>mobile = value</code>
     */
    public com.csci.cloud.admin.jooq.tables.pojos.TUser fetchOneByMobile(String value) {
        return fetchOne(TUser.T_USER.MOBILE, value);
    }

    /**
     * Fetch records that have <code>email_verified IN (values)</code>
     */
    public List<com.csci.cloud.admin.jooq.tables.pojos.TUser> fetchByEmailVerified(Integer... values) {
        return fetch(TUser.T_USER.EMAIL_VERIFIED, values);
    }

    /**
     * Fetch records that have <code>mobile_verified IN (values)</code>
     */
    public List<com.csci.cloud.admin.jooq.tables.pojos.TUser> fetchByMobileVerified(Integer... values) {
        return fetch(TUser.T_USER.MOBILE_VERIFIED, values);
    }

    /**
     * Fetch records that have <code>status IN (values)</code>
     */
    public List<com.csci.cloud.admin.jooq.tables.pojos.TUser> fetchByStatus(Integer... values) {
        return fetch(TUser.T_USER.STATUS, values);
    }

    /**
     * Fetch records that have <code>group_id IN (values)</code>
     */
    public List<com.csci.cloud.admin.jooq.tables.pojos.TUser> fetchByGroupId(Integer... values) {
        return fetch(TUser.T_USER.GROUP_ID, values);
    }

    /**
     * Fetch records that have <code>created_at IN (values)</code>
     */
    public List<com.csci.cloud.admin.jooq.tables.pojos.TUser> fetchByCreatedAt(Timestamp... values) {
        return fetch(TUser.T_USER.CREATED_AT, values);
    }

    /**
     * Fetch records that have <code>updated_at IN (values)</code>
     */
    public List<com.csci.cloud.admin.jooq.tables.pojos.TUser> fetchByUpdatedAt(Timestamp... values) {
        return fetch(TUser.T_USER.UPDATED_AT, values);
    }
}
