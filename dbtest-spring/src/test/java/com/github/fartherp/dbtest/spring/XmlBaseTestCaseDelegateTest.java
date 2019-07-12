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

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * @author CK
 * @date 2019/6/20
 */
@UseDbUnit(tables = "tb_user", fileType = "xml")
public class XmlBaseTestCaseDelegateTest extends BaseDbTestCase {

	@Autowired
	private UserMapper userMapper;

	@UseDbUnit(tables = "tb_user", fileType = "xml")
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
