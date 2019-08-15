/*
 * This file is generated by jOOQ.
 */
package com.csci.cloud.auth.server.jooq.tables.records;


import com.csci.cloud.auth.server.jooq.tables.TUserCaptcha;

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
public class TUserCaptchaRecord extends UpdatableRecordImpl<TUserCaptchaRecord> implements Record5<Integer, String, Integer, Timestamp, Timestamp> {

    private static final long serialVersionUID = 787987980;

    /**
     * Setter for <code>credit_cloud_console.t_user_captcha.id</code>. id
     */
    public TUserCaptchaRecord setId(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>credit_cloud_console.t_user_captcha.id</code>. id
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>credit_cloud_console.t_user_captcha.login_name</code>. 登录名
     */
    public TUserCaptchaRecord setLoginName(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>credit_cloud_console.t_user_captcha.login_name</code>. 登录名
     */
    public String getLoginName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>credit_cloud_console.t_user_captcha.times</code>. 登录失败的次数
     */
    public TUserCaptchaRecord setTimes(Integer value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>credit_cloud_console.t_user_captcha.times</code>. 登录失败的次数
     */
    public Integer getTimes() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>credit_cloud_console.t_user_captcha.created_at</code>. 创建时间
     */
    public TUserCaptchaRecord setCreatedAt(Timestamp value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>credit_cloud_console.t_user_captcha.created_at</code>. 创建时间
     */
    public Timestamp getCreatedAt() {
        return (Timestamp) get(3);
    }

    /**
     * Setter for <code>credit_cloud_console.t_user_captcha.updated_at</code>. 更新时间
     */
    public TUserCaptchaRecord setUpdatedAt(Timestamp value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>credit_cloud_console.t_user_captcha.updated_at</code>. 更新时间
     */
    public Timestamp getUpdatedAt() {
        return (Timestamp) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<Integer, String, Integer, Timestamp, Timestamp> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<Integer, String, Integer, Timestamp, Timestamp> valuesRow() {
        return (Row5) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return TUserCaptcha.T_USER_CAPTCHA.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return TUserCaptcha.T_USER_CAPTCHA.LOGIN_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field3() {
        return TUserCaptcha.T_USER_CAPTCHA.TIMES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field4() {
        return TUserCaptcha.T_USER_CAPTCHA.CREATED_AT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field5() {
        return TUserCaptcha.T_USER_CAPTCHA.UPDATED_AT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component2() {
        return getLoginName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component3() {
        return getTimes();
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
        return getUpdatedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getLoginName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value3() {
        return getTimes();
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
        return getUpdatedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TUserCaptchaRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TUserCaptchaRecord value2(String value) {
        setLoginName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TUserCaptchaRecord value3(Integer value) {
        setTimes(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TUserCaptchaRecord value4(Timestamp value) {
        setCreatedAt(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TUserCaptchaRecord value5(Timestamp value) {
        setUpdatedAt(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TUserCaptchaRecord values(Integer value1, String value2, Integer value3, Timestamp value4, Timestamp value5) {
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
     * Create a detached TUserCaptchaRecord
     */
    public TUserCaptchaRecord() {
        super(TUserCaptcha.T_USER_CAPTCHA);
    }

    /**
     * Create a detached, initialised TUserCaptchaRecord
     */
    public TUserCaptchaRecord(Integer id, String loginName, Integer times, Timestamp createdAt, Timestamp updatedAt) {
        super(TUserCaptcha.T_USER_CAPTCHA);

        set(0, id);
        set(1, loginName);
        set(2, times);
        set(3, createdAt);
        set(4, updatedAt);
    }
}
