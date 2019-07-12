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

import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

import java.lang.reflect.Method;

/**
 * Created by IntelliJ IDEA.
 *
 * @author CK
 * @date 2016/2/4
 */
public class DBUnitExecutionListener extends AbstractTestExecutionListener {
    public void beforeTestMethod(TestContext testContext) throws Exception {
        Object testInstance = testContext.getTestInstance();
        Method testMethod = testContext.getTestMethod();
        Class<?> testClass = testContext.getTestClass();
        if (testInstance instanceof BaseBusinessTestCase) {
            UseDbUnit annotation = testMethod.getAnnotation(UseDbUnit.class);
            if (annotation == null) {
                annotation = testClass.getAnnotation(UseDbUnit.class);
            }
            if (annotation != null) {
                String[] tables = annotation.tables();
                String fileType = annotation.fileType();
                ((BaseBusinessTestCase) testInstance).beforeForDBUnit(fileType, tables);
            }
        }
    }

    @Override
    public void afterTestMethod(TestContext testContext) throws Exception {
        Object testInstance = testContext.getTestInstance();
        Method testMethod = testContext.getTestMethod();
        Class<?> testClass = testContext.getTestClass();
        if (testInstance instanceof BaseBusinessTestCase) {
            UseDbUnit annotation = testMethod.getAnnotation(UseDbUnit.class);
            if (annotation == null) {
                annotation = testClass.getAnnotation(UseDbUnit.class);
            }
            if (annotation != null) {
                String[] tables = annotation.tables();
                String fileType = annotation.fileType();
                ((BaseBusinessTestCase) testInstance).afterForDBUnit(fileType, tables);
            }
        }
    }
}
