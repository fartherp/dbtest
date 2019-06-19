/*
 * Copyright (c) 2019. CK. All rights reserved.
 */

package com.github.fartherp.dbtest.dbunit;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * <pre>
 *  @author: cuiyuqiang
 *  @email: cuiyuqiang@ddjf.com.cn
 *  @date: 2019/6/19 17:39
 *  @project: risk-control-parent
 * </pre>
 */
public class BaseDbTestCaseTest extends BaseDbTestCase {

	@Autowired
	private UserMapper userMapper;

	@UseDbUnit(tables = "tb_user")
	@Test
	public void testFindByUsername() {
		User user = userMapper.findByUsername("name1");
		assertEquals(user.getPassword(), "password1");
	}
}
