/*
 * Copyright (c) 2017. juzhen.io. All rights reserved.
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
        IDataSet dataSet = null;
        dataSet = new XmlDataSet(Resources.getResource(testCase.getDbunitFile()).openStream());
        return dataSet;
    }

    protected boolean isUseDBUnit(String[] tables) {
        if (testCase.getDbunitFile() != null && !"".equals(testCase.getDbunitFile().trim())) {
            return true;
        }
        return false;
    }

    protected void init(String[] tableNames) throws Exception {
    }

    protected void destory(String[] tableNames) throws Exception {
    }
}
