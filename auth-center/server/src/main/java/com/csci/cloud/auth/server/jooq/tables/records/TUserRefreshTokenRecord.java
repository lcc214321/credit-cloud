/*
 * This file is generated by jOOQ.
 */
package com.csci.cloud.auth.server.jooq.tables.records;


import com.csci.cloud.auth.server.jooq.tables.TUserRefreshToken;

import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;


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
public class TUserRefreshTokenRecord extends UpdatableRecordImpl<TUserRefreshTokenRecord> implements Record5<String, Integer, Integer, Timestamp, Timestamp> {

    private static final long serialVersionUID = -296505195;

    /**
     * Setter for <code>credit_cloud_console.t_user_refresh_token.token</code>. 令牌
     */
    public TUserRefreshTokenRecord setToken(String value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>credit_cloud_console.t_user_refresh_token.token</code>. 令牌
     */
    public String getToken() {
        return (String) get(0);
    }

    /**
     * Setter for <code>credit_cloud_console.t_user_refresh_token.user_id</code>. 用户id
     */
    public TUserRefreshTokenRecord setUserId(Integer value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>credit_cloud_console.t_user_refresh_token.user_id</code>. 用户id
     */
    public Integer getUserId() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>credit_cloud_console.t_user_refresh_token.tenant_id</code>. 租户id
     */
    public TUserRefreshTokenRecord setTenantId(Integer value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>credit_cloud_console.t_user_refresh_token.tenant_id</code>. 租户id
     */
    public Integer getTenantId() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>credit_cloud_console.t_user_refresh_token.created_at</code>. 创建时间
     */
    public TUserRefreshTokenRecord setCreatedAt(Timestamp value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>credit_cloud_console.t_user_refresh_token.created_at</code>. 创建时间
     */
    public Timestamp getCreatedAt() {
        return (Timestamp) get(3);
    }

    /**
     * Setter for <code>credit_cloud_console.t_user_refresh_token.expired_at</code>. 失效时间
     */
    public TUserRefreshTokenRecord setExpiredAt(Timestamp value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>credit_cloud_console.t_user_refresh_token.expired_at</code>. 失效时间
     */
    public Timestamp getExpiredAt() {
        return (Timestamp) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<String, Integer, Integer, Timestamp, Timestamp> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<String, Integer, Integer, Timestamp, Timestamp> valuesRow() {
        return (Row5) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field1() {
        return TUserRefreshToken.T_USER_REFRESH_TOKEN.TOKEN;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field2() {
        return TUserRefreshToken.T_USER_REFRESH_TOKEN.USER_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field3() {
        return TUserRefreshToken.T_USER_REFRESH_TOKEN.TENANT_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field4() {
        return TUserRefreshToken.T_USER_REFRESH_TOKEN.CREATED_AT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field5() {
        return TUserRefreshToken.T_USER_REFRESH_TOKEN.EXPIRED_AT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component1() {
        return getToken();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component2() {
        return getUserId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component3() {
        return getTenantId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp component4() {
        return getCreatedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp component5() {
        return getExpiredAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value1() {
        return getToken();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value2() {
        return getUserId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value3() {
        return getTenantId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value4() {
        return getCreatedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value5() {
        return getExpiredAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TUserRefreshTokenRecord value1(String value) {
        setToken(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TUserRefreshTokenRecord value2(Integer value) {
        setUserId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TUserRefreshTokenRecord value3(Integer value) {
        setTenantId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TUserRefreshTokenRecord value4(Timestamp value) {
        setCreatedAt(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TUserRefreshTokenRecord value5(Timestamp value) {
        setExpiredAt(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TUserRefreshTokenRecord values(String value1, Integer value2, Integer value3, Timestamp value4, Timestamp value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached TUserRefreshTokenRecord
     */
    public TUserRefreshTokenRecord() {
        super(TUserRefreshToken.T_USER_REFRESH_TOKEN);
    }

    /**
     * Create a detached, initialised TUserRefreshTokenRecord
     */
    public TUserRefreshTokenRecord(String token, Integer userId, Integer tenantId, Timestamp createdAt, Timestamp expiredAt) {
        super(TUserRefreshToken.T_USER_REFRESH_TOKEN);

        set(0, token);
        set(1, userId);
        set(2, tenantId);
        set(3, createdAt);
        set(4, expiredAt);
    }
}
