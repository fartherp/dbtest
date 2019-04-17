/*
 * Copyright (c) 2017. juzhen.io. All rights reserved.
 */

package com.github.fartherp.dbtest.dbunit;

import com.google.common.io.Resources;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.csv.CsvDataSet;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * Author: CK
 * Date: 2016/2/4
 */
public class CsvBaseTestCaseDelegate extends BaseTestCaseDelegate {
    public CsvBaseTestCaseDelegate(BaseBusinessTestCase testCase) {
        super();
        this.testCase = testCase;
    }

    protected IDataSet getDataSet() throws Exception {
        return new CsvDataSet(new File(Resources.getResource(testCase.getDbunitDir()).getPath()));
    }

    protected boolean isUseDBUnit(String[] tableNames) {
        if (tableNames == null || tableNames.length == 0) {
            return false;
        }
        String dirLocal = testCase.getDbunitDir();
        if (dirLocal == null || "".equals(dirLocal.trim())) {
            return false;
        }
        String dirPath = Resources.getResource(testCase.getDbunitDir()).getFile();;
        File dir = new File(dirPath);
        if (!dir.exists() || !dir.isDirectory()) {
            return false;
        }
        File[] files = dir.listFiles((dir1, name) -> name.toLowerCase().endsWith(".csv"));
        return files != null && files.length != 0;
    }
}
