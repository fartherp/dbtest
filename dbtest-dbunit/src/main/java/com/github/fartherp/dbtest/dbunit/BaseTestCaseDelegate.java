/*
 * Copyright (c) 2017. CK. All rights reserved.
 */

package com.github.fartherp.dbtest.dbunit;

import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;

/**
 * Created by IntelliJ IDEA.
 * Author: CK
 * Date: 2016/2/4
 */
public abstract class BaseTestCaseDelegate {
    protected BaseBusinessTestCase testCase;

    public void beforeForDBUnit(String[] tableNames) throws Exception {
        if (!isUseDBUnit(tableNames)) {
            throw new RuntimeException("必须指定需要加载的表名");
        }
        init(tableNames);
        testCase.dataSourceDatabaseTester = new DataSourceDatabaseTester(testCase.getDataSource());
        IDataSet dataSet = getDataSet();
        testCase.dataSourceDatabaseTester.setDataSet(dataSet);

        testCase.dataSourceDatabaseTester.setSetUpOperation(DatabaseOperation.REFRESH);
        testCase.dataSourceDatabaseTester.onSetup();
    }

    public void afterForDBUnit(String[] tableNames) throws Exception {
        if (!isUseDBUnit(tableNames)) {
            throw new RuntimeException("必须指定需要加载的表名");
        }
        destroy(tableNames);
        testCase.dataSourceDatabaseTester.setTearDownOperation(DatabaseOperation.DELETE);
        testCase.dataSourceDatabaseTester.onTearDown();
    }

    protected abstract IDataSet getDataSet() throws Exception;

    /**
     * 判断是否可采用DBUnit
     *
     * @param tableNames 表名列表
     * @return true：可采用，false：不可采用
     */
    protected abstract boolean isUseDBUnit(String[] tableNames);

    protected void init(String[] tableNames) throws Exception {

    }

    protected void destroy(String[] tableNames) throws Exception {

    }
}
