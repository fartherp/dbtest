package com.github.fartherp.dbtest.dbunit;

import java.util.Arrays;
import java.util.function.Function;

/**
 * <pre>
 *  @author: cuiyuqiang
 *  @email: cuiyuqiang@ddjf.com.cn
 *  @date: 2019/4/8 10:32
 *  @project: dbtest
 *  </pre>
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
