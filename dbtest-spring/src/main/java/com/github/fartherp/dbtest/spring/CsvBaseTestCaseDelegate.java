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
import org.apache.commons.io.IOUtils;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.csv.CsvDataSet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author CK
 * @date 2016/2/4
 */
public class CsvBaseTestCaseDelegate extends BaseTestCaseDelegate {
    public CsvBaseTestCaseDelegate(BaseBusinessTestCase testCase) {
        super();
        this.testCase = testCase;
    }

    protected IDataSet getDataSet() throws Exception {
        return new CsvDataSet(new File(Resources.getResource(testCase.getDbunitDir()).getPath()));
    }

    protected void isUseDBUnit(String[] tableNames) {
        if (tableNames == null || tableNames.length == 0) {
            throw new RuntimeException("方法或类需要指定加载的表名");
        }
        String dirLocal = testCase.getDbunitDir();
        if (dirLocal == null || "".equals(dirLocal.trim())) {
			throw new RuntimeException("CSV数据目录不存在，目录名：" + dirLocal);
        }
        String dirPath = Resources.getResource(testCase.getDbunitDir()).getFile();;
        File dir = new File(dirPath);
        if (!dir.exists()) {
			throw new RuntimeException("CSV数据目录不存在，目录名：" + dirLocal);
        }
		if (!dir.isDirectory()) {
			throw new RuntimeException("目录名不能是文件，目录名：" + dirLocal);
		}
        File[] files = dir.listFiles((dir1, name) -> name.toLowerCase().endsWith(".csv"));
		if (files == null || files.length == 0) {
			throw new RuntimeException("CSV数据不存在：" + Arrays.toString(tableNames));
		}
    }

    /**
     * 重写table_ordering.txt文件，用于加载不同的csv表文件
     *
     * @param tableNames 表名列表
     */
    private void initTableOrdering(String[] tableNames) throws Exception {
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
