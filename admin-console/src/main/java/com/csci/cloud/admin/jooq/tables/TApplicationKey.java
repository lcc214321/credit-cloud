/*
 * This file is generated by jOOQ.
 */
package com.csci.cloud.admin.jooq.tables;


import com.csci.cloud.admin.jooq.CreditCloudConsole;
import com.csci.cloud.admin.jooq.Indexes;
import com.csci.cloud.admin.jooq.Keys;
import com.csci.cloud.admin.jooq.tables.records.TApplicationKeyRecord;

import java.sql.Timestamp;
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
public class TApplicationKey extends TableImpl<TApplicationKeyRecord> {

    private static final long serialVersionUID = -514256501;

    /**
     * The reference instance of <code>credit_cloud_console.t_application_key</code>
     */
    public static final TApplicationKey T_APPLICATION_KEY = new TApplicationKey();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TApplicationKeyRecord> getRecordType() {
        return TApplicationKeyRecord.class;
    }

    /**
     * The column <code>credit_cloud_console.t_application_key.app_key</code>.
     */
    public final TableField<TApplicationKeyRecord, String> APP_KEY = createField("app_key", org.jooq.impl.SQLDataType.VARCHAR(32).nullable(false), this, "");

    /**
     * The column <code>credit_cloud_console.t_application_key.app_secret</code>.
     */
    public final TableField<TApplicationKeyRecord, String> APP_SECRET = createField("app_secret", org.jooq.impl.SQLDataType.VARCHAR(64).nullable(false), this, "");

    /**
     * The column <code>credit_cloud_console.t_application_key.app_id</code>. 应用id
     */
    public final TableField<TApplicationKeyRecord, Integer> APP_ID = createField("app_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "应用id");

    /**
     * The column <code>credit_cloud_console.t_application_key.status</code>. Key的状态：0-正常，9-删除
     */
    public final TableField<TApplicationKeyRecord, Integer> STATUS = createField("status", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(DSL.inline("0", org.jooq.impl.SQLDataType.INTEGER)), this, "Key的状态：0-正常，9-删除");

    /**
     * The column <code>credit_cloud_console.t_application_key.create_at</code>.
     */
    public final TableField<TApplicationKeyRecord, Timestamp> CREATE_AT = createField("create_at", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(DSL.field("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "");

    /**
     * The column <code>credit_cloud_console.t_application_key.update_at</code>.
     */
    public final TableField<TApplicationKeyRecord, Timestamp> UPDATE_AT = createField("update_at", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(DSL.field("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "");

    /**
     * Create a <code>credit_cloud_console.t_application_key</code> table reference
     */
    public TApplicationKey() {
        this(DSL.name("t_application_key"), null);
    }

    /**
     * Create an aliased <code>credit_cloud_console.t_application_key</code> table reference
     */
    public TApplicationKey(String alias) {
        this(DSL.name(alias), T_APPLICATION_KEY);
    }

    /**
     * Create an aliased <code>credit_cloud_console.t_application_key</code> table reference
     */
    public TApplicationKey(Name alias) {
        this(alias, T_APPLICATION_KEY);
    }

    private TApplicationKey(Name alias, Table<TApplicationKeyRecord> aliased) {
        this(alias, aliased, null);
    }

    private TApplicationKey(Name alias, Table<TApplicationKeyRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> TApplicationKey(Table<O> child, ForeignKey<O, TApplicationKeyRecord> key) {
        super(child, key, T_APPLICATION_KEY);
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
        return Arrays.<Index>asList(Indexes.T_APPLICATION_KEY_APP_KEY_UNIQUE, Indexes.T_APPLICATION_KEY_APP_SECRET_UNIQUE, Indexes.T_APPLICATION_KEY_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<TApplicationKeyRecord> getPrimaryKey() {
        return Keys.KEY_T_APPLICATION_KEY_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<TApplicationKeyRecord>> getKeys() {
        return Arrays.<UniqueKey<TApplicationKeyRecord>>asList(Keys.KEY_T_APPLICATION_KEY_PRIMARY, Keys.KEY_T_APPLICATION_KEY_APP_KEY_UNIQUE, Keys.KEY_T_APPLICATION_KEY_APP_SECRET_UNIQUE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TApplicationKey as(String alias) {
        return new TApplicationKey(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TApplicationKey as(Name alias) {
        return new TApplicationKey(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public TApplicationKey rename(String name) {
        return new TApplicationKey(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public TApplicationKey rename(Name name) {
        return new TApplicationKey(name, null);
    }
}
