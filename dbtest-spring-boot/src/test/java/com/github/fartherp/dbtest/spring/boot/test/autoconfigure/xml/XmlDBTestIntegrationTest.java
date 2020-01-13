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
package com.github.fartherp.dbtest.spring.boot.test.autoconfigure.xml;

import com.github.fartherp.dbtest.spring.UseDbUnit;
import com.github.fartherp.dbtest.spring.boot.test.autoconfigure.SpringBootBaseDbTestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.TestPropertySource;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 *
 * @author CK
 * @date 2019/4/17
 */
@TestPropertySource(properties = {
        "mybatis.mapper-locations=classpath:mapper/xml/*Mapper.xml",
        "logging.level.org.springframework.jdbc=info",
        "spring.datasource.url=jdbc:mysql://localhost:3306/test_tmp?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true",
        "spring.datasource.username=root",
        "spring.datasource.password=root",
        "spring.datasource.driverClassName=com.mysql.jdbc.Driver"
})
@SpringBootApplication
public class XmlDBTestIntegrationTest extends SpringBootBaseDbTestCase {

    @Autowired
    private UserMapper userMapper;

    @UseDbUnit(tables = "tb_user", fileType = "xml")
    @Test
    public void testFindByUsername() {
        User user = userMapper.findByUsername("name1");
        assertEquals(user.getPassword(), "password1");
    }
}
