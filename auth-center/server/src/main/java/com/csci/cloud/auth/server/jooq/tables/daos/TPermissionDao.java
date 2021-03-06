/*
 * This file is generated by jOOQ.
 */
package com.csci.cloud.auth.server.jooq.tables.daos;


import com.csci.cloud.auth.server.jooq.tables.TPermission;
import com.csci.cloud.auth.server.jooq.tables.records.TPermissionRecord;

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
public class TPermissionDao extends DAOImpl<TPermissionRecord, com.csci.cloud.auth.server.jooq.tables.pojos.TPermission, Integer> {

    /**
     * Create a new TPermissionDao without any configuration
     */
    public TPermissionDao() {
        super(TPermission.T_PERMISSION, com.csci.cloud.auth.server.jooq.tables.pojos.TPermission.class);
    }

    /**
     * Create a new TPermissionDao with an attached configuration
     */
    public TPermissionDao(Configuration configuration) {
        super(TPermission.T_PERMISSION, com.csci.cloud.auth.server.jooq.tables.pojos.TPermission.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Integer getId(com.csci.cloud.auth.server.jooq.tables.pojos.TPermission object) {
        return object.getGroupId();
    }

    /**
     * Fetch records that have <code>group_id IN (values)</code>
     */
    public List<com.csci.cloud.auth.server.jooq.tables.pojos.TPermission> fetchByGroupId(Integer... values) {
        return fetch(TPermission.T_PERMISSION.GROUP_ID, values);
    }

    /**
     * Fetch a unique record that has <code>group_id = value</code>
     */
    public com.csci.cloud.auth.server.jooq.tables.pojos.TPermission fetchOneByGroupId(Integer value) {
        return fetchOne(TPermission.T_PERMISSION.GROUP_ID, value);
    }

    /**
     * Fetch records that have <code>permissions IN (values)</code>
     */
    public List<com.csci.cloud.auth.server.jooq.tables.pojos.TPermission> fetchByPermissions(String... values) {
        return fetch(TPermission.T_PERMISSION.PERMISSIONS, values);
    }
}
