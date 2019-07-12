/*
 * Copyright (c) 2019. CK. All rights reserved.
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
