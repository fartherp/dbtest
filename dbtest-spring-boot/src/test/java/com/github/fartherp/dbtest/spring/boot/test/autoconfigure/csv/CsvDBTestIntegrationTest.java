/*
 * Copyright (c) 2019. CK. All rights reserved.
 */

package com.github.fartherp.dbtest.spring.boot.test.autoconfigure.csv;

import com.github.fartherp.dbtest.spring.UseDbUnit;
import com.github.fartherp.dbtest.spring.boot.test.autoconfigure.SpringBootBaseDbTestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.TestPropertySource;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @author CK
 * @date 2019/4/17
 */
@TestPropertySource(properties = {
        "mybatis.mapper-locations=classpath:mapper/csv/*Mapper.xml",
        "logging.level.org.springframework.jdbc=debug",
        "spring.datasource.url=jdbc:mysql://localhost:3306/test_tmp?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true",
        "spring.datasource.username=root",
        "spring.datasource.password=root",
        "spring.datasource.driverClassName=com.mysql.jdbc.Driver"
})
@SpringBootApplication
public class CsvDBTestIntegrationTest extends SpringBootBaseDbTestCase {

    @Autowired
    private UserMapper userMapper;

    @UseDbUnit(tables = "tb_user")
    @Test
    public void testFindByUsername() {
        User user = userMapper.findByUsername("name1");
        assertEquals(user.getPassword(), "password1");
    }
}
