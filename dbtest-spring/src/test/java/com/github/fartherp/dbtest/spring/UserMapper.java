/*
 * Copyright (c) 2019. CK. All rights reserved.
 */

package com.github.fartherp.dbtest.spring;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * `user`
 */
@Mapper
public interface UserMapper {

	List<User> selectAll();

    User findByUsername(@Param("userName") String userName);
}
