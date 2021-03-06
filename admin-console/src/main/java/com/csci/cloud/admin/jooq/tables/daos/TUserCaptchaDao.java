/*
 * This file is generated by jOOQ.
 */
package com.csci.cloud.admin.jooq.tables.daos;


import com.csci.cloud.admin.jooq.tables.TUserCaptcha;
import com.csci.cloud.admin.jooq.tables.records.TUserCaptchaRecord;

import java.sql.Timestamp;
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
public class TUserCaptchaDao extends DAOImpl<TUserCaptchaRecord, com.csci.cloud.admin.jooq.tables.pojos.TUserCaptcha, Integer> {

    /**
     * Create a new TUserCaptchaDao without any configuration
     */
    public TUserCaptchaDao() {
        super(TUserCaptcha.T_USER_CAPTCHA, com.csci.cloud.admin.jooq.tables.pojos.TUserCaptcha.class);
    }

    /**
     * Create a new TUserCaptchaDao with an attached configuration
     */
    public TUserCaptchaDao(Configuration configuration) {
        super(TUserCaptcha.T_USER_CAPTCHA, com.csci.cloud.admin.jooq.tables.pojos.TUserCaptcha.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Integer getId(com.csci.cloud.admin.jooq.tables.pojos.TUserCaptcha object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<com.csci.cloud.admin.jooq.tables.pojos.TUserCaptcha> fetchById(Integer... values) {
        return fetch(TUserCaptcha.T_USER_CAPTCHA.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public com.csci.cloud.admin.jooq.tables.pojos.TUserCaptcha fetchOneById(Integer value) {
        return fetchOne(TUserCaptcha.T_USER_CAPTCHA.ID, value);
    }

    /**
     * Fetch records that have <code>login_name IN (values)</code>
     */
    public List<com.csci.cloud.admin.jooq.tables.pojos.TUserCaptcha> fetchByLoginName(String... values) {
        return fetch(TUserCaptcha.T_USER_CAPTCHA.LOGIN_NAME, values);
    }

    /**
     * Fetch a unique record that has <code>login_name = value</code>
     */
    public com.csci.cloud.admin.jooq.tables.pojos.TUserCaptcha fetchOneByLoginName(String value) {
        return fetchOne(TUserCaptcha.T_USER_CAPTCHA.LOGIN_NAME, value);
    }

    /**
     * Fetch records that have <code>times IN (values)</code>
     */
    public List<com.csci.cloud.admin.jooq.tables.pojos.TUserCaptcha> fetchByTimes(Integer... values) {
        return fetch(TUserCaptcha.T_USER_CAPTCHA.TIMES, values);
    }

    /**
     * Fetch records that have <code>created_at IN (values)</code>
     */
    public List<com.csci.cloud.admin.jooq.tables.pojos.TUserCaptcha> fetchByCreatedAt(Timestamp... values) {
        return fetch(TUserCaptcha.T_USER_CAPTCHA.CREATED_AT, values);
    }

    /**
     * Fetch records that have <code>updated_at IN (values)</code>
     */
    public List<com.csci.cloud.admin.jooq.tables.pojos.TUserCaptcha> fetchByUpdatedAt(Timestamp... values) {
        return fetch(TUserCaptcha.T_USER_CAPTCHA.UPDATED_AT, values);
    }
}
