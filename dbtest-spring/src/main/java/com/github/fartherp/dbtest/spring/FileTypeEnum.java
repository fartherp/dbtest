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
