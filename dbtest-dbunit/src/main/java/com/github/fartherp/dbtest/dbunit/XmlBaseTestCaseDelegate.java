/*
 * Copyright (c) 2017. CK. All rights reserved.
 */

package com.github.fartherp.dbtest.dbunit;

import com.google.common.io.Resources;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;

/**
 * Created by IntelliJ IDEA.
 * Author: CK
 * Date: 2016/2/4
 */
public class XmlBaseTestCaseDelegate extends BaseTestCaseDelegate {
    public XmlBaseTestCaseDelegate(BaseBusinessTestCase testCase) {
        super();
        this.testCase = testCase;
    }

    protected IDataSet getDataSet() throws Exception {
        return new XmlDataSet(Resources.getResource(testCase.getDbunitFile()).openStream());
    }

    protected boolean isUseDBUnit(String[] tableNames) {
        return testCase.getDbunitFile() != null && !"".equals(testCase.getDbunitFile().trim());
    }
}
