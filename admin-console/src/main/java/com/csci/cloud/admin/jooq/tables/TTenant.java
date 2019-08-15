/*
 * This file is generated by jOOQ.
 */
package com.csci.cloud.admin.jooq.tables;


import com.csci.cloud.admin.jooq.CreditCloudConsole;
import com.csci.cloud.admin.jooq.Indexes;
import com.csci.cloud.admin.jooq.Keys;
import com.csci.cloud.admin.jooq.tables.records.TTenantRecord;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


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
public class TTenant extends TableImpl<TTenantRecord> {

    private static final long serialVersionUID = -1770425192;

    /**
     * The reference instance of <code>credit_cloud_console.t_tenant</code>
     */
    public static final TTenant T_TENANT = new TTenant();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TTenantRecord> getRecordType() {
        return TTenantRecord.class;
    }

    /**
     * The column <code>credit_cloud_console.t_tenant.id</code>. 租户id
     */
    public final TableField<TTenantRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "租户id");

    /**
     * The column <code>credit_cloud_console.t_tenant.name</code>. 租户名称
     */
    public final TableField<TTenantRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR(256).nullable(false), this, "租户名称");

    /**
     * The column <code>credit_cloud_console.t_tenant.code</code>. 租户标识
     */
    public final TableField<TTenantRecord, String> CODE = createField("code", org.jooq.impl.SQLDataType.VARCHAR(16).nullable(false), this, "租户标识");

    /**
     * The column <code>credit_cloud_console.t_tenant.type</code>. 租户类型：0-个人，1-公司
     */
    public final TableField<TTenantRecord, Integer> TYPE = createField("type", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(DSL.inline("0", org.jooq.impl.SQLDataType.INTEGER)), this, "租户类型：0-个人，1-公司");

    /**
     * The column <code>credit_cloud_console.t_tenant.status</code>. 租户状态：0-正常，9-删除
     */
    public final TableField<TTenantRecord, Integer> STATUS = createField("status", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(DSL.inline("0", org.jooq.impl.SQLDataType.INTEGER)), this, "租户状态：0-正常，9-删除");

    /**
     * The column <code>credit_cloud_console.t_tenant.created_at</code>. 创建时间
     */
    public final TableField<TTenantRecord, Timestamp> CREATED_AT = createField("created_at", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(DSL.field("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "创建时间");

    /**
     * The column <code>credit_cloud_console.t_tenant.update_at</code>. 更新时间
     */
    public final TableField<TTenantRecord, Timestamp> UPDATE_AT = createField("update_at", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(DSL.field("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "更新时间");

    /**
     * Create a <code>credit_cloud_console.t_tenant</code> table reference
     */
    public TTenant() {
        this(DSL.name("t_tenant"), null);
    }

    /**
     * Create an aliased <code>credit_cloud_console.t_tenant</code> table reference
     */
    public TTenant(String alias) {
        this(DSL.name(alias), T_TENANT);
    }

    /**
     * Create an aliased <code>credit_cloud_console.t_tenant</code> table reference
     */
    public TTenant(Name alias) {
        this(alias, T_TENANT);
    }

    private TTenant(Name alias, Table<TTenantRecord> aliased) {
        this(alias, aliased, null);
    }

    private TTenant(Name alias, Table<TTenantRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> TTenant(Table<O> child, ForeignKey<O, TTenantRecord> key) {
        super(child, key, T_TENANT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return CreditCloudConsole.CREDIT_CLOUD_CONSOLE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.T_TENANT_CODE_UNIQUE, Indexes.T_TENANT_NAME_UNIQUE, Indexes.T_TENANT_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<TTenantRecord, Integer> getIdentity() {
        return Keys.IDENTITY_T_TENANT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<TTenantRecord> getPrimaryKey() {
        return Keys.KEY_T_TENANT_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<TTenantRecord>> getKeys() {
        return Arrays.<UniqueKey<TTenantRecord>>asList(Keys.KEY_T_TENANT_PRIMARY, Keys.KEY_T_TENANT_NAME_UNIQUE, Keys.KEY_T_TENANT_CODE_UNIQUE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TTenant as(String alias) {
        return new TTenant(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TTenant as(Name alias) {
        return new TTenant(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public TTenant rename(String name) {
        return new TTenant(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public TTenant rename(Name name) {
        return new TTenant(name, null);
    }
}
