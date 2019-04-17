/*
 * Copyright (c) 2019. CK. All rights reserved.
 */

package com.github.fartherp.dbtest.spring.boot.test.autoconfigure;

import com.github.fartherp.dbtest.dbunit.UseDbUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.TestPropertySource;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by IntelliJ IDEA.
 * Author: CK
 * Date: 2019/4/17
 */
@TestPropertySource(properties = {
        "mybatis.mapper-locations=classpath:mapper/*Mapper.xml",
        "logging.level.org.springframework.jdbc=debug",
        "spring.datasource.url=jdbc:mysql://10.11.0.156:3306/test_tmp?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true",
        "spring.datasource.username=root",
        "spring.datasource.password=root@123",
        "spring.datasource.driverClassName=com.mysql.jdbc.Driver"
})
@SpringBootApplication
public class DBTestIntegrationTest extends SpringBootBaseDbTestCase {

    @Autowired
    private UserMapper userMapper;

    @UseDbUnit(tables = "tb_user")
    @Test
    public void testFindByUsername() {
        User user = userMapper.findByUsername("name1");
        Assert.assertEquals(user.getPassword(), "password1");
    }
}
