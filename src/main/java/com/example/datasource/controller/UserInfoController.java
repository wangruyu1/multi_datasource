package com.example.datasource.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.datasource.UserInfoService;
import com.example.datasource.clustermapper.ClusterUserInfoMapper;
import com.example.datasource.entity.UserInfo;
import com.example.datasource.mastermapper.MasterUserInfoMapper;
import com.example.datasource.transaction.TransactionStrategy;
import com.example.datasource.utils.MapperUtil;

@RestController
public class UserInfoController {
	@Autowired
	private MasterUserInfoMapper masterUserInfoMapper;
	@Autowired
	private ClusterUserInfoMapper clusterUserInfoMapper;
	@Autowired
	private MapperUtil mapperUtil;
	@Autowired
	private UserInfoService userInfoService;

	@RequestMapping(value = "/userinfo/{id}")
	public String queryUserById(@PathVariable("id") Integer id) {
		System.out.println(mapperUtil);
		int x = 1 / 0;
		return mapperUtil.masterUserInfoMapper().selectByPrimaryKey(id).toString();
	}

	@RequestMapping(value = "/userinfo2/{id}")
	public String queryUserById2(@PathVariable("id") Integer id) {
		return clusterUserInfoMapper.selectByPrimaryKey(id).toString();
	}

	@RequestMapping(value = "/insert")
	public String insert(@RequestParam("id") Integer id) {
		UserInfo record = new UserInfo();
		record.setId(id);
		userInfoService.insert2(record);
		System.out.println(mapperUtil);
		return queryUserById(id);
	}

	@RequestMapping(value = "/insert2")
	public String insert2(@RequestParam("id") Integer id) {
		UserInfo record = new UserInfo();
		record.setId(id);
//		clusterUserInfoMapper.insert2(record);
		return queryUserById2(id);
	}
}
