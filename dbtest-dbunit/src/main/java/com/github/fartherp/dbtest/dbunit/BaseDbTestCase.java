/*
 * Copyright (c) 2017. juzhen.io. All rights reserved.
 */

package com.github.fartherp.dbtest.dbunit;

import com.github.fartherp.dbtest.dbunit.BaseBusinessTestCase;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * Created by IntelliJ IDEA.
 * Author: CK
 * Date: 2016/2/4
 */
@ContextConfiguration(locations = { "classpath:/conf/appContext-test.xml" }, inheritLocations = false)
public class BaseDbTestCase extends BaseBusinessTestCase {
    protected SimpleJdbcTemplate simpleJdbcTemplate;
    private DataSource dataSource;

    @Override
    protected DataSource getDataSource() {
        return dataSource;
    }

    @Override
    protected SimpleJdbcTemplate getSimpleJdbcTemplate() {
        return simpleJdbcTemplate;
    }

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }

    public String getDbunitDir() {
        return "data";
    }

    public String getDbunitFile() {
        return "sample_data_cust.xml";
    }
}
