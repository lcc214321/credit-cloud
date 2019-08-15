/*
 * This file is generated by jOOQ.
 */
package com.csci.cloud.admin.jooq.tables.pojos;


import java.io.Serializable;
import java.sql.Timestamp;

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
public class TConfigServer implements Serializable {

    private static final long serialVersionUID = -586816642;

    private Integer   id;
    private Integer   tenantId;
    private String    serverName;
    private Integer   serverType;
    private Integer   status;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public TConfigServer() {}

    public TConfigServer(TConfigServer value) {
        this.id = value.id;
        this.tenantId = value.tenantId;
        this.serverName = value.serverName;
        this.serverType = value.serverType;
        this.status = value.status;
        this.createdAt = value.createdAt;
        this.updatedAt = value.updatedAt;
    }

    public TConfigServer(
        Integer   id,
        Integer   tenantId,
        String    serverName,
        Integer   serverType,
        Integer   status,
        Timestamp createdAt,
        Timestamp updatedAt
    ) {
        this.id = id;
        this.tenantId = tenantId;
        this.serverName = serverName;
        this.serverType = serverType;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return this.id;
    }

    public TConfigServer setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getTenantId() {
        return this.tenantId;
    }

    public TConfigServer setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public String getServerName() {
        return this.serverName;
    }

    public TConfigServer setServerName(String serverName) {
        this.serverName = serverName;
        return this;
    }

    public Integer getServerType() {
        return this.serverType;
    }

    public TConfigServer setServerType(Integer serverType) {
        this.serverType = serverType;
        return this;
    }

    public Integer getStatus() {
        return this.status;
    }

    public TConfigServer setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public TConfigServer setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Timestamp getUpdatedAt() {
        return this.updatedAt;
    }

    public TConfigServer setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("TConfigServer (");

        sb.append(id);
        sb.append(", ").append(tenantId);
        sb.append(", ").append(serverName);
        sb.append(", ").append(serverType);
        sb.append(", ").append(status);
        sb.append(", ").append(createdAt);
        sb.append(", ").append(updatedAt);

        sb.append(")");
        return sb.toString();
    }
}
