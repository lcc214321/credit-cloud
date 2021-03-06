/*
 * This file is generated by jOOQ.
 */
package com.csci.cloud.auth.server.jooq.tables;


import com.csci.cloud.auth.server.jooq.CreditCloudConsole;
import com.csci.cloud.auth.server.jooq.Indexes;
import com.csci.cloud.auth.server.jooq.Keys;
import com.csci.cloud.auth.server.jooq.tables.records.TPermissionRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
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
public class TPermission extends TableImpl<TPermissionRecord> {

    private static final long serialVersionUID = -310621327;

    /**
     * The reference instance of <code>credit_cloud_console.t_permission</code>
     */
    public static final TPermission T_PERMISSION = new TPermission();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TPermissionRecord> getRecordType() {
        return TPermissionRecord.class;
    }

    /**
     * The column <code>credit_cloud_console.t_permission.group_id</code>. 用户组id
     */
    public final TableField<TPermissionRecord, Integer> GROUP_ID = createField("group_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "用户组id");

    /**
     * The column <code>credit_cloud_console.t_permission.permissions</code>. 权限，用半角逗号分隔(,)
     */
    public final TableField<TPermissionRecord, String> PERMISSIONS = createField("permissions", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "权限，用半角逗号分隔(,)");

    /**
     * Create a <code>credit_cloud_console.t_permission</code> table reference
     */
    public TPermission() {
        this(DSL.name("t_permission"), null);
    }

    /**
     * Create an aliased <code>credit_cloud_console.t_permission</code> table reference
     */
    public TPermission(String alias) {
        this(DSL.name(alias), T_PERMISSION);
    }

    /**
     * Create an aliased <code>credit_cloud_console.t_permission</code> table reference
     */
    public TPermission(Name alias) {
        this(alias, T_PERMISSION);
    }

    private TPermission(Name alias, Table<TPermissionRecord> aliased) {
        this(alias, aliased, null);
    }

    private TPermission(Name alias, Table<TPermissionRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> TPermission(Table<O> child, ForeignKey<O, TPermissionRecord> key) {
        super(child, key, T_PERMISSION);
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
        return Arrays.<Index>asList(Indexes.T_PERMISSION_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<TPermissionRecord> getPrimaryKey() {
        return Keys.KEY_T_PERMISSION_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<TPermissionRecord>> getKeys() {
        return Arrays.<UniqueKey<TPermissionRecord>>asList(Keys.KEY_T_PERMISSION_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TPermission as(String alias) {
        return new TPermission(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TPermission as(Name alias) {
        return new TPermission(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public TPermission rename(String name) {
        return new TPermission(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public TPermission rename(Name name) {
        return new TPermission(name, null);
    }
}
