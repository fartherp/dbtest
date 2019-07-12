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

import com.google.common.io.Resources;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;

/**
 * Created by IntelliJ IDEA.
 *
 * @author CK
 * @date 2016/2/4
 */
public class XmlBaseTestCaseDelegate extends BaseTestCaseDelegate {
    public XmlBaseTestCaseDelegate(BaseBusinessTestCase testCase) {
        super();
        this.testCase = testCase;
    }

    protected IDataSet getDataSet() throws Exception {
        return new XmlDataSet(Resources.getResource(testCase.getDbunitFile()).openStream());
    }

    protected void isUseDBUnit(String[] tableNames) {
    	if (testCase.getDbunitFile() == null || "".equals(testCase.getDbunitFile().trim())) {
    		throw new RuntimeException("XML文件不能空");
		}
    }
}
