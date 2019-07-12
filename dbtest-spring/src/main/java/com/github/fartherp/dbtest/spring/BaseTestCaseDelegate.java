/**
 *    Copyright (c) 2016-2019 CK.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.github.fartherp.dbtest.spring;

import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;

/**
 * Created by IntelliJ IDEA.
 *
 * @author CK
 * @date 2016/2/4
 */
public abstract class BaseTestCaseDelegate {
    protected BaseBusinessTestCase testCase;

    public void beforeForDBUnit(String[] tableNames) throws Exception {
		isUseDBUnit(tableNames);
        init(tableNames);
        testCase.dataSourceDatabaseTester = new DataSourceDatabaseTester(testCase.getDataSource());
        IDataSet dataSet = getDataSet();
        testCase.dataSourceDatabaseTester.setDataSet(dataSet);

        testCase.dataSourceDatabaseTester.setSetUpOperation(DatabaseOperation.REFRESH);
        testCase.dataSourceDatabaseTester.onSetup();
    }

    public void afterForDBUnit(String[] tableNames) throws Exception {
		isUseDBUnit(tableNames);
        destroy(tableNames);
        testCase.dataSourceDatabaseTester.setTearDownOperation(DatabaseOperation.DELETE);
        testCase.dataSourceDatabaseTester.onTearDown();
    }

    protected abstract IDataSet getDataSet() throws Exception;

    /**
     * 判断是否可采用DBUnit
     *
     * @param tableNames 表名列表
     */
    protected abstract void isUseDBUnit(String[] tableNames) throws RuntimeException;

    protected void init(String[] tableNames) throws Exception {

    }

    protected void destroy(String[] tableNames) throws Exception {

    }
}
