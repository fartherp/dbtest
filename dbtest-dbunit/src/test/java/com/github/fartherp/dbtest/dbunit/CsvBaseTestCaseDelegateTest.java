/*
 * Copyright (c) 2019. CK. All rights reserved.
 */

package com.github.fartherp.dbtest.dbunit;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * Author: CK
 * Date: 2019/6/20
 */
@UseDbUnit(tables = "tb_user")
public class CsvBaseTestCaseDelegateTest extends BaseDbTestCase {
	@Autowired
	private UserMapper userMapper;

	@UseDbUnit(tables = "tb_user")
	@Test
	public void testFindByUsername() {
		User user = userMapper.findByUsername("name1");
		assertEquals(user.getPassword(), "password1");
	}

	@Test
	public void testSelectAll() {
		List<User> list = userMapper.selectAll();
		assertEquals(list.size(), 3);
	}
}
