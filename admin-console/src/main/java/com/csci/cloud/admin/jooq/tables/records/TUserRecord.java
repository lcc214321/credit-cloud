/*
 * This file is generated by jOOQ.
 */
package com.csci.cloud.admin.jooq.tables.records;


import com.csci.cloud.admin.jooq.tables.TUser;

import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record12;
import org.jooq.Row12;
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
public class TUserRecord extends UpdatableRecordImpl<TUserRecord> implements Record12<Integer, Integer, String, String, String, String, Integer, Integer, Integer, Integer, Timestamp, Timestamp> {

    private static final long serialVersionUID = 1428455444;

    /**
     * Setter for <code>credit_cloud_console.t_user.id</code>. 用户id
     */
    public TUserRecord setId(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>credit_cloud_console.t_user.id</code>. 用户id
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>credit_cloud_console.t_user.tenant_id</code>. 租户id
     */
    public TUserRecord setTenantId(Integer value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>credit_cloud_console.t_user.tenant_id</code>. 租户id
     */
    public Integer getTenantId() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>credit_cloud_console.t_user.login_name</code>. 登录名
     */
    public TUserRecord setLoginName(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>credit_cloud_console.t_user.login_name</code>. 登录名
     */
    public String getLoginName() {
        return (String) get(2);
    }

    /**
     * Setter for <code>credit_cloud_console.t_user.password</code>. 密码
     */
    public TUserRecord setPassword(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>credit_cloud_console.t_user.password</code>. 密码
     */
    public String getPassword() {
        return (String) get(3);
    }

    /**
     * Setter for <code>credit_cloud_console.t_user.email</code>. 邮件
     */
    public TUserRecord setEmail(String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>credit_cloud_console.t_user.email</code>. 邮件
     */
    public String getEmail() {
        return (String) get(4);
    }

    /**
     * Setter for <code>credit_cloud_console.t_user.mobile</code>. 手机
     */
    public TUserRecord setMobile(String value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>credit_cloud_console.t_user.mobile</code>. 手机
     */
    public String getMobile() {
        return (String) get(5);
    }

    /**
     * Setter for <code>credit_cloud_console.t_user.email_verified</code>. 邮箱是否验证：0-未验证，1-已验证
     */
    public TUserRecord setEmailVerified(Integer value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>credit_cloud_console.t_user.email_verified</code>. 邮箱是否验证：0-未验证，1-已验证
     */
    public Integer getEmailVerified() {
        return (Integer) get(6);
    }

    /**
     * Setter for <code>credit_cloud_console.t_user.mobile_verified</code>.
     */
    public TUserRecord setMobileVerified(Integer value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>credit_cloud_console.t_user.mobile_verified</code>.
     */
    public Integer getMobileVerified() {
        return (Integer) get(7);
    }

    /**
     * Setter for <code>credit_cloud_console.t_user.status</code>. 用户状态：0-未激活，1-已激活，9-删除
     */
    public TUserRecord setStatus(Integer value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>credit_cloud_console.t_user.status</code>. 用户状态：0-未激活，1-已激活，9-删除
     */
    public Integer getStatus() {
        return (Integer) get(8);
    }

    /**
     * Setter for <code>credit_cloud_console.t_user.group_id</code>. 用户组id
     */
    public TUserRecord setGroupId(Integer value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for <code>credit_cloud_console.t_user.group_id</code>. 用户组id
     */
    public Integer getGroupId() {
        return (Integer) get(9);
    }

    /**
     * Setter for <code>credit_cloud_console.t_user.created_at</code>. 创建时间
     */
    public TUserRecord setCreatedAt(Timestamp value) {
        set(10, value);
        return this;
    }

    /**
     * Getter for <code>credit_cloud_console.t_user.created_at</code>. 创建时间
     */
    public Timestamp getCreatedAt() {
        return (Timestamp) get(10);
    }

    /**
     * Setter for <code>credit_cloud_console.t_user.updated_at</code>. 更新时间
     */
    public TUserRecord setUpdatedAt(Timestamp value) {
        set(11, value);
        return this;
    }

    /**
     * Getter for <code>credit_cloud_console.t_user.updated_at</code>. 更新时间
     */
    public Timestamp getUpdatedAt() {
        return (Timestamp) get(11);
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
    // Record12 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row12<Integer, Integer, String, String, String, String, Integer, Integer, Integer, Integer, Timestamp, Timestamp> fieldsRow() {
        return (Row12) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row12<Integer, Integer, String, String, String, String, Integer, Integer, Integer, Integer, Timestamp, Timestamp> valuesRow() {
        return (Row12) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return TUser.T_USER.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field2() {
        return TUser.T_USER.TENANT_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return TUser.T_USER.LOGIN_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return TUser.T_USER.PASSWORD;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return TUser.T_USER.EMAIL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field6() {
        return TUser.T_USER.MOBILE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field7() {
        return TUser.T_USER.EMAIL_VERIFIED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field8() {
        return TUser.T_USER.MOBILE_VERIFIED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field9() {
        return TUser.T_USER.STATUS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field10() {
        return TUser.T_USER.GROUP_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field11() {
        return TUser.T_USER.CREATED_AT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field12() {
        return TUser.T_USER.UPDATED_AT;
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
    public Integer component2() {
        return getTenantId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getLoginName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component4() {
        return getPassword();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component5() {
        return getEmail();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component6() {
        return getMobile();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component7() {
        return getEmailVerified();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component8() {
        return getMobileVerified();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component9() {
        return getStatus();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component10() {
        return getGroupId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp component11() {
        return getCreatedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp component12() {
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
    public Integer value2() {
        return getTenantId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getLoginName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getPassword();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getEmail();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value6() {
        return getMobile();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value7() {
        return getEmailVerified();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value8() {
        return getMobileVerified();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value9() {
        return getStatus();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value10() {
        return getGroupId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value11() {
        return getCreatedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value12() {
        return getUpdatedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TUserRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TUserRecord value2(Integer value) {
        setTenantId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TUserRecord value3(String value) {
        setLoginName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TUserRecord value4(String value) {
        setPassword(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TUserRecord value5(String value) {
        setEmail(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TUserRecord value6(String value) {
        setMobile(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TUserRecord value7(Integer value) {
        setEmailVerified(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TUserRecord value8(Integer value) {
        setMobileVerified(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TUserRecord value9(Integer value) {
        setStatus(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TUserRecord value10(Integer value) {
        setGroupId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TUserRecord value11(Timestamp value) {
        setCreatedAt(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TUserRecord value12(Timestamp value) {
        setUpdatedAt(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TUserRecord values(Integer value1, Integer value2, String value3, String value4, String value5, String value6, Integer value7, Integer value8, Integer value9, Integer value10, Timestamp value11, Timestamp value12) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        value12(value12);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached TUserRecord
     */
    public TUserRecord() {
        super(TUser.T_USER);
    }

    /**
     * Create a detached, initialised TUserRecord
     */
    public TUserRecord(Integer id, Integer tenantId, String loginName, String password, String email, String mobile, Integer emailVerified, Integer mobileVerified, Integer status, Integer groupId, Timestamp createdAt, Timestamp updatedAt) {
        super(TUser.T_USER);

        set(0, id);
        set(1, tenantId);
        set(2, loginName);
        set(3, password);
        set(4, email);
        set(5, mobile);
        set(6, emailVerified);
        set(7, mobileVerified);
        set(8, status);
        set(9, groupId);
        set(10, createdAt);
        set(11, updatedAt);
    }
}
