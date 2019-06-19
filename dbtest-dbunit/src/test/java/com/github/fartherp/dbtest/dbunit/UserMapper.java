/*
 * Copyright (c) 2019. CK. All rights reserved.
 */

package com.github.fartherp.dbtest.dbunit;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * `user`
 */
@Mapper
public interface UserMapper {

    User findByUsername(@Param("userName") String userName);
}
