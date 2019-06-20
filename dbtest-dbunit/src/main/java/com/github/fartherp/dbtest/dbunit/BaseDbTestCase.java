/*
 * Copyright (c) 2017. CK. All rights reserved.
 */

package com.github.fartherp.dbtest.dbunit;

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
    private DataSource dataSource;

    @Override
    protected DataSource getDataSource() {
        return dataSource;
    }

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getDbunitDir() {
        return "data";
    }

    public String getDbunitFile() {
        return "sample_data_cust.xml";
    }
}
