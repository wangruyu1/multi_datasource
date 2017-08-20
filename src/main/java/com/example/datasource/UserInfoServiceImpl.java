package com.example.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.datasource.entity.UserInfo;
import com.example.datasource.transaction.TransactionStrategy;
import com.example.datasource.utils.MapperUtil;

@Service
public class UserInfoServiceImpl implements UserInfoService {
	@Autowired
	private MapperUtil mapperUtil;

	@Override
	@Transactional("masterTransactionManager")
	public void insert2(UserInfo record) {
		// TODO Auto-generated method stub
		mapperUtil.masterUserInfoMapper().insert2(record);
		System.out.println(mapperUtil.masterUserInfoMapper().selectByPrimaryKey(record.getId()));
		int x = 1 / 0;
	}

}
