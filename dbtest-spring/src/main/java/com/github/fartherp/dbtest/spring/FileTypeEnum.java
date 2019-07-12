/*
 * Copyright (c) 2019. CK. All rights reserved.
 */

package com.github.fartherp.dbtest.spring;

import java.util.Arrays;
import java.util.function.Function;

/**
 * Created by IntelliJ IDEA.
 *
 * @author CK
 * @date 2019/4/8
 */
public enum FileTypeEnum {
    CSV("csv", CsvBaseTestCaseDelegate::new),
    XML("xml", XmlBaseTestCaseDelegate::new);

    public String fileType;

    public Function<BaseBusinessTestCase, BaseTestCaseDelegate> function;

    FileTypeEnum(String fileType, Function<BaseBusinessTestCase, BaseTestCaseDelegate> function) {
        this.fileType = fileType;
        this.function = function;
    }

    public static FileTypeEnum getFileTypeEnum(String fileType) {
        return Arrays.stream(FileTypeEnum.values()).filter(o -> o.fileType.equals(fileType)).findFirst()
                .orElseThrow(() -> new RuntimeException("DBUnit错误的文件格式，只支持csv或者xml文件"));
    }
}
