/*
 * This file is generated by jOOQ.
 */
package com.csci.cloud.admin.jooq.tables;


import com.csci.cloud.admin.jooq.CreditCloudConsole;
import com.csci.cloud.admin.jooq.Indexes;
import com.csci.cloud.admin.jooq.Keys;
import com.csci.cloud.admin.jooq.tables.records.TConfigServerRecord;

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
public class TConfigServer extends TableImpl<TConfigServerRecord> {

    private static final long serialVersionUID = -2135166429;

    /**
     * The reference instance of <code>credit_cloud_console.t_config_server</code>
     */
    public static final TConfigServer T_CONFIG_SERVER = new TConfigServer();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TConfigServerRecord> getRecordType() {
        return TConfigServerRecord.class;
    }

    /**
     * The column <code>credit_cloud_console.t_config_server.id</code>. 用户组id
     */
    public final TableField<TConfigServerRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "用户组id");

    /**
     * The column <code>credit_cloud_console.t_config_server.tenant_id</code>. 用户id
     */
    public final TableField<TConfigServerRecord, Integer> TENANT_ID = createField("tenant_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "用户id");

    /**
     * The column <code>credit_cloud_console.t_config_server.server_name</code>. 服务名称
     */
    public final TableField<TConfigServerRecord, String> SERVER_NAME = createField("server_name", org.jooq.impl.SQLDataType.VARCHAR(64).nullable(false), this, "服务名称");

    /**
     * The column <code>credit_cloud_console.t_config_server.server_type</code>. 服务类型 0: 区块链 1:企业征信 2:个人征信
     */
    public final TableField<TConfigServerRecord, Integer> SERVER_TYPE = createField("server_type", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "服务类型 0: 区块链 1:企业征信 2:个人征信");

    /**
     * The column <code>credit_cloud_console.t_config_server.status</code>. 服务状态.0:开启 1：关闭
     */
    public final TableField<TConfigServerRecord, Integer> STATUS = createField("status", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(DSL.inline("0", org.jooq.impl.SQLDataType.INTEGER)), this, "服务状态.0:开启 1：关闭");

    /**
     * The column <code>credit_cloud_console.t_config_server.created_at</code>. 创建时间
     */
    public final TableField<TConfigServerRecord, Timestamp> CREATED_AT = createField("created_at", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(DSL.field("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "创建时间");

    /**
     * The column <code>credit_cloud_console.t_config_server.updated_at</code>. 更新时间
     */
    public final TableField<TConfigServerRecord, Timestamp> UPDATED_AT = createField("updated_at", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(DSL.field("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "更新时间");

    /**
     * Create a <code>credit_cloud_console.t_config_server</code> table reference
     */
    public TConfigServer() {
        this(DSL.name("t_config_server"), null);
    }

    /**
     * Create an aliased <code>credit_cloud_console.t_config_server</code> table reference
     */
    public TConfigServer(String alias) {
        this(DSL.name(alias), T_CONFIG_SERVER);
    }

    /**
     * Create an aliased <code>credit_cloud_console.t_config_server</code> table reference
     */
    public TConfigServer(Name alias) {
        this(alias, T_CONFIG_SERVER);
    }

    private TConfigServer(Name alias, Table<TConfigServerRecord> aliased) {
        this(alias, aliased, null);
    }

    private TConfigServer(Name alias, Table<TConfigServerRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> TConfigServer(Table<O> child, ForeignKey<O, TConfigServerRecord> key) {
        super(child, key, T_CONFIG_SERVER);
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
        return Arrays.<Index>asList(Indexes.T_CONFIG_SERVER_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<TConfigServerRecord, Integer> getIdentity() {
        return Keys.IDENTITY_T_CONFIG_SERVER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<TConfigServerRecord> getPrimaryKey() {
        return Keys.KEY_T_CONFIG_SERVER_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<TConfigServerRecord>> getKeys() {
        return Arrays.<UniqueKey<TConfigServerRecord>>asList(Keys.KEY_T_CONFIG_SERVER_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TConfigServer as(String alias) {
        return new TConfigServer(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TConfigServer as(Name alias) {
        return new TConfigServer(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public TConfigServer rename(String name) {
        return new TConfigServer(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public TConfigServer rename(Name name) {
        return new TConfigServer(name, null);
    }
}