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
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.sql.DataSource;

/**
 * Created by IntelliJ IDEA.
 *
 * @author CK
 * @date: 2016/2/4
 */
@TestExecutionListeners({ DBUnitExecutionListener.class, TransactionalTestExecutionListener.class })
public abstract class BaseBusinessTestCase extends AbstractTestNGSpringContextTests {
    protected DataSourceDatabaseTester dataSourceDatabaseTester;

    public void beforeForDBUnit(String fileType, String[] tableNames) throws Exception {
		BaseTestCaseDelegate testCaseDelegate = createBaseTestCaseDelegate(fileType, this);
		testCaseDelegate.beforeForDBUnit(tableNames);
    }

    public void afterForDBUnit(String fileType, String[] tableNames) throws Exception {
		BaseTestCaseDelegate testCaseDelegate = createBaseTestCaseDelegate(fileType, this);
		testCaseDelegate.afterForDBUnit(tableNames);
    }

    protected abstract DataSource getDataSource();

    public abstract String getDbunitDir();

    public abstract String getDbunitFile();

    private BaseTestCaseDelegate createBaseTestCaseDelegate(String fileType, BaseBusinessTestCase testCase) {
        return FileTypeEnum.getFileTypeEnum(fileType).function.apply(testCase);
    }
}
