package com.example.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.datasource.entity.UserInfo;
import com.example.datasource.transaction.TransactionStrategy;
import com.example.datasource.utils.MapperUtil;

public interface UserInfoService extends TransactionStrategy {
	public void insert2(UserInfo record);
}
