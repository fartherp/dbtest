/*
 * Copyright (c) 2017. CK. All rights reserved.
 */

package com.github.fartherp.dbtest.dbunit;

import com.google.common.io.Resources;
import org.apache.commons.io.IOUtils;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.csv.CsvDataSet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    /**
     * 重写table_ordering.txt文件，用于加载不同的csv表文件
     *
     * @param tableNames 表名列表
     */
    private void initTableOrdering(String[] tableNames) {
        String dirPath = Resources.getResource(testCase.getDbunitDir()).getFile();
        String tableOrderingPath = dirPath + "/" + CsvDataSet.TABLE_ORDERING_FILE;
        File tableOrdering = new File(tableOrderingPath);
        OutputStream os = null;
        try {
            if (!tableOrdering.exists()) {
                tableOrdering.createNewFile();
            }
            List<String> tableList = new ArrayList<>();
            Collections.addAll(tableList, tableNames);
            os = new FileOutputStream(tableOrdering);
            IOUtils.writeLines(tableList, IOUtils.LINE_SEPARATOR_UNIX, os, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(os);
        }
    }

    protected void init(String[] tableNames) throws Exception {
        this.initTableOrdering(tableNames);
    }

    /**
     * 删除table_ordering文件
     */
    private void deleteTableOrdering() {
        String dirPath = Resources.getResource(testCase.getDbunitDir()).getFile();
        String tableOrderingPath = dirPath + "/" + CsvDataSet.TABLE_ORDERING_FILE;
        File tableOrdering = new File(tableOrderingPath);
        if (tableOrdering.exists()) {
            tableOrdering.delete();
        }
    }

    protected void destroy(String[] tableNames) throws Exception {
        this.deleteTableOrdering();
    }
}
