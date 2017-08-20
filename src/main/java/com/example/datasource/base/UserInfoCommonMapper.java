package com.example.datasource.base;

import org.apache.ibatis.annotations.Mapper;

import com.example.datasource.clustermapper.ClusterUserInfoMapper;
import com.example.datasource.entity.UserInfo;
import com.example.datasource.mastermapper.MasterUserInfoMapper;

@Mapper
public interface UserInfoCommonMapper extends MasterUserInfoMapper, ClusterUserInfoMapper {
}
