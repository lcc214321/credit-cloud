/*
 * This file is generated by jOOQ.
 */
package com.csci.cloud.admin.jooq.tables.pojos;


import java.io.Serializable;

import javax.annotation.Generated;


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
public class TPermission implements Serializable {

    private static final long serialVersionUID = 109622486;

    private Integer groupId;
    private String  permissions;

    public TPermission() {}

    public TPermission(TPermission value) {
        this.groupId = value.groupId;
        this.permissions = value.permissions;
    }

    public TPermission(
        Integer groupId,
        String  permissions
    ) {
        this.groupId = groupId;
        this.permissions = permissions;
    }

    public Integer getGroupId() {
        return this.groupId;
    }

    public TPermission setGroupId(Integer groupId) {
        this.groupId = groupId;
        return this;
    }

    public String getPermissions() {
        return this.permissions;
    }

    public TPermission setPermissions(String permissions) {
        this.permissions = permissions;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("TPermission (");

        sb.append(groupId);
        sb.append(", ").append(permissions);

        sb.append(")");
        return sb.toString();
    }
}
