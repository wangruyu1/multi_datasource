package com.example.datasource.utils;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.example.datasource.base.UserInfoCommonMapper;
import com.example.datasource.clustermapper.ClusterUserInfoMapper;
import com.example.datasource.mastermapper.MasterUserInfoMapper;

@Component
//@Scope("prototype")
public class MapperUtil {
	@Resource(name = "masterSqlSession")
	private SqlSession masterSqlSession;
	@Resource(name = "clusterSqlSession")
	private SqlSession clusterSqlSession;
	// private UserInfoCommonMapper masterUserInfoMapper;
	// private UserInfoCommonMapper clusterUserInfoMapper;

	public MasterUserInfoMapper masterUserInfoMapper() {
		MasterUserInfoMapper baseMapper = masterSqlSession.getMapper(UserInfoCommonMapper.class);
		return baseMapper;
	}

	public ClusterUserInfoMapper clusterUserInfoMapper() {
		ClusterUserInfoMapper baseMapper = clusterSqlSession.getMapper(UserInfoCommonMapper.class);
		return baseMapper;
	}
}
