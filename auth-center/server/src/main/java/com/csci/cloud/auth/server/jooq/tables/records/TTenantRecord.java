/*
 * This file is generated by jOOQ.
 */
package com.csci.cloud.auth.server.jooq.tables.records;


import com.csci.cloud.auth.server.jooq.tables.TTenant;

import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record7;
import org.jooq.Row7;
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
public class TTenantRecord extends UpdatableRecordImpl<TTenantRecord> implements Record7<Integer, String, String, Integer, Integer, Timestamp, Timestamp> {

    private static final long serialVersionUID = 799494492;

    /**
     * Setter for <code>credit_cloud_console.t_tenant.id</code>. 租户id
     */
    public TTenantRecord setId(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>credit_cloud_console.t_tenant.id</code>. 租户id
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>credit_cloud_console.t_tenant.name</code>. 租户名称
     */
    public TTenantRecord setName(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>credit_cloud_console.t_tenant.name</code>. 租户名称
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>credit_cloud_console.t_tenant.code</code>. 租户标识
     */
    public TTenantRecord setCode(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>credit_cloud_console.t_tenant.code</code>. 租户标识
     */
    public String getCode() {
        return (String) get(2);
    }

    /**
     * Setter for <code>credit_cloud_console.t_tenant.type</code>. 租户类型：0-个人，1-公司
     */
    public TTenantRecord setType(Integer value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>credit_cloud_console.t_tenant.type</code>. 租户类型：0-个人，1-公司
     */
    public Integer getType() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>credit_cloud_console.t_tenant.status</code>. 租户状态：0-正常，9-删除
     */
    public TTenantRecord setStatus(Integer value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>credit_cloud_console.t_tenant.status</code>. 租户状态：0-正常，9-删除
     */
    public Integer getStatus() {
        return (Integer) get(4);
    }

    /**
     * Setter for <code>credit_cloud_console.t_tenant.created_at</code>. 创建时间
     */
    public TTenantRecord setCreatedAt(Timestamp value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>credit_cloud_console.t_tenant.created_at</code>. 创建时间
     */
    public Timestamp getCreatedAt() {
        return (Timestamp) get(5);
    }

    /**
     * Setter for <code>credit_cloud_console.t_tenant.update_at</code>. 更新时间
     */
    public TTenantRecord setUpdateAt(Timestamp value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>credit_cloud_console.t_tenant.update_at</code>. 更新时间
     */
    public Timestamp getUpdateAt() {
        return (Timestamp) get(6);
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
    // Record7 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row7<Integer, String, String, Integer, Integer, Timestamp, Timestamp> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row7<Integer, String, String, Integer, Integer, Timestamp, Timestamp> valuesRow() {
        return (Row7) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return TTenant.T_TENANT.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return TTenant.T_TENANT.NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return TTenant.T_TENANT.CODE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field4() {
        return TTenant.T_TENANT.TYPE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field5() {
        return TTenant.T_TENANT.STATUS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field6() {
        return TTenant.T_TENANT.CREATED_AT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field7() {
        return TTenant.T_TENANT.UPDATE_AT;
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
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component4() {
        return getType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component5() {
        return getStatus();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp component6() {
        return getCreatedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp component7() {
        return getUpdateAt();
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
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value4() {
        return getType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value5() {
        return getStatus();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value6() {
        return getCreatedAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value7() {
        return getUpdateAt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TTenantRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TTenantRecord value2(String value) {
        setName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TTenantRecord value3(String value) {
        setCode(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TTenantRecord value4(Integer value) {
        setType(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TTenantRecord value5(Integer value) {
        setStatus(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TTenantRecord value6(Timestamp value) {
        setCreatedAt(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TTenantRecord value7(Timestamp value) {
        setUpdateAt(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TTenantRecord values(Integer value1, String value2, String value3, Integer value4, Integer value5, Timestamp value6, Timestamp value7) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached TTenantRecord
     */
    public TTenantRecord() {
        super(TTenant.T_TENANT);
    }

    /**
     * Create a detached, initialised TTenantRecord
     */
    public TTenantRecord(Integer id, String name, String code, Integer type, Integer status, Timestamp createdAt, Timestamp updateAt) {
        super(TTenant.T_TENANT);

        set(0, id);
        set(1, name);
        set(2, code);
        set(3, type);
        set(4, status);
        set(5, createdAt);
        set(6, updateAt);
    }
}